package ch18;

public class SimplePair<T> {
	private T data1;
	private T data2;
	//데이터 두개 선언
	
	SimplePair(T data1, T data2){
		this.data1 = data1;
		this.data2 = data2;
	}
	//생성자에 들어갈 필드의 타입은 class를 선언할 때의 type이여야 한다.본문에서는 String
	//생성자 선언
	
	public void setFirst(T data1) {this.data1 = data1;}
	public void setSecond(T data2) {this.data2 = data2;}
	//Getter 선언
	
	public T getFirst() {return data1;}
	public T getSecond() {return data2;}
	//Setter 선언
}
