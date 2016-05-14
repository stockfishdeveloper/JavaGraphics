import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.lang.Math;
@SuppressWarnings("serial")
class Square extends JComponent
{
	static Point origin = new Point(350, 350, 0);
	Point p1 = new Point(-50, -50, -50);
	Point p2 = new Point(50, -50, -50);
	Point p3 = new Point(-50, 50, -50);
	Point p4 = new Point(50, 50, -50);
	Point p5 = new Point(-50, -50, 50);
	Point p6 = new Point(50, -50, 50);
	Point p7 = new Point(-50, 50, 50);
	Point p8 = new Point(50, 50, 50);
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		super.paintComponent(gr);
		//RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g.setRenderingHints(rh);
		g.setColor(Color.red);
		g.drawLine(p1.GetX() + 300, p1.GetY() + 300, p2.GetX() + 300, p2.GetY() + 300);
		g.drawLine(p1.GetX() + 300, p1.GetY() + 300, p3.GetX() + 300, p3.GetY() + 300);
		g.drawLine(p3.GetX() + 300, p3.GetY() + 300, p4.GetX() + 300, p4.GetY() + 300);
		g.drawLine(p2.GetX() + 300, p2.GetY() + 300, p4.GetX() + 300, p4.GetY() + 300);
		//g.setColor(Color.green);
		g.drawLine(p5.GetX() + 300, p5.GetY() + 300, p6.GetX() + 300, p6.GetY() + 300);
		g.drawLine(p5.GetX() + 300, p5.GetY() + 300, p7.GetX() + 300, p7.GetY() + 300);
		g.drawLine(p7.GetX() + 300, p7.GetY() + 300, p8.GetX() + 300, p8.GetY() + 300);
		g.drawLine(p6.GetX() + 300, p6.GetY() + 300, p8.GetX() + 300, p8.GetY() + 300);
		g.setColor(Color.blue);
		g.drawLine(p1.GetX() + 300, p1.GetY() + 300, p5.GetX() + 300, p5.GetY() + 300);
		g.drawLine(p2.GetX() + 300, p2.GetY() + 300, p6.GetX() + 300, p6.GetY() + 300);
		g.drawLine(p3.GetX() + 300, p3.GetY() + 300, p7.GetX() + 300, p7.GetY() + 300);
		g.drawLine(p4.GetX() + 300, p4.GetY() + 300, p8.GetX() + 300, p8.GetY() + 300);
		g.setColor(Color.green);
		g.drawLine(p1.GetX() + 300, p1.GetY() + 300, p8.GetX() + 300, p8.GetY() + 300);
		g.drawLine(p4.GetX() + 300, p4.GetY() + 300, p5.GetX() + 300, p5.GetY() + 300);
		g.drawLine(p2.GetX() + 300, p2.GetY() + 300, p7.GetX() + 300, p7.GetY() + 300);
		g.drawLine(p3.GetX() + 300, p3.GetY() + 300, p6.GetX() + 300, p6.GetY() + 300);
		int[] x1 = new int[4];
		int[] y1 = new int[4];
		int[] x2 = new int[4];
		int[] y2 = new int[4];
		x1[0] = p2.GetX() + 300;
		x1[1] = p1.GetX() + 300;
		x1[2] = p3.GetX() + 300;
		x1[3] = p4.GetX() + 300;
		y1[0] = p2.GetY() + 300;
		y1[1] = p1.GetY() + 300;
		y1[2] = p3.GetY() + 300;
		y1[3] = p4.GetY() + 300;
		x2[0] = p6.GetX() + 300;
		x2[1] = p5.GetX() + 300;
		x2[2] = p7.GetX() + 300;
		x2[3] = p8.GetX() + 300;
		y2[0] = p6.GetY() + 300;
		y2[1] = p5.GetY() + 300;
		y2[2] = p7.GetY() + 300;
		y2[3] = p8.GetY() + 300;
		if((p1.GetZ() + p2.GetZ() + p3.GetZ() + p4.GetZ()) <= (p5.GetZ() + p6.GetZ() + p7.GetZ() + p8.GetZ()))
		{
		Polygon poly = new Polygon(x2, y2, x2.length);
		g.setColor(Color.red);
		g.fillPolygon(poly);
		poly = new Polygon(x1, y1, x1.length);
		g.setColor(Color.blue);
		g.fillPolygon(poly);
		}
		else
		{
			Polygon poly = new Polygon(x1, y1, x1.length);
			g.setColor(Color.blue);
			g.fillPolygon(poly);
			poly = new Polygon(x2, y2, x2.length);
			g.setColor(Color.red);
			g.fillPolygon(poly);
		}
    }
}

