import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class SampleMesh
{
	public ArrayList<Triangle> triangles;
	public Color color;
	public SampleMesh(Point center, Color c)
	{
		color = c;
		triangles = new ArrayList<>();
		Point[][] points = new Point[100][100];
		int[] elevation = new int[10000];
		for(int i = 0; i < 10000; i++)
		{
			elevation[i] = center.GetY() + (int)(Math.random() * 3);
		}
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				points[i][j] = new Point(((i - 50) * 1) + center.GetExX(), elevation[i * j], (((50 - j) * 1) + center.GetExZ()));
			}
		}
		java.util.Random rand = new java.util.Random(0);
		float r;
		float g;
		float b;
		for(int j = 0; j < 99; j++)
		{
			for(int i = 0; i < 99; i++)
			{
                                r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
				triangles.add(new Triangle(points[i][j], points[i + 1][j + 1], points[i][j + 1], new Color(r, g, b)));
                                r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
                                triangles.add(new Triangle(points[i][j], points[i + 1][j + 1], points[i][j + 1], new Color(r, g, b)));
                                r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
                                triangles.add(new Triangle(points[i][j], points[i + 1][j], points[i + 1][j + 1], new Color(r, g, b)));
                                r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
                                triangles.add(new Triangle(points[i][j], points[i + 1][j], points[i + 1][j + 1], new Color(r, g, b)));
                        }
		}
        }
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutYAxis(p, degrees);
        }
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutYAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutXAxis(p, degrees);
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutXAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutZAxis(p, degrees);
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutZAxis(p, degrees);
	}
	public void Draw_Mesh(BufferedImage buf)
    {
        for(int i = 0; i < triangles.size(); i++)
        {
            triangles.get(i).Draw_Mesh(buf);
        }
    }
	public void Render(BufferedImage buf)
	{
            for(int i = 0; i < triangles.size(); i+=2)
            {
            	triangles.get(i).Render(buf);
            }
	}
}
