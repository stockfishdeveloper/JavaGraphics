import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

class Sphere {
	public int xoffset = 640;
	public int yoffset = 500;
	Point[] points;
	ArrayList<Triangle> triangles;
	BufferedImage img;
	Pixel[][][] pixels;
	Color color;

	public Sphere() {

	}

	public Sphere(int layers, int ppl, Color c) {
		triangles = new ArrayList<>();
		points = new Point[(((layers - 2) * ppl) + 2)];
		points[0] = new Point(0, (double) (layers) / 2, 0);
		points[points.length - 1] = new Point(0, -(double) (layers) / 2, 0);
		Point[] semicircle = new Point[layers];
		semicircle[0] = new Point(0, ((double) (layers)) / 2, 0);
		float degreesperiter = 180.0f / ((float) (layers - 1));
		for (int i = 1; i < layers - 1; i++) {
			semicircle[i] = new Point(semicircle[0]);
			semicircle[i].RotateClockwiseAboutZAxis(new Point(0, 0, 0), i * degreesperiter);
		}
		semicircle[layers - 1] = new Point(semicircle[0]);
		semicircle[layers - 1].RotateClockwiseAboutZAxis(new Point(0, 0, 0), 180);
		degreesperiter = (360.0f) / (float) (ppl);
		int count = 1;
		for (int i = 0; i < layers - 2; i++) {
			for (int j = 0; j < ppl; j++) {
				points[count] = new Point(semicircle[i + 1]);
				points[count++].RotateCounterClockwiseAboutYAxis(new Point(0, semicircle[i + 1].GetExY(), 0),
						degreesperiter);
			}
		}
		System.out.println(points[4].GetY());
		count = 0;
		for (int i = 0; i < ppl; i++) {
			Point p1 = new Point(points[0]);
			Point p2 = new Point(points[i + 2]);
			Point p3 = new Point(points[i + 1]);
			triangles.add(new Triangle(p1, p2, p3, Color.blue));
		}
		for (int i = ppl; i < (points.length - 1) - ppl; i++) {
			Point p1 = new Point(points[i]);
			Point p2 = new Point(points[i + 2]);
			Point p3 = new Point(points[i + 1]);
			triangles.add(new Triangle(p1, p2, p3, Color.red));
		}
		for (int i = points.length - ppl; i < points.length - 1; i++) {
			Point p1 = new Point(points[i]);
			Point p2 = new Point(points[i + 1]);
			Point p3 = new Point(points[i]);
			triangles.add(new Triangle(p1, p2, p3, Color.green));
		}
		System.out.println(triangles.size());
	}

	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees) {
		for (Triangle t : triangles)
			t.RotateCounterClockwiseAboutYAxis(p, degrees);
	}

	public void RotateClockwiseAboutYAxis(Point p, float degrees) {
		for (Triangle t : triangles)
			t.RotateClockwiseAboutYAxis(p, degrees);
	}

	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees) {
		for (Triangle t : triangles)
			t.RotateCounterClockwiseAboutXAxis(p, degrees);
	}

	public void RotateClockwiseAboutXAxis(Point p, float degrees) {
		for (Triangle t : triangles)
			t.RotateClockwiseAboutXAxis(p, degrees);
	}

	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees) {
		for (Triangle t : triangles)
			t.RotateCounterClockwiseAboutZAxis(p, degrees);
	}

	public void RotateClockwiseAboutZAxis(Point p, float degrees) {
		for (Triangle t : triangles)
			t.RotateClockwiseAboutZAxis(p, degrees);
	}

	public void Resize(float scaleFactor) {
		for (Triangle t : triangles)
			t.Resize(scaleFactor);
	}

	public Point GetCenter() {
		double totx = 0;
		double toty = 0;
		double totz = 0;
		for (Triangle t : triangles) {
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
		totx /= 3 * triangles.size();
		toty /= 3 * triangles.size();
		totz /= 3 * triangles.size();
		return new Point(totx, toty, totz);
	}

	public void Render(BufferedImage buf) {
		for (Triangle t : triangles) {
			t.Render(buf);
		}
	}

	public void Draw_Mesh(BufferedImage buf) {
		for (Triangle t : triangles) {
			t.Draw_Mesh(buf);
		}
	}

	/*
	 * public void Render(Graphics2D g) { for(int i = 0; i < 100; i++) {
	 * g.drawLine(points[i].GetX() + 200, points[i].GetY() + 200, points[i +
	 * 1].GetX() + 200, points[i + 1].GetY() + 200); } }
	 */
	public void Move(Point target, float distance) {
		Point center = GetCenter();
		Point orig = GetCenter();
		Util.MovePointTowardsAnotherPoint(center, target, distance);
		for (Triangle t : triangles) {
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

	public void SetTexture(String file) {
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException ex) {
		}
	}
}
