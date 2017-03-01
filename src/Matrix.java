class Matrix
{
	double[][] elements;
	public Matrix()
	{
		elements = new double[3][3];
		elements[0][0] = 1;
		elements[0][1] = 0;
		elements[0][2] = 0;
		elements[1][0] = 0;
		elements[1][1] = 1;
		elements[1][2] = 0;
		elements[2][0] = 0;
		elements[2][1] = 0;
		elements[2][2] = 1;
	}
	public Matrix(double[] elems)
	{
		elements = new double[3][3];
		elements[0][0] = elems[0];
		elements[0][1] = elems[1];
		elements[0][2] = elems[2];
		elements[1][0] = elems[3];
		elements[1][1] = elems[4];
		elements[1][2] = elems[5];
		elements[2][0] = elems[6];
		elements[2][1] = elems[7];
		elements[2][2] = elems[8];
	}
	public Matrix(Matrix m)
	{
		elements = new double[3][3];
		elements[0][0] = m.elements[0][0];
		elements[0][1] = m.elements[0][1];
		elements[0][2] = m.elements[0][2];
		elements[1][0] = m.elements[1][0];
		elements[1][1] = m.elements[1][1];
		elements[1][2] = m.elements[1][2];
		elements[2][0] = m.elements[2][0];
		elements[2][1] = m.elements[2][1];
		elements[2][2] = m.elements[2][2];
	}
	public Matrix(Axis a, float degrees)
	{
		elements = new double[3][3];
		if(a == Axis.X)
		{
			double[] elems = {	1, 0, 0,	
								0, Util.cos(degrees), Util.sin(degrees),
								0, -Util.sin(degrees), Util.cos(degrees) };
			elements[0][0] = elems[0];
			elements[0][1] = elems[1];
			elements[0][2] = elems[2];
			elements[1][0] = elems[3];
			elements[1][1] = elems[4];
			elements[1][2] = elems[5];
			elements[2][0] = elems[6];
			elements[2][1] = elems[7];
			elements[2][2] = elems[8];
		}
		else if(a == Axis.Y)
		{
			double[] elems = { 	Util.cos(degrees), 0, -Util.sin(degrees),	
								0, 1, 0,
								Util.sin(degrees), 0, Util.cos(degrees) };
			elements[0][0] = elems[0];
			elements[0][1] = elems[1];
			elements[0][2] = elems[2];
			elements[1][0] = elems[3];
			elements[1][1] = elems[4];
			elements[1][2] = elems[5];
			elements[2][0] = elems[6];
			elements[2][1] = elems[7];
			elements[2][2] = elems[8];
		}
		else
		{
			double[] elems = { 	Util.cos(degrees), Util.sin(degrees), 0,	
								-Util.sin(degrees), Util.cos(degrees), 0,
								0, 0, 1 };
			elements[0][0] = elems[0];
			elements[0][1] = elems[1];
			elements[0][2] = elems[2];
			elements[1][0] = elems[3];
			elements[1][1] = elems[4];
			elements[1][2] = elems[5];
			elements[2][0] = elems[6];
			elements[2][1] = elems[7];
			elements[2][2] = elems[8];
		}
	}
	public Point Multiply(Point p)
	{
		double x = ((p.GetExX() * elements[0][0]) + (p.GetExY() * elements[0][1]) + (p.GetExZ() * elements[0][2]));
		double y = ((p.GetExX() * elements[1][0]) + (p.GetExY() * elements[1][1]) + (p.GetExZ() * elements[1][2]));
		double z = ((p.GetExX() * elements[2][0]) + (p.GetExY() * elements[2][1]) + (p.GetExZ() * elements[2][2]));
		return new Point(x, y, z);
	}
	public Matrix Multiply(Matrix m)
	{
		double[][] values = new double[3][3];
		values[0][0] = ((this.elements[0][0] * m.elements[0][0]) + (this.elements[0][1] * m.elements[1][0])) + (this.elements[0][2] * m.elements[2][0]);
		values[0][1] = ((this.elements[0][0] * m.elements[0][1]) + (this.elements[0][1] * m.elements[1][1])) + (this.elements[0][2] * m.elements[2][1]);
		values[0][2] = ((this.elements[0][0] * m.elements[0][2]) + (this.elements[0][1] * m.elements[1][2])) + (this.elements[0][2] * m.elements[2][2]);
		values[1][0] = ((this.elements[1][0] * m.elements[0][0]) + (this.elements[1][1] * m.elements[1][0])) + (this.elements[1][2] * m.elements[2][0]);
		values[1][1] = ((this.elements[1][0] * m.elements[0][1]) + (this.elements[1][1] * m.elements[1][1])) + (this.elements[1][2] * m.elements[2][1]);
		values[1][2] = ((this.elements[1][0] * m.elements[0][2]) + (this.elements[1][1] * m.elements[1][2])) + (this.elements[1][2] * m.elements[2][2]);
		values[2][0] = ((this.elements[2][0] * m.elements[0][0]) + (this.elements[2][1] * m.elements[1][0])) + (this.elements[2][2] * m.elements[2][0]);
		values[2][1] = ((this.elements[2][0] * m.elements[0][1]) + (this.elements[2][1] * m.elements[1][1])) + (this.elements[2][2] * m.elements[2][1]);
		values[2][2] = ((this.elements[2][0] * m.elements[0][2]) + (this.elements[2][1] * m.elements[1][2])) + (this.elements[2][2] * m.elements[2][2]);
		Matrix result = new Matrix();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				result.elements[i][j] = values[i][j];
			}
		}
		return result;
	}
	public void Output()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(elements[i][j] + " ");
			}
			System.out.println();
		}
	}
}
