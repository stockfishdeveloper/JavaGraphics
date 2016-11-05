
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Run
{
            public static JFrame frame;
            public static JPanel panel;
            public static void main(String[] args) throws InterruptedException
            {
            World w = new World();
            frame = new JFrame();
            frame.setSize(1280, 1000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new JPanel();
            panel.setLayout(null);
            panel.add(w);
            panel.addMouseMotionListener(w);
            panel.addMouseWheelListener(w);
            frame.add(panel);
            frame.setVisible(true);
            /*while(true){
            	for(int
            			i = 0; i < 4; i++)
            		w.t[i].RotateClockwiseAboutXAxis(new Point(0, 0, 0), 5.0f);
            	for(int i = 4; i < 8; i++)
            		w.t[i].RotateClockwiseAboutYAxis(new Point(0, 0, 0), 5.0f);
            	for(int i = 8; i < 12; i++)
            		w.t[i].RotateClockwiseAboutZAxis(new Point(0, 0, 0), 5.0f);
            	w.repaint();
            	Thread.sleep(30);
              }*/
            }
}
