package ch18;

public class SimplePairTest {
	public static void main(String[] args) {
	SimplePair<String> pair = new SimplePair<String>("apple","tomato");
	
	System.out.println(pair.getFirst());
	System.out.println(pair.getSecond());
	
	pair.setFirst("���");
	pair.setSecond("��ģ");
	
	System.out.println(pair.getFirst());
	System.out.println(pair.getSecond());
	}
}
