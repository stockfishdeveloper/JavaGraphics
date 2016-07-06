class Triangle
{
	Point[] points = new Point[3];
	public Triangle(Point p1, Point p2, Point p3)
	{
		points[0] = p1;
		points[1] = p2;
		points[2] = p3;
	}
	public boolean Draw()
	{
		Point p = Util.GetNormalVector(points[0], points[1], points[2]);
		double dotproduct = Util.GetDotProduct(p, World.Camera);
		boolean draw = dotproduct < 0 ? true : false;
		return draw;
	}
}
