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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial")
class Tetrahedron extends JComponent implements MouseMotionListener, MouseListener, MouseWheelListener
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 0;
	public int yoffset = 0;
	boolean isSelected = false;
	private Rectangle BoundingBox = new Rectangle();
	Point[] points = new Point[4];
	public Tetrahedron()
	{
		
	}
	public Tetrahedron(Point center, float scaleFactor)
	{
		for(int i = 0; i < 4; i++) points[i] = new Point(0, 0, 0);
		points[0].SetX(center.GetExX() != 0 ? center.GetExX() * -1 * scaleFactor : -scaleFactor);
		points[0].SetY(0);
		points[0].SetZ(center.GetExZ() != 0 ? center.GetExZ() * -0.707 * scaleFactor : -0.707 * scaleFactor);
		points[1].SetX(center.GetExX() != 0 ? center.GetExX() * scaleFactor : scaleFactor);
		points[1].SetY(0);
		points[1].SetZ(center.GetExZ() != 0 ? center.GetExZ() * -0.707 * scaleFactor : -0.707 * scaleFactor);
		points[2].SetX(0);
		points[2].SetY(center.GetExY() != 0 ? center.GetExY() * -1 * scaleFactor : -scaleFactor);
		points[2].SetZ(center.GetExZ() != 0 ? center.GetExZ() * 0.707 * scaleFactor : 0.707 * scaleFactor);
		points[3].SetX(0);
		points[3].SetY(center.GetExY() != 0 ? center.GetExY() * scaleFactor : scaleFactor);
		points[3].SetZ(center.GetExZ() != 0 ? center.GetExZ() * 0.707 * scaleFactor : 0.707 * scaleFactor);
		UpdateBoundingBox();
		repaint();
	}
	public void UpdateBoundingBox()
	{
		int gx = -10000;
		int ly = 10000;
		int lx = 10000;
		int gy = -10000;
		if(points[0].GetExX() > gx) gx = points[0].GetX();
		if(points[0].GetExX() < lx) lx = points[0].GetX();
		if(points[0].GetExY() < ly) ly = points[0].GetY();
		if(points[0].GetExY() > gy) gy = points[0].GetY();
		if(points[1].GetExX() > gx) gx = points[1].GetX();
		if(points[1].GetExX() < lx) lx = points[1].GetX();
		if(points[1].GetExY() < ly) ly = points[1].GetY();
		if(points[1].GetExY() > gy) gy = points[1].GetY();
		if(points[2].GetExX() > gx) gx = points[2].GetX();
		if(points[2].GetExX() < lx) lx = points[2].GetX();
		if(points[2].GetExY() < ly) ly = points[2].GetY();
		if(points[2].GetExY() > gy) gy = points[2].GetY();
		if(points[3].GetExX() > gx) gx = points[3].GetX();
		if(points[3].GetExX() < lx) lx = points[3].GetX();
		if(points[3].GetExY() < ly) ly = points[3].GetY();
		if(points[3].GetExY() > gy) gy = points[3].GetY();
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
		for(Point p : points)
			p.RotateCounterClockwiseAboutYAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutYAxis(float degrees)
	{
		for(Point p : points)
			p.RotateClockwiseAboutYAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		for(Point p : points)
			p.RotateCounterClockwiseAboutXAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutXAxis(float degrees)
	{
		for(Point p : points)
			p.RotateClockwiseAboutXAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		for(Point p : points)
			p.RotateCounterClockwiseAboutZAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutZAxis(float degrees)
	{
		for(Point p : points)
			p.RotateClockwiseAboutZAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void Resize(float scaleFactor)
	{
		for(Point p : points)
		{
			p.SetX(p.GetExX() * scaleFactor);
			p.SetY(p.GetExY() * scaleFactor);
			p.SetZ(p.GetExZ() * scaleFactor);
		}
		UpdateBoundingBox();
		repaint();
	}
	public void DrawOutline(Graphics2D g)
	{
		Point one = Util.GetNormalVector(points[0], points[1], points[2]);
		Point two = Util.GetNormalVector(points[0], points[2], points[3]);
		double dotproduct = Util.GetDotProduct(one, World.Camera);
		double dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[0].GetX() + xoffset, points[0].GetY() + yoffset, points[2].GetX() + xoffset, points[2].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[0], points[1], points[2]);
		two = Util.GetNormalVector(points[0], points[3], points[1]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[0].GetX() + xoffset, points[0].GetY() + yoffset, points[1].GetX() + xoffset, points[1].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[0], points[2], points[3]);
		two = Util.GetNormalVector(points[0], points[3], points[1]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[0].GetX() + xoffset, points[0].GetY() + yoffset, points[3].GetX() + xoffset, points[3].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[3], points[2], points[1]);
		two = Util.GetNormalVector(points[0], points[3], points[1]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[3].GetX() + xoffset, points[3].GetY() + yoffset, points[1].GetX() + xoffset, points[1].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[3], points[2], points[1]);
		two = Util.GetNormalVector(points[0], points[2], points[3]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[3].GetX() + xoffset, points[3].GetY() + yoffset, points[2].GetX() + xoffset, points[2].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[3], points[2], points[1]);
		two = Util.GetNormalVector(points[0], points[1], points[2]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[2].GetX() + xoffset, points[2].GetY() + yoffset, points[1].GetX() + xoffset, points[1].GetY() + yoffset);
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
		x1[0] = points[0].GetX() + xoffset;
		x1[1] = points[2].GetX() + xoffset;
		x1[2] = points[3].GetX() + xoffset;
		y1[0] = points[0].GetY() + yoffset;
		y1[1] = points[2].GetY() + yoffset;
		y1[2] = points[3].GetY() + yoffset;
		x2[0] = points[0].GetX() + xoffset;
		x2[1] = points[1].GetX() + xoffset;
		x2[2] = points[2].GetX() + xoffset;
		y2[0] = points[0].GetY() + yoffset;
		y2[1] = points[1].GetY() + yoffset;
		y2[2] = points[2].GetY() + yoffset;
		x3[0] = points[0].GetX() + xoffset;
		x3[1] = points[1].GetX() + xoffset;
		x3[2] = points[3].GetX() + xoffset;
		y3[0] = points[0].GetY() + yoffset;
		y3[1] = points[1].GetY() + yoffset;
		y3[2] = points[3].GetY() + yoffset;
		x4[0] = points[1].GetX() + xoffset;
		x4[1] = points[2].GetX() + xoffset;
		x4[2] = points[3].GetX() + xoffset;
		y4[0] = points[1].GetY() + yoffset;
		y4[1] = points[2].GetY() + yoffset;
		y4[2] = points[3].GetY() + yoffset;
		Point p = new Point(0, 0, 0);
		p = Util.GetNormalVector(points[0], points[2], points[3]);
		double dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x1, y1, x1.length);
			g.setColor(Color.blue);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[0], points[1], points[2]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x2, y2, x2.length);
			g.setColor(Color.red);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[0], points[3], points[1]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x3, y3, x3.length);
			g.setColor(Color.yellow);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[1], points[3], points[2]);
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
		}		
	}
	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		if(m.getPreciseWheelRotation() < 0)
			Resize(1.1f);
		else
			Resize(0.9f);	
	}
}
