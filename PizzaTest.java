package ch05_2.oop;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PizzaTest extends JFrame{
	private WelcomePanel welcomePanel;
	private TypePanel1 typePanel1;
	private TypePanel2 typePanel2;
	private TypePanel3 typePanel3;
	private InputMenu inputMenu;
	
	class WelcomePanel extends JPanel{
		private JLabel message;
		public WelcomePanel() {
			message = new JLabel("�ڹ� ���ڿ� ���� ���� ȯ���մϴ�.");
			add(message);
		}
	}
	
	class TypePanel1 extends JPanel{
		private JRadioButton combo, potato, bulgogi;
		public TypePanel1() {
			setLayout(new GridLayout(3,1));
			combo = new JRadioButton("�޺�",true);
			potato = new JRadioButton("��������");	
			bulgogi = new JRadioButton("�Ұ��");
			ButtonGroup bg1 = new ButtonGroup();
			bg1.add(combo);
			bg1.add(potato);
			bg1.add(bulgogi);
			setBorder(BorderFactory.createTitledBorder("����"));
			add(combo);
			add(potato);
			add(bulgogi);
		}
		
	}
	
	class TypePanel2 extends JPanel {
		private JRadioButton pimang, cheese, peperoni, bacon;
		public TypePanel2() {
			setLayout(new GridLayout(4,1));
			pimang = new JRadioButton("�Ǹ�",true);
			cheese = new JRadioButton("ġ��");
			peperoni = new JRadioButton("����δ�");
			bacon = new JRadioButton("������");
			
			ButtonGroup bg2 = new ButtonGroup();
			bg2.add(pimang);
			bg2.add(cheese);
			bg2.add(peperoni);
			bg2.add(bacon);
			setBorder(BorderFactory.createTitledBorder("�߰� ����"));
			add(pimang);
			add(cheese);
			add(peperoni);
			add(bacon);
		}
	}
	
	class TypePanel3 extends JPanel{
		private JRadioButton small, medium, large;
		public TypePanel3() {
			setLayout(new GridLayout(3,1));
			small = new JRadioButton("Small",true);
			medium = new JRadioButton("Medium");
			large = new JRadioButton("Large");
			
			ButtonGroup bg3 = new ButtonGroup();
			bg3.add(small);
			bg3.add(medium);
			bg3.add(large);
			setBorder(BorderFactory.createTitledBorder("ũ��"));
			add(small);
			add(medium);
			add(large);
		}
	}
	
	class InputMenu extends JPanel{
		private JButton order, cancel;
		private JTextField result;
		
		InputMenu(){
			order = new JButton("�ֹ�");
			cancel = new JButton("���");
			result = new JTextField(10);
			result.setEditable(false);
			add(order);
			add(cancel);
			add(result);
		}

	}
	
	PizzaTest(){
		welcomePanel = new WelcomePanel();
		typePanel1 = new TypePanel1();
		typePanel2 = new TypePanel2();
		typePanel3 = new TypePanel3();
		inputMenu = new InputMenu();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,250);
		this.setVisible(true);
		
		add(welcomePanel, BorderLayout.NORTH);
		add(typePanel1, BorderLayout.WEST);
		add(typePanel2, BorderLayout.CENTER);
		add(typePanel3, BorderLayout.EAST);
		add(inputMenu, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new PizzaTest();
	}

}
