class Camera
{
	private Point location;
	private Point direction;
	private Point left;
	private Point right;
	private Point up;
	private Point eye;
	Frustum frustum;
	public Camera()
	{
		location = new Point(0, 0, 0);
		direction = new Point(0, 0, 1);
		left = new Point(-1, 0, 0);
		right = new Point(1, 0, 0);
		up = new Point(0, 1, 0);
		eye = new Point(0, 0, -32);
		Point[] points = new Point[8];
		points[0] = new Point(32, 25, 0);
		points[1] = new Point(32, -25, 0);
		points[2] = new Point(-32, -25, 0);
		points[3] = new Point(-32, 25, 0);
		points[4] = new Point(132, 103, 100);
		points[5] = new Point(132, -103, 100);
		points[6]  = new Point(-132, -103, 100);
		points[7] = new Point(-132, 103, 100);
		frustum = new Frustum(points);
	}
	public void Copy(Camera cam)
	{
		frustum = new Frustum(cam.frustum);
		location = new Point(cam.location.GetExX(), cam.location.GetExY(), cam.location.GetExZ());
		direction = new Point(cam.direction.GetExX(), cam.direction.GetExY(), cam.direction.GetExZ());
		left = new Point(cam.left.GetExX(), cam.left.GetExY(), cam.left.GetExZ());
		right = new Point(cam.right.GetExX(), cam.right.GetExY(), cam.right.GetExZ());
		eye = new Point(cam.eye.GetExX(), cam.eye.GetExY(), cam.eye.GetExZ());
		up = new Point(cam.up.GetExX(), cam.up.GetExY(), cam.up.GetExZ());
	}
	public void SetUp(Point p)
	{
		up = new Point(p);
	}
	public Point GetUp()
	{
		return new Point(up);
	}
	public void SetLeft(Point p)
	{
		left = new Point(p);
	}
	public Point GetLeft()
	{
		return new Point(left);
	}
	public void SetLocation(Point p)
	{
		location = new Point(p);
	}
	public Point GetLocation()
	{
		return new Point(location);
	}
	public void SetDirection(Point p)
	{
		direction = new Point(p);
		Util.NormalizeVector(direction);
	}
	public Point GetDirection()
	{
		return new Point(direction);
	}
	public Point GetEye()
	{
		return new Point(eye);
	}
	public void SetEye(Point p)
	{
		eye = new Point(p);
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
		left.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		right.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		up.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		frustum.RotateClockwiseAboutYAxis(p, degrees);
		direction.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateClockwiseAboutYAxis(p, degrees);
		location.RotateClockwiseAboutYAxis(p, degrees);
		left.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		right.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		up.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
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
			left.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			right.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			up.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
		
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
			left.RotateClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			right.RotateClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			up.RotateClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
		
	}
	public void RotateCounterClockwiseAboutZAxis(Point p, float degrees)
	{
		if(Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ())) + degrees <= 90)
		{
			frustum.RotateCounterClockwiseAboutZAxis(p, degrees);
			direction.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			eye.RotateCounterClockwiseAboutZAxis(p, degrees);
			location.RotateCounterClockwiseAboutZAxis(p, degrees);
			left.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			right.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			up.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
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
			left.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			right.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			up.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
		}
	}
	public void RotateCounterClockwiseAboutUpAxis(Point p, float degrees)
	{
		float cy = 0;
		float ccy = 0;
		float cx = 0;
		float ccx = 0;
		Point p1 = new Point(p);
		double tan = Math.atan(direction.GetExZ() / direction.GetExX());
		if(direction.GetExZ() > 0)
		{
			if(direction.GetExX() > 0)
			{
				RotateCounterClockwiseAboutYAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
				p1.RotateCounterClockwiseAboutYAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
				ccy = (float)(90d - Util.RadiansToDegrees(tan));
			}
			else if(direction.GetExX() < 0)
			{
				RotateClockwiseAboutYAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
				p1.RotateClockwiseAboutYAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
				cy = (float)(90d + Util.RadiansToDegrees(tan));
			}
		}
		else if(direction.GetExZ() < 0)
		{
			if(direction.GetExX() < 0)
			{
				RotateClockwiseAboutYAxis(location, (float) (Util.RadiansToDegrees(tan) + 90d));
				p1.RotateClockwiseAboutYAxis(location, (float) (Util.RadiansToDegrees(tan) + 90d));
				cy = (float) (Util.RadiansToDegrees(tan) + 90d);
			}
			else if(direction.GetExX() > 0)
			{
				RotateCounterClockwiseAboutYAxis(location, (float) (-Util.RadiansToDegrees(tan) + 90d));
				p1.RotateCounterClockwiseAboutYAxis(location, (float) (-Util.RadiansToDegrees(tan) + 90d));
				ccy = (float) (-Util.RadiansToDegrees(tan) + 90d);
			}
		}
		tan = Math.atan(direction.GetExY() / direction.GetExZ());
		if(direction.GetExY() > 0)
		{
			if(direction.GetExZ() > 0)
			{
				RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
				p1.RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
				cx = (float)(Util.RadiansToDegrees(tan));
			}
			else if(direction.GetExZ() < 0)
			{
				RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
				p1.RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
				ccx = (float)(-Util.RadiansToDegrees(tan));
			}
		}
		else if(direction.GetExY() < 0)
		{
			if(direction.GetExZ() < 0)
			{
				RotateClockwiseAboutXAxis(location, (float) (Util.RadiansToDegrees(tan)));
				p1.RotateClockwiseAboutXAxis(location, (float) (Util.RadiansToDegrees(tan)));
				cx = (float) (Util.RadiansToDegrees(tan)); 
			}
			else if(direction.GetExZ() > 0)
			{
				RotateCounterClockwiseAboutXAxis(location, (float) (-Util.RadiansToDegrees(tan)));
				p1.RotateCounterClockwiseAboutXAxis(location, (float) (-Util.RadiansToDegrees(tan)));
				ccx = (float) (-Util.RadiansToDegrees(tan));
			}
		}
		frustum.RotateCounterClockwiseAboutYAxis(p1, degrees);
		direction.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateCounterClockwiseAboutYAxis(p1, degrees);
		location.RotateCounterClockwiseAboutYAxis(p1, degrees);
		left.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		right.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		up.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);

		
	}
	public void RotateClockwiseAboutUpAxis(Point p, float degrees)
	{
		
	}
	public void RotateCounterClockwiseAboutRightAxis(Point p, float degrees)
	{
		
	}
	public void RotateClockwiseAboutRightAxis(Point p, float degrees)
	{
		
	}
	public void RotateCounterClockwiseAboutForwardAxis(Point p, float degrees)
	{
		
	}
	public void RotateClockwiseAboutForwardAxis(Point p, float degrees)
	{
		
	}
	public void MoveForward(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, direction, distance);
        Util.MovePointAlongVector(location, direction, distance);
		Util.MovePointAlongVector(eye, direction, distance);
	}
	public void MoveBackward(float distance)
	{
		Point newdir = new Point(-direction.GetExX(), -direction.GetExY(), -direction.GetExZ());
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, newdir, distance);
        Util.MovePointAlongVector(location, newdir, distance);
		Util.MovePointAlongVector(eye, newdir, distance);
	}
	public void MoveLeft(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, left, distance);
        Util.MovePointAlongVector(location, left, distance);
		Util.MovePointAlongVector(eye, left, distance);
	}
	public void MoveRight(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, right, distance);
        Util.MovePointAlongVector(location, right, distance);
		Util.MovePointAlongVector(eye, right, distance);
	}
    public void MoveUp(float distance)
	{
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, up, distance);
        Util.MovePointAlongVector(location, up, distance);
		Util.MovePointAlongVector(eye, up, distance);
	}
	public void MoveDown(float distance)
	{
        Point newdir = new Point(-up.GetExX(), -up.GetExY(), -up.GetExZ());
		for(Point p : frustum.points)
			Util.MovePointAlongVector(p, newdir, distance);
        Util.MovePointAlongVector(location, newdir, distance);
		Util.MovePointAlongVector(eye, newdir, distance);
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
		Camera cam = new Camera();
		cam.Copy(this);
		double tan = Math.atan(direction.GetExZ() / direction.GetExX());
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
		
		
		
		tan = Math.atan(up.GetExY() / up.GetExX());
		if(cam.up.GetExY() > 0)
		{
			if(cam.up.GetExX() > 0)
			{
				cam.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), (float)(90d - Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), (float)(90d - Util.RadiansToDegrees(tan)));
			}
			else if(cam.up.GetExX() < 0)
			{
				cam.RotateClockwiseAboutZAxis(new Point(0, 0, 0), (float)(90d + Util.RadiansToDegrees(tan)));
				for(Point p : t.points)	
					p.RotateClockwiseAboutZAxis(new Point(0, 0, 0), (float)(90d + Util.RadiansToDegrees(tan)));
			}
		}
		else if(cam.up.GetExY() < 0)
		{
			if(cam.up.GetExX() < 0)
			{
				cam.RotateClockwiseAboutZAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan) + 90d));
				for(Point p : t.points)	
					p.RotateClockwiseAboutZAxis(new Point(0, 0, 0), (float) (Util.RadiansToDegrees(tan) + 90d));
			}
			else if(cam.up.GetExX() > 0)
			{
				cam.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan) + 90d));
				for(Point p : t.points)	
					p.RotateCounterClockwiseAboutZAxis(new Point(0, 0, 0), (float) (-Util.RadiansToDegrees(tan) + 90d));
			}
		}
		//if(Math.abs(cam.GetUp().GetExY() - 1) > 0.1) cam.up.Print_Info();
		for(Point p : t.points)
		{
			if(p.GetExZ() <= cam.eye.GetExZ())
                            return null;
			/*if(p.GetExX() - 32 > cam.location.GetExZ())
				return null;
			if(-p.GetExX() - 32 > cam.location.GetExZ())
				return null;
			if((p.GetExZ() * 0.78) + 25 < cam.location.GetExY())
				return null;
			if((p.GetExZ() * -0.78) + 25 < cam.location.GetExY())
				return null;*/
		}
		object = new Triangle(t);
		for(int i = 0; i < 3; i++)
		{
			double eyetoscreen = Util.Distance_Between(cam.eye, cam.location);
			double eyetoobjectz = object.points[i].GetExZ() - cam.eye.GetExZ();
			double smalllegx = object.points[i].GetExX() - cam.eye.GetExX();
			double biglegratio = eyetoscreen / eyetoobjectz;
			object.points[i].SetX(smalllegx * biglegratio * 20);
			
			double smalllegy = object.points[i].GetExY() - cam.eye.GetExY();
			object.points[i].SetY(smalllegy * biglegratio * 20);
		}
		return object;
	}
	public Point LookAt(Point point)
	{
		/*if(!(frustum.Contains(point)))
		{
			return null;
		}*/
		Point p = new Point(point.GetExX(), point.GetExY(), point.GetExZ());
		Point object;
		Camera cam = new Camera();
		cam.Copy(this);
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
		if(p.GetExZ() <= eye.GetExZ())
                    return null;
		/*if(p.GetExX() - 32 > p.GetExZ())
                    return null;
		if(-p.GetExX() - 32 > p.GetExZ())
                    return null;
		if((p.GetExZ() * 0.78) + 25 < p.GetExY())
                    return null;
		if((p.GetExZ() * -0.78) + 25 < p.GetExY())
                    return null;*/
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
                object.SetX(object.GetExX() * 2);
                object.SetY(object.GetExY() * 2);
                object.SetZ(object.GetExZ() * 2);
		return object;
	}
}
