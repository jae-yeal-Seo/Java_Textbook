package ch19;

import java.net.*;
import java.io.*;

public class HTTPPOSTURLTest {
	public static void main(String[] args) throws Exception{
		String site = "http://localhost:9090/todos";
		
		URL url = new URL(site);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		
		con.setDoInput(true);
		con.setDoOutput(true);
		//입출력 다하겠다.
		con.setRequestMethod("POST");//
		
		
		con.setRequestProperty("content-type","application/x-www-form-urlencoded");
		//내가 보내는 content-type이 두번째 인자(formData)임.얘는 헤더정보임.
		String data = "id=woduf9173&pw=1111";//
//		정보는 body로 보내는 거임.
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("id=woduf9173&pw=1111");
//		buffer.append("id=woduf9173").append("&pw=1111");
//		data = "id=woduf9173" + "&pw=1111";
//	 	위3가지 방법으로도 문자열을 저장하는 것이 가능함.
		OutputStream ostream = con.getOutputStream();//
		//OutputStream은 사용자가 보낸 정보가 있다.
		OutputStreamWriter owriter = new OutputStreamWriter(ostream, "UTF-8");//
		PrintWriter writer = new PrintWriter(owriter);//
		writer.println(data);
		//writer라는 인스턴스에 data를 보낸다.
		writer.flush();
		
//		--------------------------------------------------------------------------------
		
		InputStream stream = con.getInputStream();
		
		InputStreamReader streamReader = new InputStreamReader(stream);
		
		BufferedReader reader = new BufferedReader(streamReader);
		String line = null;
		while((line = reader.readLine())!=null){
			System.out.print(line);
		}
	}
}
