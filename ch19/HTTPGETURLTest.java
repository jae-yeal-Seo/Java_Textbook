package ch19;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGETURLTest {
	//GET방식의 요청

	public static void main(String[] args) throws Exception{
	String site = "https://www.google.com/search?q=java&ie=UTF-8";
	URL url = new URL(site);
	//URL : 인터넷에 존재하는 서버의 자원에 접근할 수 있는 주소 (ex)프로토콜,호스트명,포트번호,경로명,파일명,쿼리)
	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	//URL보다 더 많은 기능을 사용하기 위해서 url.openConnection()을 HttpURLConnection에 집어넣었다.
	
	con.setRequestMethod("GET");
	//InputStream을 받을 거니까 GET으로 설정
	con.setRequestProperty("User-Agent", "Mozilla/5.0");
	
	int code = con.getResponseCode();
	System.out.println("Response code:"+code);
	//노드시간에 배운 거(200이면성공, 302번,, 이런거)
	InputStream stream = con.getInputStream();
	//서버에서 주는 stream을 받는다.(객체생성)
	
	stream.read();
	//그리고 읽는다.
	
	InputStreamReader streamReader = new InputStreamReader(stream,"UTF-8");
	//InputStreamReader두번째 인자로 UTF-8을 넣으면 글자가 안 깨지고 잘 나온다.(읽기위해)
	
	BufferedReader reader = new BufferedReader(streamReader);
	String line = null;
	while((line = reader.readLine())!=null){
		//더이상 읽을게 없을 때까지 출력한다.
		System.out.println(line);
	}
	}
}

