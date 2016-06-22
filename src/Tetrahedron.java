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
class Tetrahedron extends JComponent implements MouseMotionListener, MouseListener
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 0;
	public int yoffset = 0;
	boolean isSelected = false;
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
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateClockwiseAboutYAxis(float degrees)
	{
		p1.RotateClockwiseAboutYAxis(degrees);
		p2.RotateClockwiseAboutYAxis(degrees);
		p3.RotateClockwiseAboutYAxis(degrees);
		p4.RotateClockwiseAboutYAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutXAxis(degrees);
		p2.RotateCounterClockwiseAboutXAxis(degrees);
		p3.RotateCounterClockwiseAboutXAxis(degrees);
		p4.RotateCounterClockwiseAboutXAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateClockwiseAboutXAxis(float degrees)
	{
		p1.RotateClockwiseAboutXAxis(degrees);
		p2.RotateClockwiseAboutXAxis(degrees);
		p3.RotateClockwiseAboutXAxis(degrees);
		p4.RotateClockwiseAboutXAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		p1.RotateCounterClockwiseAboutZAxis(degrees);
		p2.RotateCounterClockwiseAboutZAxis(degrees);
		p3.RotateCounterClockwiseAboutZAxis(degrees);
		p4.RotateCounterClockwiseAboutZAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
	}
	public void RotateClockwiseAboutZAxis(float degrees)
	{
		p1.RotateClockwiseAboutZAxis(degrees);
		p2.RotateClockwiseAboutZAxis(degrees);
		p3.RotateClockwiseAboutZAxis(degrees);
		p4.RotateClockwiseAboutZAxis(degrees);
		paintImmediately(0, 0, 1280, 1000);
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
	public void Selected()
	{
		
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
	}
	public void mouseDragged(MouseEvent m) {
		JComponent c = (JComponent) getComponentAt(m.getX(), m.getY());
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
