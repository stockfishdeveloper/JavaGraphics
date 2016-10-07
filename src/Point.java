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
	void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		double changex = p.GetExX() > 0 ? -p.GetExX() : p.GetExX();
		double changez = p.GetExZ() > 0 ? -p.GetExZ() : p.GetExZ();
		double s = Math.sin(Math.toRadians(degrees));
		double c = Math.cos(Math.toRadians(degrees));
		x += changex;
		z += changez;
		double hx = z * s;
		hx = hx + (x * c);
		double hz = z * c;
		z = hz - (x * s);
		x = hx;
		x -= changex;
		z -= changez;
	}
	void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		double changex = p.GetExX() > 0 ? -p.GetExX() : p.GetExX();
		double changez = p.GetExZ() > 0 ? -p.GetExZ() : p.GetExZ();
		double s = Math.sin(-Math.toRadians(degrees));
		double c = Math.cos(-Math.toRadians(degrees));
		x += changex;
		z += changez;
		double hx = z * s;
		hx = hx + (x * c);
		double hz = z * c;
		z = hz - (x * s);
		x = hx;
		x -= changex;
		z -= changez;
	}
	void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		double changey = p.GetExY() > 0 ? -p.GetExY() : p.GetExY();
		double changez = p.GetExZ() > 0 ? -p.GetExZ() : p.GetExZ();
		double s = Math.sin(Math.toRadians(degrees));
		double c = Math.cos(Math.toRadians(degrees));
		y += changey;
		z += changez;
		double hy = y * c;
		hy = hy - (z * s);
		double hz = y * s;
		z = hz + (z * c);
		y = hy;
		y -= changey;
		z -= changez;
	}
	void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		double changey = p.GetExY() > 0 ? -p.GetExY() : p.GetExY();
		double changez = p.GetExZ() > 0 ? -p.GetExZ() : p.GetExZ();
		double s = Math.sin(-Math.toRadians(degrees));
		double c = Math.cos(-Math.toRadians(degrees));
		y += changey;
		z += changez;
		double hy = y * c;
		hy = hy - (z * s);
		double hz = y * s;
		z = hz + (z * c);
		y = hy;
		y -= changey;
		z -= changez;
	}
	void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		double changex = p.GetExX() > 0 ? -p.GetExX() : p.GetExX();
		double changey = p.GetExY() > 0 ? -p.GetExY() : p.GetExY();
		double s = Math.sin(-Math.toRadians(degrees));
		double c = Math.cos(-Math.toRadians(degrees));
		x += changex;
		y += changey;
		double hx = x * c;
		hx = hx - (y * s);
		double hy = x * s;
		y = hy + (y * c);
		x = hx;
		x -= changex;
		y -= changey;
	}
	void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		double changex = p.GetExX() > 0 ? -p.GetExX() : p.GetExX();
		double changey = p.GetExY() > 0 ? -p.GetExY() : p.GetExY();
		double s = Math.sin(Math.toRadians(degrees));
		double c = Math.cos(Math.toRadians(degrees));
		x += changex;
		y += changey;
		double hx = x * c;
		hx = hx - (y * s);
		double hy = x * s;
		y = hy + (y * c);
		x = hx;
		x -= changex;
		y -= changey;
	}
	public boolean SameLocation(Point p1, Point p2)
	{
		if((p1.GetX() == p2.GetX()) && (p1.GetY() == p2.GetY()) && (p1.GetZ() == p2.GetZ()))
			return true;
		return false;
	}
}
