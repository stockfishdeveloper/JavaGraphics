import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
class RectangularPrism
{
	public int xoffset = 640;
	public int yoffset = 500;
	Point[] points = new Point[8];
	Triangle[] triangles = new Triangle[12];
	BufferedImage img;
	Pixel[][][] pixels;
	public RectangularPrism()
	{
		
	}
	public RectangularPrism(Point center, int length, int width, int height, String texture)
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
		try {
            img = ImageIO.read(new File(texture));
        } catch (IOException ex) {
         		}
		pixels = new Pixel[6][img.getWidth()][img.getHeight()];
		double unitsperpixel = (double)((length * 2) / (double)(img.getWidth()));
		//Front
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[0][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))), (unitsperpixel * (j - (img.getHeight() / 2))), center.GetExZ() - length, new Color(img.getRGB(i, j)));
			}
		}
		//Back
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[1][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))), (unitsperpixel * (j - (img.getHeight() / 2))), center.GetExZ() + length, new Color(img.getRGB(i, j)));
			}
		}
		//Top
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[2][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))),  center.GetExY() + length, (unitsperpixel * (j - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		//Bottom
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[3][i][j] = new Pixel((unitsperpixel * (i - (img.getWidth() / 2))),  center.GetExY() - length, (unitsperpixel * (j - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		//Left
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[4][i][j] = new Pixel(center.GetExX() - length, (unitsperpixel *  (j - (img.getWidth() / 2))), (unitsperpixel * (i - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		//Right
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				pixels[5][i][j] = new Pixel(center.GetExX() + length, (unitsperpixel * (j - (img.getWidth() / 2))), (unitsperpixel * (i - (img.getHeight() / 2))), new Color(img.getRGB(i, j)));
			}
		}
		ArrayList<Pixel> pix = new ArrayList<>();
		ArrayList<Pixel> pix2 = new ArrayList<>();
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
		totx /= 36.0d;
		toty /= 36.0d;
		totz /= 36.0d;
		return new Point(totx, toty, totz);
	}
	public void Render(BufferedImage buf)
	{
            for(Triangle t : triangles)
            {
                t.Render(buf);
            }
	}
        public void Draw_Mesh(BufferedImage buf)
	{
            for(Triangle t : triangles)
            {
                t.Draw_Mesh(buf);
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
