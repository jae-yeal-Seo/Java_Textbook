package ch19;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host2InetAddr {
	public static void main(String[] args) throws Exception {
		String hostName = "www.naver.com";
		
		
			InetAddress address = InetAddress.getByName(hostName);
			//InetAddress : �ڹٿ��� IP�ּҸ� ǥ���� �� ����ϴ� Ŭ����
			System.out.println("IP�ּ�:"+address.getHostAddress());
			//InetAddress�� �ν��Ͻ��� �̿��ؼ� IP�ּҸ� ��´�.
		
	}
}
