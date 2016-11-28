import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Triangle implements Comparable<Triangle>
{
	static boolean blue = true;
	Point[] points = new Point[3];
	Pixel[] pixels;
	public Triangle(Point p1, Point p2, Point p3, ArrayList<Pixel> data)
	{
		pixels = new Pixel[data.size()];
		points[0] = new Point(p1.GetExX(), p1.GetExY(), p1.GetExZ());
		points[1] = new Point(p2.GetExX(), p2.GetExY(), p2.GetExZ());
		points[2] = new Point(p3.GetExX(), p3.GetExY(), p3.GetExZ());
		for(int i = 0; i < data.size(); i++)
			pixels[i] = Pixel.Copy(data.get(i));
	}
	public Triangle(Triangle triangle)
	{
		for(int i = 0; i < 3; i++)
		{
			this.points[i] = new Point(triangle.points[i].GetExX(), triangle.points[i].GetExY(), triangle.points[i].GetExZ());
		}
		for(int i = 0; i < triangle.pixels.length; i++)
		{
			this.pixels[i] = Pixel.Copy(triangle.pixels[i]);
		}
	}
	public void Print_Info()
	{
		for(Point p : points)
		{
			System.out.println(p.GetExX() + " " + p.GetExY() + " " + p.GetExZ());
		}
	}
	public boolean Should_Be_Drawn()
	{
		Point p = Util.GetNormalVector(points[0], points[1], points[2]);
		double dotproduct = Util.GetDotProduct(p, World.camera.direction);
		boolean draw = dotproduct < 0 ? true : false;
		return draw;
	}
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateCounterClockwiseAboutYAxis(p, degrees);
		for(Pixel p2 : pixels)
			p2.RotateCounterClockwiseAboutYAxis(p, degrees);
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateClockwiseAboutYAxis(p, degrees);
		for(Pixel p2 : pixels)
			p2.RotateClockwiseAboutYAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateCounterClockwiseAboutXAxis(p, degrees);
		for(Pixel p2 : pixels)
			p2.RotateCounterClockwiseAboutXAxis(p, degrees);
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateClockwiseAboutXAxis(p, degrees);
		for(Pixel p2 : pixels)
			p2.RotateClockwiseAboutXAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateCounterClockwiseAboutZAxis(p, degrees);
		for(Pixel p2 : pixels)
			p2.RotateCounterClockwiseAboutZAxis(p, degrees);
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateClockwiseAboutZAxis(p, degrees);
		for(Pixel p2 : pixels)
			p2.RotateClockwiseAboutZAxis(p, degrees);
	}
	public void Resize(float scaleFactor)
	{
		for(Point p : points)
		{
			p.SetX(p.GetExX() * scaleFactor);
			p.SetY(p.GetExY() * scaleFactor);
			p.SetZ(p.GetExZ() * scaleFactor);
		}
	}
	public Point GetCenter()
	{
		double x = (points[0].GetExX() + points[1].GetExX() + points[2].GetExX()) / 3.0; 
		double y = (points[0].GetExY() + points[1].GetExY() + points[2].GetExY()) / 3.0;
		double z = (points[0].GetExZ() + points[1].GetExZ() + points[2].GetExZ()) / 3.0;
		return new Point(x, y, z);
	}
	public void Render(BufferedImage image)
	{
		if(Should_Be_Drawn())
		{
			for(int i = 0; i < pixels.length; i++)
			{
				Point p = World.camera.LookAt(pixels[i]);
				if(p != null)
				if(p.GetX() > -640 && p.GetX() < 640 && p.GetY() > -500 && p.GetY() < 500)
					image.setRGB(p.GetX() + 640, p.GetY() + 500, pixels[i].GetColor());
			}
		}
	}
	public void Draw_Mesh(Graphics2D g)
	{
		//g.drawLine(points[0].GetX() + 200, points[0].GetY() + 200, points[1].GetX() + 200, points[1].GetY() + 200);
		//g.drawLine(points[1].GetX() + 200, points[1].GetY() + 200, points[2].GetX() + 200, points[2].GetY() + 200);
		//g.drawLine(points[0].GetX() + 200, points[0].GetY() + 200, points[2].GetX() + 200, points[2].GetY() + 200);
		if(Should_Be_Drawn())
		{
			g.setColor(blue == true ? Color.blue : Color.red);
			int[] x = new int[3];
			int[] y = new int[3];
			for(int i = 0; i < 3; i++)
			{
				x[i] = points[i].GetX() + 200;
				y[i] = points[i].GetY() + 200;
			}
			Polygon poly = new Polygon(x, y, 3);
			g.fillPolygon(poly);
			boolean f = !blue;
			blue = f;
		}
	}
	public int GreatestX()
	{
		int gx = -10000;
		for(Point p : points) if(p.GetExX() > gx) gx = p.GetX();
		return gx;
	}
	public int LeastX()
	{
		int lx = 10000;
		for(Point p : points) if(p.GetExX() < lx) lx = p.GetX();
		return lx;
	}
	public int GreatestY()
	{
		int gy = -10000;
		for(Point p : points) if(p.GetExY() > gy) gy = p.GetY();
		return gy;
	}
	public int LeastY()
	{
		int ly = 10000;
		for(Point p : points) if(p.GetExY() < ly) ly = p.GetY();
		return ly;
	}
        @Override
	public int compareTo(Triangle t) 
	{
		if (this.GetCenter().GetExZ() <= t.GetCenter().GetExZ()) {
            return 1;
        } else if (this.GetCenter().GetExZ() > t.GetCenter().GetExZ()) {
            return -1;
        } else {
            return 0;
        }
	}
}
