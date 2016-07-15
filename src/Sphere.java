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
		triangles = new Triangle[2 * layers * ( 2 * layers + ppl)];
		for(int i = 0; i < triangles.length; i++) triangles[i] = new Triangle(new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0));
		int count = 0;
		double[] semicircle = new double[layers * 2];
		for(int i = -layers; i < layers; i++)
		{
			semicircle[count++] = Math.sqrt((double)((layers * layers) - (i * i)));
		}
		count = 0;
		int scount = 0;
		points = new Point[layers * 2 * ppl];
		for(int i = 0; i < points.length; i++) points[i] = new Point(0, 0, 0);
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
		count = 0;
		for(int i = 0; i < (2 * layers - 2); i++)
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
					triangles[count++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]));
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
						triangles[count++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]));
				}
			}
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
	public void DrawOutline(Graphics2D g)
	{
		
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
				//blue = !blue;
			}
		}
	}
	public void mouseDragged(MouseEvent m) {
		Component c = (Component) World.panel.getComponentAt(m.getX(), m.getY());
		//if(c != null && c == (this) && isSelected == true)
		//{
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
		//}
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
