import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Run
{
    public static JFrame frame;
    public static JPanel panel;
    public static Long nextframe;
    public static void main(String[] args) throws InterruptedException, IOException
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
        panel.setFocusable(true);
        panel.addKeyListener(w);
        frame.add(panel);
        frame.setVisible(true);
        nextframe = System.currentTimeMillis();
        //World.camera.MoveUp(400);
        Mesh mesh = new Mesh("L:\\teapot.obj");
        while(true)
        {
        	if(System.currentTimeMillis() >= nextframe)
            {
        		//w.camera.RotateClockwiseAboutUpAxis(1f);
        		//World.camera.MoveBackward(50);
                //System.out.println(Util.Distance_Between(World.camera.GetLeft(), new Point(0, 0, 0)));
        		w.repaint();
                nextframe += 150;
            }
        }
    }
}
