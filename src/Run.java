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
		/*World.camera.MoveForward(3275);
                Point p = new Point(300, 0, 1000);
                System.out.println(World.camera.frustum.Contains(p));*/
                for(int i = 0; i < 10000; i++){
			World.t.RotateClockwiseAboutYAxis(World.t.GetCenter(), 1);
                        World.c.RotateCounterClockwiseAboutYAxis(World.c.GetCenter(), 1);
                        World.camera.Print_Info();
			/*World.camera.MoveForward(5);*/World.t.repaint();World.c.repaint();Thread.sleep(10);}
	}
}
