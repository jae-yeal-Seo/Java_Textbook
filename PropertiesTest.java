package ch15;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest {

	public static void main(String[] args) {
//		Map<String,String> env = System.getenv();
//		Set<String> keys = env.keySet();
//		//key�� ���� set�� ������.
//		for(String key : keys) {
//			System.out.println(key);
//		}
		//Properties�� <key,value>�� Ÿ���� ���� String,String���� ������ ������ Map�̴�.
		Properties pros = new Properties();
		try(FileReader reader = new FileReader("dict.pros")){//�� ������ src�� ���� ������ �־�� �ȴ�.
			pros.load(reader);
		}catch(Exception err) {
			System.out.println(err.getMessage());
		}
		//Map��
		
//		pros.put("name", "ȫ�浿");
//		pros.put("age", "20");
		Set<Object> keys = pros.keySet();
		//pros��� ���� key�� �������� �����! ���׸��� Object�� �޾ƾ� �Ѵ�!
		for(Object key: keys) {
			System.out.println(key+":"+pros.get(key));
		}
		try(PrintWriter out = new PrintWriter(new File("dict.props"))){
			pros.store(out, "MyDictionary");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
//		Set<Object> keys = pros.keySet();
	}

}
