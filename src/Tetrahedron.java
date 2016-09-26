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
	Triangle[] triangles = new Triangle[4];
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
		triangles[0] = new Triangle(points[0], points[1], points[2]);
		triangles[1] = new Triangle(points[0], points[2], points[3]);
		triangles[2] = new Triangle(points[0], points[3], points[1]);
		triangles[3] = new Triangle(points[1], points[3], points[2]);
		UpdateBoundingBox();
		repaint();
	}
	public void UpdateBoundingBox()
	{
		int gx = -10000;
		int ly = 10000;
		int lx = 10000;
		int gy = -10000;
		for(Triangle t : triangles)
		{
			if(t.GreatestX() > gx) gx = t.GreatestX();
			if(t.LeastX() < lx) lx = t.LeastX();
			if(t.GreatestY() > gy) gy = t.GreatestY();
			if(t.LeastY() < ly) ly = t.LeastY();
		}
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
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutYAxis(p, degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutYAxis(p, degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutXAxis(p, degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutXAxis(p, degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutZAxis(p, degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutZAxis(p, degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void Resize(float scaleFactor)
	{
		for(Triangle t : triangles)
			t.Resize(scaleFactor);
		UpdateBoundingBox();
		repaint();
	}
	public Point GetCenter()
	{
		double totx = 0;
		double toty = 0;
		double totz = 0;
		for(Triangle t : triangles)
		{
			totx += t.points[0].GetExX();
			totx += t.points[1].GetExX();
			totx += t.points[2].GetExX();
			toty += t.points[0].GetExY();
			toty += t.points[1].GetExY();
			toty += t.points[2].GetExY();
			totz += t.points[0].GetExZ();
			totz += t.points[1].GetExZ();
			totz += t.points[2].GetExZ();
		}
		totx /= 12.0d;
		toty /= 12.0d;
		totz /= 12.0d;
		return new Point(totx, toty, totz);
	}
	public void DrawOutline(Graphics2D g)
	{
		if(triangles[0].Should_Be_Drawn() && (!triangles[1].Should_Be_Drawn()) || !triangles[0].Should_Be_Drawn() && (triangles[1].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[0], triangles[1]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[0].Should_Be_Drawn() && (!triangles[2].Should_Be_Drawn()) || !triangles[0].Should_Be_Drawn() && (triangles[2].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[0], triangles[2]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[0].Should_Be_Drawn() && (!triangles[3].Should_Be_Drawn()) || !triangles[0].Should_Be_Drawn() && (triangles[3].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[0], triangles[3]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[3].Should_Be_Drawn() && (!triangles[2].Should_Be_Drawn()) || !triangles[3].Should_Be_Drawn() && (triangles[2].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[3], triangles[2]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[3].Should_Be_Drawn() && (!triangles[1].Should_Be_Drawn()) || !triangles[3].Should_Be_Drawn() && (triangles[1].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[3], triangles[1]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[1].Should_Be_Drawn() && (!triangles[2].Should_Be_Drawn()) || !triangles[1].Should_Be_Drawn() && (triangles[2].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[1], triangles[2]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
	}
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		boolean blue = true;
		for(Triangle t : triangles)
		{
			if(t.Should_Be_Drawn())
			{
				g.setColor(blue ? Color.blue : Color.red);
				int[] x = new int[3];
				int[] y = new int[3];
				for(int i = 0; i < 3; i++)
				{
					x[i] = t.points[i].GetX() + xoffset;
					y[i] = t.points[i].GetY() + yoffset;
				}
				Polygon poly = new Polygon(x, y, 3);
				g.fillPolygon(poly);
				blue = !blue;
			}
		}
		if(isSelected)
		{
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.green);
			DrawOutline(g);
		}
	}
	public void Move(Point target, float distance)
	{
		Point center = GetCenter();
		Point orig = GetCenter();
		Util.MovePointTowardsAnotherPoint(center, target, distance);
		for(Triangle t : triangles)
		{
			t.points[0].SetX(t.points[0].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[0].SetY(t.points[0].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[0].SetZ(t.points[0].GetExZ() + (center.GetExZ() - orig.GetExZ()));
			t.points[1].SetX(t.points[1].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[1].SetY(t.points[1].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[1].SetZ(t.points[1].GetExZ() + (center.GetExZ() - orig.GetExZ()));
			t.points[2].SetX(t.points[2].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[2].SetY(t.points[2].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[2].SetZ(t.points[2].GetExZ() + (center.GetExZ() - orig.GetExZ()));
		}
		UpdateBoundingBox();
		repaint();
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
				RotateCounterClockwiseAboutYAxis(GetCenter(), 3.0f);
			}
			if(m.getX() > currx)
			{
				currx = m.getX();
				RotateClockwiseAboutYAxis(GetCenter(), 3.0f);
			}
			if(m.getY() > curry)
			{
				curry = m.getY();
				RotateCounterClockwiseAboutXAxis(GetCenter(), 3.0f);
			}
			if(m.getY() < curry)
			{
				curry = m.getY();
				RotateClockwiseAboutXAxis(GetCenter(), 3.0f);
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
