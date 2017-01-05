class Frustum
{
	Point[] points = new Point[8];
	double greatestx;
	double leastx;
	double greatesty;
	double leasty;
	double greatestz;
	double leastz;
	public Frustum(Point[] points1)
	{
		for(int i = 0; i < 8; i++)
		{
			points[i] = points1[i];
		}
		greatestx = -10000000;
		leastx = 10000000;
		greatesty = -10000000;
		leasty = 10000000;
		greatestz = -10000000;
		leastz = 10000000;
	}
	public Frustum(Frustum frustum)
	{
		for(int i = 0; i < 8; i++)
		{
			points[i] = new Point(frustum.points[i].GetExX(), frustum.points[i].GetExY(), frustum.points[i].GetExZ());
		}
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
	public boolean Contains(Point p)
        {
		return (//p.GetExX() > leastx && p.GetExX() < greatestx && 
				//p.GetExY() > leasty && p.GetExY() < greatesty &&
				p.GetExZ() > leastz /*&& p.GetExZ() < greatestz*/);
	}
}
