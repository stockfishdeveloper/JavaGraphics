import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

class Square implements Comparable<Triangle> {
	public Color color;
	Point[] points = new Point[4];
	Pixel[][] pixels;
	int pixdim;

	public Square(Point p1, Point p2, Point p3, Point p4, Pixel[][] data, int dimension) {
		pixels = new Pixel[dimension + 1][dimension + 1];
		pixdim = dimension + 1;
		points[0] = new Point(p1.GetExX(), p1.GetExY(), p1.GetExZ());
		points[1] = new Point(p2.GetExX(), p2.GetExY(), p2.GetExZ());
		points[2] = new Point(p3.GetExX(), p3.GetExY(), p3.GetExZ());
		points[3] = new Point(p4.GetExX(), p4.GetExY(), p4.GetExZ());
		for (int i = 0; i < pixdim; i++)
			for (int j = 0; j < pixdim; j++)
				pixels[i][j] = Pixel.Copy(data[i][j]);
	}

	public Square(Point p1, Point p2, Point p3, Point p4, Color c) {
		pixels = null;
		points[0] = new Point(p1.GetExX(), p1.GetExY(), p1.GetExZ());
		points[1] = new Point(p2.GetExX(), p2.GetExY(), p2.GetExZ());
		points[2] = new Point(p3.GetExX(), p3.GetExY(), p3.GetExZ());
		points[3] = new Point(p4.GetExX(), p4.GetExY(), p4.GetExZ());
		color = c;
	}

	public Square(Square square) {
		for (int i = 0; i < 4; i++) {
			this.points[i] = new Point(square.points[i].GetExX(), square.points[i].GetExY(), square.points[i].GetExZ());
		}
		if (square.pixels != null) {
			for (int i = 0; i < square.pixels.length; i++)
				for (int j = 0; j < square.pixels.length; j++) {
					this.pixels[i][j] = Pixel.Copy(square.pixels[i][j]);
				}
		} else {
			color = square.color;
			pixels = null;
		}
	}

	public void Print_Info() {
		for (Point p : points) {
			System.out.println(p.GetExX() + " " + p.GetExY() + " " + p.GetExZ());
		}
	}

