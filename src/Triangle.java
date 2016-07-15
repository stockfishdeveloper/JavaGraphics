class Triangle
{
	Point[] points = new Point[3];
	public Triangle(Point p1, Point p2, Point p3)
	{
		points[0] = new Point(p1.GetExX(), p1.GetExY(), p1.GetExZ());
		points[1] = new Point(p2.GetExX(), p2.GetExY(), p2.GetExZ());
		points[2] = new Point(p3.GetExX(), p3.GetExY(), p3.GetExZ());
	}
	public boolean Should_Be_Drawn()
	{
		Point p = Util.GetNormalVector(points[0], points[1], points[2]);
		double dotproduct = Util.GetDotProduct(p, World.Camera);
		boolean draw = dotproduct < 0 ? true : false;
		return draw;
	}
	public void RotateCounterClockwiseAboutYAxis(float degrees)
	{
		for(Point p : points)
			p.RotateCounterClockwiseAboutYAxis(degrees);
	}
	public void RotateClockwiseAboutYAxis(float degrees)
	{
		for(Point p : points)
			p.RotateClockwiseAboutYAxis(degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		for(Point p : points)
			p.RotateCounterClockwiseAboutXAxis(degrees);
	}
	public void RotateClockwiseAboutXAxis(float degrees)
	{
		for(Point p : points)
			p.RotateClockwiseAboutXAxis(degrees);
	}
	public void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		for(Point p : points)
			p.RotateCounterClockwiseAboutZAxis(degrees);
	}
	public void RotateClockwiseAboutZAxis(float degrees)
	{
		for(Point p : points)
			p.RotateClockwiseAboutZAxis(degrees);
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
