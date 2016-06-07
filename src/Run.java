class Run
{
	public static void main(String[] args)
	{
		World world = new World();
		for(int i = 0; i < 300; i++)
		{
			world.s.RotateClockwiseAboutXAxis(1f);
			world.s.TranslateVisiblePosition(1, 1);
			world.s.RotateClockwiseAboutZAxis(1f);
			world.s.TranslateVisiblePosition(1, 1);
			world.s.RotateClockwiseAboutYAxis(1f);
		}
	}
}
