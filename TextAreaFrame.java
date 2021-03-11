package ch05_2.oop;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAreaFrame extends JFrame{
	private JTextField textField;
	private JTextArea textArea;
	private MyPanel100 myPanel100;
	
	class MyPanel100 extends JPanel implements KeyListener{

	
		
		MyPanel100(){
		
		textField = new JTextField(30);
		textArea = new JTextArea(15,30);
		
		add(textField, BorderLayout.NORTH);
		textField.addKeyListener(this);
		add(textArea, BorderLayout.CENTER);
		textArea.setEditable(false);
		textField.addKeyListener(this);
		
		
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				String text = textField.getText();
				textArea.append(text+"\n");
				textField.setText(null);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	TextAreaFrame(){
		myPanel100 = new MyPanel100();
		this.setSize(320,230);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(myPanel100);
	}
	
	public static void main(String[] args) {
		new TextAreaFrame();
	}
}