class Frame implements MouseListener, MouseMotionListener
{
	int currx = 350;
	int curry = 350;
	Square s; 
	JFrame frame;
	JPanel panel;
	public Frame()
	{
	frame = new JFrame();
	frame.setSize(500, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel = new JPanel();
	panel.addMouseListener(this);
	panel.addMouseMotionListener(this);
	s = new Square();
	s.setPreferredSize(new Dimension(1100, 1100));
	s.setMaximumSize(s.getPreferredSize());
	s.setMinimumSize(s.getPreferredSize());
	panel.add(s, BorderLayout.CENTER);
	frame.add(panel, BorderLayout.CENTER);
	frame.setVisible(true);
	}

	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent m) {
		//System.out.println(m.getX() + " " + m.getY());
	}

	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		if(m.getX() > currx)
		{	
			currx = m.getX();
			s.p1.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p2.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p3.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p4.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p5.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p6.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p7.RotateCounterClockwiseAboutYAxis(Square.origin);
			s.p8.RotateCounterClockwiseAboutYAxis(Square.origin);
		}
		if(m.getX() < currx)
		{
			currx = m.getX();
			s.p1.RotateClockwiseAboutYAxis(Square.origin);
			s.p2.RotateClockwiseAboutYAxis(Square.origin);
			s.p3.RotateClockwiseAboutYAxis(Square.origin);
			s.p4.RotateClockwiseAboutYAxis(Square.origin);
			s.p5.RotateClockwiseAboutYAxis(Square.origin);
			s.p6.RotateClockwiseAboutYAxis(Square.origin);
			s.p7.RotateClockwiseAboutYAxis(Square.origin);
			s.p8.RotateClockwiseAboutYAxis(Square.origin);
		}
		if(m.getY() < curry)
		{
			curry = m.getY();
			s.p1.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p2.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p3.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p4.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p5.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p6.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p7.RotateCounterClockwiseAboutXAxis(Square.origin);
			s.p8.RotateCounterClockwiseAboutXAxis(Square.origin);
		}
		if(m.getY() > curry)
		{
			curry = m.getY();
			s.p1.RotateClockwiseAboutXAxis(Square.origin);
			s.p2.RotateClockwiseAboutXAxis(Square.origin);
			s.p3.RotateClockwiseAboutXAxis(Square.origin);
			s.p4.RotateClockwiseAboutXAxis(Square.origin);
			s.p5.RotateClockwiseAboutXAxis(Square.origin);
			s.p6.RotateClockwiseAboutXAxis(Square.origin);
			s.p7.RotateClockwiseAboutXAxis(Square.origin);
			s.p8.RotateClockwiseAboutXAxis(Square.origin);
		}
		//System.out.println("X: " + s.p1.GetExX() + " Y: " + s.p1.GetExY() + " Z: " + s.p1.GetExZ());
		//System.out.println("Distance from origin: " + Math.hypot(0 - s.p1.GetExX(), 0 - s.p1.GetExZ()));
		s.paintImmediately(0, 0, 1000, 1000);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class Point
{
	private double x;
	private double y;
	private double z;
	Point(int fx, int fy, int fz)
	{
		x = fx;
		y = fy;
		z = fz;
	}
	int GetX()
	{
		return (int) Math.round(x);
	}
	int GetY()
	{
		return (int) Math.round(y);
	}
	int GetZ()
	{
		return (int) Math.round(z);
	}
	double GetExX()
	{
		return x;
	}
	double GetExY()
	{
		return y;
	}
	double GetExZ()
	{
		return z;
	}
	void SetX(double newx)
	{
		x = newx;
	}
	void SetY(double newy)
	{
		y = newy;
	}
	void SetZ(double newz)
	{
		z = newz;
	}
	void RotateClockwiseAboutYAxis(Point origin)
	{
		double hx = z * (Math.sin(-3 / 57.2958));
		hx = hx + (x * Math.cos(-3 / 57.2958));
		double hz = z * (Math.cos(-3 / 57.2958));
		z = hz - (x * Math.sin(-3 / 57.2958));
		x = hx;
	}
	void RotateCounterClockwiseAboutYAxis(Point origin)
	{
		double hx = z * (Math.sin(3 / 57.2958));
		hx = hx + (x * Math.cos(3 / 57.2958));
		double hz = z * (Math.cos(3 / 57.2958));
		z = hz - (x * Math.sin(3 / 57.2958));
		x = hx;
	}
	void RotateClockwiseAboutXAxis(Point origin)
	{
		double hy = y * (Math.cos(-3 / 57.2958));
		hy = hy - (z * Math.sin(-3 / 57.2958));
		double hz = y * (Math.sin(-3 / 57.2958));
		z = hz + (z * Math.cos(-3 / 57.2958));
		y = hy;
	}
	void RotateCounterClockwiseAboutXAxis(Point origin)
	{
		double hy = y * (Math.cos(3 / 57.2958));
		hy = hy - (z * Math.sin(3 / 57.2958));
		double hz = y * (Math.sin(3 / 57.2958));
		z = hz + (z * Math.cos(3 / 57.2958));
		y = hy;
	}
}

class Run
{
	public static void main(String[] args)
	{
		Frame frame = new Frame();
	}
}
