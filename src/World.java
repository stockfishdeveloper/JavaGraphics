import javax.swing.JFrame;
import javax.swing.JPanel;

class World //implements MouseListener, MouseMotionListener
{
	final int length = 6;
	Cube[] t = new Cube[length * length * length];
	int[] x = new int[length * length * length];
	int[] y = new int[length * length * length];
	int[] z = new int[length * length * length];
	static Point Camera = new Point(0, 0, 1);
	JFrame frame;
	static JPanel panel;
	public World()
	{
	frame = new JFrame();
	frame.setSize(1280, 1000);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel = new JPanel();
	panel.setLayout(null);
	/*int[] sx = { -200, -100, 0, 100, 200 };
	int[] sy = { 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 0, 0, 0, 0, 0, -100, -100, -100, -100, -100, -200, -200, -200, -200, -200,};
	int[] sz = { -200, -100, 0, 100, 200 };
	for(int i = 0; i < 25; i++)
	{
		for(int j = 0; j < 5; j++)
		{
			x[(i * 5) + j] = sx[j];
		}
	}
	for(int i = 0; i < 5; i++)
	{
		for(int j = 0; j < 25; j++)
		{
			y[(i * 25) + j] = sy[j];
		}
	}
	for(int i = 0; i < 5; i++)
	{
		for(int j = 0; j < 25; j++)
		{
			z[(i * 25) + j] = sz[i];
		}
	}*/
	WriteDataForCubeOfCubes(length);
	for(int i = 0; i < length * length * length; i++)
	{
	Point p = new Point(x[i], y[i], z[i]);
	t[i] = new Cube(p, 10);
	t[i].TranslateVisiblePosition(600, 500);
	panel.addMouseMotionListener(t[i]);
	panel.addMouseListener(t[i]);
	//t.setPreferredSize(new Dimension(1200, 1200));
	//t.setMaximumSize(t.getPreferredSize());
	//t.setMinimumSize(t.getPreferredSize());
	panel.add(t[i]);
	}
	frame.add(panel);
	frame.setVisible(true);
	}
	public void WriteDataForCubeOfCubes(int dimension)
	{
		int[] lx = new int[dimension * dimension * dimension];
		int[] ly = new int[dimension * dimension * dimension];
		int[] lz = new int[dimension * dimension * dimension];
		int[] sx = new int[dimension];
		int[] sy = new int[dimension * dimension];
		int[] sz = new int[dimension];
		int count = 0;
		for(int i = (int)((dimension / 2.0)*-100) + 50; i < (int)((dimension / 2.0)*100) + 50; i += 100)
		{
			sx[count++] = i;
		}
		count = 0;
		int[] ty = new int[dimension];
		for(int i = (int)((dimension / 2.0)*100) - 50; i >= (int)((dimension / 2.0)*-100) + 50; i -= 100)
		{
			ty[count++] = i;
		}
		count = 0;
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				sy[(i * dimension) + j] = ty[i];
			}
		}
		count = 0;
		for(int i = (int)((dimension / 2.0)*-100) + 50; i < (int)((dimension / 2.0)*100) + 50; i += 100)
		{
			sz[count++] = i;
		}
		for(int i = 0; i < dimension * dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				lx[(i * dimension) + j] = sx[j];
			}
		}
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension * dimension; j++)
			{
				ly[(i * dimension * dimension) + j] = sy[j];
			}
		}
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension * dimension; j++)
			{
				lz[(i * dimension * dimension) + j] = sz[i];
			}
		}
		for(int i = 0; i < dimension * dimension * dimension; i++)
		{
			x[i] = lx[i];
			y[i] = ly[i];
			z[i] = lz[i];
		}
		/*for(int i = 0; i < dimension * dimension * dimension; i++)
			System.out.println(y[i]);*/

	}
}
