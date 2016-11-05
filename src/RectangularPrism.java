import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
@SuppressWarnings("serial")
class RectangularPrism
{
	int currx = 350;
	int curry = 350;
	static Point origin = new Point(350, 350, 0);
	public int xoffset = 640;
	public int yoffset = 500;
	boolean isSelected = false;
	private Rectangle BoundingBox = new Rectangle();
	Point[] points = new Point[8];
	Triangle[] triangles = new Triangle[12];
	public RectangularPrism()
	{
		
	}
	public RectangularPrism(Point center, int length, int width, int height)
	{
		for(int i = 0; i < 8; i++) points[i] = new Point(0, 0, 0);
		points[0].SetX(center.GetExX() - (length / 2));
		points[0].SetY(center.GetExY() + (height / 2));
		points[0].SetZ(center.GetExZ() - (width / 2));
		points[1].SetX(center.GetExX() + (length / 2));
		points[1].SetY(center.GetExY() + (height / 2));
		points[1].SetZ(center.GetExZ() - (width / 2));
		points[2].SetX(center.GetExX() - (length / 2));
		points[2].SetY(center.GetExY() - (height / 2));
		points[2].SetZ(center.GetExZ() - (width / 2));
		points[3].SetX(center.GetExX() + (length / 2));
		points[3].SetY(center.GetExY() - (height / 2));
		points[3].SetZ(center.GetExZ() - (width / 2));
		points[4].SetX(center.GetExX() - (length / 2));
		points[4].SetY(center.GetExY() + (height / 2));
		points[4].SetZ(center.GetExZ() + (width / 2));
		points[5].SetX(center.GetExX() + (length / 2));
		points[5].SetY(center.GetExY() + (height / 2));
		points[5].SetZ(center.GetExZ() + (width / 2));
		points[6].SetX(center.GetExX() - (length / 2));
		points[6].SetY(center.GetExY() - (height / 2));
		points[6].SetZ(center.GetExZ() + (width / 2));
		points[7].SetX(center.GetExX() + (length / 2));
		points[7].SetY(center.GetExY() - (height / 2));
		points[7].SetZ(center.GetExZ() + (width / 2));
		triangles[0] = new Triangle(points[0], points[1], points[2]);
		triangles[1] = new Triangle(points[1], points[3], points[2]);
		triangles[2] = new Triangle(points[5], points[4], points[6]);
		triangles[3] = new Triangle(points[5], points[6], points[7]);
		triangles[4] = new Triangle(points[4], points[1], points[0]);
		triangles[5] = new Triangle(points[4], points[5], points[1]);
		triangles[6] = new Triangle(points[2], points[3], points[7]);
		triangles[7] = new Triangle(points[2], points[7], points[6]);
		triangles[8] = new Triangle(points[4], points[0], points[2]);
		triangles[9] = new Triangle(points[4], points[2], points[6]);
		triangles[10] = new Triangle(points[1], points[5], points[7]);
		triangles[11] = new Triangle(points[1], points[7], points[3]);
	}
	public void UpdateBoundingBox()
	{
		int gx = -10000;
		int ly = 10000;
		int lx = 10000;
		int gy = -10000;
		for(Triangle t : triangles)
		{
			if(t.GreatestX() > gx) gx = t.GreatestX();
			if(t.LeastX() < lx) lx = t.LeastX();
			if(t.GreatestY() > gy) gy = t.GreatestY();
			if(t.LeastY() < ly) ly = t.LeastY();
		}
		BoundingBox = new Rectangle();
		BoundingBox.add(gx + xoffset, gy + yoffset);
		BoundingBox.add(gx + xoffset, ly + yoffset);
		BoundingBox.add(lx + xoffset, ly + yoffset);
		BoundingBox.add(lx + xoffset, gy + yoffset);
		
	}
	public void TranslateVisiblePosition(int x, int y)
	{
		xoffset += x;
		yoffset += y;
		UpdateBoundingBox();
		
	}
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutYAxis(p, degrees);
		UpdateBoundingBox();
		
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutYAxis(p, degrees);
		UpdateBoundingBox();
		
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutXAxis(p, degrees);
		UpdateBoundingBox();
		
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutXAxis(p, degrees);
		UpdateBoundingBox();
		
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutZAxis(p, degrees);
		UpdateBoundingBox();
		
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutZAxis(p, degrees);
		UpdateBoundingBox();
		
	}
	public void Resize(float scaleFactor)
	{
		for(Triangle t : triangles)
			t.Resize(scaleFactor);
		UpdateBoundingBox();
		
	}
	public Point GetCenter()
	{
		double totx = 0;
		double toty = 0;
		double totz = 0;
		for(Triangle t : triangles)
		{
			totx += t.points[0].GetExX();
			totx += t.points[1].GetExX();
			totx += t.points[2].GetExX();
			toty += t.points[0].GetExY();
			toty += t.points[1].GetExY();
			toty += t.points[2].GetExY();
			totz += t.points[0].GetExZ();
			totz += t.points[1].GetExZ();
			totz += t.points[2].GetExZ();
		}
		totx /= 36.0d;
		toty /= 36.0d;
		totz /= 36.0d;
		return new Point(totx, toty, totz);
	}
	public void DrawOutline(Graphics2D g)
	{
		if(triangles[0].Should_Be_Drawn() && (!triangles[4].Should_Be_Drawn()) || !triangles[0].Should_Be_Drawn() && (triangles[4].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[0], triangles[4]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[1].Should_Be_Drawn() && (!triangles[11].Should_Be_Drawn()) || !triangles[1].Should_Be_Drawn() && (triangles[11].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[1], triangles[11]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[1].Should_Be_Drawn() && (!triangles[6].Should_Be_Drawn()) || !triangles[1].Should_Be_Drawn() && (triangles[6].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[1], triangles[6]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[0].Should_Be_Drawn() && (!triangles[8].Should_Be_Drawn()) || !triangles[0].Should_Be_Drawn() && (triangles[8].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[0], triangles[8]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[2].Should_Be_Drawn() && (!triangles[5].Should_Be_Drawn()) || !triangles[2].Should_Be_Drawn() && (triangles[5].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[2], triangles[5]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[3].Should_Be_Drawn() && (!triangles[10].Should_Be_Drawn()) || !triangles[3].Should_Be_Drawn() && (triangles[10].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[3], triangles[10]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[3].Should_Be_Drawn() && (!triangles[7].Should_Be_Drawn()) || !triangles[3].Should_Be_Drawn() && (triangles[7].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[3], triangles[7]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[2].Should_Be_Drawn() && (!triangles[9].Should_Be_Drawn()) || !triangles[2].Should_Be_Drawn() && (triangles[9].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[2], triangles[9]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[5].Should_Be_Drawn() && (!triangles[10].Should_Be_Drawn()) || !triangles[5].Should_Be_Drawn() && (triangles[10].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[5], triangles[10]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[4].Should_Be_Drawn() && (!triangles[8].Should_Be_Drawn()) || !triangles[4].Should_Be_Drawn() && (triangles[8].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[4], triangles[8]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[6].Should_Be_Drawn() && (!triangles[11].Should_Be_Drawn()) || !triangles[6].Should_Be_Drawn() && (triangles[11].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[6], triangles[11]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
		if(triangles[7].Should_Be_Drawn() && (!triangles[9].Should_Be_Drawn()) || !triangles[7].Should_Be_Drawn() && (triangles[9].Should_Be_Drawn()))
		{
			int[] coords = new int[4];
			coords = Util.GetSharedSide(triangles[7], triangles[9]);
			g.drawLine(coords[0] + xoffset, coords[1] + yoffset, coords[2] + xoffset, coords[3] + yoffset);
		}
	}
	public void Render()
	{
		Color color = Color.blue;
		boolean blue = true;
		for(Triangle t : triangles)
		{
			if(t.Should_Be_Drawn())
			{
				Triangle triangle = World.camera.LookAt(t);
				if(triangle != null)
				{
					blue = !blue;
					color = blue ? Color.blue : Color.green;
					triangle.SetColor(color);
					World.triangles.add(triangle);
				}
			}
		}
	}
	public void Move(Point target, float distance)
	{
		Point center = GetCenter();
		Point orig = GetCenter();
		Util.MovePointTowardsAnotherPoint(center, target, distance);
		for(Triangle t : triangles)
		{
			t.points[0].SetX(t.points[0].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[0].SetY(t.points[0].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[0].SetZ(t.points[0].GetExZ() + (center.GetExZ() - orig.GetExZ()));
			t.points[1].SetX(t.points[1].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[1].SetY(t.points[1].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[1].SetZ(t.points[1].GetExZ() + (center.GetExZ() - orig.GetExZ()));
			t.points[2].SetX(t.points[2].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[2].SetY(t.points[2].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[2].SetZ(t.points[2].GetExZ() + (center.GetExZ() - orig.GetExZ()));
		}
		UpdateBoundingBox();
		
	}
}
