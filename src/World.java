import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

class World extends JComponent implements MouseMotionListener, MouseWheelListener
{
	final int length = 6;
	int[] x = new int[length * length * length];
	int[] y = new int[length * length * length];
	int[] z = new int[length * length * length];
	static RectangularPrism[] t = new RectangularPrism[4];
	//static RectangularPrism c;
	int currx = 0;
	int curry = 0;
	JFrame frame;
	static JPanel panel;
	static Camera camera;
	public World()
	{
	frame = new JFrame();
	frame.setSize(1280, 1000);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel = new JPanel();
	panel.setLayout(null);
	t[0] = new RectangularPrism(new Point(0, 0, 2000), 4000, 10, 4000);
	t[1] = new RectangularPrism(new Point(2000, 0, 0), 10, 4000, 4000);
	t[2] = new RectangularPrism(new Point(0, 0, -2000), 4000, 10, 4000);
	t[3] = new RectangularPrism(new Point(-2000, 0, 0), 10, 4000, 4000);
	//c = new RectangularPrism(new Point(0, 0, 0), 2000, 2000, 10);
	camera = new Camera();
	//Triangle tr = new Triangle(new Point(20, 0, 20), new Point(0, 20, 20), new Point(-20, 0, 20));
	//for(int i = 0; i < 1000; i++)
	//{
	//camera.RotateClockwiseAboutYAxis(camera.location, 90);
	//camera.RotateClockwiseAboutZAxis(camera.location, 45);
	//camera.MoveForward(1);
	//}*/
	//camera.LookAt(tr).Print_Info();
	//t.TranslateVisiblePosition(600, 500);
	//panel.addMouseMotionListener(t);
	panel.addMouseMotionListener(this);
	panel.addMouseWheelListener(this);
	//panel.addMouseListener(t);
	//panel.addMouseWheelListener(t);
	for(RectangularPrism r : t) panel.add(r);
	//panel.add(c);
	frame.add(panel);
	frame.setVisible(true);
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent m) {
		if(m.getX() > currx)
			camera.RotateClockwiseAboutYAxis(camera.location, 0.3f);
		else if(m.getX() < currx)
			camera.RotateCounterClockwiseAboutYAxis(camera.location, 0.3f);
		if(m.getY() > curry)
			camera.RotateCounterClockwiseAboutXAxis(camera.location, 0.3f);
		else if(m.getY() < curry)
			camera.RotateClockwiseAboutXAxis(camera.location, 0.3f);
		currx = m.getX();
		curry = m.getY();
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		if(m.getPreciseWheelRotation() < 0)
			camera.MoveForward(100);
		else
			camera.MoveBackward(100);	
		
	}
}
