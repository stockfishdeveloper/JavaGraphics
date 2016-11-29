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
            while(true){
            	w.repaint();
            	Thread.sleep(10);
              }
            }
}
