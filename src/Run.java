class Run
{
	public static void main(String[] args)
	{
		World world = new World();
		/*for(int i = 0; i < 1000; i++)
		{
			world.t.Resize(1.001f);
		}*/
		/*for(int i = 0; i < 360; i++)
		{
			world.t.RotateClockwiseAboutXAxis(1f);
			world.t.TranslateVisiblePosition(1, 1);
			world.t.RotateClockwiseAboutZAxis(1f);
			world.t.TranslateVisiblePosition(1, 1);
			world.t.RotateClockwiseAboutYAxis(1f);
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
		for(int i = 0; i < 360; i++)
		{
			for(int j = 0; j < 27; j++)
			{
			world.t[j].RotateClockwiseAboutYAxis(5f);
			world.t[j].RotateClockwiseAboutXAxis(5f);
			}
		}
		/*for(int i = 0; i < 1000000; i++)
		{
			Util.Distance_Between(new Point(i, i, i), new Point(i, i, i));
			Util.GetNormalVector(new Point(i, i, i), new Point(i, i, i), new Point(i, i, i));
			Util.NormalizeVector(new Point(i, i, i));
			Util.GetDotProduct(new Point(i, i, i), new Point(i, i, i));
		}*/
	}
}
