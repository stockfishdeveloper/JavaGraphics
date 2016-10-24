import java.util.Random;

class Run
{
	public static void main(String[] args) throws InterruptedException
	{
		World world = new World();
		/*for(int i = 0; i < 360; i++)
		{
			Thread.sleep(100);
			for(int j = 0; j < 100; j++)
			{
					Thread.sleep(10);
					world.t.Move(new Point(100, 0, 0), 3f);
					//Thread.sleep(0);
					//world.t[j].TranslateVisiblePosition(1, 1);
					//Thread.sleep(1);
					//////////////////////world.t.RotateClockwiseAboutXAxis(5f);
					//Thread.sleep(0);
					//world.t[j].Resize(1.01f);
					//Thread.sleep(0);
			}
		}*/
		//World.camera.MoveForward(300);
		      			while(true){
		    	        //World.t.repaint();
                        //World.c.repaint();
		      				for(RectangularPrism r : World.t) r.repaint();
                        Thread.sleep(10);
                        }
	}
}
