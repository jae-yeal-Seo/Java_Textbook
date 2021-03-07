package ch05_2.oop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TwoDimension extends JFrame {

	private JTextField[] coefficient;
	private JButton draw;
	private MyPanel myPanel;
	private String A;
	private String B;
	private String C;
	private int a;
	private int b;
	private int c;
	

	class MyPanel extends JPanel implements ActionListener{

		MyPanel(){
			setLayout(null);
			coefficient = new JTextField[3];
			draw = new JButton();
			draw.setText("DRAW");
			draw.setSize(80, 40);
			add(draw);
			draw.setLocation(420,15);
			draw.addActionListener(this);
			for(int i=0;i<3;i++) {
				coefficient[i] = new JTextField();
				coefficient[i].setSize(130, 30);
				if(i==0)
					coefficient[i].setLocation(30, 15);
				else
					coefficient[i].setLocation(30+i*130, 15);
				add(coefficient[i]);
			}
			
		
		}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				g2.drawLine(0, 200, 400, 200);
				g2.drawLine(200, 0, 200, 400);	 
				g2.setPaint(Color.red);
					for(int i = -20; i<20; i++) {
						int x = i;
						int y = (int)(a*x*x-b*x+c);
						g2.fillOval(200+x-2, 198-y, 5, 5);
					}
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				A = coefficient[0].getText();
				B = coefficient[1].getText();
				C = coefficient[2].getText();
				if(e.getSource()==draw) {
				a = Integer.parseInt(A);
				b = Integer.parseInt(B);
				c = Integer.parseInt(C);
				repaint();
				}
			}
		
	}
	TwoDimension(){
		myPanel = new MyPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("이차함수 그리기");
		setSize(700,400);
		add(myPanel);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TwoDimension();
	}

	
}
