class Point
{
	private double x;
	private double y;
	private double z;
	Point(int fx, int fy, int fz)
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
		double hx = z * (Math.sin(-degrees / 57.2958));
		hx = hx + (x * Math.cos(-degrees / 57.2958));
		double hz = z * (Math.cos(-degrees / 57.2958));
		z = hz - (x * Math.sin(-degrees / 57.2958));
		x = hx;
	}
	void RotateCounterClockwiseAboutYAxis(float degrees)
	{
		double hx = z * (Math.sin(degrees / 57.2958));
		hx = hx + (x * Math.cos(degrees / 57.2958));
		double hz = z * (Math.cos(degrees / 57.2958));
		z = hz - (x * Math.sin(degrees / 57.2958));
		x = hx;
	}
	void RotateClockwiseAboutXAxis(float degrees)
	{
		double hy = y * (Math.cos(-degrees / 57.2958));
		hy = hy - (z * Math.sin(-degrees / 57.2958));
		double hz = y * (Math.sin(-degrees / 57.2958));
		z = hz + (z * Math.cos(-degrees / 57.2958));
		y = hy;
	}
	void RotateCounterClockwiseAboutXAxis(float degrees)
	{
		double hy = y * (Math.cos(degrees / 57.2958));
		hy = hy - (z * Math.sin(degrees / 57.2958));
		double hz = y * (Math.sin(degrees / 57.2958));
		z = hz + (z * Math.cos(degrees / 57.2958));
		y = hy;
	}
	void RotateClockwiseAboutZAxis(float degrees)
	{
		double hx = x * (Math.cos(-degrees / 57.2958));
		hx = hx - (y * Math.sin(-degrees / 57.2958));
		double hy = x * (Math.sin(-degrees / 57.2958));
		y = hy + (y * Math.cos(-degrees / 57.2958));
		x = hx;
	}
	void RotateCounterClockwiseAboutZAxis(float degrees)
	{
		double hx = x * (Math.cos(degrees / 57.2958));
		hx = hx - (y * Math.sin(degrees / 57.2958));
		double hy = x * (Math.sin(degrees / 57.2958));
		y = hy + (y * Math.cos(degrees / 57.2958));
		x = hx;
	}
}
