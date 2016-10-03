class Camera
{
	public Point location;
	public Point direction;
	Point eye;
	Frustum frustum;
	public Camera()
	{
		location = new Point(0, 0, 0);
		direction = new Point(0, 0, 1);
		eye = new Point(0, 0, 5200 / 3);
		Point[] points = new Point[8];
		points[0] = new Point(640, 500, 0);
		points[1] = new Point(640, -500, 0);
		points[2] = new Point(-640, -500, 0);
		points[3] = new Point(-640, 500, 0);
		points[4] = new Point(1280, 1040, 5200);
		points[5] = new Point(1280, -1040, 5200);
		points[6]  = new Point(-1280, -1040, 5200);
		points[7] = new Point(-1280, 1040, 5200);
		frustum = new Frustum(points);
	}
	
	public void PrintInfo()
	{
		System.out.println("Frustum points are:");
		for(Point p : frustum.points)
		{
			System.out.println(p.GetExX() + " " + p.GetExY() + " " + p.GetExZ());
		}
		System.out.println("=============================");
		System.out.println("Location is at:");
		System.out.println(location.GetExX() + " " + location.GetExY() + " " + location.GetExZ());
		System.out.println("=============================");
		System.out.println("Eye is at:");
		System.out.println(eye.GetExX() + " " + eye.GetExY() + " " + eye.GetExZ());
		System.out.println("=============================");
		System.out.println("Direction is at:");
		System.out.println(direction.GetExX() + " " + direction.GetExY() + " " + direction.GetExZ());
		System.out.println("==========================================================");
	}
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		frustum.RotateCounterClockwiseAboutYAxis(p, degrees);
		direction.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateCounterClockwiseAboutYAxis(p, degrees);
		location.RotateCounterClockwiseAboutYAxis(p, degrees);
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		frustum.RotateClockwiseAboutYAxis(p, degrees);
		direction.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateClockwiseAboutYAxis(p, degrees);
		location.RotateClockwiseAboutYAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		frustum.RotateCounterClockwiseAboutXAxis(p, degrees);
		direction.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
		eye.RotateCounterClockwiseAboutXAxis(p, degrees);
		location.RotateCounterClockwiseAboutXAxis(p, degrees);
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		frustum.RotateClockwiseAboutXAxis(p, degrees);
		direction.RotateClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
		eye.RotateClockwiseAboutXAxis(p, degrees);
		location.RotateClockwiseAboutXAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		frustum.RotateCounterClockwiseAboutZAxis(p, degrees);
		direction.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
		eye.RotateCounterClockwiseAboutZAxis(p, degrees);
		location.RotateCounterClockwiseAboutZAxis(p, degrees);
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		frustum.RotateClockwiseAboutZAxis(p, degrees);
		direction.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
		eye.RotateClockwiseAboutZAxis(p, degrees);
		location.RotateClockwiseAboutZAxis(p, degrees);
	}
	public void MoveForward(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, direction, distance);
		Util.MovePointAlongVector(location, direction, distance);
		Util.MovePointAlongVector(eye, direction, distance);
	}
	public void MoveBackward(float distance)
	{
		Point newdir = new Point(-direction.GetExX(), -direction.GetExY(), -direction.GetExZ());
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, newdir, distance);
		Util.MovePointAlongVector(location, newdir, distance);
		Util.MovePointAlongVector(eye, newdir, distance);
	}
}
