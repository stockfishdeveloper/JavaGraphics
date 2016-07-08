class Run
{
	public static void main(String[] args) throws InterruptedException
	{
		World world = new World();
		/*for(int i = 0; i < 1000; i++)
		{
			world.t.Resize(1.001f);
		}*/
		/*for(int i = 0; i < 3600; i++)
		{
			try {
				Thread.sleep(10);
				world.t.RotateClockwiseAboutXAxis(2f);
				Thread.sleep(10);
				world.t.RotateClockwiseAboutYAxis(2f);
				Thread.sleep(10);
				world.t.TranslateVisiblePosition(1, 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
		/*for(int i = 0; i < 10000; i++)
		{
			world.t.RotateClockwiseAboutYAxis(1f);
			world.t.RotateClockwiseAboutXAxis(1f);
			world.t.RotateClockwiseAboutZAxis(1f);
			world.t.RotateCounterClockwiseAboutZAxis(1f);
			world.t.RotateCounterClockwiseAboutYAxis(1f);
			world.t.RotateCounterClockwiseAboutXAxis(1f);
		}*/
		/*world.t.Resize(5f);
		for(int i = 0; i < 360; i++)
		{
			Thread.sleep(10);
			for(int j = 0; j < 1; j++)
			{
					//Thread.sleep(0);
					world.t.RotateClockwiseAboutYAxis(5f);
					//Thread.sleep(0);
					//world.t[j].TranslateVisiblePosition(1, 1);
					//Thread.sleep(1);
					world.t.RotateClockwiseAboutXAxis(5f);
					//Thread.sleep(0);
					//world.t[j].Resize(1.01f);
					//Thread.sleep(0);
			}
		}*/
	}
}
