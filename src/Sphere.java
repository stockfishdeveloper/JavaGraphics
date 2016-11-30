import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Sphere
{
	public int xoffset = 640;
	public int yoffset = 500;
	Point[] points;
	Triangle[] triangles;
	BufferedImage img;
	Pixel[][][] pixels;
        Color color;
	public Sphere()
	{
		
	}
        public Sphere(int layers, int ppl, Color c)
	{
                color = c;
                pixels = null;
		int Layers = layers * 2;
		int PPL = ppl;
		triangles = new Triangle[(2 * layers * ( 2 * layers + ppl)) + (2 * ppl)];
		for(int i = 0; i < triangles.length; i++) triangles[i] = new Triangle(new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0), c);
		int count = 0;
		double[] semicircle = new double[layers * 2];
		for(int i = -layers; i < layers; i++)
		{
                    semicircle[count++] = Math.sqrt((double)((layers * layers) - (i * i)));
		}
		count = 0;
		int scount = 0;
		points = new Point[(layers * 2 * ppl) + 2];
		for(int i = 0; i < points.length; i++) points[i] = new Point(0, 0, 0);
		points[count++] = new Point(0, layers / 2, 0);
		for(int i = layers; i > -layers; i--)
		{
                    for(int j = 0; j < ppl; j++)
                    {
			points[count].SetY(i);
			float degrees = (float)(j) * (360.0f / (float)(ppl));
			Point p1 = new Point(semicircle[scount], 0, 0);
			p1.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
			points[count].SetX(p1.GetExX());
			points[count++].SetZ(p1.GetExZ());
                    }
                    scount++;
		}
		points[count++] = new Point(0, -(layers / 2), 0);
		//count = 0;
		int count2 = 0;
		for(int i = 0; i < ((2 * layers) - 2); i++)
		{
                    for(int j = 0; j < ppl; j++)
                    {
			int[] x = new int[3];
			int[] y = new int[3];
			int[] z = new int[3];
			x[0] = points[(ppl * i) + j].GetX();
			x[1] = points[(ppl * i) + j + 1].GetX();
			x[2] = points[(ppl * i) + j + ppl + 1].GetX();
			y[0] = points[(ppl * i) + j].GetY();
			y[1] = points[(ppl * i) + j + 1].GetY();
			y[2] = points[(ppl * i) + j + ppl + 1].GetY();
			z[0] = points[(ppl * i) + j].GetZ();
			z[1] = points[(ppl * i) + j + 1].GetZ();
			z[2] = points[(ppl * i) + j + ppl + 1].GetZ();
			triangles[count2++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]), c);
			if(i > 0)
			{
                            x[0] = points[(ppl * i) + j].GetX();
                            x[1] = points[((ppl * i) + j) - ppl].GetX();
                            x[2] = points[(ppl * i) + j + 1].GetX();
                            y[0] = points[(ppl * i) + j].GetY();
                            y[1] = points[((ppl * i) + j) - ppl].GetY();
                            y[2] = points[(ppl * i) + j + 1].GetY();
                            z[0] = points[(ppl * i) + j].GetZ();
                            z[1] = points[((ppl * i) + j) - ppl].GetZ();
                            z[2] = points[(ppl * i) + j + 1].GetZ();
                            triangles[count2++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]), c);
			}
                    }
		}
		count2 = 0;
		for(int j = 1; j < ppl; j++)
		{
                    int[] x = new int[3];
                    int[] y = new int[3];
                    int[] z = new int[3];
                    x[0] = points[0].GetX();
                    x[1] = points[j].GetX();
                    x[2] = points[j + 1].GetX();
                    y[0] = points[0].GetY();
                    y[1] = points[j].GetY();
                    y[2] = points[j + 1].GetY();
                    z[0] = points[0].GetZ();
                    z[1] = points[j].GetZ();
                    z[2] = points[j + 1].GetZ();
                    triangles[count2++] = new Triangle(new Point(x[0], y[0], z[0]), new Point(x[1], y[1], z[1]), new Point(x[2], y[2], z[2]), c);
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
	public void Resize(float scaleFactor)
	{
		for(Triangle t : triangles)
			t.Resize(scaleFactor);
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
		totx /= 3 * triangles.length;
		toty /= 3 * triangles.length;
		totz /= 3 * triangles.length;
		return new Point(totx, toty, totz);
	}
	public void Render(BufferedImage buf)
	{
            for(Triangle t : triangles)
            {
                t.Render(buf);
            }
	}
        /*public void Render(Graphics2D g)
	{
            for(Triangle t : triangles)
            {
                t.Render(g);
            }
	}*/
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
	}
	public void SetTexture(String file)
	{
            try
            {
                img = ImageIO.read(new File(file));
            } 
            catch (IOException ex) {}
        }
}
