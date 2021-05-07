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
	/*입력필드, 버튼 2개*/
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	
	//한영사전 : 한글단어와 대응되는 영어단어의 쌍을 저장. 맵이 좋을 것이다.
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
		
		if(e.getSource()==searchBtn) {			//검색버튼
			/*
			 * inputField에 입력된 단어를 추출
			 * dict 맵 객체에서 그 단어에 대응되는 영어단어를 찾아 디스플레이.
			 */
			
			String value = dict.get(key);
			if(value==null) {	//그 key에 대응되는 영단어가 없는 경우
				//없다고 디스플레이
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다.", key, JOptionPane.ERROR_MESSAGE);
				
			}else {
				//대응되는 단어를 디스플레이
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		else if (e.getSource()==addBtn) {		//추가버튼
			/*
			 * inputFiled에 입력된 단어를 추출
			 * 그 단어에 대응되는 영어단어를 입력받고
			 * dict 맵 객체에 <한글단어, 영어단어>의 쌍을 추가
			 */
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력하세요");
			if(value.trim().length()==0) return;
			dict.put(key, value);
			JOptionPane.showMessageDialog(this, "영어단어가 추가되었습니다","성공",JOptionPane.INFORMATION_MESSAGE);
			
		}
		inputField.requestFocus();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDictionary());
		frame.setTitle("나의 한영사전");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
