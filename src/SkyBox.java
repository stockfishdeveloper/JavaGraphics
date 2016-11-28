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
                BufferedImage toporbot = cameradirection.GetExY() > 0 ? up : down;
		if(cameradirection.GetExZ() > 0)
		{
			if(cameradirection.GetExX() > 0)
			{//DONE
				leftmost = front;
				rightmost = right;
				double tan = Math.toDegrees(Math.atan(cameradirection.GetExX() / cameradirection.GetExZ()));
				int coorx = (int) (tan * -14.222222);
				double ycoor = (cameradirection.GetExY() > 0 ? 1 : -1) * 11.111111 * Util.Get_Angle_Between_Vectors(cameradirection, new Point(0, 0, 0), new Point(cameradirection.GetExX(), 0, cameradirection.GetExZ()));
				g.drawImage(leftmost, coorx, (int)(ycoor), 1280, 1000, null);
				g.drawImage(rightmost, coorx + 1280, (int)(ycoor), 1280, 1000, null);
                g.drawImage(toporbot, coorx, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
                //g.drawImage(toporbot, coorx + 1280, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
			}
			else
			{
				leftmost = left;
				rightmost = front;
				double tan = Math.toDegrees(Math.atan(cameradirection.GetExX() / cameradirection.GetExZ()));
				int coorx = (int) (tan * -14.222222);
				double ycoor = (cameradirection.GetExY() > 0 ? 1 : -1) * 11.111111 * Util.Get_Angle_Between_Vectors(cameradirection, new Point(0, 0, 0), new Point(cameradirection.GetExX(), 0, cameradirection.GetExZ()));
				g.drawImage(leftmost, coorx - 1280, (int)(ycoor), 1280, 1000, null);
				g.drawImage(rightmost, coorx, (int)(ycoor), 1280, 1000, null);
                g.drawImage(toporbot, coorx, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
                //g.drawImage(toporbot, coorx, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
			}
		}
		else
		{
			if(cameradirection.GetExX() > 0)
			{
				leftmost = back;
				rightmost = right;
				Point turned = new Point(-cameradirection.GetExX(), cameradirection.GetExY(), -cameradirection.GetExZ());
				double tan = Math.toDegrees(Math.atan(turned.GetExZ() / turned.GetExX()));
				int coorx = (int) (tan * 14.222222);
				double ycoor = (cameradirection.GetExY() > 0 ? 1 : -1) * 11.111111 * Util.Get_Angle_Between_Vectors(cameradirection, new Point(0, 0, 0), new Point(cameradirection.GetExX(), 0, cameradirection.GetExZ()));
				g.drawImage(leftmost, coorx + 1280, (int)(ycoor), 1280, 1000, null);
				g.drawImage(rightmost, coorx, (int)(ycoor), 1280, 1000, null);
                g.drawImage(toporbot, coorx + 1280, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
                //g.drawImage(toporbot, coorx, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
			}
			else
			{
				leftmost = back;
				rightmost = left;
                                
				Point turned = new Point(-cameradirection.GetExX(), cameradirection.GetExY(), -cameradirection.GetExZ());
				double tan = Math.toDegrees(Math.atan(turned.GetExZ() / turned.GetExX()));
				int coorx = (int) (tan * 14.222222);
				double ycoor = (cameradirection.GetExY() > 0 ? 1 : -1) * 11.111111 * Util.Get_Angle_Between_Vectors(cameradirection, new Point(0, 0, 0), new Point(cameradirection.GetExX(), 0, cameradirection.GetExZ()));
				g.drawImage(leftmost, coorx - 1280, (int)(ycoor), 1280, 1000, null);
				g.drawImage(rightmost, coorx, (int)(ycoor), 1280, 1000, null);
                g.drawImage(toporbot, coorx - 1280, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
                //g.drawImage(toporbot, coorx, toporbot == up ? (int)(ycoor - 1000) : (int)(ycoor + 1000), 1280, 1000, null);
			}
		}
	}
}
