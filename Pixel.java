import java.awt.Color;

class Pixel extends Point {
	Color c;

	public Pixel(double newx, double newy, double newz, Color newcolor) {
		this.x = newx;
		this.y = newy;
		this.z = newz;
		this.c = newcolor;
	}

	public static Pixel Copy(Pixel other) {
		return new Pixel(other.x, other.y, other.z, other.c);
	}

	public void SetColor(Color newcolor) {
		c = newcolor;
	}

	public void SetColor(int newcolor) {
		c = new Color(newcolor);
	}

	public int GetColor() {
		return c.getRGB();
	}
}
