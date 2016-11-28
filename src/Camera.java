class Camera
{
	public Point location;
	public Point direction;
	public Point left;
	public Point right;
	public Point up;
	Point eye;
	Frustum frustum;
	public Camera()
	{
		location = new Point(0, 0, 0);
		direction = new Point(0, 0, 1);
		left = new Point(-1, 0, 0);
		right = new Point(1, 0, 0);
		up = new Point(0, 1, 0);
		eye = new Point(0, 0, -5200 / 3);
		Point[] points = new Point[8];
		points[0] = new Point(640, 500, 0);
		points[1] = new Point(640, -500, 0);
		points[2] = new Point(-640, -500, 0);
		points[3] = new Point(-640, 500, 0);
		points[4] = new Point(1280, 1040, 5200);
		points[5] = new Point(1280, -1040, 5200);
		points[6]  = new Point(-1280, -1040, 5200);
		points[7] = new Point(-1280, 1040, 5200);
		frustum = new Frustum(points);
	}
	public Camera(Camera camera)
	{
		//this.frustum = new Frustum(camera.frustum);
		this.location = new Point(camera.location.GetExX(), camera.location.GetExY(), camera.location.GetExZ());
		this.direction = new Point(camera.direction.GetExX(), camera.direction.GetExY(), camera.direction.GetExZ());
		this.left = new Point(camera.left.GetExX(), camera.left.GetExY(), camera.left.GetExZ());
		this.right = new Point(camera.right.GetExX(), camera.right.GetExY(), camera.right.GetExZ());
		this.eye = new Point(camera.eye.GetExX(), camera.eye.GetExY(), camera.eye.GetExZ());
		this.up = new Point(camera.up.GetExX(), camera.up.GetExY(), camera.up.GetExZ());
	}
	
	public void Print_Info()
	{
        System.out.println("Camera info:\n=============================\n");
		System.out.println("Frustum points are:");
		for(Point p : frustum.points)
		{
			System.out.println(p.GetExX() + " " + p.GetExY() + " " + p.GetExZ());
		}
		System.out.println("=============================");
		System.out.println("Location is at:");
		System.out.println(location.GetExX() + " " + location.GetExY() + " " + location.GetExZ());
		System.out.println("=============================");
		System.out.println("Eye is at:");
		System.out.println(eye.GetExX() + " " + eye.GetExY() + " " + eye.GetExZ());
		System.out.println("=============================");
		System.out.println("Direction is at:");
		System.out.println(direction.GetExX() + " " + direction.GetExY() + " " + direction.GetExZ());
		System.out.println("==========================================================");
	}
	public void RotateCounterClockwiseAboutYAxis(Point p, float degrees)
	{
		frustum.RotateCounterClockwiseAboutYAxis(p, degrees);
		direction.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateCounterClockwiseAboutYAxis(p, degrees);
		location.RotateCounterClockwiseAboutYAxis(p, degrees);
		left.RotateCounterClockwiseAboutYAxis(p, degrees);
		right.RotateCounterClockwiseAboutYAxis(p, degrees);
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		frustum.RotateClockwiseAboutYAxis(p, degrees);
		direction.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateClockwiseAboutYAxis(p, degrees);
		location.RotateClockwiseAboutYAxis(p, degrees);
		left.RotateClockwiseAboutYAxis(p, degrees);
		right.RotateClockwiseAboutYAxis(p, degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		double angle = Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ()));
		if(direction.GetExZ() > 0 && direction.GetExY() > 0)
		{
			if(angle + degrees > 90)
				return;
		}
               if(direction.GetExZ() < 0 && direction.GetExY() < 0)
                {
                    if(angle + degrees > 90)
                        return;
                }
		
			frustum.RotateCounterClockwiseAboutXAxis(p, degrees);
			direction.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			eye.RotateCounterClockwiseAboutXAxis(p, degrees);
			location.RotateCounterClockwiseAboutXAxis(p, degrees);
			left.RotateCounterClockwiseAboutXAxis(p, degrees);
			right.RotateCounterClockwiseAboutXAxis(p, degrees);
			up.RotateCounterClockwiseAboutXAxis(p, degrees);
		
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		double angle = Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ()));
		if(direction.GetExZ() > 0 && direction.GetExY() < 0)
		{
			if(angle + degrees > 90)
				return;
		}
                if(direction.GetExZ() < 0 && direction.GetExY() > 0)
                {
                    if(angle + degrees > 90)
                        return;
                }
			frustum.RotateClockwiseAboutXAxis(p, degrees);
			direction.RotateClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			eye.RotateClockwiseAboutXAxis(p, degrees);
			location.RotateClockwiseAboutXAxis(p, degrees);
			left.RotateClockwiseAboutXAxis(p, degrees);
			right.RotateClockwiseAboutXAxis(p, degrees);
			up.RotateClockwiseAboutXAxis(p, degrees);
		
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		if(Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ())) + degrees <= 90)
		{
			frustum.RotateCounterClockwiseAboutZAxis(p, degrees);
			direction.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			eye.RotateCounterClockwiseAboutZAxis(p, degrees);
			location.RotateCounterClockwiseAboutZAxis(p, degrees);
			left.RotateCounterClockwiseAboutZAxis(p, degrees);
			right.RotateCounterClockwiseAboutZAxis(p, degrees);
			up.RotateCounterClockwiseAboutZAxis(p, degrees);
		}
	}
	public void RotateClockwiseAboutZAxis(Point p, float degrees)
	{
		if(Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ())) + degrees <= 90)
		{
			frustum.RotateClockwiseAboutZAxis(p, degrees);
			direction.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			eye.RotateClockwiseAboutZAxis(p, degrees);
			location.RotateClockwiseAboutZAxis(p, degrees);
			left.RotateClockwiseAboutZAxis(p, degrees);
			right.RotateClockwiseAboutZAxis(p, degrees);
			up.RotateClockwiseAboutZAxis(p, degrees);
		}
	}
	public void MoveForward(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, direction, distance);
        frustum.UpdateBounds();
		Util.MovePointAlongVector(location, direction, distance);
		Util.MovePointAlongVector(eye, direction, distance);
	}
	public void MoveBackward(float distance)
	{
		Point newdir = new Point(-direction.GetExX(), -direction.GetExY(), -direction.GetExZ());
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, newdir, distance);
        frustum.UpdateBounds();
		Util.MovePointAlongVector(location, newdir, distance);
		Util.MovePointAlongVector(eye, newdir, distance);
	}
	public void MoveLeft(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, left, distance);
        frustum.UpdateBounds();
		Util.MovePointAlongVector(location, left, distance);
		Util.MovePointAlongVector(eye, left, distance);
	}
	public void MoveRight(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, right, distance);
        frustum.UpdateBounds();
		Util.MovePointAlongVector(location, right, distance);
		Util.MovePointAlongVector(eye, right, distance);
	}
	public Triangle LookAt(Triangle triangle)
	{
		/*for(Point p : triangle.points)
		{
			if(frustum.Contains(p) == true)
				return null;
		}*/
		/*if(!(frustum.Contains(triangle.points[0]) || frustum.Contains(triangle.points[1]) || frustum.Contains(triangle.points[2])))
		{
			return null;
		}*/
		Triangle t = new Triangle(triangle);
		Triangle object;
		Camera cam = new Camera(this);
		double tan = Math.atan(cam.direction.GetExX() / cam.direction.GetExZ());
		if(cam.direction.GetExZ() > 0)
		{
			if(cam.direction.GetExX() > 0)
			{
				cam.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d - Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d - Util.RadiansToDegrees(tan)));
			}
			else if(cam.direction.GetExX() < 0)
			{
				cam.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d + Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d + Util.RadiansToDegrees(tan)));
			}
		}
		else if(cam.direction.GetExZ() < 0)
		{
			if(cam.direction.GetExX() < 0)
			{
				cam.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan) + 90d));
				for(Point p : t.points)	
					p.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan) + 90d));
			}
			else if(cam.direction.GetExX() > 0)
			{
				cam.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan) + 90d));
				for(Point p : t.points)	
					p.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan) + 90d));
			}
		}
		tan = Math.atan(cam.direction.GetExY() / cam.direction.GetExZ());
		if(cam.direction.GetExY() > 0)
		{
			if(cam.direction.GetExZ() > 0)
			{
				cam.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float)(Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float)(Util.RadiansToDegrees(tan)));
			}
			else if(cam.direction.GetExZ() < 0)
			{
				cam.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float)(-Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float)(-Util.RadiansToDegrees(tan)));
			}
		}
		else if(cam.direction.GetExY() < 0)
		{
			if(cam.direction.GetExZ() < 0)
			{
				cam.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan)));
			}
			else if(cam.direction.GetExZ() > 0)
			{
				cam.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan)));
			}
		}
		object = new Triangle(t);
		for(int i = 0; i < 3; i++)
		{
			double diff_x = t.points[i].GetExX()/* - cam.eye.GetExX()*/;
			double eyetoscreen = Util.Distance_Between(cam.eye, cam.location);
			double bigleg = Util.Distance_Between(cam.eye, new Point(t.points[i].GetExX(), cam.eye.GetExY(), t.points[i].GetExZ()));
			double ratio = bigleg / eyetoscreen;
			double finalx = diff_x / ratio;
			object.points[i].SetX(cam.eye.GetExX() + finalx);
			
			double diff_y = t.points[i].GetExY()/* - cam.eye.GetExY()*/;
			double finaly = diff_y / ratio;
			object.points[i].SetY(cam.eye.GetExY() + finaly);
		}
		//cam.PrintInfo();
		return object;
	}
	public Point LookAt(Point point)
	{
		/*for(Point p : triangle.points)
		{
			if(frustum.Contains(p) == true)
				return null;
		}*/
		if(!(frustum.Contains(point)))
		{
			return null;
		}
		Point p = new Point(point.GetExX(), point.GetExY(), point.GetExZ());
		Point object;
		Camera cam = new Camera(this);
		double tan = Util.atan((float)cam.direction.GetExX(), (float)cam.direction.GetExZ());
		if(cam.direction.GetExZ() > 0)
		{
			if(cam.direction.GetExX() > 0)
			{
				cam.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d - Util.RadiansToDegrees(tan)));
				p.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d - Util.RadiansToDegrees(tan)));
			}
			else if(cam.direction.GetExX() < 0)
			{
				cam.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d + Util.RadiansToDegrees(tan)));
				p.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float)(90d + Util.RadiansToDegrees(tan)));
			}
		}
		else if(cam.direction.GetExZ() < 0)
		{
			if(cam.direction.GetExX() < 0)
			{
				cam.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan) + 90d));
				p.RotateClockwiseAboutYAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan) + 90d));
			}
			else if(cam.direction.GetExX() > 0)
			{
				cam.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan) + 90d));
				p.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan) + 90d));
			}
		}
		tan = Util.atan((float)cam.direction.GetExY(), (float)cam.direction.GetExZ());
		if(cam.direction.GetExY() > 0)
		{
			if(cam.direction.GetExZ() > 0)
			{
				cam.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float)(Util.RadiansToDegrees(tan)));
				p.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float)(Util.RadiansToDegrees(tan)));
			}
			else if(cam.direction.GetExZ() < 0)
			{
				cam.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float)(-Util.RadiansToDegrees(tan)));
				p.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float)(-Util.RadiansToDegrees(tan)));
			}
		}
		else if(cam.direction.GetExY() < 0)
		{
			if(cam.direction.GetExZ() < 0)
			{
				cam.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan)));
				p.RotateClockwiseAboutXAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan)));
			}
			else if(cam.direction.GetExZ() > 0)
			{
				cam.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan)));
				p.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan)));
			}
		}
		object = new Point(p.GetExX(), p.GetExY(), p.GetExZ());
		double diff_x = p.GetExX()/* - cam.eye.GetExX()*/;
		double eyetoscreen = Util.Distance_Between(cam.eye, cam.location);
		double bigleg = Util.Distance_Between(cam.eye, new Point(p.GetExX(), cam.eye.GetExY(), p.GetExZ()));
		double ratio = bigleg / eyetoscreen;
		double finalx = diff_x / ratio;
		object.SetX(cam.eye.GetExX() + finalx);
		double diff_y = p.GetExY()/* - cam.eye.GetExY()*/;
		double finaly = diff_y / ratio;
		object.SetY(cam.eye.GetExY() + finaly);
		//cam.PrintInfo();
		return object;
	}
}
