class Point
{
	protected double x;
	protected double y;
	protected double z;
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
	Point(Point other)
	{
		this.x = other.GetExX();
		this.y = other.GetExY();
		this.z = other.GetExZ();
	}
        public void Print_Info()
        {
        	System.out.println("Point:\n=============================");
            System.out.println(x + " " + y + " " + z);
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
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		double changex = -p.GetExX();
		double changez = -p.GetExZ();
		double s = Util.sin(degrees);
		double c = Util.cos(degrees);
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
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		double changex = -p.GetExX();
		double changez = -p.GetExZ();
		double s = Util.sin(-degrees);
		double c = Util.cos(-degrees);
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
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		double changey = -p.GetExY();
		double changez = -p.GetExZ();
		double s = Util.sin(degrees);
		double c = Util.cos(degrees);
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
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		double changey = -p.GetExY();
		double changez = -p.GetExZ();
		double s = Util.sin(-degrees);
		double c = Util.cos(-degrees);
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
		double changex = -p.GetExX();
		double changey = -p.GetExY();
		double s = Util.sin(-degrees);
		double c = Util.cos(-degrees);
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
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		double changex = -p.GetExX();
		double changey = -p.GetExY();
		double s = Util.sin(degrees);
		double c = Util.cos(degrees);
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
