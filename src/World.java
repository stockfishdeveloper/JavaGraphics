import javax.swing.JFrame;
import javax.swing.JPanel;

class World
{
	final int length = 6;
	int[] x = new int[length * length * length];
	int[] y = new int[length * length * length];
	int[] z = new int[length * length * length];
	static Cube t;
	static Cube c;
	//static Point Camera = new Point(0, 0, 1);
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
	t = new Cube(new Point(300, 0, 1000), 100);
	c = new Cube(new Point(-300, 0, 1000), 100);
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
	panel.addMouseMotionListener(t);
	panel.addMouseListener(t);
	panel.addMouseWheelListener(t);
	panel.add(t);
	panel.add(c);
	frame.add(panel);
	frame.setVisible(true);
	}
}
