package ch05_2.oop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextFieldFrame extends JFrame{
	
	private MyPanelReal myPanel;
	private JLabel a,c;
	private JTextField b,d;
	private JButton button;
	
	TextFieldFrame(){
		myPanel = new MyPanelReal();
		setTitle("제곱 계산하기");
		setSize(280,170);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(myPanel);
	}
	
	class MyPanelReal extends JPanel implements MouseListener{
		
		private static final int VK_ENTER = 0;

		MyPanelReal(){
		a = new JLabel("숫자 입력");
		
		b = new JTextField(15);
		
		c = new JLabel("제곱한 값");
		
		d = new JTextField(15);
		button = new JButton("OK");
		add(a);
		add(b);
		add(c);
		add(d);
		add(button);
		button.addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==button) {
			int y = Integer.parseInt(b.getText());
			int k = y*y;
			d.setText(Integer.toString(k));
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	public static void main(String[] args) {
		new TextFieldFrame();
	}
}
