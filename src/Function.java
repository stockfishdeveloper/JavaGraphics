import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Function
{
	Point[] points;
	public Function()
	{
		points = new Point[1600];
		int index = 0;
		for(int x = -20; x < 20; x += 1)
		{
			for(int z = -20; z < 20; z += 1)
			{
				points[index++] = new Point(x, (x * x) + (z * z), z);
			}
		}
	}
	public void Draw_Mesh(BufferedImage buf)
	{
		Graphics2D g = buf.createGraphics();
        g.setColor(Color.green);
		for(int i = 0; i < 1599; i++)
		{
			Point p1 = World.camera.LookAt(points[i]);
			Point p2 = World.camera.LookAt(points[i + 1]);
			if(p1 != null && p1.GetX() > -640 && p1.GetX() < 640 && p1.GetY() > -500 && p1.GetY() < 500)
				if(p2 != null && p2.GetX() > -640 && p2.GetX() < 640 && p2.GetY() > -500 && p2.GetY() < 500)
					g.drawLine(p1.GetX() + 640, p1.GetY() + 500, p2.GetX() + 640, p2.GetY() + 500);
		}
	}
}
