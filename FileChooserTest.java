package ch05_2.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileChooserTest extends JFrame implements ActionListener{

	JButton openButton, saveButton;
	JFileChooser fc;
	
	public FileChooserTest() {
		setTitle("���� ���ñ� �׽�Ʈ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		
		fc = new JFileChooser();
		
		JLabel label = new JLabel("���� ���ñ� ������Ʈ �׽�Ʈ�Դϴ�.");
		openButton = new JButton("���� ����");
		openButton.addActionListener(this);
		
		saveButton = new JButton("��������");
		saveButton.addActionListener(this);
		
		saveButton = new JButton("���� ����");
		saveButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(openButton);
		panel.add(saveButton);
		add(panel);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==openButton) {
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			}
			else {
				
			}
		}
		else if(e.getSource() == saveButton) {
			int returnVal = fc.showSaveDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			}
			else {
				
			}
		}
	}
	public static void main(String[] args) {
		new FileChooserTest();
	}
}
