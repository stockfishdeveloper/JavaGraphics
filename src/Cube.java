import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
class Cube
{
	Point[] points = new Point[8];
	Square[] squares = new Square[6];
	BufferedImage img;
	Pixel[][][] pixels;
        Color color;
	public Cube()
	{
		
	}
        public Cube(Point center, int radius, Color c)
	{
                color = c;
                pixels = null;
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
		squares[0] = new Square(points[0], points[1], points[3], points[2], c);
		squares[1] = new Square(points[5], points[4], points[6], points[7], c);
		squares[2] = new Square(points[4], points[5], points[1], points[0], c);
		squares[3] = new Square(points[2], points[3], points[7], points[6], c);
		squares[4] = new Square(points[0], points[2], points[6], points[4], c);
		squares[5] = new Square(points[1], points[5], points[7], points[3], c);
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
		pixels = new Pixel[6][img.getWidth() + 1][img.getHeight() + 1];
		double unitsperpixel = (double)((radius * 2) / (double)(img.getWidth()));
		//Front
		for(int i = 0; i < img.getWidth() + 1; i++)
		{
			for(int j = 0; j < img.getHeight() + 1; j++)
			{
				pixels[0][i][j] = new Pixel(((unitsperpixel * (i - (img.getWidth() / 2))) + center.GetExX()), ((unitsperpixel * (j - (img.getHeight() / 2))) + center.GetExY()), center.GetExZ() - radius, new Color(img.getRGB((i == img.getWidth() ? i - 1 : i), (j == img.getWidth() ? j - 1 : j))));
			}
		}
		//Back
		for(int i = 0; i < img.getWidth() + 1; i++)
		{
			for(int j = 0; j < img.getHeight() + 1; j++)
			{
				pixels[1][i][j] = new Pixel(((unitsperpixel * (i - (img.getWidth() / 2))) + center.GetExX()), ((unitsperpixel * (j - (img.getHeight() / 2))) + center.GetExY()), center.GetExZ() + radius, new Color(img.getRGB((i == img.getWidth() ? i - 1 : i), (j == img.getWidth() ? j - 1 : j))));
			}
		}
		//Top
		for(int i = 0; i < img.getWidth() + 1; i++)
		{
			for(int j = 0; j < img.getHeight() + 1; j++)
			{
				pixels[2][i][j] = new Pixel(((unitsperpixel * (i - (img.getWidth() / 2))) + center.GetExX()),  center.GetExY() + radius, ((unitsperpixel * (j - (img.getHeight() / 2))) + center.GetExZ()), new Color(img.getRGB((i == img.getWidth() ? i - 1 : i), (j == img.getWidth() ? j - 1 : j))));
			}
		}
		//Bottom
		for(int i = 0; i < img.getWidth() + 1; i++)
		{
			for(int j = 0; j < img.getHeight() + 1; j++)
			{
				pixels[3][i][j] = new Pixel(((unitsperpixel * (i - (img.getWidth() / 2))) + center.GetExX()),  center.GetExY() - radius, ((unitsperpixel * (j - (img.getHeight() / 2))) + center.GetExZ()), new Color(img.getRGB((i == img.getWidth() ? i - 1 : i), (j == img.getWidth() ? j - 1 : j))));
			}
		}
		//Left
		for(int i = 0; i < img.getWidth() + 1; i++)
		{
			for(int j = 0; j < img.getHeight() + 1; j++)
			{
				pixels[4][i][j] = new Pixel(center.GetExX() - radius, ((unitsperpixel *  (j - (img.getWidth() / 2))) + center.GetExY()), ((unitsperpixel * (i - (img.getHeight() / 2))) + center.GetExZ()), new Color(img.getRGB((i == img.getWidth() ? i - 1 : i), (j == img.getWidth() ? j - 1 : j))));
			}
		}
		//Right
		for(int i = 0; i < img.getWidth() + 1; i++)
		{
			for(int j = 0; j < img.getHeight() + 1; j++)
			{
				pixels[5][i][j] = new Pixel(center.GetExX() + radius, ((unitsperpixel * (j - (img.getWidth() / 2))) + center.GetExY()), ((unitsperpixel * (i - (img.getHeight() / 2))) + center.GetExZ()), new Color(img.getRGB((i == img.getWidth() ? i - 1 : i), (j == img.getWidth() ? j - 1 : j))));
			}
		}
		int inc = img.getWidth();
		squares[0] = new Square(points[0], points[1], points[3], points[2], pixels[0], inc);
		squares[1] = new Square(points[5], points[4], points[6], points[7], pixels[1], inc);
		squares[2] = new Square(points[4], points[5], points[1], points[0], pixels[2], inc);
		squares[3] = new Square(points[2], points[3], points[7], points[6], pixels[3], inc);
		squares[4] = new Square(points[0], points[2], points[6], points[4], pixels[4], inc);
		squares[5] = new Square(points[1], points[5], points[7], points[3], pixels[5], inc);
	}
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Square t : squares)
			t.RotateCounterClockwiseAboutYAxis(p, degrees);
        }
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		for(Square t : squares)
			t.RotateClockwiseAboutYAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Square t : squares)
			t.RotateCounterClockwiseAboutXAxis(p, degrees);
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		for(Square t : squares)
			t.RotateClockwiseAboutXAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Square t : squares)
			t.RotateCounterClockwiseAboutZAxis(p, degrees);
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		for(Square t : squares)
			t.RotateClockwiseAboutZAxis(p, degrees);
	}
	public void Resize(float scaleFactor)
	{
		for(Square t : squares)
			t.Resize(scaleFactor);
	}
	public Point GetCenter()
	{
		double totx = 0;
		double toty = 0;
		double totz = 0;
		for(Square t : squares)
		{
			totx += t.points[0].GetExX();
			totx += t.points[1].GetExX();
			totx += t.points[2].GetExX();
            totx += t.points[3].GetExX();
			toty += t.points[0].GetExY();
			toty += t.points[1].GetExY();
			toty += t.points[2].GetExY();
            toty += t.points[3].GetExY();
			totz += t.points[0].GetExZ();
			totz += t.points[1].GetExZ();
			totz += t.points[2].GetExZ();
            totz += t.points[3].GetExZ();
		}
		totx /= 24.0d;
		toty /= 24.0d;
		totz /= 24.0d;
		return new Point(totx, toty, totz);
	}
	public void Render(BufferedImage buf)
	{
            for(Square t : squares)
            {
                t.Render(buf);
            }
	}
    public void Draw_Mesh(BufferedImage buf)
    {
        for(Square t : squares)
        {
            t.Draw_Mesh(buf);
        }
    }
	public void Move(Point target, float distance)
	{
		Point center = GetCenter();
		Point orig = GetCenter();
		Util.MovePointTowardsAnotherPoint(center, target, distance);
		for(Square t : squares)
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
            t.points[3].SetX(t.points[3].GetExX() + (center.GetExX() - orig.GetExX()));
			t.points[3].SetY(t.points[3].GetExY() + (center.GetExY() - orig.GetExY()));
			t.points[3].SetZ(t.points[3].GetExZ() + (center.GetExZ() - orig.GetExZ()));
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
