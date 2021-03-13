package ch05_2.oop;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ComboBoxFrame extends JFrame implements ActionListener{
	private JLabel label;
	private ArrayList<String> animals;
	private JComboBox animalList;
	private boolean check;
	private boolean check2;
	public ComboBoxFrame() {
		animals = new ArrayList<String>();
		animals.add("dog");
		animals.add("lion");
		animals.add("tiger");
		this.setTitle("콤보박스");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);

		animalList = new JComboBox(animals.toArray());
		animalList.setSelectedIndex(0);
		animalList.addActionListener(this);
		animalList.setEditable(true);


		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		changePicture(animals.toArray()[animalList.getSelectedIndex()]);

		add(animalList, BorderLayout.PAGE_START);
		add(label, BorderLayout.PAGE_END);
		setVisible(true);
	}

	protected void changePicture(Object name) {
		ImageIcon icon = new ImageIcon(name+".gif");
		label.setIcon(icon);
		if(icon != null) {
			label.setText(null);
		}
		else {
			label.setText("이미지가 발견되지 않았습니다.");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("comboBoxEdited")) {
			check =true;
			JComboBox cb = (JComboBox) e.getSource();
			String name = (String) cb.getSelectedItem();
			changePicture(name);
			for(int i=0;i<animals.size();i++) {
				if(animals.toArray()[i].equals(animalList.getSelectedItem())) {
					check = false;
				}
			}
			if(check==true) {
				animalList.addItem(animalList.getSelectedItem());
				animals.add(animalList.getSelectedItem().toString());
			}
		}
	}

	public static void main(String[] args) {
		new ComboBoxFrame();
	}

}
