package ch05_2.oop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoreShapes extends JFrame{
	public MoreShapes() {
		setSize(600,130);
		setTitle("Java 2D Shapes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MyPanel();
		add(panel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MoreShapes();
	}
}
class MyPanel extends JPanel{
	ArrayList<Shape> shapeArray = new ArrayList<Shape>();
	
	public MyPanel() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		GradientPaint gp = new GradientPaint(0,10,Color.WHITE,0,70,Color.red);
		g2.setStroke(new BasicStroke(3));
		
		g2.setPaint(Color.red);
		g2.fill(new Rectangle2D.Float(10,10,70,80));
		
		g2.setPaint(gp);
		g2.fill(new RoundRectangle2D.Float(110,10,70,80,20,20));
		
		g2.setPaint(Color.red);
		g2.fill(new Ellipse2D.Float(210,10,80,80));
		
		g2.setPaint(gp);
		g2.fill(new Arc2D.Float(310,10,80,80,25,245,Arc2D.OPEN));
		
		g2.setPaint(Color.red);
		g2.fill(new Arc2D.Float(410,10,80,80,25,240,Arc2D.CHORD));
		
		g2.setPaint(gp);
		g2.fill(new Arc2D.Float(510,10,80,80,45,225,Arc2D.PIE));
	}
}