	public boolean Should_Be_Drawn() {
		Point p = Util.GetNormalVector(points[0], points[1], points[2]);
		double dotproduct = Util.GetDotProduct(p, World.camera.GetDirection());
		boolean draw = dotproduct < 0 ? true : false;
		return draw;
	}

	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees) {
		for (Point p1 : points)
			p1.RotateCounterClockwiseAboutYAxis(p, degrees);
		if (pixels != null)
			for (int i = 0; i < pixdim; i++)
				for (int j = 0; j < pixdim; j++)
					pixels[i][j].RotateCounterClockwiseAboutYAxis(p, degrees);
	}

	public void RotateClockwiseAboutYAxis(Point p, float degrees) {
		for (Point p1 : points)
			p1.RotateClockwiseAboutYAxis(p, degrees);
		if (pixels != null)
			for (int i = 0; i < pixdim; i++)
				for (int j = 0; j < pixdim; j++)
					pixels[i][j].RotateClockwiseAboutYAxis(p, degrees);
	}

	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees) {
		for (Point p1 : points)
			p1.RotateCounterClockwiseAboutXAxis(p, degrees);
		if (pixels != null)
			for (int i = 0; i < pixdim; i++)
				for (int j = 0; j < pixdim; j++)
					pixels[i][j].RotateCounterClockwiseAboutXAxis(p, degrees);
	}

	public void RotateClockwiseAboutXAxis(Point p, float degrees) {
		for (Point p1 : points)
			p1.RotateClockwiseAboutXAxis(p, degrees);
		if (pixels != null)
			for (int i = 0; i < pixdim; i++)
				for (int j = 0; j < pixdim; j++)
					pixels[i][j].RotateClockwiseAboutXAxis(p, degrees);
	}

	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees) {
		for (Point p1 : points)
			p1.RotateCounterClockwiseAboutZAxis(p, degrees);
		if (pixels != null)
			for (int i = 0; i < pixdim; i++)
				for (int j = 0; j < pixdim; j++)
					pixels[i][j].RotateCounterClockwiseAboutZAxis(p, degrees);
	}

	public void RotateClockwiseAboutZAxis(Point p, float degrees) {
		for (Point p1 : points)
			p1.RotateClockwiseAboutZAxis(p, degrees);
		if (pixels != null)
			for (int i = 0; i < pixdim; i++)
				for (int j = 0; j < pixdim; j++)
					pixels[i][j].RotateClockwiseAboutZAxis(p, degrees);
	}

	public void Resize(float scaleFactor) {
		for (Point p : points) {
			p.SetX(p.GetExX() * scaleFactor);
			p.SetY(p.GetExY() * scaleFactor);
			p.SetZ(p.GetExZ() * scaleFactor);
		}
	}

	public Point GetCenter() {
		double x = (points[0].GetExX() + points[1].GetExX() + points[2].GetExX() + points[3].GetExX()) / 4.0;
		double y = (points[0].GetExY() + points[1].GetExY() + points[2].GetExY() + points[3].GetExY()) / 4.0;
		double z = (points[0].GetExZ() + points[1].GetExZ() + points[2].GetExZ() + points[3].GetExZ()) / 4.0;
		return new Point(x, y, z);
	}

	public void Render(BufferedImage image) {
		Graphics2D g = image.createGraphics();
		if (Should_Be_Drawn()) {
			if (pixels != null) {
				for (int i = 0; i < pixdim - 1; i++)
					for (int j = 0; j < pixdim - 1; j++) {
						Square p = World.camera.LookAt(new Square(pixels[i][j], pixels[i + 1][j], pixels[i + 1][j + 1],
								pixels[i][j + 1], new Color(pixels[i][j].GetColor())));
						if (p != null) {
							if (p.GetCenter().GetX() >= -640 && p.GetCenter().GetX() < 640
									&& p.GetCenter().GetY() >= -500 && p.GetCenter().GetY() < 500) {
								if (World.distancefromscreen[(int) Math.round(p.GetCenter().GetExX())
										+ 640][(int) Math.round(p.GetCenter().GetExY()) + 500] >= p.GetCenter()
												.GetExZ()) {
									g.setColor(new Color(pixels[i][j].GetColor()));
									int[] x = new int[4];
									int[] y = new int[4];
									for (int q = 0; q < 4; q++) {
										x[q] = p.points[q].GetX() + 640;
										y[q] = p.points[q].GetY() + 500;
									}
									Polygon poly = new Polygon(x, y, 4);
									g.fillPolygon(poly);
									World.distancefromscreen[(int) Math.round(p.GetCenter().GetExX())
											+ 640][(int) Math.round(p.GetCenter().GetExY()) + 500] = p.GetCenter()
													.GetExZ();
								}
							}
						}
					}
			} else {
				g.setColor(color);
				int[] x = new int[4];
				int[] y = new int[4];
				Square t = World.camera.LookAt(this);
				for (int i = 0; i < 4; i++) {
					if (t != null) {
						x[i] = t.points[i].GetX() + 640;
						y[i] = t.points[i].GetY() + 500;
					}
				}
				if (t != null) {
					Polygon poly = new Polygon(x, y, 4);
					g.fillPolygon(poly);
				}
			}
		}
	}

	/*
	 * public void Render(Graphics2D g) { g.setColor(color); Triangle t = new
	 * Triangle(World.camera.LookAt(this)); int[] x = new int[3]; int[] y = new
	 * int[3]; for(int i = 0; i < 3; i++) { x[i] = t.points[i].GetX() + 640; y[i] =
	 * t.points[i].GetY() + 500; } Polygon poly = new Polygon(x, y, 3);
	 * g.fillPolygon(poly); }
	 */
	public void Draw_Mesh(BufferedImage buf) {
		// if(Should_Be_Drawn())
		{
			Graphics2D g = buf.createGraphics();
			g.setColor(color);
			Square t = World.camera.LookAt(this);
			if (t != null) {
				g.drawLine(t.points[0].GetX() + 640, t.points[0].GetY() + 500, t.points[1].GetX() + 640,
						t.points[1].GetY() + 500);
				g.drawLine(t.points[1].GetX() + 640, t.points[1].GetY() + 500, t.points[2].GetX() + 640,
						t.points[2].GetY() + 500);
				g.drawLine(t.points[2].GetX() + 640, t.points[2].GetY() + 500, t.points[3].GetX() + 640,
						t.points[3].GetY() + 500);
				g.drawLine(t.points[0].GetX() + 640, t.points[0].GetY() + 500, t.points[3].GetX() + 640,
						t.points[3].GetY() + 500);
			}
		}
	}

	public int GreatestX() {
		int gx = -10000;
		for (Point p : points)
			if (p.GetExX() > gx)
				gx = p.GetX();
		return gx;
	}

	public int LeastX() {
		int lx = 10000;
		for (Point p : points)
			if (p.GetExX() < lx)
				lx = p.GetX();
		return lx;
	}

	public int GreatestY() {
		int gy = -10000;
		for (Point p : points)
			if (p.GetExY() > gy)
				gy = p.GetY();
		return gy;
	}

	public int LeastY() {
		int ly = 10000;
		for (Point p : points)
			if (p.GetExY() < ly)
				ly = p.GetY();
		return ly;
	}

	@Override
	public int compareTo(Triangle t) {
		if (this.GetCenter().GetExZ() <= t.GetCenter().GetExZ()) {
			return 1;
		} else if (this.GetCenter().GetExZ() > t.GetCenter().GetExZ()) {
			return -1;
		} else {
			return 0;
		}
	}
}
