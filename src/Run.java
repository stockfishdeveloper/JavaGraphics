import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Run
{
    public static JFrame frame;
    public static JPanel panel;
    public static Long nextframe;
    static Camera cam;
    public static void main(String[] args) throws InterruptedException, IOException
    {
    	cam = new Camera();
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
        //Point p = new Point(10, 0, 0);
        //p.RotateCounterClockwiseAboutYAxis(new Point(2, 0, 0), 180);
        //Point p1 = w.camera.LookAt(p);
        //p1.Print_Info();
        while(true)
        {
        	if(System.currentTimeMillis() >= nextframe)
            {
        		//w.camera.RotMat.Output();
        		//w.cube.RotateClockwiseAboutYAxis(new Point(0, 0, 0), 1.5f);
                //w.cube.RotateClockwiseAboutXAxis(new Point(0, 0, 0), 1.5f);
        		//World.camera.MoveBackward(50);
                //System.out.println(Util.Distance_Between(World.camera.GetDirection(), new Point(0, 0, 0)));
        		w.repaint();
                nextframe += 100;
            }
        }
    }
}
