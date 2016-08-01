class Point
{
	private double x;
	private double y;
	private double z;
	Point()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	Point(int fx, int fy, int fz)
	{
		x = (float) fx;
		y = (float) fy;
		z = (float) fz;
	}
	Point(double fx, double fy, double fz)
	{
		x = fx;
		y = fy;
		z = fz;
	}
	int GetX()
	{
		return (int) Math.round(x);
	}
	int GetY()
	{
		return (int) Math.round(y);
	}
	int GetZ()
	{
		return (int) Math.round(z);
	}
	double GetExX()
	{
		return x;
	}
	double GetExY()
	{
		return y;
	}
	double GetExZ()
	{
		return z;
	}
	void SetX(double newx)
	{
		x = newx;
	}
	void SetY(double newy)
	{
		y = newy;
	}
	void SetZ(double newz)
	{
		z = newz;
	}
	void RotateClockwiseAboutYAxis(float degrees)
	{
		double s = Math.sin(-degrees / 57.2958);
		double c = Math.cos(-degrees / 57.2958);
		double hx = z * s;
		hx = hx + (x * c);
		double hz = z * c;
		z = hz - (x * s);
		x = hx;
	}
	void RotateCounterClockwiseAboutYAxis(float degrees)
	{
		double s = Math.sin(degrees / 57.2958);
		double c = Math.cos(degrees / 57.2958);
		double hx = z * s;
		hx = hx + (x * c);
		double hz = z * c;
		z = hz - (x * s);
		x = hx;
	}
	void RotateClockwiseAboutXAxis(float degrees)
	{
		double s = Math.sin(-degrees / 57.2958);
		double c = Math.cos(-degrees / 57.2958);
		double hy = y * c;
		hy = hy - (z * s);
		double hz = y * s;
		z = hz + (z * c);
		y = hy;
	}
	void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		double s = Math.sin(degrees / 57.2958);
		double c = Math.cos(degrees / 57.2958);
		double hy = y * c;
		hy = hy - (z * s);
		double hz = y * s;
		z = hz + (z * c);
		y = hy;
	}
	void RotateClockwiseAboutZAxis(float degrees)
	{
		double s = Math.sin(-degrees / 57.2958);
		double c = Math.cos(-degrees / 57.2958);
		double hx = x * c;
		hx = hx - (y * s);
		double hy = x * s;
		y = hy + (y * c);
		x = hx;
	}
	void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		double s = Math.sin(degrees / 57.2958);
		double c = Math.cos(degrees / 57.2958);
		double hx = x * c;
		hx = hx - (y * s);
		double hy = x * s;
		y = hy + (y * c);
		x = hx;
	}
	public boolean SameLocation(Point p1, Point p2)
	{
		if((p1.GetX() == p2.GetX()) && (p1.GetY() == p2.GetY()) && (p1.GetZ() == p2.GetZ()))
			return true;
		return false;
	}
}
