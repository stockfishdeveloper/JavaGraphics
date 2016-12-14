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
		Point[][] points = new Point[50][50];
		int[] elevation = new int[2500];
		for(int i = 0; i < 2500; i++)
		{
			elevation[i] = center.GetY() + (int)(Math.random() * 5);
		}
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 50; j++)
			{
				points[i][j] = new Point(((i - 25) * 1) + center.GetExX(), elevation[i * j], (((j - 25) * 1) + center.GetExZ()));
			}
		}
		for(int i = 0; i < 49; i++)
		{
			for(int j = 0; j < 49; j++)
			{
				triangles.add(new Triangle(points[i][j], points[i + 1][j + 1], points[i + 1][j], color));
				triangles.add(new Triangle(points[i][j], points[i][j + 1], points[i + 1][j + 1], color));
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
