package ch18;

public class SimplePair<T> {
	private T data1;
	private T data2;
	//������ �ΰ� ����
	
	SimplePair(T data1, T data2){
		this.data1 = data1;
		this.data2 = data2;
	}
	//�����ڿ� �� �ʵ��� Ÿ���� class�� ������ ���� type�̿��� �Ѵ�.���������� String
	//������ ����
	
	public void setFirst(T data1) {this.data1 = data1;}
	public void setSecond(T data2) {this.data2 = data2;}
	//Getter ����
	
	public T getFirst() {return data1;}
	public T getSecond() {return data2;}
	//Setter ����
}
