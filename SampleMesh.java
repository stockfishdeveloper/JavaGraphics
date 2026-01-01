import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class SampleMesh {
	public ArrayList<Triangle> triangles;
	public Color color;

	public SampleMesh(Point center, Color c, int dimension) {
		color = c;
		triangles = new ArrayList<>();
		Point[][] points = new Point[dimension][dimension];
		int[] elevation = new int[dimension * dimension];
		for (int i = 0; i < dimension * dimension; i++) {
			elevation[i] = center.GetY() + (int) (Math.random() * 10);
		}
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				points[i][j] = new Point(((i - 50) * 10) + center.GetExX(), elevation[i * j],
						(((50 - j) * 10) + center.GetExZ()));
			}
		}
		java.util.Random rand = new java.util.Random(0);
		float r;
		float g;
		float b;
		for (int j = 0; j < dimension - 1; j++) {
			for (int i = 0; i < dimension - 1; i++) {
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

	public void Draw_Mesh(BufferedImage buf) {
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).Draw_Mesh(buf);
		}
	}

	public void Render(BufferedImage buf) {
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).Render(buf);
		}
	}
}
