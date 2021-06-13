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
		//InputStream을 읽어온다.
		BufferedReader buffReader = new BufferedReader(reader);
		//reader를 한라인씩 읽기 위해서 BufferedReader를 사용한다.
		
		String line = null;
//		String content = "";
		StringBuffer buf = new StringBuffer();
		while((line = buffReader.readLine())!=null) {
			System.out.println(line);
			buf.append(line);
			//buf에 쌓아둠.
		}
		
		Gson gson = new Gson();
		
		Post[] posts = gson.fromJson(buf.toString(), Post[].class );
		//쌓은거를 .json형식으로 나눔 posts[0].getuserID(), .getId(), getTitle(), getbody()이런식으로 쓸 수 있다. 
		for(Post post : posts) {
			System.out.println("userId:"+post.getUserId()+",id:"+post.getId());
			//여기서의 post는 posts[0],posts[1],,등과 같음.각각의 타입은 POST임
		}
		insertIntoDB(posts);
		
//		Person person = gson.fromJson("{'name':'gdhon','age':10, 'graduated':false}", Person.class);
		
		/*
		 * Person p = new Person();
		 * p.setName("gdhon");
		 * p.setAge(10);
		 * .
		 * .
		 * 이런식으로 알아서(내부적으로) 파싱해줌. 알아서 파싱해주기 위해서 JavaBean형태로 정의해야 됨.
		 */
		
//		System.out.println(person.getName());
//		System.out.println(person.getAge());
//		System.out.println(person.isGraduated());
		
		buffReader.close();
	}
	private static void insertIntoDB(Post[] posts) throws Exception{
		// 드라이버 로딩
		// DB서버에 연결하고
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
//	//JavaBean형태로 클래스를 정의
//	//1. private member 변수에 대한 public getter와 setter를 가진다.
//	//2. default 생성자를 가진다.
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
