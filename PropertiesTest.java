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
//		//key만 따로 set로 빼놓음.
//		for(String key : keys) {
//			System.out.println(key);
//		}
		//Properties는 <key,value>의 타입이 각각 String,String으로 고정된 일종의 Map이다.
		Properties pros = new Properties();
		try(FileReader reader = new FileReader("dict.pros")){//이 파일을 src와 같은 레벨이 있어야 된다.
			pros.load(reader);
		}catch(Exception err) {
			System.out.println(err.getMessage());
		}
		//Map임
		
//		pros.put("name", "홍길동");
//		pros.put("age", "20");
		Set<Object> keys = pros.keySet();
		//pros라는 맵의 key만 집합으로 만든다! 제네릭은 Object로 받아야 한다!
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
