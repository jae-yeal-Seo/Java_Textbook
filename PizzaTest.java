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
			message = new JLabel("자바 피자에 오신 것을 환영합니다.");
			add(message);
		}
	}
	
	class TypePanel1 extends JPanel{
		private JRadioButton combo, potato, bulgogi;
		public TypePanel1() {
			setLayout(new GridLayout(3,1));
			combo = new JRadioButton("콤보",true);
			potato = new JRadioButton("포테이토");	
			bulgogi = new JRadioButton("불고기");
			ButtonGroup bg1 = new ButtonGroup();
			bg1.add(combo);
			bg1.add(potato);
			bg1.add(bulgogi);
			setBorder(BorderFactory.createTitledBorder("종류"));
			add(combo);
			add(potato);
			add(bulgogi);
		}
		
	}
	
	class TypePanel2 extends JPanel {
		private JRadioButton pimang, cheese, peperoni, bacon;
		public TypePanel2() {
			setLayout(new GridLayout(4,1));
			pimang = new JRadioButton("피망",true);
			cheese = new JRadioButton("치즈");
			peperoni = new JRadioButton("페페로니");
			bacon = new JRadioButton("베이컨");
			
			ButtonGroup bg2 = new ButtonGroup();
			bg2.add(pimang);
			bg2.add(cheese);
			bg2.add(peperoni);
			bg2.add(bacon);
			setBorder(BorderFactory.createTitledBorder("추가 토핑"));
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
			setBorder(BorderFactory.createTitledBorder("크기"));
			add(small);
			add(medium);
			add(large);
		}
	}
	
	class InputMenu extends JPanel{
		private JButton order, cancel;
		private JTextField result;
		
		InputMenu(){
			order = new JButton("주문");
			cancel = new JButton("취소");
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
