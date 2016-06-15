import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial")
class Cube extends JComponent implements MouseMotionListener, MouseListener
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 0;
	public int yoffset = 0;
	boolean isSelected = false;
	Point p1 = new Point(-50, -50, -50);
	Point p2 = new Point(50, -50, -50);
	Point p3 = new Point(-50, 50, -50);
	Point p4 = new Point(50, 50, -50);
	Point p5 = new Point(-50, -50, 50);
	Point p6 = new Point(50, -50, 50);
	Point p7 = new Point(-50, 50, 50);
	Point p8 = new Point(50, 50, 50);
	public Cube()
	{
		
	}
	public Cube(Point center, int radius)
	{
		p1.SetX(center.GetExX() - radius);
		p1.SetY(center.GetExY() + radius);
		p1.SetZ(center.GetExZ() - radius);
		p2.SetX(center.GetExX() + radius);
		p2.SetY(center.GetExY() + radius);
		p2.SetZ(center.GetExZ() - radius);
		p3.SetX(center.GetExX() - radius);
		p3.SetY(center.GetExY() - radius);
		p3.SetZ(center.GetExZ() - radius);
		p4.SetX(center.GetExX() + radius);
		p4.SetY(center.GetExY() - radius);
		p4.SetZ(center.GetExZ() - radius);
		p5.SetX(center.GetExX() - radius);
		p5.SetY(center.GetExY() + radius);
		p5.SetZ(center.GetExZ() + radius);
		p6.SetX(center.GetExX() + radius);
		p6.SetY(center.GetExY() + radius);
		p6.SetZ(center.GetExZ() + radius);
		p7.SetX(center.GetExX() - radius);
		p7.SetY(center.GetExY() - radius);
		p7.SetZ(center.GetExZ() + radius);
		p8.SetX(center.GetExX() + radius);
		p8.SetY(center.GetExY() - radius);
		p8.SetZ(center.GetExZ() + radius);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void TranslateVisiblePosition(int x, int y)
	{
		xoffset += x;
		yoffset += y;
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateCounterClockwiseAboutYAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutYAxis(degrees);
		p2.RotateCounterClockwiseAboutYAxis(degrees);
		p3.RotateCounterClockwiseAboutYAxis(degrees);
		p4.RotateCounterClockwiseAboutYAxis(degrees);
		p5.RotateCounterClockwiseAboutYAxis(degrees);
		p6.RotateCounterClockwiseAboutYAxis(degrees);
		p7.RotateCounterClockwiseAboutYAxis(degrees);
		p8.RotateCounterClockwiseAboutYAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateClockwiseAboutYAxis(float degrees)
	{
		p1.RotateClockwiseAboutYAxis(degrees);
		p2.RotateClockwiseAboutYAxis(degrees);
		p3.RotateClockwiseAboutYAxis(degrees);
		p4.RotateClockwiseAboutYAxis(degrees);
		p5.RotateClockwiseAboutYAxis(degrees);
		p6.RotateClockwiseAboutYAxis(degrees);
		p7.RotateClockwiseAboutYAxis(degrees);
		p8.RotateClockwiseAboutYAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutXAxis(degrees);
		p2.RotateCounterClockwiseAboutXAxis(degrees);
		p3.RotateCounterClockwiseAboutXAxis(degrees);
		p4.RotateCounterClockwiseAboutXAxis(degrees);
		p5.RotateCounterClockwiseAboutXAxis(degrees);
		p6.RotateCounterClockwiseAboutXAxis(degrees);
		p7.RotateCounterClockwiseAboutXAxis(degrees);
		p8.RotateCounterClockwiseAboutXAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateClockwiseAboutXAxis(float degrees)
	{
		p1.RotateClockwiseAboutXAxis(degrees);
		p2.RotateClockwiseAboutXAxis(degrees);
		p3.RotateClockwiseAboutXAxis(degrees);
		p4.RotateClockwiseAboutXAxis(degrees);
		p5.RotateClockwiseAboutXAxis(degrees);
		p6.RotateClockwiseAboutXAxis(degrees);
		p7.RotateClockwiseAboutXAxis(degrees);
		p8.RotateClockwiseAboutXAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutZAxis(degrees);
		p2.RotateCounterClockwiseAboutZAxis(degrees);
		p3.RotateCounterClockwiseAboutZAxis(degrees);
		p4.RotateCounterClockwiseAboutZAxis(degrees);
		p5.RotateCounterClockwiseAboutZAxis(degrees);
		p6.RotateCounterClockwiseAboutZAxis(degrees);
		p7.RotateCounterClockwiseAboutZAxis(degrees);
		p8.RotateCounterClockwiseAboutZAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateClockwiseAboutZAxis(float degrees)
	{
		p1.RotateClockwiseAboutZAxis(degrees);
		p2.RotateClockwiseAboutZAxis(degrees);
		p3.RotateClockwiseAboutZAxis(degrees);
		p4.RotateClockwiseAboutZAxis(degrees);
		p5.RotateClockwiseAboutZAxis(degrees);
		p6.RotateClockwiseAboutZAxis(degrees);
		p7.RotateClockwiseAboutZAxis(degrees);
		p8.RotateClockwiseAboutZAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void Selected()
	{
		
	}
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		super.paintComponent(gr);
		//RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g.setRenderingHints(rh);
		int[] x1 = new int[4];
		int[] y1 = new int[4];
		int[] x2 = new int[4];
		int[] y2 = new int[4];
		int[] x3 = new int[4];
		int[] y3 = new int[4];
		int[] x4 = new int[4];
		int[] y4 = new int[4];
		int[] x5 = new int[4];
		int[] y5 = new int[4];
		int[] x6 = new int[4];
		int[] y6 = new int[4];
		x1[0] = p2.GetX() + xoffset;
		x1[1] = p1.GetX() + xoffset;
		x1[2] = p3.GetX() + xoffset;
		x1[3] = p4.GetX() + xoffset;
		y1[0] = p2.GetY() + yoffset;
		y1[1] = p1.GetY() + yoffset;
		y1[2] = p3.GetY() + yoffset;
		y1[3] = p4.GetY() + yoffset;
		x2[0] = p6.GetX() + xoffset;
		x2[1] = p5.GetX() + xoffset;
		x2[2] = p7.GetX() + xoffset;
		x2[3] = p8.GetX() + xoffset;
		y2[0] = p6.GetY() + yoffset;
		y2[1] = p5.GetY() + yoffset;
		y2[2] = p7.GetY() + yoffset;
		y2[3] = p8.GetY() + yoffset;
		x3[0] = p1.GetX() + xoffset;
		x3[1] = p5.GetX() + xoffset;
		x3[2] = p7.GetX() + xoffset;
		x3[3] = p3.GetX() + xoffset;
		y3[0] = p1.GetY() + yoffset;
		y3[1] = p5.GetY() + yoffset;
		y3[2] = p7.GetY() + yoffset;
		y3[3] = p3.GetY() + yoffset;
		x4[0] = p2.GetX() + xoffset;
		x4[1] = p6.GetX() + xoffset;
		x4[2] = p8.GetX() + xoffset;
		x4[3] = p4.GetX() + xoffset;
		y4[0] = p2.GetY() + yoffset;
		y4[1] = p6.GetY() + yoffset;
		y4[2] = p8.GetY() + yoffset;
		y4[3] = p4.GetY() + yoffset;
		x5[0] = p1.GetX() + xoffset;
		x5[1] = p2.GetX() + xoffset;
		x5[2] = p6.GetX() + xoffset;
		x5[3] = p5.GetX() + xoffset;
		y5[0] = p1.GetY() + yoffset;
		y5[1] = p2.GetY() + yoffset;
		y5[2] = p6.GetY() + yoffset;
		y5[3] = p5.GetY() + yoffset;
		x6[0] = p3.GetX() + xoffset;
		x6[1] = p4.GetX() + xoffset;
		x6[2] = p8.GetX() + xoffset;
		x6[3] = p7.GetX() + xoffset;
		y6[0] = p3.GetY() + yoffset;
		y6[1] = p4.GetY() + yoffset;
		y6[2] = p8.GetY() + yoffset;
		y6[3] = p7.GetY() + yoffset;
		Point p = new Point(0, 0, 0);
		p = Util.GetNormalVector(p1, p2, p4);
		double dotproduct = Util.GetDotProduct(p, World.Camera);
		/*if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x1, y1, x1.length);
			g.setColor(Color.blue);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p6, p5, p7);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x2, y2, x2.length);
			g.setColor(Color.red);
			g.fillPolygon(poly);
		}*/
		p = Util.GetNormalVector(p1, p5, p6);
		System.out.println("X: " + p.GetExY() + " Y: " + p.GetExY() + " Z: " + p.GetExZ());
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x3, y3, x3.length);
			g.setColor(Color.yellow);
			g.fillPolygon(poly);
		}
		/*p = Util.GetNormalVector(p3, p4, p8);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x4, y4, x4.length);
			g.setColor(Color.gray);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p5, p1, p3);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x5, y5, x5.length);
			g.setColor(Color.black);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p2, p6, p8);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x6, y6, x6.length);
			g.setColor(Color.magenta);
			g.fillPolygon(poly);
		}*/
	}
	public void mouseDragged(MouseEvent m) {
		JComponent c = (JComponent) getComponentAt(m.getX(), m.getY());
		if(c != null && c == (this) && isSelected == true)
		{
		if(SwingUtilities.isRightMouseButton(m))
		{
			if(m.getX() > currx)
			{	
				currx = m.getX();
				RotateCounterClockwiseAboutYAxis(3.0f);
			}
			if(m.getX() < currx)
			{
				currx = m.getX();
				RotateClockwiseAboutYAxis(3.0f);
			}
			if(m.getY() < curry)
			{
				curry = m.getY();
				RotateCounterClockwiseAboutXAxis(3.0f);
			}
			if(m.getY() > curry)
			{
				curry = m.getY();
				RotateClockwiseAboutXAxis(3.0f);
			}
		}
		else if(SwingUtilities.isLeftMouseButton(m))
		{
			TranslateVisiblePosition(m.getX() - xoffset, m.getY() - yoffset);
		}
		}
	}
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent m) {
		isSelected = (isSelected == true ? false : true);
	}
	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
}
