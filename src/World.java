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
        public Triangle t;
	public static Camera camera;
	       
	public World()
	{
            cube = new Cube(new Point(0, 0, 0), 64, "F:\\grid_cool2.jpg");
            t = new Triangle(new Point(100, 0, 200), new Point(250, 300, 200), new Point(400, 0, 200), Color.gray);
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
            this.setBounds(0, 0, 1280, 1000);
         }
        @Override
        public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		Graphics2D graphics = screen.createGraphics();
		graphics.fillRect(0, 0, 1280, 1000);
		cube.Render(screen);
                g.drawImage(screen, 0, 0, 1280, 1000, null);
                t.Render(g);
        }
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent m) {
		if(m.getX() > currx)
                {
                    cube.RotateClockwiseAboutYAxis(cube.GetCenter(), 5f);
                    t.RotateClockwiseAboutYAxis(t.GetCenter(), 5f);
                }
		else if(m.getX() < currx)
                {
                    cube.RotateCounterClockwiseAboutYAxis(cube.GetCenter(), 5f);
                    t.RotateCounterClockwiseAboutYAxis(t.GetCenter(), 5f);
                }
		if(m.getY() > curry)
                {
                    cube.RotateClockwiseAboutXAxis(cube.GetCenter(), 5f);
                    t.RotateClockwiseAboutXAxis(t.GetCenter(), 5f);
                }
		else if(m.getY() < curry)
                {
                    cube.RotateCounterClockwiseAboutXAxis(cube.GetCenter(), 5f);
                    t.RotateCounterClockwiseAboutXAxis(t.GetCenter(), 5f);
                }
		currx = m.getX();
		curry = m.getY();
		//repaint();
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		if(m.getPreciseWheelRotation() < 0)
			camera.MoveForward(200);
		else
			camera.MoveBackward(200);	
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
