package ch18;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.google.gson.Gson;

public class RemoteJSON {

	public static void main(String[] args) throws Exception{
		String site = "https://jsonplaceholder.typicode.com/posts";
		URL url = new URL(site);
		
		URLConnection con = url.openConnection();
		
		InputStream stream = con.getInputStream();
		InputStreamReader reader = new InputStreamReader(stream);
		//InputStream�� �о�´�.
		BufferedReader buffReader = new BufferedReader(reader);
		//reader�� �Ѷ��ξ� �б� ���ؼ� BufferedReader�� ����Ѵ�.
		
		String line = null;
//		String content = "";
		StringBuffer buf = new StringBuffer();
		while((line = buffReader.readLine())!=null) {
			System.out.println(line);
			buf.append(line);
			//buf�� �׾Ƶ�.
		}
		
		Gson gson = new Gson();
		
		Post[] posts = gson.fromJson(buf.toString(), Post[].class );
		//�����Ÿ� .json�������� ���� posts[0].getuserID(), .getId(), getTitle(), getbody()�̷������� �� �� �ִ�. 
		for(Post post : posts) {
			System.out.println("userId:"+post.getUserId()+",id:"+post.getId());
			//���⼭�� post�� posts[0],posts[1],,��� ����.������ Ÿ���� POST��
		}
		insertIntoDB(posts);
		
//		Person person = gson.fromJson("{'name':'gdhon','age':10, 'graduated':false}", Person.class);
		
		/*
		 * Person p = new Person();
		 * p.setName("gdhon");
		 * p.setAge(10);
		 * .
		 * .
		 * �̷������� �˾Ƽ�(����������) �Ľ�����. �˾Ƽ� �Ľ����ֱ� ���ؼ� JavaBean���·� �����ؾ� ��.
		 */
		
//		System.out.println(person.getName());
//		System.out.println(person.getAge());
//		System.out.println(person.isGraduated());
		
		buffReader.close();
	}
	private static void insertIntoDB(Post[] posts) throws Exception{
		// ����̹� �ε�
		// DB������ �����ϰ�
//		Connection con = DriverManager.getConnection(...);
		/*
		 * create table posts(
		 * userId int,
		 * id int primary key,
		 * title varchar(50),
		 * body text(50)
		 */
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3306/oop3";
		
		Connection con = DriverManager.getConnection(url, "root","250303gg@@");
		String sql = "insert into posts(userId, id, title, body) values(?,?,?,?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		for(Post post : posts) {
			pstmt.setInt(1,post.getUserId());
			pstmt.setInt(2, post.getId());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getBody());
			
			pstmt.executeUpdate();
		}
	}
}
//class Person {
//	//JavaBean���·� Ŭ������ ����
//	//1. private member ������ ���� public getter�� setter�� ������.
//	//2. default �����ڸ� ������.
//	private String name;
//	private int age;
//	private boolean graduated;
//	
//	 
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//	public boolean isGraduated() {
//		return graduated;
//	}
//	public void setGraduated(boolean graduated) {
//		this.graduated = graduated;
//	}
//	
//	
//}
