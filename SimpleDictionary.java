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
	/*입력필드, 버튼 2개*/
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	private static final String DIC_FILE_NAME="dict.pros";
	
	private static final String JDBC_CLASS_NAME = "org.mariadb.jdbc.Driver";
	private static final String  DB_URL = "jdbc:mariadb://localhost:3306/oop3";
	//localhost중 3306포트를 물고있는.그리고 DBMS의 테이블입력
	private static final String DB_USER="root";
	private static final String DB_PASSWORD = "250303gg@@";
	//하드디스크에서 메모리로 적재하기 위함!
	
	//한영사전 : 한글단어와 대응되는 영어단어의 쌍을 저장. 맵이 좋을 것이다.
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
		//JDBC 드라이버를 메모리에 적재하기.
		//JDBC 드라이버 클래스 이름은 DBMS마다 다르다.
		//MariaDB의 경우는 드라이버 이름이 org.mariadb.jdbc.Driver이다.
	}
	
	private void buildDictionaryFromDB() {
		
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
			String sql = "select * from dict2";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//PreparedStatement 객체는 실행준비가 완료된 SQL 객체.
			//prepareStatement는 4가지 과정(SQL보낼 때마다 실행하는)중 2번째를 미리 해줌.
			//Connection객체에게 Statement 객체또는 PreparedStatement객체를 요구
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String key = rs.getString("han");
				String value =rs.getString("eng");
				dict.put(key, value);
				dict.put(value, key);
				System.out.println("["+key+ "] : ["+value+"]");
			}
			//Insert, Delete, Update문의 실행은 executeUpdate()메서드를 호출하고 select문의 실행은 executeQuery()메서드를 호출한다.
			//몇 개인지 모르니까 하나하나씩 뽑기위해 ResultSet을 사용 next를 하면 다음 column을 불러옴.
			//typedl varchar이기 때문에 rs.getString을 함.
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			//Connection객체를 반환함.
			//exception이 날 수 있음. 3개가 일치하지 않을 경우.
		
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
		//일단 버튼누르면 창에서 글자 가져옴(key)
		System.out.println("["+key+"]");
		if(key.trim().length()==0) return;
		//search button or add button detection.
		
		if(e.getSource()==searchBtn) {			//검색버튼
			/*
			 * inputField에 입력된 단어를 추출
			 * dict 맵 객체에서 그 단어에 대응되는 영어단어를 찾아 디스플레이.
			 */
			
			String value = dict.get(key);
			
			if(value==null) {	//그 key에 대응되는 영단어가 없는 경우
				//없다고 디스플레이
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다.", key, JOptionPane.ERROR_MESSAGE);
				
			}else {
				//대응되는 단어를 디스플레이
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
		else if (e.getSource()==addBtn) {		//추가버튼
			/*
			 * inputFiled에 입력된 단어를 추출
			 * 그 단어에 대응되는 영어단어를 입력받고
			 * dict 맵 객체에 <한글단어, 영어단어>의 쌍을 추가
			 */
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력하세요");
			if(value.trim().length()==0) return;
			dict.put(key, value);
			dict.put(value,key);
			JOptionPane.showMessageDialog(this, "영어단어가 추가되었습니다","성공",JOptionPane.INFORMATION_MESSAGE);
//			addWordToFile(key,value);
			addWordToDB(key,value);
			//여기까지 key와 value를 입력했고, 이 내용을 File로 전송한다.
		}
	}
		private void addWordToDB(String key,String value) {
			try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
				String sql = "insert into dict2 values(?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// ? 자리의 칼럼 데이터 타입에 다라 
				// 적절한 setXXX() 매서드를 호출해야 한다.
				// 예를들어, 칼럼이 char 또는 varchar타입이면 setString()
				// 칼럼이 TimeStamp 타입이면 setDate(), setTimestamp(),
				// 칼럼이 int 타입이면 setInt()...
				pstmt.setString(1, key);//첫번째 자리(?)에는 key를 넣고
				pstmt.setString(2, value);//두번째 자리(?)에는 value를 넣는다.
				pstmt.executeUpdate();//서버로 요청을 보낸다.
				//실행준비
				//1. 문법검사
				//2. 정당성검사 : 테이블, 칼럼등이 실제로 있는지, 있다면 이 사용자가 레코드를 삽입
				//3. 실행계획을 세운다(execution plan)
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		//드라이버를 메모리에 적재한다. <- 메모리 적재는 한 번만 하면 되고, 이미 생성자에서 했다.
		//추가했을 때 DB에도 추가하자! 메모리 뿐만 아니라.
		//DB에 연결해서 Connection 객체를 반환받느다.
		//Connection 객체에게 PreparedStatement 객체를 요청한다.
		//PreparedStatement 객체의 executedUpdate()메소드를 호출해서 DB에 저장한다.
		
		//query문만 바꿔주면 된다.그리고 setString이 있다. 
		
//		inputField.requestFocus();
	
	private void addWordToFile(String key, String value) {
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)){
			String str = key+"="+value+"\n";
			fWriter.write(str);
			//fWriter로 인해 덮어써버림.-->FileWriter의 두번째 인자에서 결정함.
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDictionary());
		frame.setTitle("나의 한영사전");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
