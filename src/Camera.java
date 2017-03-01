class Camera
{
	private Point location;
	private Point direction;
	private Point left;
	private Point right;
	private Point up;
	private Point eye;
	Frustum frustum;
	Matrix RotMat;
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
		RotMat = new Matrix();
	}
	public void Copy(Camera cam)
	{
		//frustum = new Frustum(cam.frustum);
		location = new Point(cam.location.GetExX(), cam.location.GetExY(), cam.location.GetExZ());
		direction = new Point(cam.direction.GetExX(), cam.direction.GetExY(), cam.direction.GetExZ());
		left = new Point(cam.left.GetExX(), cam.left.GetExY(), cam.left.GetExZ());
		right = new Point(cam.right.GetExX(), cam.right.GetExY(), cam.right.GetExZ());
		eye = new Point(cam.eye.GetExX(), cam.eye.GetExY(), cam.eye.GetExZ());
		up = new Point(cam.up.GetExX(), cam.up.GetExY(), cam.up.GetExZ());
		this.RotMat = new Matrix(cam.RotMat);
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
		//frustum.RotateCounterClockwiseAboutYAxis(p, degrees);
		direction.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateCounterClockwiseAboutYAxis(p, degrees);
		location.RotateCounterClockwiseAboutYAxis(p, degrees);
		left.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		right.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		up.RotateCounterClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
	}
	public void RotateClockwiseAboutYAxis(Point p, float degrees)
	{
		//frustum.RotateClockwiseAboutYAxis(p, degrees);
		direction.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		eye.RotateClockwiseAboutYAxis(p, degrees);
		location.RotateClockwiseAboutYAxis(p, degrees);
		left.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		right.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
		up.RotateClockwiseAboutYAxis(new Point(0, 0, 0), degrees);
	}
	public void RotateCounterClockwiseAboutXAxis(Point p, float degrees)
	{
		/*double angle = Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ()));
		if(direction.GetExZ() > 0 && direction.GetExY() > 0)
		{
			if(angle + degrees > 90)
				return;
		}
        if(direction.GetExZ() < 0 && direction.GetExY() < 0)
           {
           if(angle + degrees > 90)
                        return;
                }*/
		
			//frustum.RotateCounterClockwiseAboutXAxis(p, degrees);
			direction.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			eye.RotateCounterClockwiseAboutXAxis(p, degrees);
			location.RotateCounterClockwiseAboutXAxis(p, degrees);
			left.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			right.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
			up.RotateCounterClockwiseAboutXAxis(new Point(0, 0, 0), degrees);
		
	}
	public void RotateClockwiseAboutXAxis(Point p, float degrees)
	{
		/*double angle = Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ()));
		if(direction.GetExZ() > 0 && direction.GetExY() < 0)
		{
			if(angle + degrees > 90)
				return;
		}
                if(direction.GetExZ() < 0 && direction.GetExY() > 0)
                {
                    if(angle + degrees > 90)
                        return;
                }*/
			//frustum.RotateClockwiseAboutXAxis(p, degrees);
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
			//frustum.RotateCounterClockwiseAboutZAxis(p, degrees);
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
			//frustum.RotateClockwiseAboutZAxis(p, degrees);
			direction.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			eye.RotateClockwiseAboutZAxis(p, degrees);
			location.RotateClockwiseAboutZAxis(p, degrees);
			left.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			right.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
			up.RotateClockwiseAboutZAxis(new Point(0, 0, 0), degrees);
		}
	}
	public void RotateCounterClockwiseAboutUpAxis(float degrees)
	{
            Rotation rx = new Rotation(true, Axis.X, 0.0f);
            Rotation ry = new Rotation(true, Axis.Y, 0.0f);
            Rotation rz = new Rotation(true, Axis.Z, 0.0f);
            double tan = Math.atan(direction.GetExZ() / direction.GetExX());
            if(direction.GetExZ() > 0)
            {
                    if(direction.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutYAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                            ry = new Rotation(false, Axis.Y, (float)(90d - Util.RadiansToDegrees(tan)));
                    }
                    else if(direction.GetExX() < 0)
                    {
                            RotateClockwiseAboutYAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                            ry = new Rotation(true, Axis.Y, (float)(90d + Util.RadiansToDegrees(tan)));
                    }
            }
            else if(direction.GetExZ() < 0)
            {
                    if(direction.GetExX() < 0)
                    {
                            RotateClockwiseAboutYAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                            ry = new Rotation(true, Axis.Y, (float)(Util.RadiansToDegrees(tan) + 90d));
                    }
                    else if(direction.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutYAxis(location, (float) (-Util.RadiansToDegrees(tan) + 90d));
                            ry = new Rotation(false, Axis.Y, (float)(-Util.RadiansToDegrees(tan) + 90d));
                    }
            }
            tan = Math.atan(direction.GetExY() / direction.GetExZ());
            if(direction.GetExY() > 0)
            {
                    if(direction.GetExZ() > 0)
                    {
                            RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                            rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                    }
                    else if(direction.GetExZ() < 0)
                    {
                            RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                            rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                    }
            }
            else if(direction.GetExY() < 0)
            {
                    if(direction.GetExZ() < 0)
                    {
                            RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                            rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                    }
                    else if(direction.GetExZ() > 0)
                    {
                            RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                            rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                    }
            }
            tan = Math.atan(up.GetExY() / up.GetExX());
            if(up.GetExY() > 0)
            {
                    if(up.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutZAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                            rz = new Rotation(false, Axis.Z, (float)(90d - Util.RadiansToDegrees(tan)));
                    }
                    else if(up.GetExX() < 0)
                    {
                            RotateClockwiseAboutZAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                            rz = new Rotation(true, Axis.Z, (float)(90d + Util.RadiansToDegrees(tan)));
                    }
            }
            else if(up.GetExY() < 0)
            {
                    if(up.GetExX() < 0)
                    {
                            RotateClockwiseAboutZAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                            rz = new Rotation(true, Axis.Z, (float)(Util.RadiansToDegrees(tan) + 90d));
                    }
                    else if(up.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutZAxis(location, (float)(-Util.RadiansToDegrees(tan) + 90d));
                            rz = new Rotation(false, Axis.Z, (float)(-Util.RadiansToDegrees(tan) + 90d));
                    }
            }
            RotateCounterClockwiseAboutYAxis(location, degrees);
            //Undo_Rotation(rz);
            Undo_Rotation(rx);
            Undo_Rotation(ry);
            UpdateRotationMatrix();
	}
	public void RotateClockwiseAboutUpAxis(float degrees)
	{
            Rotation rx = new Rotation(true, Axis.X, 0.0f);
            Rotation ry = new Rotation(true, Axis.Y, 0.0f);
            Rotation rz = new Rotation(true, Axis.Z, 0.0f);
            double tan = Math.atan(direction.GetExZ() / direction.GetExX());
            if(direction.GetExZ() > 0)
            {
                    if(direction.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutYAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                            ry = new Rotation(false, Axis.Y, (float)(90d - Util.RadiansToDegrees(tan)));
                    }
                    else if(direction.GetExX() < 0)
                    {
                            RotateClockwiseAboutYAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                            ry = new Rotation(true, Axis.Y, (float)(90d + Util.RadiansToDegrees(tan)));
                    }
            }
            else if(direction.GetExZ() < 0)
            {
                    if(direction.GetExX() < 0)
                    {
                            RotateClockwiseAboutYAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                            ry = new Rotation(true, Axis.Y, (float)(Util.RadiansToDegrees(tan) + 90d));
                    }
                    else if(direction.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutYAxis(location, (float) (-Util.RadiansToDegrees(tan) + 90d));
                            ry = new Rotation(false, Axis.Y, (float)(-Util.RadiansToDegrees(tan) + 90d));
                    }
            }
            tan = Math.atan(direction.GetExY() / direction.GetExZ());
            if(direction.GetExY() > 0)
            {
                    if(direction.GetExZ() > 0)
                    {
                            RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                            rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                    }
                    else if(direction.GetExZ() < 0)
                    {
                            RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                            rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                    }
            }
            else if(direction.GetExY() < 0)
            {
                    if(direction.GetExZ() < 0)
                    {
                            RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                            rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                    }
                    else if(direction.GetExZ() > 0)
                    {
                            RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                            rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                    }
            }
            tan = Math.atan(up.GetExY() / up.GetExX());
            if(up.GetExY() > 0)
            {
                    if(up.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutZAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                            rz = new Rotation(false, Axis.Z, (float)(90d - Util.RadiansToDegrees(tan)));
                    }
                    else if(up.GetExX() < 0)
                    {
                            RotateClockwiseAboutZAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                            rz = new Rotation(true, Axis.Z, (float)(90d + Util.RadiansToDegrees(tan)));
                    }
            }
            else if(up.GetExY() < 0)
            {
                    if(up.GetExX() < 0)
                    {
                            RotateClockwiseAboutZAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                            rz = new Rotation(true, Axis.Z, (float)(Util.RadiansToDegrees(tan) + 90d));
                    }
                    else if(up.GetExX() > 0)
                    {
                            RotateCounterClockwiseAboutZAxis(location, (float)(-Util.RadiansToDegrees(tan) + 90d));
                            rz = new Rotation(false, Axis.Z, (float)(-Util.RadiansToDegrees(tan) + 90d));
                    }
            }
            RotateClockwiseAboutYAxis(location, degrees);
            //Undo_Rotation(rz);
            Undo_Rotation(rx);
            Undo_Rotation(ry);
            UpdateRotationMatrix();
        }
	public void RotateCounterClockwiseAboutRightAxis(float degrees)
	{
		double angle = Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ()));
		/*if(direction.GetExZ() > 0 && direction.GetExY() < 0)
		{
			if(angle + degrees > 70)
				return;
		}*/
        if(direction.GetExZ() < 0 && direction.GetExY() > 0)
        {
        	if(angle + degrees > 70)
        	return;
        }
		Rotation rx = new Rotation(true, Axis.X, 0.0f);
        Rotation ry = new Rotation(true, Axis.Y, 0.0f);
        Rotation rz = new Rotation(true, Axis.Z, 0.0f);
        double tan = Math.atan(direction.GetExZ() / direction.GetExX());
        if(direction.GetExZ() > 0)
        {
                if(direction.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutYAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                        ry = new Rotation(false, Axis.Y, (float)(90d - Util.RadiansToDegrees(tan)));
                }
                else if(direction.GetExX() < 0)
                {
                        RotateClockwiseAboutYAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                        ry = new Rotation(true, Axis.Y, (float)(90d + Util.RadiansToDegrees(tan)));
                }
        }
        else if(direction.GetExZ() < 0)
        {
                if(direction.GetExX() < 0)
                {
                        RotateClockwiseAboutYAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                        ry = new Rotation(true, Axis.Y, (float)(Util.RadiansToDegrees(tan) + 90d));
                }
                else if(direction.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutYAxis(location, (float)(-Util.RadiansToDegrees(tan) + 90d));
                        ry = new Rotation(false, Axis.Y, (float)(-Util.RadiansToDegrees(tan) + 90d));
                }
        }
        tan = Math.atan(direction.GetExY() / direction.GetExZ());
        if(direction.GetExY() > 0)
        {
                if(direction.GetExZ() > 0)
                {
                        RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                        rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                }
                else if(direction.GetExZ() < 0)
                {
                        RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                        rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                }
        }
        else if(direction.GetExY() < 0)
        {
                if(direction.GetExZ() < 0)
                {
                        RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                        rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                }
                else if(direction.GetExZ() > 0)
                {
                        RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                        rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                }
        }
        tan = Math.atan(up.GetExY() / up.GetExX());
        if(up.GetExY() > 0)
        {
                if(up.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutZAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                        rz = new Rotation(false, Axis.Z, (float)(90d - Util.RadiansToDegrees(tan)));
                }
                else if(up.GetExX() < 0)
                {
                        RotateClockwiseAboutZAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                        rz = new Rotation(true, Axis.Z, (float)(90d + Util.RadiansToDegrees(tan)));
                }
        }
        else if(up.GetExY() < 0)
        {
                if(up.GetExX() < 0)
                {
                        RotateClockwiseAboutZAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                        rz = new Rotation(true, Axis.Z, (float)(Util.RadiansToDegrees(tan) + 90d));
                }
                else if(up.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutZAxis(location, (float)(-Util.RadiansToDegrees(tan) + 90d));
                        rz = new Rotation(false, Axis.Z, (float)(-Util.RadiansToDegrees(tan) + 90d));
                }
        }
        RotateCounterClockwiseAboutXAxis(location, degrees);
        //Undo_Rotation(rz);
        Undo_Rotation(rx);
        Undo_Rotation(ry);
        UpdateRotationMatrix();
    }
	public void RotateClockwiseAboutRightAxis(float degrees)
	{
		//direction.Print_Info();
		double angle = Util.Get_Angle_Between_Vectors(direction, new Point(0, 0, 0), new Point(direction.GetExX(), 0, direction.GetExZ()));
		if(direction.GetExZ() > 0 && direction.GetExY() < 0)
		{
			if(angle + degrees > 70)
				return;
		}
        /*if(direction.GetExZ() < 0 && direction.GetExY() > 0)
        {
        	if(angle + degrees > 70)
        	return;
        }*/
		Rotation rx = new Rotation(true, Axis.X, 0.0f);
        Rotation ry = new Rotation(true, Axis.Y, 0.0f);
        Rotation rz = new Rotation(true, Axis.Z, 0.0f);
        double tan = Math.atan(direction.GetExZ() / direction.GetExX());
        if(direction.GetExZ() > 0)
        {
                if(direction.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutYAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                        ry = new Rotation(false, Axis.Y, (float)(90d - Util.RadiansToDegrees(tan)));
                }
                else if(direction.GetExX() < 0)
                {
                        RotateClockwiseAboutYAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                        ry = new Rotation(true, Axis.Y, (float)(90d + Util.RadiansToDegrees(tan)));
                }
        }
        else if(direction.GetExZ() < 0)
        {
                if(direction.GetExX() < 0)
                {
                        RotateClockwiseAboutYAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                        ry = new Rotation(true, Axis.Y, (float)(Util.RadiansToDegrees(tan) + 90d));
                }
                else if(direction.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutYAxis(location, (float)(-Util.RadiansToDegrees(tan) + 90d));
                        ry = new Rotation(false, Axis.Y, (float)(-Util.RadiansToDegrees(tan) + 90d));
                }
        }
        tan = Math.atan(direction.GetExY() / direction.GetExZ());
        if(direction.GetExY() > 0)
        {
                if(direction.GetExZ() > 0)
                {
                        RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                        rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                }
                else if(direction.GetExZ() < 0)
                {
                        RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan) + 180));
                        rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan) + 180));
                }
        }
        else if(direction.GetExY() < 0)
        {
        	    if(direction.GetExZ() < 0)
                {
        	    	//direction.Print_Info();
                        RotateClockwiseAboutXAxis(location, (float)(Util.RadiansToDegrees(tan)));
                        rx = new Rotation(true, Axis.X, (float)(Util.RadiansToDegrees(tan)));
                        //direction.Print_Info();
                        //System.out.println("DONE");
                }
                else if(direction.GetExZ() > 0)
                {
                	    RotateCounterClockwiseAboutXAxis(location, (float)(-Util.RadiansToDegrees(tan)));
                        rx = new Rotation(false, Axis.X, (float)(-Util.RadiansToDegrees(tan)));
                }
        }
        tan = Math.atan(up.GetExY() / up.GetExX());
        if(up.GetExY() > 0)
        {
                if(up.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutZAxis(location, (float)(90d - Util.RadiansToDegrees(tan)));
                        rz = new Rotation(false, Axis.Z, (float)(90d - Util.RadiansToDegrees(tan)));
                }
                else if(up.GetExX() < 0)
                {
                        RotateClockwiseAboutZAxis(location, (float)(90d + Util.RadiansToDegrees(tan)));
                        rz = new Rotation(true, Axis.Z, (float)(90d + Util.RadiansToDegrees(tan)));
                }
        }
        else if(up.GetExY() < 0)
        {
                if(up.GetExX() < 0)
                {
                        RotateClockwiseAboutZAxis(location, (float)(Util.RadiansToDegrees(tan) + 90d));
                        rz = new Rotation(true, Axis.Z, (float)(Util.RadiansToDegrees(tan) + 90d));
                }
                else if(up.GetExX() > 0)
                {
                        RotateCounterClockwiseAboutZAxis(location, (float)(-Util.RadiansToDegrees(tan) + 90d));
                        rz = new Rotation(false, Axis.Z, (float)(-Util.RadiansToDegrees(tan) + 90d));
                }
        }
        RotateClockwiseAboutXAxis(location, degrees);
        //Undo_Rotation(rz);
        Undo_Rotation(rx);
        Undo_Rotation(ry);
        UpdateRotationMatrix();
        //direction.Print_Info();
        //System.out.println("DONE");
	}
	public void RotateCounterClockwiseAboutForwardAxis(float degrees)
	{
		
	}
	public void RotateClockwiseAboutForwardAxis(float degrees)
	{
		
	}
    public void Undo_Rotation(Rotation r)
    {
    	if(r.clockwise == true)
        {
            if(r.axis == Axis.Y)
            {
                RotateCounterClockwiseAboutYAxis(location, r.degrees);
            }
            else if(r.axis == Axis.X)
            {
                RotateCounterClockwiseAboutXAxis(location, r.degrees);
            }   
            else
            {
                RotateCounterClockwiseAboutZAxis(location, r.degrees);
            }
        }
        else
        {
            if(r.axis == Axis.Y)
            {
                RotateClockwiseAboutYAxis(location, r.degrees);
            }
            else if(r.axis == Axis.X)
            {
                RotateClockwiseAboutXAxis(location, r.degrees);
            }   
            else
            {
                RotateClockwiseAboutZAxis(location, r.degrees);
            }
        }
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
	void UpdateRotationMatrix()
	{
		Matrix xrot = new Matrix();
		Matrix yrot = new Matrix();
		double tan = Math.atan(direction.GetExZ() / direction.GetExX());
		if(direction.GetExZ() > 0)
		{
			if(direction.GetExX() > 0)
			{
				yrot = new Matrix(Axis.Y, (float)(90d - Util.RadiansToDegrees(tan)));
			}
			else if(direction.GetExX() < 0)
			{
				yrot = new Matrix(Axis.Y, (float)-(90d + Util.RadiansToDegrees(tan)));
			}
		}
		else if(direction.GetExZ() < 0)
		{
			if(direction.GetExX() < 0)
			{
				yrot = new Matrix(Axis.Y, (float)-(Util.RadiansToDegrees(tan) + 90d));
			}
			else if(direction.GetExX() > 0)
			{
				yrot = new Matrix(Axis.Y, (float)(-Util.RadiansToDegrees(tan) + 90d));
			}
		}
		tan = Math.atan(direction.GetExY() / direction.GetExZ());
		if(direction.GetExY() > 0)
		{
			if(direction.GetExZ() > 0)
			{
				xrot = new Matrix(Axis.X, (float)-(Util.RadiansToDegrees(tan)));
			}
			else if(direction.GetExZ() < 0)
			{
				xrot = new Matrix(Axis.X, (float)(-Util.RadiansToDegrees(tan) + 180));
			}
		}
		else if(direction.GetExY() < 0)
		{
			if(direction.GetExZ() < 0)
			{
				xrot = new Matrix(Axis.X, (float)-(Util.RadiansToDegrees(tan) + 180));
			}
			else if(direction.GetExZ() > 0)
			{
				xrot = new Matrix(Axis.X, (float)(-Util.RadiansToDegrees(tan)));
			}
		}
		RotMat = new Matrix(xrot.Multiply(yrot));
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
		Triangle object = new Triangle(triangle);
		Point Eye = new Point(eye);
		//Eye = RotMat.Multiply(Eye);
		for(int i = 0; i < 3; i++)
		{
			object.points[i] = new Point(RotMat.Multiply(t.points[i]));
		}
		for(int i = 0; i < 3; i++)
		{
			double eyetoscreen = Util.Distance_Between(Eye, location);
			double eyetoobjectz = object.points[i].GetExZ() - Eye.GetExZ();
			double smalllegx = object.points[i].GetExX() - Eye.GetExX();
			double biglegratio = eyetoscreen / eyetoobjectz;
			object.points[i].SetX(smalllegx * biglegratio * 20);
			
			double smalllegy = Eye.GetExY() - object.points[i].GetExY();
			object.points[i].SetY(smalllegy * biglegratio * 20);
		}
		return object;
	}
        public Square LookAt(Square square)
	{
		Square t = new Square(square);
		Square object = new Square(square);
		Point Eye = new Point(eye);
		RotMat.Multiply(Eye);
		for(int i = 0; i < 4; i++)
		{
			object.points[i] = new Point(RotMat.Multiply(t.points[i]));
		}
		for(int i = 0; i < 4; i++)
		{
			double eyetoscreen = Util.Distance_Between(Eye, location);
			double eyetoobjectz = object.points[i].GetExZ() - Eye.GetExZ();
			double smalllegx = object.points[i].GetExX() - Eye.GetExX();
			double biglegratio = eyetoscreen / eyetoobjectz;
			object.points[i].SetX(smalllegx * biglegratio * 20);
			
			double smalllegy = Eye.GetExY() - object.points[i].GetExY();
			object.points[i].SetY(smalllegy * biglegratio * 20);
		}
		return object;
	}
	public Point LookAt(Point point)
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
		Point Eye = new Point(eye);
		Eye = RotMat.Multiply(Eye);
		Point p = RotMat.Multiply(point);
		double eyetoscreen = Util.Distance_Between(Eye, location);
		double eyetoobjectz = p.GetExZ() - Eye.GetExZ();
		double smalllegx = p.GetExX() - Eye.GetExX();
		double biglegratio = eyetoscreen / eyetoobjectz;
		p.SetX(smalllegx * biglegratio * 20);
		double smalllegy = Eye.GetExY() - p.GetExY();
		p.SetY(smalllegy * biglegratio * 20);
		return p;
	}
}
