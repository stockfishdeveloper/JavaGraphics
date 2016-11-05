import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class SkyBox
{
	BufferedImage front;
	BufferedImage back;
	BufferedImage left;
	BufferedImage right;
	BufferedImage up;
	BufferedImage down;
	public SkyBox()
	{
		front = null;
		back = null;
		left = null;
		right = null;
		up = null;
		down = null;
	}
	public void SetFrontFace(String location)
	{
		try {
			front = ImageIO.read(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void SetBackFace(String location)
	{
		try {
			back = ImageIO.read(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void SetLeftFace(String location)
	{
		try {
			left = ImageIO.read(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void SetRightFace(String location)
	{
		try {
			right = ImageIO.read(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void SetUpFace(String location)
	{
		try {
			up = ImageIO.read(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void SetDownFace(String location)
	{
		try {
			down = ImageIO.read(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void Render(Graphics2D g, Point cameradirection)
	{
		BufferedImage leftmost;
		BufferedImage rightmost;
		if(cameradirection.GetExZ() > 0)
		{
			if(cameradirection.GetExX() > 0)
			{//DONE
				leftmost = front;
				rightmost = right;
				double tan = Math.toDegrees(Math.atan(cameradirection.GetExX() / cameradirection.GetExZ()));
				System.out.println("TAN: " + tan);
				int coorx = (int) (tan * 7.111111);
				g.drawImage(leftmost, coorx - 640, 0, 1280, 1000, null);
				g.drawImage(rightmost, coorx + 640, 0, 1280, 1000, null);
			}
			else
			{
				leftmost = left;
				rightmost = front;
				double tan = Math.toDegrees(Math.atan(cameradirection.GetExX() / cameradirection.GetExZ()));
				System.out.println("TAN: " + tan);
				int coorx = (int) (tan * 7.111111);
				g.drawImage(leftmost, coorx - 640, 0, 1280, 1000, null);
				g.drawImage(rightmost, coorx + 640, 0, 1280, 1000, null);
			}
		}
		else
		{
			if(cameradirection.GetExX() > 0)
			{
				leftmost = right;
				rightmost = back;
			}
			else
			{
				leftmost = back;
				rightmost = left;
			}
			/*double tan = Math.toDegrees(Math.atan(cameradirection.GetExX() / cameradirection.GetExZ()));
			int coorx = (int) (tan * 7.111111);
			g.drawImage(leftmost, coorx, 0, 1280, 1000, null);
			g.drawImage(rightmost, coorx + 1280, 0, 1280, 1000, null);*/
		}
	}
}
