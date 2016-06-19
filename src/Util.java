class Util
{
	public static double Distance_Between(Point p1, Point p2)
	{
		double dist_x = Math.abs(p1.GetExX() - p2.GetExX());
		double dist_y = Math.abs(p1.GetExY() - p2.GetExY());
		double dist_z = Math.abs(p1.GetExZ() - p2.GetExZ());
		double hypot_xy = Math.hypot(dist_x, dist_y);
		return Math.hypot(hypot_xy, dist_z);
	}
	public static Point GetNormalVector(Point p1, Point p2, Point p3)
	{
		double diff_x = p3.GetExX() - p2.GetExX();
		double diff_y = p3.GetExY() - p2.GetExY();
		double diff_z = p3.GetExZ() - p2.GetExZ();
		double diff_x1 = p1.GetExX() - p2.GetExX();
		double diff_y1 = p1.GetExY() - p2.GetExY();
		double diff_z1 = p1.GetExZ() - p2.GetExZ();
		Point orig = new Point(0, 0, 0);
		orig.SetX((diff_y * diff_z1) - (diff_y1 * diff_z));
		orig.SetY((diff_z * diff_x1) - (diff_z1 * diff_x));
		orig.SetZ((diff_x * diff_y1) - (diff_x1 * diff_y));
		NormalizeVector(orig);
		return orig;
	}
	public static void NormalizeVector(Point p)
	{
		double x = p.GetExX();
		double y = p.GetExY();
		double z = p.GetExZ();
		Point origin = new Point(0, 0, 0);
		double distance = Distance_Between(origin, p);
		p.SetX(x / distance);
		p.SetY(y / distance);
		p.SetZ(z / distance);
	}
	public static double GetDotProduct(Point p1, Point p2)
	{
		return ((p1.GetExX() * p2.GetExX()) + (p1.GetExY() * p2.GetExY()) + (p1.GetExZ() * p2.GetExZ()));
	}
}
