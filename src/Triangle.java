class Triangle
{
	Point[] points = new Point[3];
	public Triangle(Point p1, Point p2, Point p3)
	{
		points[0] = new Point(p1.GetExX(), p1.GetExY(), p1.GetExZ());
		points[1] = new Point(p2.GetExX(), p2.GetExY(), p2.GetExZ());
		points[2] = new Point(p3.GetExX(), p3.GetExY(), p3.GetExZ());
	}
	public Triangle(Triangle triangle)
	{
		for(int i = 0; i < 3; i++)
		{
			this.points[i] = new Point(triangle.points[i].GetExX(), triangle.points[i].GetExY(), triangle.points[i].GetExZ());
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
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateClockwiseAboutYAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateCounterClockwiseAboutXAxis(p, degrees);
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateClockwiseAboutXAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateCounterClockwiseAboutZAxis(p, degrees);
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Point p1 : points)
			p1.RotateClockwiseAboutZAxis(p, degrees);
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
}
