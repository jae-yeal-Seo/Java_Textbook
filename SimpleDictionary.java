package ch15;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleDictionary extends JPanel implements ActionListener{
	/*�Է��ʵ�, ��ư 2��*/
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�");
	
	//�ѿ����� : �ѱ۴ܾ�� �����Ǵ� ����ܾ��� ���� ����. ���� ���� ���̴�.
	Map<String,String> dict = new HashMap<>();
	
	public SimpleDictionary() {
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600,50));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		System.out.println("["+key+"]");
		if(key.trim().length()==0) return;
		//search button or add button detection.
		
		if(e.getSource()==searchBtn) {			//�˻���ư
			/*
			 * inputField�� �Էµ� �ܾ ����
			 * dict �� ��ü���� �� �ܾ �����Ǵ� ����ܾ ã�� ���÷���.
			 */
			
			String value = dict.get(key);
			if(value==null) {	//�� key�� �����Ǵ� ���ܾ ���� ���
				//���ٰ� ���÷���
				JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�.", key, JOptionPane.ERROR_MESSAGE);
				
			}else {
				//�����Ǵ� �ܾ ���÷���
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		else if (e.getSource()==addBtn) {		//�߰���ư
			/*
			 * inputFiled�� �Էµ� �ܾ ����
			 * �� �ܾ �����Ǵ� ����ܾ �Է¹ް�
			 * dict �� ��ü�� <�ѱ۴ܾ�, ����ܾ�>�� ���� �߰�
			 */
			String value = JOptionPane.showInputDialog(this, key + "�� �����Ǵ� ����ܾ �Է��ϼ���");
			if(value.trim().length()==0) return;
			dict.put(key, value);
			JOptionPane.showMessageDialog(this, "����ܾ �߰��Ǿ����ϴ�","����",JOptionPane.INFORMATION_MESSAGE);
			
		}
		inputField.requestFocus();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDictionary());
		frame.setTitle("���� �ѿ�����");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
