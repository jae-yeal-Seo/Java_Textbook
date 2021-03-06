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
		//		이미지 파일을 읽어서 image 객체로 생성
		//		20ms마다 이벤트를 발생시키키는 timer 객체를 생성하고 timer를 start 시킴.
		
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
//		여기서 this는 actionPerformed
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, x, y, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//	x,y좌표 변경
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
		frame.setTitle("애니메이션 테스트");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setVisible(true);
	}

}
