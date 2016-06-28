import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

class World //implements MouseListener, MouseMotionListener
{
	public Cube[] t = new Cube[27];
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
	int[] x = { -100, 0, 100, -100, 0, 100, -100, 0, 100, -100, 0, 100, -100, 0, 100, -100, 0, 100, -100, 0, 100, -100, 0, 100, -100, 0, 100 };
	int[] y = { 100, 100, 100, 0, 0, 0, -100, -100, -100, 100, 100, 100, 0, 0, 0, -100, -100, -100, 100, 100, 100, 0, 0, 0, -100, -100, -100 };
	int[] z = { -100, -100, -100, -100, -100, -100, -100, -100, -100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
	for(int i = 0; i < 27; i++)
	{
	Point p = new Point(x[i], y[i], z[i]);
	t[i] = new Cube(p, 10);
	t[i].TranslateVisiblePosition(300, 300);
	panel.addMouseMotionListener(t[i]);
	panel.addMouseListener(t[i]);
	t[i].setPreferredSize(new Dimension(1200, 1200));
	t[i].setMaximumSize(t[i].getPreferredSize());
	t[i].setMinimumSize(t[i].getPreferredSize());
	panel.add(t[i]);
	}
	frame.add(panel, BorderLayout.CENTER);
	frame.setVisible(true);
	}
}
