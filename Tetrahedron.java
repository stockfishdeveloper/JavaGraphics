import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Tetrahedron {
	public int xoffset = 640;
	public int yoffset = 500;
	Color color;
	Point[] points = new Point[4];
	Triangle[] triangles = new Triangle[4];

	public Tetrahedron() {

	}

	public Tetrahedron(Point center, float scaleFactor, Color c) {
		color = c;
		for (int i = 0; i < 4; i++)
			points[i] = new Point(0, 0, 0);
		points[0].SetX(center.GetExX() != 0 ? center.GetExX() * -1 * scaleFactor : -scaleFactor);
		points[0].SetY(0);
		points[0].SetZ(center.GetExZ() != 0 ? center.GetExZ() * -0.707 * scaleFactor : -0.707 * scaleFactor);
		points[1].SetX(center.GetExX() != 0 ? center.GetExX() * scaleFactor : scaleFactor);
		points[1].SetY(0);
		points[1].SetZ(center.GetExZ() != 0 ? center.GetExZ() * -0.707 * scaleFactor : -0.707 * scaleFactor);
		points[2].SetX(0);
		points[2].SetY(center.GetExY() != 0 ? center.GetExY() * -1 * scaleFactor : -scaleFactor);
		points[2].SetZ(center.GetExZ() != 0 ? center.GetExZ() * 0.707 * scaleFactor : 0.707 * scaleFactor);
		points[3].SetX(0);
		points[3].SetY(center.GetExY() != 0 ? center.GetExY() * scaleFactor : scaleFactor);
		points[3].SetZ(center.GetExZ() != 0 ? center.GetExZ() * 0.707 * scaleFactor : 0.707 * scaleFactor);
		triangles[0] = new Triangle(points[0], points[1], points[2], color);
		triangles[1] = new Triangle(points[0], points[2], points[3], color);
		triangles[2] = new Triangle(points[0], points[3], points[1], color);
		triangles[3] = new Triangle(points[1], points[3], points[2], color);
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
		totx /= 36.0d;
		toty /= 36.0d;
		totz /= 36.0d;
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
}
