import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

class World extends JComponent implements MouseMotionListener, MouseListener, MouseWheelListener
{
	private static final long serialVersionUID = 1L;
	RectangularPrism[] t = new RectangularPrism[4];
	static BufferedImage screen;
	BufferedImage blank;
	int currx = 0;
	int curry = 0;
	public Cube cube;
	public static Camera camera;
	//public SkyBox skybox;
        
	public World()
	{
            cube = new Cube(new Point(0, 0, 0), 64, "L:\\grid_cool2.jpg");
			camera = new Camera();
			screen = new BufferedImage(1280, 1000, BufferedImage.TYPE_INT_RGB);
			blank = new BufferedImage(1280, 1000, BufferedImage.TYPE_INT_RGB);
			for(int i = 0; i < screen.getWidth(); i++)
			{
				for(int j = 0; j < screen.getHeight(); j++)
				{
					screen.setRGB(i, j, 16777215);
					blank.setRGB(i, j, 16777215);
				}
			}
            /*skybox = new SkyBox();
            skybox.SetFrontFace("L:\\3DSquare\\bin\\sky_fr.jpg");
    	    skybox.SetBackFace("L:\\3DSquare\\bin\\sky_bk.jpg");
    	    skybox.SetLeftFace("L:\\3DSquare\\bin\\sky_lf.jpg");
    	    skybox.SetRightFace("L:\\3DSquare\\bin\\sky_rt.jpg");
    	    skybox.SetUpFace("L:\\3DSquare\\bin\\sky_up.jpg");
    	    skybox.SetDownFace("L:\\3DSquare\\bin\\sky_dn.jpg");*/
            this.setBounds(0, 0, 1280, 1000);
         }
        @Override
        public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		//screen.setData(blank.getRaster());
		Graphics2D graphics = screen.createGraphics();
		graphics.fillRect(0, 0, 1280, 1000);
		//skybox.Render(g, camera.direction);
		/*for(RectangularPrism c : t)
			c.Render();*/
		/*for(int i = 0; i < 1280; i++)
		{
			for(int j = 0; j < 1000; j++)
			{
				screen.setRGB(i, j, 16777215);
			}
		}*/
		cube.Render(screen);
		/*for(Triangle t : cube.triangles)
		{
			t.Draw_Mesh(g);
		}*/
		g.drawImage(screen, 0, 0, 1280, 1000, null);
		//camera.Print_Info();
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent m) {
		if(m.getX() > currx)
			cube.RotateClockwiseAboutYAxis(cube.GetCenter(), 3f);
		else if(m.getX() < currx)
			cube.RotateCounterClockwiseAboutYAxis(cube.GetCenter(), 3f);
		if(m.getY() > curry)
			cube.RotateClockwiseAboutXAxis(cube.GetCenter(), 3f);
		else if(m.getY() < curry)
			cube.RotateCounterClockwiseAboutXAxis(cube.GetCenter(), 3f);
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
