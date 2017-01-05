import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Logger;

class Util
{
	private static final int ATAN2_BITS = 7;
	private static final int ATAN2_BITS2 = ATAN2_BITS << 1;
	private static final int ATAN2_MASK = ~(-1 << ATAN2_BITS2);
	private static final int ATAN2_COUNT = ATAN2_MASK + 1;
	private static final int ATAN2_DIM = (int) Math.sqrt(ATAN2_COUNT);
    private static final float INV_ATAN2_DIM_MINUS_1 = 1.0f / (ATAN2_DIM - 1);
	private static final float DEG = 180.0f / (float) Math.PI;
    private static final float[] atan2 = new float[ATAN2_COUNT];
    static
    {
       for (int i = 0; i < ATAN2_DIM; i++)
       {
          for (int j = 0; j < ATAN2_DIM; j++)
          {
             float x0 = (float) i / ATAN2_DIM;
             float y0 = (float) j / ATAN2_DIM;

             atan2[j * ATAN2_DIM + i] = (float) Math.atan2(y0, x0);
          }
       }
    }
    
    private static final float RAD = (float) Math.PI / 180.0F;
    private static final int SIN_BITS = 12;
    private static final int SIN_MASK = ~(-1 << SIN_BITS);
    private static final int SIN_COUNT = SIN_MASK + 1;
    private static final float radFull = (float) (Math.PI * 2.0);
    private static final float degFull = (float) (360.0);
    private static final float radToIndex = SIN_COUNT / radFull;
    private static final float degToIndex = SIN_COUNT / degFull;
    private static final float[] sin = new float[SIN_COUNT];
    private static final float[] cos = new float[SIN_COUNT];
    static
    {
       for (int i = 0; i < SIN_COUNT; i++)
       {
          sin[i] = (float) Math.sin((float) (i + 0.5f) / SIN_COUNT * radFull);
          cos[i] = (float) Math.cos((float) (i + 0.5f) / SIN_COUNT * radFull);
       }
    }
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
	static void MovePointTowardsAnotherPoint(Point point, Point target, double distance)
	{
		double Distance = Distance_Between(point, target);
		double changes = distance / Distance;
		point.SetX(point.GetExX() + (changes * (target.GetExX() - point.GetExX())));
		point.SetY(point.GetExY() + (changes * (target.GetExY() - point.GetExY())));
		point.SetZ(point.GetExZ() + (changes * (target.GetExZ() - point.GetExZ())));
	}
	public static void MovePointAlongVector(Point point, Point direction, double distance)
	{
		Point normalized = new Point(direction);
		Util.NormalizeVector(normalized);
		double diffx = distance * normalized.GetExX();
		double diffy = distance * normalized.GetExY();
		double diffz = distance * normalized.GetExZ();
		point.SetX(point.GetExX() + diffx);
		point.SetY(point.GetExY() + diffy);
		point.SetZ(point.GetExZ() + diffz);
	}
	public static double Get_Angle_Between_Vectors(Point p1, Point p2, Point p3)
	{
		Point one = new Point(p1.GetExX(), p1.GetExY(), p1.GetExZ());
		Point two = new Point(p2.GetExX(), p2.GetExY(), p2.GetExZ());
		Point three = new Point(p3.GetExX(), p3.GetExY(), p3.GetExZ());
                double dist = Distance_Between(two, new Point(0, 0, 0));
                if(two.GetExX() == 0.0 && two.GetExY() == 0.0 && two.GetExY() == 0.0)
                    two = new Point(0.0000001, 0.0000001, 0.0000001);
                MovePointAlongVector(one, new Point(-two.GetExX(), -two.GetExY(), -two.GetExZ()), dist);
		MovePointAlongVector(three, new Point(-two.GetExX(), -two.GetExY(), -two.GetExZ()), dist);
		MovePointAlongVector(two, new Point(-two.GetExX(), -two.GetExY(), -two.GetExZ()), dist);
                NormalizeVector(one);
                NormalizeVector(three);
                double dot = GetDotProduct(one, three);
		double angle = Math.toDegrees(Math.acos(dot));
		return angle;
	}
	public static final float atan(float y, float x)
	   {
	      float add, mul;

	      if (x < 0.0f)
	      {
	         if (y < 0.0f)
	         {
	            x = -x;
	            y = -y;

	            mul = 1.0f;
	         }
	         else
	         {
	            x = -x;
	            mul = -1.0f;
	         }

	         add = -3.141592653f;
	      }
	      else
	      {
	         if (y < 0.0f)
	         {
	            y = -y;
	            mul = -1.0f;
	         }
	         else
	         {
	            mul = 1.0f;
	         }

	         add = 0.0f;
	      }

	      float invDiv = 1.0f / (((x < y) ? y : x) * INV_ATAN2_DIM_MINUS_1);

	      int xi = (int) (x * invDiv);
	      int yi = (int) (y * invDiv);

	      return (atan2[yi * ATAN2_DIM + xi] + add) * mul;
	   }
	public static final float sin(float deg)
	   {
	      return sin[(int) (deg * degToIndex) & SIN_MASK];
	   }

	public static final float cos(float deg)
	   {
	      return cos[(int) (deg * degToIndex) & SIN_MASK];
	   }

}
