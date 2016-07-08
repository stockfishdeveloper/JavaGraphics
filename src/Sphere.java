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
	int layers = 0;
	int ppl = 0;
	public Sphere()
	{
		
	}
	public Sphere(int layers, int ppl)
	{
		this.requestFocusInWindow();
		this.layers = layers * 2;
		this.ppl = ppl;
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
		//for(Point p : points) System.out.println(p.GetY());
		UpdateBoundingBox();
		repaint();
	}
	public void UpdateBoundingBox()
	{
		int gx = -10000;
		int ly = 10000;
		int lx = 10000;
		int gy = -10000;
		for(int i = 0; i < points.length; i++)
		{
			if(points[i].GetExX() > gx) gx = points[i].GetX();
			if(points[i].GetExX() < lx) lx = points[i].GetX();
			if(points[i].GetExY() > gy) gy = points[i].GetY();
			if(points[i].GetExY() < ly) ly = points[i].GetY();
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
		/*Point one = Util.GetNormalVector(p1, p2, p4);
		Point two = Util.GetNormalVector(p5, p6, p2);
		double dotproduct = Util.GetDotProduct(one, World.Camera);
		double dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p1.GetX() + xoffset, p1.GetY() + yoffset, p2.GetX() + xoffset, p2.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p5, p6, p2);
		two = Util.GetNormalVector(p6, p5, p7);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p5.GetX() + xoffset, p5.GetY() + yoffset, p6.GetX() + xoffset, p6.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p5, p6, p2);
		two = Util.GetNormalVector(p2, p6, p8);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p2.GetX() + xoffset, p2.GetY() + yoffset, p6.GetX() + xoffset, p6.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p5, p6, p2);
		two = Util.GetNormalVector(p5, p1, p3);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p1.GetX() + xoffset, p1.GetY() + yoffset, p5.GetX() + xoffset, p5.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p3, p4, p8);
		two = Util.GetNormalVector(p1, p2, p4);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p4.GetX() + xoffset, p4.GetY() + yoffset, p3.GetX() + xoffset, p3.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p3, p4, p8);
		two = Util.GetNormalVector(p6, p5, p7);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct < 0 && dotproduct1 >= 0))
		{
			g.drawLine(p7.GetX() + xoffset, p7.GetY() + yoffset, p8.GetX() + xoffset, p8.GetY() + yoffset);
		}		
		one = Util.GetNormalVector(p3, p4, p8);
		two = Util.GetNormalVector(p2, p6, p8);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p4.GetX() + xoffset, p4.GetY() + yoffset, p8.GetX() + xoffset, p8.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p3, p4, p8);
		two = Util.GetNormalVector(p5, p1, p3);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p3.GetX() + xoffset, p3.GetY() + yoffset, p7.GetX() + xoffset, p7.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p1, p2, p4);
		two = Util.GetNormalVector(p2, p6, p8);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p2.GetX() + xoffset, p2.GetY() + yoffset, p4.GetX() + xoffset, p4.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p2, p6, p8);
		two = Util.GetNormalVector(p6, p5, p7);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p6.GetX() + xoffset, p6.GetY() + yoffset, p8.GetX() + xoffset, p8.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p1, p2, p4);
		two = Util.GetNormalVector(p5, p1, p3);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p1.GetX() + xoffset, p1.GetY() + yoffset, p3.GetX() + xoffset, p3.GetY() + yoffset);
		}
		one = Util.GetNormalVector(p5, p1, p3);
		two = Util.GetNormalVector(p6, p5, p7);
		dotproduct = Util.GetDotProduct(one, World.Camera);
		dotproduct1 = Util.GetDotProduct(two,  World.Camera);
		if((dotproduct >= 0 && dotproduct1 <= 0) || (dotproduct <= 0 && dotproduct1 >= 0))
		{
			g.drawLine(p7.GetX() + xoffset, p7.GetY() + yoffset, p5.GetX() + xoffset, p5.GetY() + yoffset);
		}*/
	}
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		//super.paintComponent(gr);
		//RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g.setRenderingHints(rh);
		/*int[] x1 = new int[4];
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
		if(dotproduct < 0)
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
		}
		p = Util.GetNormalVector(p5, p1, p3);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x3, y3, x3.length);
			g.setColor(Color.yellow);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p2, p6, p8);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x4, y4, x4.length);
			g.setColor(Color.gray);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p5, p6, p2);
		dotproduct = Util.GetDotProduct(p, World.Camera);
		if(dotproduct < 0)
		{
			Polygon poly = new Polygon(x5, y5, x5.length);
			g.setColor(Color.black);
			g.fillPolygon(poly);
		}
		p = Util.GetNormalVector(p3, p4, p8);
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
		for(int i = 0; i < layers - 2; i++)
		{
			g.setColor(i % 2 == 0 ? Color.red : Color.blue);
			for(int j = 0; j < ppl; j++)
			{
				Point p = Util.GetNormalVector(points[(ppl * i) + j], points[(ppl * i) + j + 1], points[(ppl * i) + j + ppl + 1]);
				Util.NormalizeVector(p);
				if(Util.GetDotProduct(p, World.Camera) > 0)
				{
					int[] x = new int[3];
					int[] y = new int[3];
					x[0] = points[(ppl * i) + j].GetX() + xoffset;
					x[1] = points[(ppl * i) + j + 1].GetX() + xoffset;
					x[2] = points[(ppl * i) + j + ppl + 1].GetX() + xoffset;
					y[0] = points[(ppl * i) + j].GetY() + yoffset;
					y[1] = points[(ppl * i) + j + 1].GetY() + yoffset;
					y[2] = points[(ppl * i) + j + ppl + 1].GetY() + yoffset;
					Polygon poly = new Polygon(x, y, x.length);
					g.fillPolygon(poly);
				}
				if(i > 0)
				{
					p = Util.GetNormalVector(points[(ppl * i) + j], points[((ppl * i) + j) - ppl], points[(ppl * i) + j + 1]);
					Util.NormalizeVector(p);
					if(Util.GetDotProduct(p, World.Camera) > 0)
					{
						Color c = g.getColor();
						if(c.equals(Color.red))
							g.setColor(Color.blue);
						else
							g.setColor(Color.red);
						int[] x = new int[3];
						int[] y = new int[3];
						x[0] = points[(ppl * i) + j].GetX() + xoffset;
						x[1] = points[((ppl * i) + j) - ppl].GetX() + xoffset;
						x[2] = points[(ppl * i) + j + 1].GetX() + xoffset;
						y[0] = points[(ppl * i) + j].GetY() + yoffset;
						y[1] = points[((ppl * i) + j) - ppl].GetY() + yoffset;
						y[2] = points[(ppl * i) + j + 1].GetY() + yoffset;
						Polygon poly = new Polygon(x, y, x.length);
						g.fillPolygon(poly);
						g.setColor(c);
					}
				}
			}
		}
		/*g.setColor(Color.black);
		for(int i = 1; i < points.length; i++)
		{
			g.setColor(i % 2 == 0 ? Color.black : Color.red);
			g.drawLine(points[i-1].GetX() + xoffset, points[i-1].GetY() + yoffset, points[i].GetX() + xoffset, points[i].GetY() + yoffset);
		}*/
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
