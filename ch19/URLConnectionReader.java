package ch19;

import java.io.BufferedReader; 
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {

	public static void main(String[] args) {
		try {
			//URL객체생성
			URL site = new URL("https://www.naver.com");
			
			//연결설립
			URLConnection con = site.openConnection();
			
			//연결로부터 스트림을 얻어야 한다.
			InputStream stream = con.getInputStream();
			
			stream.read();
			
			InputStreamReader streamReader = new InputStreamReader(stream);
			
			BufferedReader reader = new BufferedReader(streamReader);
			String line = null;
			while((line = reader.readLine())!=null){
				System.out.print(line);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
