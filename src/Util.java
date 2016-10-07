class Util
{
	public static double Distance_Between(Point p1, Point p2)
	{
		return (Math.sqrt(((p2.GetExX() - p1.GetExX()) * (p2.GetExX() - p1.GetExX())) + ((p2.GetExY() - p1.GetExY()) * (p2.GetExY() - p1.GetExY())) + (p2.GetExZ() - p1.GetExZ()) * (p2.GetExZ() - p1.GetExZ())));
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
	public static double RadiansToDegrees(double radians)
	{
		return Math.toDegrees(radians);
	}
	static int[] GetSharedSide(Triangle t1, Triangle t2)
	{
		int[] coords = new int[4];
		int count = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(t1.points[0].SameLocation(t1.points[i], t2.points[j]))
				{
					coords[count++] = t1.points[i].GetX();
					coords[count++] = t2.points[j].GetY();
				}
			}
		}
		return coords;
	}
	static void MovePointTowardsAnotherPoint(Point point, Point target, float distance)
	{
		double Distance = Distance_Between(point, target);
		double changes = distance / Distance;
		point.SetX(point.GetExX() + (changes * (target.GetExX() - point.GetExX())));
		point.SetY(point.GetExY() + (changes * (target.GetExY() - point.GetExY())));
		point.SetZ(point.GetExZ() + (changes * (target.GetExZ() - point.GetExZ())));
	}
	public static void MovePointAlongVector(Point point, Point direction, float distance)
	{
		Point normalized = direction;
		Util.NormalizeVector(normalized);
		double diffx = distance * normalized.GetExX();
		double diffy = distance * normalized.GetExY();
		double diffz = distance * normalized.GetExZ();
		point.SetX(point.GetExX() + diffx);
		point.SetY(point.GetExY() + diffy);
		point.SetZ(point.GetExZ() + diffz);
	}
}
