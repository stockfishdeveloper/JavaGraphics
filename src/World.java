import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

class World //implements MouseListener, MouseMotionListener
{
	Square s; 
	JFrame frame;
	static JPanel panel;
	public World()
	{
	frame = new JFrame();
	frame.setSize(1280, 1000);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel = new JPanel();
	Point p = new Point(0, 0, 0);
	s = new Square(p, 100);
	s.TranslateVisiblePosition(200,  200);
	panel.addMouseMotionListener(s);
	panel.addMouseListener(s);
	s.setPreferredSize(new Dimension(1200, 1200));
	s.setMaximumSize(s.getPreferredSize());
	s.setMinimumSize(s.getPreferredSize());
	panel.add(s);
	frame.add(panel, BorderLayout.CENTER);
	frame.setVisible(true);
	}
}
