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
class Sphere extends JComponent implements MouseMotionListener, MouseListener, MouseWheelListener
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 0;
	public int yoffset = 0;
	boolean isSelected = false;
	private Rectangle BoundingBox = new Rectangle();
	Point[] points;
	Triangle[] triangles;
	int layers = 0;
	int ppl = 0;
	public Sphere()
	{
		
	}
	public Sphere(int layers, int ppl)
	{
		this.layers = layers * 2;
		this.ppl = ppl;
		triangles = new Triangle[(2 * layers * ( 2 * layers + ppl)) + (2 * ppl)];
		for(int i = 0; i < triangles.length; i++) triangles[i] = new Triangle(new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0));
		int count = 0;
		double[] semicircle = new double[layers * 2];
		for(int i = -layers; i < layers; i++)
		{
			semicircle[count++] = Math.sqrt((double)((layers * layers) - (i * i)));
		}
		count = 0;
		int scount = 0;
		points = new Point[(layers * 2 * ppl) + 2];
		for(int i = 0; i < points.length; i++) points[i] = new Point(0, 0, 0);
		points[count++] = new Point(0, layers / 2, 0);
		for(int i = layers; i > -layers; i--)
		{
			for(int j = 0; j < ppl; j++)
			{
				points[count].SetY(i);
				float degrees = (float)(j) * (360.0f / (float)(ppl));
				Point p1 = new Point(semicircle[scount], 0, 0);
				p1.RotateCounterClockwiseAboutYAxis(degrees);
				points[count].SetX(p1.GetExX());
				points[count++].SetZ(p1.GetExZ());
			}
			scount++;
		}
		points[count++] = new Point(0, -(layers / 2), 0);
		//count = 0;
		int count2 = 0;
		for(int i = 0; i < ((2 * layers) - 2); i++)
		{
			for(int j = 0; j < ppl; j++)
			{
					int[] x = new int[3];
					int[] y = new int[3];
					int[] z = new int[3];
					x[0] = points[(ppl * i) + j].GetX() + xoffset;
					x[1] = points[(ppl * i) + j + 1].GetX() + xoffset;
					x[2] = points[(ppl * i) + j + ppl + 1].GetX() + xoffset;
					y[0] = points[(ppl * i) + j].GetY() + yoffset;
					y[1] = points[(ppl * i) + j + 1].GetY() + yoffset;
					y[2] = points[(ppl * i) + j + ppl + 1].GetY() + yoffset;
					z[0] = points[(ppl * i) + j].GetZ();
					z[1] = points[(ppl * i) + j + 1].GetZ();
					z[2] = points[(ppl * i) + j + ppl + 1].GetZ();
					triangles[count2++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]));
					if(i > 0)
						{
							x[0] = points[(ppl * i) + j].GetX() + xoffset;
							x[1] = points[((ppl * i) + j) - ppl].GetX() + xoffset;
							x[2] = points[(ppl * i) + j + 1].GetX() + xoffset;
							y[0] = points[(ppl * i) + j].GetY() + yoffset;
							y[1] = points[((ppl * i) + j) - ppl].GetY() + yoffset;
							y[2] = points[(ppl * i) + j + 1].GetY() + yoffset;
							z[0] = points[(ppl * i) + j].GetZ();
							z[1] = points[((ppl * i) + j) - ppl].GetZ();
							z[2] = points[(ppl * i) + j + 1].GetZ();
							triangles[count2++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]));
						}
			}
		}
		count2 = 0;
		for(int j = 1; j < ppl; j++)
		{
				int[] x = new int[3];
				int[] y = new int[3];
				int[] z = new int[3];
				x[0] = points[0].GetX() + xoffset;
				x[1] = points[j].GetX() + xoffset;
				x[2] = points[j + 1].GetX() + xoffset;
				y[0] = points[0].GetY() + yoffset;
				y[1] = points[j].GetY() + yoffset;
				y[2] = points[j + 1].GetY() + yoffset;
				z[0] = points[0].GetZ();
				z[1] = points[j].GetZ();
				z[2] = points[j + 1].GetZ();
				triangles[count2++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]));
		}
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
	public void RotateCounterClockwiseAboutYAxis(float degrees)
	{
		for(Triangle t : triangles)
		t.RotateCounterClockwiseAboutYAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutYAxis(float degrees)
	{
		for(Triangle t : triangles)
		t.RotateClockwiseAboutYAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		for(Triangle t : triangles)
		t.RotateCounterClockwiseAboutXAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutXAxis(float degrees)
	{
		for(Triangle t : triangles)
		t.RotateClockwiseAboutXAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		for(Triangle t : triangles)
		t.RotateCounterClockwiseAboutZAxis(degrees);
		UpdateBoundingBox();
		repaint();
	}
	public void RotateClockwiseAboutZAxis(float degrees)
	{
		for(Triangle t : triangles)
		t.RotateClockwiseAboutZAxis(degrees);
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
		for(int i = 0; i < triangles.length - ppl; i++)
		{
			if((triangles[i].Should_Be_Drawn() && !triangles[i + (ppl - 1)].Should_Be_Drawn()) || (!triangles[i].Should_Be_Drawn() && triangles[i + (ppl - 1)].Should_Be_Drawn()))
			{
				int[] coords = new int[4];
				coords = Util.GetSharedSide(triangles[i], triangles[i + (ppl - 1)]);
				g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
			}
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
