import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

class World extends JComponent implements MouseMotionListener, MouseListener, MouseWheelListener
{
	private static final long serialVersionUID = 1L;
	RectangularPrism[] t = new RectangularPrism[4];
	public static ArrayList<Triangle> triangles;
	int currx = 0;
	int curry = 0;
	public static Camera camera;
	public SkyBox skybox;
        
	public World()
	{
            t[0] = new RectangularPrism(new Point(0, 0, 2000), 2000, 10, 2000);
            t[1] = new RectangularPrism(new Point(2000, 0, 0), 10, 2000, 2000);
            t[2] = new RectangularPrism(new Point(0, 0, -2000), 2000, 10, 2000);
            t[3] = new RectangularPrism(new Point(-2000, 0, 0), 10, 2000, 2000);
            /*t[4] = new RectangularPrism(new Point(0, 0, 2000), 2000, 10, 2000);
            t[5] = new RectangularPrism(new Point(2000, 0, 0), 10, 2000, 2000);
            t[6] = new RectangularPrism(new Point(0, 0, -2000), 2000, 10, 2000);
            t[7] = new RectangularPrism(new Point(-2000, 0, 0), 10, 2000, 2000);
            t[8] = new RectangularPrism(new Point(0, 0, 2000), 2000, 10, 2000);
            t[9] = new RectangularPrism(new Point(2000, 0, 0), 10, 2000, 2000);
            t[10] = new RectangularPrism(new Point(0, 0, -2000), 2000, 10, 2000);
            t[11] = new RectangularPrism(new Point(-2000, 0, 0), 10, 2000, 2000);*/
            camera = new Camera();
            triangles = new ArrayList<>();
            skybox = new SkyBox();
            skybox.SetFrontFace("F:\\3DSquare\\bin\\sky_fr.jpg");
    	    skybox.SetBackFace("F:\\3DSquare\\bin\\sky_bk.jpg");
    	    skybox.SetLeftFace("F:\\3DSquare\\bin\\sky_lf.jpg");
    	    skybox.SetRightFace("F:\\3DSquare\\bin\\sky_rt.jpg");
    	    skybox.SetUpFace("F:\\3DSquare\\bin\\sky_up.jpg");
    	    skybox.SetDownFace("F:\\3DSquare\\bin\\sky_dn.jpg");
            this.setBounds(0, 0, 1280, 1000);
         }
        @Override
        public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		skybox.Render(g, camera.direction);
		/*for(RectangularPrism c : t)
			c.Render();*/
		Collections.sort(triangles);
		for(Triangle t : triangles)
		{
			g.setColor(t.color);
			int[] x = new int[3];
			int[] y = new int[3];
			x[0] = t.points[0].GetX();
			x[1] = t.points[1].GetX();
			x[2] = t.points[2].GetX();
			y[0] = t.points[0].GetY();
			y[1] = t.points[1].GetY();
			y[2] = t.points[2].GetY();
			Polygon poly = new Polygon(x, y, 3);
			g.fillPolygon(poly);
		}
                triangles.clear();
                camera.Print_Info();
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent m) {
		if(m.getX() > currx)
			camera.RotateClockwiseAboutYAxis(camera.location, 3f);
		else if(m.getX() < currx)
			camera.RotateCounterClockwiseAboutYAxis(camera.location, 3f);
		if(m.getY() > curry)
			camera.RotateClockwiseAboutXAxis(camera.location, 3f);
		else if(m.getY() < curry)
			camera.RotateCounterClockwiseAboutXAxis(camera.location, 3f);
		currx = m.getX();
		curry = m.getY();
		repaint();
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		if(m.getPreciseWheelRotation() < 0)
			camera.MoveForward(200);
		else
			camera.MoveBackward(200);	
		repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
