package ch19;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host2InetAddr {
	public static void main(String[] args) throws Exception {
		String hostName = "www.naver.com";
		
		
			InetAddress address = InetAddress.getByName(hostName);
			//InetAddress : 자바에서 IP주소를 표현할 때 사용하는 클래스
			System.out.println("IP주소:"+address.getHostAddress());
			//InetAddress의 인스턴스를 이용해서 IP주소를 얻는다.
		
	}
}
