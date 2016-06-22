import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

class World //implements MouseListener, MouseMotionListener
{
	RectangularPrism t;
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
	t = new RectangularPrism(p, 200, 100, 100);
	t.TranslateVisiblePosition(200,  200);
	panel.addMouseMotionListener(t);
	panel.addMouseListener(t);
	t.setPreferredSize(new Dimension(1200, 1200));
	t.setMaximumSize(t.getPreferredSize());
	t.setMinimumSize(t.getPreferredSize());
	panel.add(t);
	frame.add(panel, BorderLayout.CENTER);
	frame.setVisible(true);
	}
}
