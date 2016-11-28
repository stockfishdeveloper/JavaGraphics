import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
class Cube
{
	public int xoffset = 640;
	public int yoffset = 500;
	boolean isSelected = false;
	private Rectangle BoundingBox = new Rectangle();
	Point[] points = new Point[8];
	Triangle[] triangles = new Triangle[12];
	BufferedImage img;
	Pixel[][][] pixels;
	public Cube()
	{
		
	}
	public Cube(Point center, int radius, String texture)
	{
		for(int i = 0; i < 8; i++) points[i] = new Point(0, 0, 0);
		points[0].SetX(center.GetExX() - radius);
		points[0].SetY(center.GetExY() + radius);
		points[0].SetZ(center.GetExZ() - radius);
		points[1].SetX(center.GetExX() + radius);
		points[1].SetY(center.GetExY() + radius);
		points[1].SetZ(center.GetExZ() - radius);
		points[2].SetX(center.GetExX() - radius);
		points[2].SetY(center.GetExY() - radius);
		points[2].SetZ(center.GetExZ() - radius);
		points[3].SetX(center.GetExX() + radius);
		points[3].SetY(center.GetExY() - radius);
		points[3].SetZ(center.GetExZ() - radius);
		points[4].SetX(center.GetExX() - radius);
		points[4].SetY(center.GetExY() + radius);
		points[4].SetZ(center.GetExZ() + radius);
		points[5].SetX(center.GetExX() + radius);
		points[5].SetY(center.GetExY() + radius);
		points[5].SetZ(center.GetExZ() + radius);
		points[6].SetX(center.GetExX() - radius);
		points[6].SetY(center.GetExY() - radius);
		points[6].SetZ(center.GetExZ() + radius);
		points[7].SetX(center.GetExX() + radius);
		points[7].SetY(center.GetExY() - radius);
		points[7].SetZ(center.GetExZ() + radius);
		/*triangles[0] = new Triangle(points[0], points[1], points[2]);
		triangles[1] = new Triangle(points[1], points[3], points[2]);
		triangles[2] = new Triangle(points[5], points[4], points[7]);
		triangles[3] = new Triangle(points[4], points[6], points[7]);
		triangles[4] = new Triangle(points[4], points[5], points[0]);
		triangles[5] = new Triangle(points[5], points[1], points[0]);
		triangles[6] = new Triangle(points[2], points[3], points[6]);
		triangles[7] = new Triangle(points[3], points[7], points[6]);
		triangles[8] = new Triangle(points[4], points[0], points[6]);
		triangles[9] = new Triangle(points[0], points[2], points[6]);
		triangles[10] = new Triangle(points[1], points[5], points[3]);
		triangles[11] = new Triangle(points[5], points[7], points[3]);*/
		try {
            img = ImageIO.read(new File(texture));
        } catch (IOException ex) {
         		}
		pixels = new Pixel[6][img.getWidth()][img.getHeight()];
		double unitsperpixel = (double)((radius * 2) / (double)(img.getWidth()));
		//Front
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[0][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))), (unitsperpixel * (j - (img.getHeight() / 2))), center.GetExZ() - radius, new Color(img.getRGB(i, j)));
			}
		}
		//Back
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[1][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))), (unitsperpixel * (j - (img.getHeight() / 2))), center.GetExZ() + radius, new Color(img.getRGB(i, j)));
			}
		}
		//Top
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[2][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))),  center.GetExY() + radius, (unitsperpixel * (j - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		//Bottom
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[3][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))),  center.GetExY() - radius, (unitsperpixel * (j - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		//Left
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[4][i][j] = new Pixel(center.GetExX() - radius, (unitsperpixel *  (j - (img.getWidth() / 2))), (unitsperpixel * (i - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		//Right
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[5][i][j] = new Pixel(center.GetExX() + radius, (unitsperpixel * (j - (img.getWidth() / 2))), (unitsperpixel * (i - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		ArrayList<Pixel> pix = new ArrayList<Pixel>();
		ArrayList<Pixel> pix2 = new ArrayList<Pixel>();
		int inc = img.getWidth();
		for(int p = 0; p < 6; p++)
		{
			for(int i = 0; i < img.getWidth(); i++)
			{
				for(int j = 0; j < img.getHeight(); j++)
				{
					if(j < inc)
					{
						pix.add(pixels[p][i][j]);
					}
					else
					{
						pix2.add(pixels[p][i][j]);
					}
				}
				inc--;
			}
			if(p == 0)
			{
				triangles[0] = new Triangle(points[0], points[1], points[2], pix);
				triangles[1] = new Triangle(points[1], points[3], points[2], pix2);
			}
			else if(p == 1)
			{
				triangles[2] = new Triangle(points[5], points[4], points[7], pix);
				triangles[3] = new Triangle(points[4], points[6], points[7], pix2);
			}
			else if(p == 2)
			{
				triangles[4] = new Triangle(points[4], points[5], points[0], pix);
				triangles[5] = new Triangle(points[5], points[1], points[0], pix2);
			}
			else if(p == 3)
			{
				triangles[6] = new Triangle(points[2], points[3], points[6], pix);
				triangles[7] = new Triangle(points[3], points[7], points[6], pix2);
			}
			else if(p == 4)
			{
				triangles[8] = new Triangle(points[4], points[0], points[6], pix);
				triangles[9] = new Triangle(points[0], points[2], points[6], pix2);
			}
			else if(p == 5)
			{
				triangles[10] = new Triangle(points[1], points[5], points[3], pix);
				triangles[11] = new Triangle(points[5], points[7], points[3], pix2);
			}
			inc = img.getWidth();
			pix.clear();
			pix2.clear();
		}
		//UpdateBoundingBox();
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
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[h][i][j].RotateCounterClockwiseAboutYAxis(p, degrees);
			}
		}*/
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutYAxis(p, degrees);
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[h][i][j].RotateClockwiseAboutYAxis(p, degrees);
			}
		}*/
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutXAxis(p, degrees);
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[h][i][j].RotateCounterClockwiseAboutXAxis(p, degrees);
			}
		}*/
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutXAxis(p, degrees);
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[h][i][j].RotateClockwiseAboutXAxis(p, degrees);
			}
		}*/
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateCounterClockwiseAboutZAxis(p, degrees);
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[h][i][j].RotateCounterClockwiseAboutZAxis(p, degrees);
			}
		}*/
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Triangle t : triangles)
			t.RotateClockwiseAboutZAxis(p, degrees);
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[h][i][j].RotateClockwiseAboutZAxis(p, degrees);
			}
		}*/
	}
	public void Resize(float scaleFactor)
	{
		for(Triangle t : triangles)
			t.Resize(scaleFactor);
		//UpdateBoundingBox();
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
	public void Render(BufferedImage buf)
	{
		/*for(int h = 0; h < 6; h++)
		for(int i = 0; i < img.getWidth(); i++)
	    {
			for(int j = 0; j < img.getHeight(); j++)
				{
					Point p = new Point(pixels[h][i][j].GetExX(), pixels[h][i][j].GetExY(), pixels[h][i][j].GetExZ()); 
					p = World.camera.LookAt(p);
					if(p.GetX() >= 0 && p.GetX() < 1280 && p.GetY() >= 0 && p.GetY() < 1000)
						buf.setRGB(p.GetX() + 100, p.GetY() + 100, pixels[h][i][j].GetColor());
				}
	    }*/
		for(Triangle t : triangles)
		{
			t.Render(buf);
		}
	}
	public void AnimateSmoothly(int x, int y, int milliseconds) throws InterruptedException
	{
		float frame = (float) (milliseconds / 150.0);
		float changex = x / frame;
		float changey = y / frame;
		for(int i = 0; i < milliseconds; i += frame)
		{
			TranslateVisiblePosition((int) changex, (int) changey);
			Thread.sleep((long) frame);
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
	public void SetTexture(String file)
	{
		try {
            img = ImageIO.read(new File(file));
        } catch (IOException ex) {
         		}

	}
}
