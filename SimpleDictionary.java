package ch15;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleDictionary extends JPanel implements ActionListener{
	/*�Է��ʵ�, ��ư 2��*/
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�");
	private static final String DIC_FILE_NAME="dict.pros";
	
	private static final String JDBC_CLASS_NAME = "org.mariadb.jdbc.Driver";
	private static final String  DB_URL = "jdbc:mariadb://localhost:3306/oop3";
	//localhost�� 3306��Ʈ�� �����ִ�.�׸��� DBMS�� ���̺��Է�
	private static final String DB_USER="root";
	private static final String DB_PASSWORD = "250303gg@@";
	//�ϵ��ũ���� �޸𸮷� �����ϱ� ����!
	
	//�ѿ����� : �ѱ۴ܾ�� �����Ǵ� ����ܾ��� ���� ����. ���� ���� ���̴�.
	Map<String,String> dict = new HashMap<>();
	
	public SimpleDictionary() {
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600,50));
//		buildDictionaryFromFile();
		buildDictionaryFromDB();
		
		
		try {
			Class.forName(JDBC_CLASS_NAME);
			buildDictionaryFromDB();
		}catch(Exception e) {
			System.out.println(e.getMessage());
//			System.exit(1);
		}
		//JDBC ����̹��� �޸𸮿� �����ϱ�.
		//JDBC ����̹� Ŭ���� �̸��� DBMS���� �ٸ���.
		//MariaDB�� ���� ����̹� �̸��� org.mariadb.jdbc.Driver�̴�.
	}
	
	private void buildDictionaryFromDB() {
		
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
			String sql = "select * from dict2";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//PreparedStatement ��ü�� �����غ� �Ϸ�� SQL ��ü.
			//prepareStatement�� 4���� ����(SQL���� ������ �����ϴ�)�� 2��°�� �̸� ����.
			//Connection��ü���� Statement ��ü�Ǵ� PreparedStatement��ü�� �䱸
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String key = rs.getString("han");
				String value =rs.getString("eng");
				dict.put(key, value);
				dict.put(value, key);
				System.out.println("["+key+ "] : ["+value+"]");
			}
			//Insert, Delete, Update���� ������ executeUpdate()�޼��带 ȣ���ϰ� select���� ������ executeQuery()�޼��带 ȣ���Ѵ�.
			//�� ������ �𸣴ϱ� �ϳ��ϳ��� �̱����� ResultSet�� ��� next�� �ϸ� ���� column�� �ҷ���.
			//typedl varchar�̱� ������ rs.getString�� ��.
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			//Connection��ü�� ��ȯ��.
			//exception�� �� �� ����. 3���� ��ġ���� ���� ���.
		
	}
	
	private void buildDictionaryFromFile() {
		Properties props = new Properties();
		try(FileReader fReader = new FileReader(DIC_FILE_NAME)){
		props.load(fReader);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Set<Object> set = props.keySet();
		for( Object key : set ) {
			Object value = props.get(key);
			dict.put((String)key, (String)value);
			dict.put((String)value, (String)key);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		//�ϴ� ��ư������ â���� ���� ������(key)
		System.out.println("["+key+"]");
		if(key.trim().length()==0) return;
		//search button or add button detection.
		
		if(e.getSource()==searchBtn) {			//�˻���ư
			/*
			 * inputField�� �Էµ� �ܾ ����
			 * dict �� ��ü���� �� �ܾ �����Ǵ� ����ܾ ã�� ���÷���.
			 */
			
			String value = dict.get(key);
			
			if(value==null) {	//�� key�� �����Ǵ� ���ܾ ���� ���
				//���ٰ� ���÷���
				JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�.", key, JOptionPane.ERROR_MESSAGE);
				
			}else {
				//�����Ǵ� �ܾ ���÷���
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
		else if (e.getSource()==addBtn) {		//�߰���ư
			/*
			 * inputFiled�� �Էµ� �ܾ ����
			 * �� �ܾ �����Ǵ� ����ܾ �Է¹ް�
			 * dict �� ��ü�� <�ѱ۴ܾ�, ����ܾ�>�� ���� �߰�
			 */
			String value = JOptionPane.showInputDialog(this, key + "�� �����Ǵ� ����ܾ �Է��ϼ���");
			if(value.trim().length()==0) return;
			dict.put(key, value);
			dict.put(value,key);
			JOptionPane.showMessageDialog(this, "����ܾ �߰��Ǿ����ϴ�","����",JOptionPane.INFORMATION_MESSAGE);
//			addWordToFile(key,value);
			addWordToDB(key,value);
			//������� key�� value�� �Է��߰�, �� ������ File�� �����Ѵ�.
		}
	}
		private void addWordToDB(String key,String value) {
			try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
				String sql = "insert into dict2 values(?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// ? �ڸ��� Į�� ������ Ÿ�Կ� �ٶ� 
				// ������ setXXX() �ż��带 ȣ���ؾ� �Ѵ�.
				// �������, Į���� char �Ǵ� varcharŸ���̸� setString()
				// Į���� TimeStamp Ÿ���̸� setDate(), setTimestamp(),
				// Į���� int Ÿ���̸� setInt()...
				pstmt.setString(1, key);//ù��° �ڸ�(?)���� key�� �ְ�
				pstmt.setString(2, value);//�ι�° �ڸ�(?)���� value�� �ִ´�.
				pstmt.executeUpdate();//������ ��û�� ������.
				//�����غ�
				//1. �����˻�
				//2. ���缺�˻� : ���̺�, Į������ ������ �ִ���, �ִٸ� �� ����ڰ� ���ڵ带 ����
				//3. �����ȹ�� �����(execution plan)
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		//����̹��� �޸𸮿� �����Ѵ�. <- �޸� ����� �� ���� �ϸ� �ǰ�, �̹� �����ڿ��� �ߴ�.
		//�߰����� �� DB���� �߰�����! �޸� �Ӹ� �ƴ϶�.
		//DB�� �����ؼ� Connection ��ü�� ��ȯ�޴���.
		//Connection ��ü���� PreparedStatement ��ü�� ��û�Ѵ�.
		//PreparedStatement ��ü�� executedUpdate()�޼ҵ带 ȣ���ؼ� DB�� �����Ѵ�.
		
		//query���� �ٲ��ָ� �ȴ�.�׸��� setString�� �ִ�. 
		
//		inputField.requestFocus();
	
	private void addWordToFile(String key, String value) {
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)){
			String str = key+"="+value+"\n";
			fWriter.write(str);
			//fWriter�� ���� ��������.-->FileWriter�� �ι�° ���ڿ��� ������.
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDictionary());
		frame.setTitle("���� �ѿ�����");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
