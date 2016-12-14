import java.awt.Color;
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
	static BufferedImage screen;
	BufferedImage blank;
	int currx = 0;
	int curry = 0;
	public Cube cube;
	public static Camera camera;
	SampleMesh sample;
	static double[][] distancefromscreen = new double[1280][1000];
	       
	public World()
	{
            cube = new Cube(new Point(0, 0, 0), 30,/*"L:\\grid_cool2.jpg"*/Color.gray);
            sample = new SampleMesh(new Point(0, 0, 0), Color.red);
            camera = new Camera();
            screen = new BufferedImage(1280, 1000, BufferedImage.TYPE_INT_RGB);
            blank = new BufferedImage(1280, 1000, BufferedImage.TYPE_INT_RGB);
            for(int i = 0; i < 1280; i++)
            {
            	for(int j = 0; j < 1000; j++)
            	{
            		distancefromscreen[i][j] = Double.MAX_VALUE;
            	}
            }
            for(int i = 0; i < screen.getWidth(); i++)
		{
                    for(int j = 0; j < screen.getHeight(); j++)
			{
                            screen.setRGB(i, j, 16777215);
                            blank.setRGB(i, j, 16777215);
			}
		}
            this.setBounds(0, 0, 1280, 1000);
         }
        @Override
        public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		Graphics2D graphics = screen.createGraphics();
		graphics.fillRect(0, 0, 1280, 1000);
		cube.Draw_Mesh(screen);
		sample.Draw_Mesh(screen);
		g.drawImage(screen, 0, 0, 1280, 1000, null);
		for(int i = 0; i < 1280; i++)
        {
        	for(int j = 0; j < 1000; j++)
        	{
        		distancefromscreen[i][j] = Double.MAX_VALUE;
        	}
        }
        }
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent m) {
		if(m.getX() > currx)
                {
					cube.RotateClockwiseAboutYAxis(new Point(0, 0, 0), 1f);
                    sample.RotateClockwiseAboutYAxis(new Point(0, 0, 0), 1f);
			//camera.RotateClockwiseAboutYAxis(camera.location, 1f);
                }
		else if(m.getX() < currx)
                {
					cube.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), 1f);
					sample.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), 1f);
			//camera.RotateCounterClockwiseAboutYAxis(camera.location, 1f);
                }
		if(m.getY() > curry)
                {
					cube.RotateClockwiseAboutXAxis(new Point(0, 0, 0), 1f);
                                    sample.RotateClockwiseAboutXAxis(new Point(0, 0, 0), 1f);
			//camera.RotateClockwiseAboutXAxis(camera.location, 1f);
                }
		else if(m.getY() < curry)
                {
					cube.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), 1f);
					sample.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), 1f);
			//camera.RotateCounterClockwiseAboutXAxis(camera.location, 1f);
                }
		currx = m.getX();
		curry = m.getY();
		//repaint();
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		if(m.getPreciseWheelRotation() < 0)
			camera.MoveForward(10);
		else
			camera.MoveBackward(10);	
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
