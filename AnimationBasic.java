package ch05_2.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class AnimationBasic extends JPanel implements ActionListener{
	/*
	 * */
	private final int WIDTH = 500, HEIGHT = 300;
	private BufferedImage image;
	private Timer timer;
	private int x,y;
	private final int START_X = 0, START_Y = 210;
	private boolean checkX = false;
	private boolean checkY = true;
	

	public AnimationBasic() {
		//		�̹��� ������ �о image ��ü�� ����
		//		20ms���� �̺�Ʈ�� �߻���ŰŰ�� timer ��ü�� �����ϰ� timer�� start ��Ŵ.
		
		File file = new File("spaceship.jpg");
		try {
		image = ImageIO.read(file);
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.print(HEIGHT-image.getHeight());
		x = START_X;
		y = START_Y;
		
		timer = new Timer(1,this);
		timer.start();
//		���⼭ this�� actionPerformed
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, x, y, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//	x,y��ǥ ����
		if(x==0||x==WIDTH-image.getWidth()-17)
			checkX = !checkX;
		if(y==0||y==HEIGHT-image.getHeight()-39)
			checkY = !checkY;
		if(checkX)
			x += 1;
		else 
			x -= 1;
		if(checkY)
			y +=1;
		else
			y -=1;
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new AnimationBasic());
		frame.setTitle("�ִϸ��̼� �׽�Ʈ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setVisible(true);
	}

}
