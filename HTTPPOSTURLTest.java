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
		//����� ���ϰڴ�.
		con.setRequestMethod("POST");//
		
		
		con.setRequestProperty("content-type","application/x-www-form-urlencoded");
		//���� ������ content-type�� �ι�° ����(formData)��.��� ���������.
		String data = "id=woduf9173&pw=1111";//
//		������ body�� ������ ����.
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("id=woduf9173&pw=1111");
//		buffer.append("id=woduf9173").append("&pw=1111");
//		data = "id=woduf9173" + "&pw=1111";
//	 	��3���� ������ε� ���ڿ��� �����ϴ� ���� ������.
		OutputStream ostream = con.getOutputStream();//
		//OutputStream�� ����ڰ� ���� ������ �ִ�.
		OutputStreamWriter owriter = new OutputStreamWriter(ostream, "UTF-8");//
		PrintWriter writer = new PrintWriter(owriter);//
		writer.println(data);
		//writer��� �ν��Ͻ��� data�� ������.
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
