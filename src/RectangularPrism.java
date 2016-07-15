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
class RectangularPrism extends JComponent implements MouseMotionListener, MouseListener, MouseWheelListener
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 0;
	public int yoffset = 0;
	boolean isSelected = false;
	private Rectangle BoundingBox = new Rectangle();
	Point[] points = new Point[8];
	Triangle[] triangles = new Triangle[12];
	public RectangularPrism()
	{
		
	}
	public RectangularPrism(Point center, int length, int width, int height)
	{
		for(int i = 0; i < 8; i++) points[i] = new Point(0, 0, 0);
		points[0].SetX(center.GetExX() - (length / 2));
		points[0].SetY(center.GetExY() + (height / 2));
		points[0].SetZ(center.GetExZ() - (width / 2));
		points[1].SetX(center.GetExX() + (length / 2));
		points[1].SetY(center.GetExY() + (height / 2));
		points[1].SetZ(center.GetExZ() - (width / 2));
		points[2].SetX(center.GetExX() - (length / 2));
		points[2].SetY(center.GetExY() - (height / 2));
		points[2].SetZ(center.GetExZ() - (width / 2));
		points[3].SetX(center.GetExX() + (length / 2));
		points[3].SetY(center.GetExY() - (height / 2));
		points[3].SetZ(center.GetExZ() - (width / 2));
		points[4].SetX(center.GetExX() - (length / 2));
		points[4].SetY(center.GetExY() + (height / 2));
		points[4].SetZ(center.GetExZ() + (width / 2));
		points[5].SetX(center.GetExX() + (length / 2));
		points[5].SetY(center.GetExY() + (height / 2));
		points[5].SetZ(center.GetExZ() + (width / 2));
		points[6].SetX(center.GetExX() - (length / 2));
		points[6].SetY(center.GetExY() - (height / 2));
		points[6].SetZ(center.GetExZ() + (width / 2));
		points[7].SetX(center.GetExX() + (length / 2));
		points[7].SetY(center.GetExY() - (height / 2));
		points[7].SetZ(center.GetExZ() + (width / 2));
		triangles[0] = new Triangle(points[0], points[1], points[2]);
		triangles[1] = new Triangle(points[1], points[3], points[2]);
		triangles[2] = new Triangle(points[5], points[4], points[6]);
		triangles[3] = new Triangle(points[5], points[6], points[7]);
		triangles[4] = new Triangle(points[4], points[1], points[0]);
		triangles[5] = new Triangle(points[4], points[5], points[1]);
		triangles[6] = new Triangle(points[2], points[3], points[7]);
		triangles[7] = new Triangle(points[2], points[7], points[6]);
		triangles[8] = new Triangle(points[4], points[0], points[2]);
		triangles[9] = new Triangle(points[4], points[2], points[6]);
		triangles[10] = new Triangle(points[1], points[5], points[7]);
		triangles[11] = new Triangle(points[1], points[7], points[3]);
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
		Point one = Util.GetNormalVector(points[0], points[1], points[3]);
		Point two = Util.GetNormalVector(points[4], points[5], points[1]);
		double dotproduct = Util.GetDotProduct(one, World.Camera);
		double dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[0].GetX() + xoffset, points[0].GetY() + yoffset, points[1].GetX() + xoffset, points[1].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[4], points[5], points[1]);
		two = Util.GetNormalVector(points[5], points[4], points[6]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[4].GetX() + xoffset, points[4].GetY() + yoffset, points[5].GetX() + xoffset, points[5].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[4], points[5], points[1]);
		two = Util.GetNormalVector(points[1], points[5], points[7]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[1].GetX() + xoffset, points[1].GetY() + yoffset, points[5].GetX() + xoffset, points[5].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[4], points[5], points[1]);
		two = Util.GetNormalVector(points[4], points[0], points[2]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[0].GetX() + xoffset, points[0].GetY() + yoffset, points[4].GetX() + xoffset, points[4].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[2], points[3], points[7]);
		two = Util.GetNormalVector(points[0], points[1], points[3]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[3].GetX() + xoffset, points[3].GetY() + yoffset, points[2].GetX() + xoffset, points[2].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[2], points[3], points[7]);
		two = Util.GetNormalVector(points[5], points[4], points[6]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct < 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[6].GetX() + xoffset, points[6].GetY() + yoffset, points[7].GetX() + xoffset, points[7].GetY() + yoffset);
		}		
		one = Util.GetNormalVector(points[2], points[3], points[7]);
		two = Util.GetNormalVector(points[1], points[5], points[7]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[3].GetX() + xoffset, points[3].GetY() + yoffset, points[7].GetX() + xoffset, points[7].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[2], points[3], points[7]);
		two = Util.GetNormalVector(points[4], points[0], points[2]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[2].GetX() + xoffset, points[2].GetY() + yoffset, points[6].GetX() + xoffset, points[6].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[0], points[1], points[3]);
		two = Util.GetNormalVector(points[1], points[5], points[7]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[1].GetX() + xoffset, points[1].GetY() + yoffset, points[3].GetX() + xoffset, points[3].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[1], points[5], points[7]);
		two = Util.GetNormalVector(points[5], points[4], points[6]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[5].GetX() + xoffset, points[5].GetY() + yoffset, points[7].GetX() + xoffset, points[7].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[0], points[1], points[3]);
		two = Util.GetNormalVector(points[4], points[0], points[2]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[0].GetX() + xoffset, points[0].GetY() + yoffset, points[2].GetX() + xoffset, points[2].GetY() + yoffset);
		}
		one = Util.GetNormalVector(points[4], points[0], points[2]);
		two = Util.GetNormalVector(points[5], points[4], points[6]);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(points[6].GetX() + xoffset, points[6].GetY() + yoffset, points[4].GetX() + xoffset, points[4].GetY() + yoffset);
		}
	}
	public void paintComponent(Graphics gr)
	{
		/*Graphics2D g = (Graphics2D)gr;
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
		x1[0] = points[1].GetX() + xoffset;
		x1[1] = points[0].GetX() + xoffset;
		x1[2] = points[2].GetX() + xoffset;
		x1[3] = points[3].GetX() + xoffset;
		y1[0] = points[1].GetY() + yoffset;
		y1[1] = points[0].GetY() + yoffset;
		y1[2] = points[2].GetY() + yoffset;
		y1[3] = points[3].GetY() + yoffset;
		x2[0] = points[5].GetX() + xoffset;
		x2[1] = points[4].GetX() + xoffset;
		x2[2] = points[6].GetX() + xoffset;
		x2[3] = points[7].GetX() + xoffset;
		y2[0] = points[5].GetY() + yoffset;
		y2[1] = points[4].GetY() + yoffset;
		y2[2] = points[6].GetY() + yoffset;
		y2[3] = points[7].GetY() + yoffset;
		x3[0] = points[0].GetX() + xoffset;
		x3[1] = points[4].GetX() + xoffset;
		x3[2] = points[6].GetX() + xoffset;
		x3[3] = points[2].GetX() + xoffset;
		y3[0] = points[0].GetY() + yoffset;
		y3[1] = points[4].GetY() + yoffset;
		y3[2] = points[6].GetY() + yoffset;
		y3[3] = points[2].GetY() + yoffset;
		x4[0] = points[1].GetX() + xoffset;
		x4[1] = points[5].GetX() + xoffset;
		x4[2] = points[7].GetX() + xoffset;
		x4[3] = points[3].GetX() + xoffset;
		y4[0] = points[1].GetY() + yoffset;
		y4[1] = points[5].GetY() + yoffset;
		y4[2] = points[7].GetY() + yoffset;
		y4[3] = points[3].GetY() + yoffset;
		x5[0] = points[0].GetX() + xoffset;
		x5[1] = points[1].GetX() + xoffset;
		x5[2] = points[5].GetX() + xoffset;
		x5[3] = points[4].GetX() + xoffset;
		y5[0] = points[0].GetY() + yoffset;
		y5[1] = points[1].GetY() + yoffset;
		y5[2] = points[5].GetY() + yoffset;
		y5[3] = points[4].GetY() + yoffset;
		x6[0] = points[2].GetX() + xoffset;
		x6[1] = points[3].GetX() + xoffset;
		x6[2] = points[7].GetX() + xoffset;
		x6[3] = points[6].GetX() + xoffset;
		y6[0] = points[2].GetY() + yoffset;
		y6[1] = points[3].GetY() + yoffset;
		y6[2] = points[7].GetY() + yoffset;
		y6[3] = points[6].GetY() + yoffset;
		Point p = new Point(0, 0, 0);
		p = Util.GetNormalVector(points[0], points[1], points[3]);
		double dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x1, y1, x1.length);
			g.setColor(Color.blue);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[5], points[4], points[6]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x2, y2, x2.length);
			g.setColor(Color.red);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[4], points[0], points[2]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x3, y3, x3.length);
			g.setColor(Color.yellow);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[1], points[5], points[7]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x4, y4, x4.length);
			g.setColor(Color.gray);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[4], points[5], points[1]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x5, y5, x5.length);
			g.setColor(Color.black);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(points[2], points[3], points[7]);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x6, y6, x6.length);
			g.setColor(Color.magenta);
			g.fillPolygon(poly);
		}
		if(isSelected)
		{
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.green);
			DrawOutline(g);
		}*/
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
