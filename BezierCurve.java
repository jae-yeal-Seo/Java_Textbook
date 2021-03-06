package ch05_2.oop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BezierCurve extends JFrame implements MouseListener, MouseMotionListener{

	private int[] xs = {50,150,400,450};
	private int[] ys = {200,50,300,200};
//	각 점들의 초기값이다.
	private int dragIndex = -1;
//	각 점들보다 가로, 세로 4씩 더 큰 사각형을 "눌렀는지"체크하기 위한 필드이다. MousePressed()와 MouseDragged()에서 쓰인다.
	private MyPanel drawPanel;
//	JPanel을 상속하고 paintComponetn(Graphics g)가 있는 타입(클래스)의 인스턴스 drawPanel.
	class MyPanel extends JPanel{
//		MyPanel구현
		@Override
		public void paintComponent(Graphics g) {
			g.setColor(Color.blue);
			g.fillRect(xs[0], ys[0], 16, 16);
			g.fillRect(xs[2], ys[2], 16, 16);
			g.setColor(Color.red);
			g.fillRect(xs[1], ys[1], 16, 16);
			g.fillRect(xs[3], ys[3], 16, 16);
//		좌표는 위에서 정했다. 그리는 건 paintComponent(Graphics g)에서 한다.
		Graphics2D g2d = (Graphics2D) g;
//		Graphics2D가 자식이기 때문에 g는 형변환을 한다.
		g2d.setColor(Color.black);
		GeneralPath path = new GeneralPath();
		path.moveTo(xs[0], ys[0]);
//		moveTo는 시작점을 말한다.
		path.curveTo(xs[1], ys[1],xs[2], ys[2],xs[3], ys[3]);
//		1번째, 2번째, 3번째(마지막 제어점)에 따라 
		g2d.draw(path);
//		선을 그린다. 
		}
	}
	
	
	
	
	
	public BezierCurve() {
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("베지어 커브 데모");
		drawPanel = new MyPanel();
		drawPanel.addMouseListener(this);
		drawPanel.addMouseMotionListener(this);
		add(drawPanel, BorderLayout.CENTER);
	}
//	생성자에선 기본적인 성질을 입력한다. 
	
	public static void main(String[] args) {
		new BezierCurve();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		dragIndex = -1;
		for(int i=0;i<4;i++) {
			Rectangle r = new Rectangle(xs[i]-4,ys[i]-4,20,20);
			if(r.contains(e.getX(),e.getY())) {
				dragIndex = i;
			}
		}
//		클릭했을 때 해당 좌표가 위에서 만든 Rectangle에 속하면(contains라는 메소드 사용)dragIndex를 해당 좌표순서로 바꿔놓는다. dragIndex는 드래그하고 
//		마우스를 놓을 때까지 유지된다.
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		dragIndex = -1;
	}
//	마우스를 놓으면 dragIndex는 -1로 초기화한다. 근데 필요없는 부분이다.
	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragIndex !=-1) {
			xs[dragIndex] = e.getX();
			ys[dragIndex] = e.getY();
		}
//	네모를 지금 마우스가 누르고 있는 곳으로 계속 옮긴다. 드래그의 기준은 누르고 있는 상태에서 1이라도 움직이면 작동되므로 가장 많이 실행되는 메소드이다.
		repaint();
	}
	
	
	
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
