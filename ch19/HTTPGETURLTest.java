package ch19;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGETURLTest {
	//GET����� ��û

	public static void main(String[] args) throws Exception{
	String site = "https://www.google.com/search?q=java&ie=UTF-8";
	URL url = new URL(site);
	//URL : ���ͳݿ� �����ϴ� ������ �ڿ��� ������ �� �ִ� �ּ� (ex)��������,ȣ��Ʈ��,��Ʈ��ȣ,��θ�,���ϸ�,����)
	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	//URL���� �� ���� ����� ����ϱ� ���ؼ� url.openConnection()�� HttpURLConnection�� ����־���.
	
	con.setRequestMethod("GET");
	//InputStream�� ���� �Ŵϱ� GET���� ����
	con.setRequestProperty("User-Agent", "Mozilla/5.0");
	
	int code = con.getResponseCode();
	System.out.println("Response code:"+code);
	//���ð��� ��� ��(200�̸鼺��, 302��,, �̷���)
	InputStream stream = con.getInputStream();
	//�������� �ִ� stream�� �޴´�.(��ü����)
	
	stream.read();
	//�׸��� �д´�.
	
	InputStreamReader streamReader = new InputStreamReader(stream,"UTF-8");
	//InputStreamReader�ι�° ���ڷ� UTF-8�� ������ ���ڰ� �� ������ �� ���´�.(�б�����)
	
	BufferedReader reader = new BufferedReader(streamReader);
	String line = null;
	while((line = reader.readLine())!=null){
		//���̻� ������ ���� ������ ����Ѵ�.
		System.out.println(line);
	}
	}
}

