package ch18;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TableViewer extends JFrame implements ActionListener{

	private JTextField idField, titleField, publisherField, yearField, priceField;
	private JButton previousBtn, nextBtn, insertBtn, finishBtn, deleteBtn;
	private ResultSet rs = null;
	private Connection con = null;

	public TableViewer() {
	//2.TableViewer ��ü�� ������ ��, DB�� books���̺��� ���ڵ���� �о� �´�.
	//-�����ͺ��̽��� ����
	//-select �� �����ϰ� ��ȯ�� ResultSet��ü�� ������ �־�� ��.
		try {
		 Class.forName("org.mariadb.jdbc.Driver");
		 //Driver����
		 con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/oop3","root","250303gg@@");
		 //DB����
		 String sql = "select * from books";
		 //������
		 PreparedStatement pstmt = con.prepareStatement(sql);
		 //PreparedStatement�� sql�� �������
		 rs = pstmt.executeQuery();	
		 //select�ϱ� executeQuery()����. 
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("DB���ῡ ������ �־� ���α׷��� �����մϴ�.");
			System.exit(0);//���μ��� �����Ŵ.
		}
	
	// 1.�� ������Ʈ�� frame�� �߰��ϱ�
	 this.setLayout(new GridLayout(0,2));
	 
	 this.add(new JLabel("ID",JLabel.CENTER));
	 idField = new JTextField(10);
	 this.add(idField);
	 
	 this.add(new JLabel("Title",JLabel.CENTER));
	 titleField = new JTextField(10);
	 this.add(titleField);
	 
	 this.add(new JLabel("Publisher",JLabel.CENTER));
	 publisherField = new JTextField(10);
	 this.add(publisherField);
	 
	 this.add(new JLabel("Year",JLabel.CENTER));
	 yearField = new JTextField(10);
	 this.add(yearField);
	 
	 this.add(new JLabel("Price",JLabel.CENTER));
	 priceField = new JTextField(10);
	 this.add(priceField);
	 
	 previousBtn = new JButton("Previous");
	 previousBtn.addActionListener(this);
	 this.add(previousBtn);
	 
	 nextBtn = new JButton("Next");
	 nextBtn.addActionListener(this);
	 this.add(nextBtn);
	 
	 insertBtn = new JButton("Insert");
	 insertBtn.addActionListener(this);
	 this.add(insertBtn);
	 
	 deleteBtn = new JButton("delete");
	 deleteBtn.addActionListener(this);
	 this.add(deleteBtn);
	 
	 finishBtn = new JButton("Finish");
	 finishBtn.addActionListener(this);
	 this.add(finishBtn);
	 
//	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 //X��ư ������ �ȳ������� (finish��ư�� �׷��� ���� ������ �����ϱ�)
	 this.setSize(350,200);
	 this.setVisible(true);
	}
	
	public static void main(String[] args) { 
		new TableViewer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource()==nextBtn||e.getSource()==previousBtn) { 
				//������ư, ������ư �����ÿ�
				if(e.getSource()==nextBtn) {
					rs.next();
					//rs���� �������� ����� �ִ�. rs.next()��� �ϸ� ���� ���ڵ带 �ҷ��´�.
				}
				else if(e.getSource()==previousBtn) {
					rs.previous();
					//rs.previous()��� �ϸ� ���� ���ڵ带 �ҷ��´�.
				}
				int bookId = rs.getInt("book_id");
				//�ε���
				idField.setText(String.valueOf(bookId));
				String title = rs.getString("title");
				titleField.setText(title);
				String publisher = rs.getString("publisher");
				publisherField.setText(publisher);
				Date year = rs.getDate("year");
				yearField.setText(year.toString());
				int price = rs.getInt("price");
				priceField.setText(String.valueOf(price));
				//���� ���ڵ��� Į������ �о��, 
				//JTextField������ �����Ѵ�.
				//idField, titleField, publisherField, yearField, priceField
			}
			
			if(e.getSource()==insertBtn) {
				//�̹� DB������ �Ǿ� �ְ�..
				//�̹� ���� ������ ������ �ִ� Connection ��ü�� 
				//insert ���� �̿� prepare�ϰ�
				//��ȯ�� PreparedStatement ��ü�� �̿��ؼ� �����û�� ������ ������. 
				
				//Statement �Ǵ� PreparedStatement ��ü�� ����� �� �ִ�.
				//�ֳ��ϸ� Statement ��ü�� ����� ���α׷����� �߸��ϸ�
				//���Ȼ��� ������� ���� �� �ִ�. 
				//SQL Injection ����� ����� ��Ŀ�� ������ ���� �� �ִ�. 
				String sql = "insert into books(title, publisher, year, price) values (?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				/*
				 * ?�ڸ��� �� ���� ������ ���� �� �Ŀ�, ���� ��û�� ������ ������ �Ѵ�.
				 * ?�ڸ��� ���� ������ ��, �����Ǵ� Į���� ������ Ÿ�Կ� ���� ������ setXXX�޼��带 ȣ���ؾ� �Ѵ�.
				 * ���� ���, ?�� �����Ǵ� Į���� price�̰�, �� Į���� ������ Ÿ���� int�̸� setInt �޼��带 ȣ��...
				 */
				String title = titleField.getText();
				pstmt.setString(1,title);
				
				String publisher = publisherField.getText();
				pstmt.setString(2, publisher);
				
				String year = yearField.getText();
				//���ڿ��κ��� java.util.Date ��ü�� ������ �� �ִ� SimpleDateFormat ��ü�� �����Ѵ�.
				//�� ��, date���� ������ �˷��ش�. 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				sdf.parse(year);
//				Date date = new Date(sdf.parse(year).getTime());
				
				//���ڿ��� parsing �ؼ� java.util.Date ��ü�� ����.
				java.util.Date date = sdf.parse(year);
				
				//java.sql.Date ��ü ���� 3��° ? �ڸ��� ����.
//				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//				
//				pstmt.setDate(3, sqlDate);
				
				pstmt.setDate(3, Date.valueOf(year));
				
				String price = priceField.getText();
				pstmt.setInt(4,  Integer.valueOf(price));
				//������ SQL���� select ���� ��쿡�� excuteQuery �޼��带 ȣ��.
				//������ SQL���� insert, delete, �Ǵ� update�� ��쿡�� executeUpdate �޼��带 ȣ��
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(this, "��ϼ���!");
				reloading();
				
			}
			if(e.getSource()==deleteBtn) {
				String deletebook = idField.getText();
				PreparedStatement pstmt = con.prepareStatement("delete from books where book_id="+deletebook);
				pstmt.executeUpdate();
				reloading();
			}
			else if (e.getSource()==finishBtn) {
				System.out.println("���α׷��� �����մϴ�....");
				con.close();
				//�������
				this.dispose();
				//dispose()�� �������� �ʴ� ���ҽ��� �����ϴµ� ���ȴ�.
				System.exit(0);
			}
		}
		catch(Exception err) {
			JOptionPane.showMessageDialog(this, "���� �߻�["+err.getMessage()+"]");
			System.out.println(err.getMessage());
		}
	}
	private void reloading() throws Exception{
		String sql = "select * from books order by book_id desc";
		PreparedStatement pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}

}
