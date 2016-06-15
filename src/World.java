import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

class World //implements MouseListener, MouseMotionListener
{
	Cube c;
	static Point Camera = new Point(0, 0, 1);
	JFrame frame;
	static JPanel panel;
	public World()
	{
	frame = new JFrame();
	frame.setSize(1280, 1000);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel = new JPanel();
	Point p = new Point(0, 0, 0);
	c = new Cube(p, 100);
	c.TranslateVisiblePosition(200,  200);
	panel.addMouseMotionListener(c);
	panel.addMouseListener(c);
	c.setPreferredSize(new Dimension(1200, 1200));
	c.setMaximumSize(c.getPreferredSize());
	c.setMinimumSize(c.getPreferredSize());
	panel.add(c);
	frame.add(panel, BorderLayout.CENTER);
	frame.setVisible(true);
	}
}
