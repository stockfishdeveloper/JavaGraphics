import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial")
class Tetrahedron extends JComponent implements MouseMotionListener, MouseListener
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 0;
	public int yoffset = 0;
	boolean isSelected = false;
	private Rectangle BoundingBox = new Rectangle();
	Point p1 = new Point(-100, 0, -70.7);
	Point p2 = new Point(100, 0, -70.7);
	Point p3 = new Point(0, -100, 70.7);
	Point p4 = new Point(0, 100, 70.7);
	public Tetrahedron()
	{
		
	}
	public Tetrahedron(Point center, float scaleFactor)
	{
		p1.SetX(center.GetExX() != 0 ? center.GetExX() * -1 * scaleFactor : -scaleFactor);
		p1.SetY(0);
		p1.SetZ(center.GetExZ() != 0 ? center.GetExZ() * -0.707 * scaleFactor : -0.707 * scaleFactor);
		p2.SetX(center.GetExX() != 0 ? center.GetExX() * scaleFactor : scaleFactor);
		p2.SetY(0);
		p2.SetZ(center.GetExZ() != 0 ? center.GetExZ() * -0.707 * scaleFactor : -0.707 * scaleFactor);
		p3.SetX(0);
		p3.SetY(center.GetExY() != 0 ? center.GetExY() * -1 * scaleFactor : -scaleFactor);
		p3.SetZ(center.GetExZ() != 0 ? center.GetExZ() * 0.707 * scaleFactor : 0.707 * scaleFactor);
		p4.SetX(0);
		p4.SetY(center.GetExY() != 0 ? center.GetExY() * scaleFactor : scaleFactor);
		p4.SetZ(center.GetExZ() != 0 ? center.GetExZ() * 0.707 * scaleFactor : 0.707 * scaleFactor);
		UpdateBoundingBox();
		repaint();
	}
	public void UpdateBoundingBox()
	{
		int gx = -10000;
		int ly = 10000;
		int lx = 10000;
		int gy = -10000;
		if(p1.GetExX() > gx) gx = p1.GetX();
		if(p1.GetExX() < lx) lx = p1.GetX();
		if(p1.GetExY() < ly) ly = p1.GetY();
		if(p1.GetExY() > gy) gy = p1.GetY();
		if(p2.GetExX() > gx) gx = p2.GetX();
		if(p2.GetExX() < lx) lx = p2.GetX();
		if(p2.GetExY() < ly) ly = p2.GetY();
		if(p2.GetExY() > gy) gy = p2.GetY();
		if(p3.GetExX() > gx) gx = p3.GetX();
		if(p3.GetExX() < lx) lx = p3.GetX();
		if(p3.GetExY() < ly) ly = p3.GetY();
		if(p3.GetExY() > gy) gy = p3.GetY();
		if(p4.GetExX() > gx) gx = p4.GetX();
		if(p4.GetExX() < lx) lx = p4.GetX();
		if(p4.GetExY() < ly) ly = p4.GetY();
		if(p4.GetExY() > gy) gy = p4.GetY();
		BoundingBox = new Rectangle();
		BoundingBox.add(gx + xoffset, gy + yoffset);
		BoundingBox.add(gx + xoffset, ly + yoffset);
		BoundingBox.add(lx + xoffset, ly + yoffset);
		BoundingBox.add(lx + xoffset, gy + yoffset);
		this.setBounds(BoundingBox);
	}
	public void TranslateVisiblePosition(int x, int y)
	{
		xoffset += x;
		yoffset += y;
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutYAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutYAxis(degrees);
		p2.RotateCounterClockwiseAboutYAxis(degrees);
		p3.RotateCounterClockwiseAboutYAxis(degrees);
		p4.RotateCounterClockwiseAboutYAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutYAxis(float degrees)
	{
		p1.RotateClockwiseAboutYAxis(degrees);
		p2.RotateClockwiseAboutYAxis(degrees);
		p3.RotateClockwiseAboutYAxis(degrees);
		p4.RotateClockwiseAboutYAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutXAxis(degrees);
		p2.RotateCounterClockwiseAboutXAxis(degrees);
		p3.RotateCounterClockwiseAboutXAxis(degrees);
		p4.RotateCounterClockwiseAboutXAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutXAxis(float degrees)
	{
		p1.RotateClockwiseAboutXAxis(degrees);
		p2.RotateClockwiseAboutXAxis(degrees);
		p3.RotateClockwiseAboutXAxis(degrees);
		p4.RotateClockwiseAboutXAxis(degrees);
		repaint();
	}
	public void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutZAxis(degrees);
		p2.RotateCounterClockwiseAboutZAxis(degrees);
		p3.RotateCounterClockwiseAboutZAxis(degrees);
		p4.RotateCounterClockwiseAboutZAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutZAxis(float degrees)
	{
		p1.RotateClockwiseAboutZAxis(degrees);
		p2.RotateClockwiseAboutZAxis(degrees);
		p3.RotateClockwiseAboutZAxis(degrees);
		p4.RotateClockwiseAboutZAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void Resize(float scaleFactor)
	{
		p1.SetX(p1.GetExX() * scaleFactor);
		p1.SetY(p1.GetExY() * scaleFactor);
		p1.SetZ(p1.GetExZ() * scaleFactor);
		p2.SetX(p2.GetExX() * scaleFactor);
		p2.SetY(p2.GetExY() * scaleFactor);
		p2.SetZ(p2.GetExZ() * scaleFactor);
		p3.SetX(p3.GetExX() * scaleFactor);
		p3.SetY(p3.GetExY() * scaleFactor);
		p3.SetZ(p3.GetExZ() * scaleFactor);
		p4.SetX(p4.GetExX() * scaleFactor);
		p4.SetY(p4.GetExY() * scaleFactor);
		p4.SetZ(p4.GetExZ() * scaleFactor);
	}
	public void DrawOutline(Graphics2D g)
	{
		Point one = Util.GetNormalVector(p1, p2, p3);
		Point two = Util.GetNormalVector(p1, p3, p4);
		double dotproduct = Util.GetDotProduct(one, World.Camera);
		double dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p1.GetX() + xoffset, p1.GetY() + yoffset, p3.GetX() + xoffset, p3.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p1, p2, p3);
		two = Util.GetNormalVector(p1, p4, p2);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p1.GetX() + xoffset, p1.GetY() + yoffset, p2.GetX() + xoffset, p2.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p1, p3, p4);
		two = Util.GetNormalVector(p1, p4, p2);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p1.GetX() + xoffset, p1.GetY() + yoffset, p4.GetX() + xoffset, p4.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p4, p3, p2);
		two = Util.GetNormalVector(p1, p4, p2);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p4.GetX() + xoffset, p4.GetY() + yoffset, p2.GetX() + xoffset, p2.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p4, p3, p2);
		two = Util.GetNormalVector(p1, p3, p4);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p4.GetX() + xoffset, p4.GetY() + yoffset, p3.GetX() + xoffset, p3.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p4, p3, p2);
		two = Util.GetNormalVector(p1, p2, p3);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p3.GetX() + xoffset, p3.GetY() + yoffset, p2.GetX() + xoffset, p2.GetY() + yoffset);
		}
	}
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		super.paintComponent(gr);
		int[] x1 = new int[3];
		int[] y1 = new int[3];
		int[] x2 = new int[3];
		int[] y2 = new int[3];
		int[] x3 = new int[3];
		int[] y3 = new int[3];
		int[] x4 = new int[3];
		int[] y4 = new int[3];
		x1[0] = p1.GetX() + xoffset;
		x1[1] = p3.GetX() + xoffset;
		x1[2] = p4.GetX() + xoffset;
		y1[0] = p1.GetY() + yoffset;
		y1[1] = p3.GetY() + yoffset;
		y1[2] = p4.GetY() + yoffset;
		x2[0] = p1.GetX() + xoffset;
		x2[1] = p2.GetX() + xoffset;
		x2[2] = p3.GetX() + xoffset;
		y2[0] = p1.GetY() + yoffset;
		y2[1] = p2.GetY() + yoffset;
		y2[2] = p3.GetY() + yoffset;
		x3[0] = p1.GetX() + xoffset;
		x3[1] = p2.GetX() + xoffset;
		x3[2] = p4.GetX() + xoffset;
		y3[0] = p1.GetY() + yoffset;
		y3[1] = p2.GetY() + yoffset;
		y3[2] = p4.GetY() + yoffset;
		x4[0] = p2.GetX() + xoffset;
		x4[1] = p3.GetX() + xoffset;
		x4[2] = p4.GetX() + xoffset;
		y4[0] = p2.GetY() + yoffset;
		y4[1] = p3.GetY() + yoffset;
		y4[2] = p4.GetY() + yoffset;
		Point p = new Point(0, 0, 0);
		p = Util.GetNormalVector(p1, p3, p4);
		double dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x1, y1, x1.length);
			g.setColor(Color.blue);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p1, p2, p3);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x2, y2, x2.length);
			g.setColor(Color.red);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p1, p4, p2);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x3, y3, x3.length);
			g.setColor(Color.yellow);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p2, p4, p3);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x4, y4, x4.length);
			g.setColor(Color.gray);
			g.fillPolygon(poly);
		}
		if(isSelected)
		{
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.green);
			DrawOutline(g);
		}
	}
	public void mouseDragged(MouseEvent m) {
		Component c = (Component) World.panel.getComponentAt(m.getX(), m.getY());
		if(c != null && c == (this) && isSelected == true)
		{
		if(SwingUtilities.isRightMouseButton(m))
		{
			if(m.getX() < currx)
			{	
				currx = m.getX();
				RotateCounterClockwiseAboutYAxis(3.0f);
			}
			if(m.getX() > currx)
			{
				currx = m.getX();
				RotateClockwiseAboutYAxis(3.0f);
			}
			if(m.getY() > curry)
			{
				curry = m.getY();
				RotateCounterClockwiseAboutXAxis(3.0f);
			}
			if(m.getY() < curry)
			{
				curry = m.getY();
				RotateClockwiseAboutXAxis(3.0f);
			}
		}
		else if(SwingUtilities.isLeftMouseButton(m))
		{
			TranslateVisiblePosition(m.getX() - currx, m.getY() - curry);
			currx = m.getX();
			curry = m.getY();
		}
		}
	}
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent m) {
		Component c = (Component) World.panel.getComponentAt(m.getX(), m.getY());
		if(c != null && c == (this) && SwingUtilities.isLeftMouseButton(m))
		{
		isSelected = (isSelected == true ? false : true);
		UpdateBoundingBox();
		repaint();
		}
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
		Component c = (Component) World.panel.getComponentAt(m.getX(), m.getY());
		if(c != null && c == (this) && SwingUtilities.isLeftMouseButton(m))
		{
			currx = m.getX();
			curry = m.getY();
			System.out.println("Mouse pressed");
		}		
	}
	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
}
