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
//	�� ������ �ʱⰪ�̴�.
	private int dragIndex = -1;
//	�� ���麸�� ����, ���� 4�� �� ū �簢���� "��������"üũ�ϱ� ���� �ʵ��̴�. MousePressed()�� MouseDragged()���� ���δ�.
	private MyPanel drawPanel;
//	JPanel�� ����ϰ� paintComponetn(Graphics g)�� �ִ� Ÿ��(Ŭ����)�� �ν��Ͻ� drawPanel.
	class MyPanel extends JPanel{
//		MyPanel����
		@Override
		public void paintComponent(Graphics g) {
			g.setColor(Color.blue);
			g.fillRect(xs[0], ys[0], 16, 16);
			g.fillRect(xs[2], ys[2], 16, 16);
			g.setColor(Color.red);
			g.fillRect(xs[1], ys[1], 16, 16);
			g.fillRect(xs[3], ys[3], 16, 16);
//		��ǥ�� ������ ���ߴ�. �׸��� �� paintComponent(Graphics g)���� �Ѵ�.
		Graphics2D g2d = (Graphics2D) g;
//		Graphics2D�� �ڽ��̱� ������ g�� ����ȯ�� �Ѵ�.
		g2d.setColor(Color.black);
		GeneralPath path = new GeneralPath();
		path.moveTo(xs[0], ys[0]);
//		moveTo�� �������� ���Ѵ�.
		path.curveTo(xs[1], ys[1],xs[2], ys[2],xs[3], ys[3]);
//		1��°, 2��°, 3��°(������ ������)�� ���� 
		g2d.draw(path);
//		���� �׸���. 
		}
	}
	
	
	
	
	
	public BezierCurve() {
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������ Ŀ�� ����");
		drawPanel = new MyPanel();
		drawPanel.addMouseListener(this);
		drawPanel.addMouseMotionListener(this);
		add(drawPanel, BorderLayout.CENTER);
	}
//	�����ڿ��� �⺻���� ������ �Է��Ѵ�. 
	
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
//		Ŭ������ �� �ش� ��ǥ�� ������ ���� Rectangle�� ���ϸ�(contains��� �޼ҵ� ���)dragIndex�� �ش� ��ǥ������ �ٲ���´�. dragIndex�� �巡���ϰ� 
//		���콺�� ���� ������ �����ȴ�.
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		dragIndex = -1;
	}
//	���콺�� ������ dragIndex�� -1�� �ʱ�ȭ�Ѵ�. �ٵ� �ʿ���� �κ��̴�.
	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragIndex !=-1) {
			xs[dragIndex] = e.getX();
			ys[dragIndex] = e.getY();
		}
//	�׸� ���� ���콺�� ������ �ִ� ������ ��� �ű��. �巡���� ������ ������ �ִ� ���¿��� 1�̶� �����̸� �۵��ǹǷ� ���� ���� ����Ǵ� �޼ҵ��̴�.
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
