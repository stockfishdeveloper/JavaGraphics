import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;

class World extends JComponent implements Runnable, MouseMotionListener,
		MouseListener, MouseWheelListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private float fps;
	private boolean running;
	private int frames;
	static BufferedImage screen;
	BufferedImage blank;
	int currx = 0;
	int curry = 0;
	public static Camera camera;
	SampleMesh sample;
	static double[][] distancefromscreen = new double[1280][1000];
	volatile ArrayList<MouseEvent> events;
	public ArrayList<Cube> cubes;

	public World() {
		frames = 0;
		fps = 0;
		thread = new Thread(this);
		cubes = new ArrayList<>();
		boolean blue = true;
		for (int j = 0; j < 10; j++)
			for (int v = 0; v < 10; v++) {
				cubes.add(new Cube(new Point(j * 100, 0, v * 100), 10,
				(blue ? Color.blue : Color.red)));
				blue = !blue;
			}
		//cubes.add(new Cube(new Point(-5, 0, 40),10,"L:\\woodend1.png"/*Color.blue*/));
		//cubes.add(new Cube(new Point(5, 0, 40),10,"L:\\woodend1.png"/*Color.red*/));
		events = new ArrayList<>();
		camera = new Camera();
		camera.MoveBackward(20);
		sample = new SampleMesh(new Point(0, 0, 0), Color.red, 50);
		screen = new BufferedImage(1280, 1000, BufferedImage.TYPE_INT_RGB);
		blank = new BufferedImage(1280, 1000, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 1280; i++) {
			for (int j = 0; j < 1000; j++) {
				distancefromscreen[i][j] = Double.MAX_VALUE;
			}
		}
		for (int i = 0; i < screen.getWidth(); i++) {
			for (int j = 0; j < screen.getHeight(); j++) {
				screen.setRGB(i, j, 16777215);
				blank.setRGB(i, j, 16777215);
			}
		}
		this.setBounds(0, 0, 1280, 1000);
		start();
	}

	private synchronized void start() {
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized void ApplyEvents() {
		try {
			for (MouseEvent m : events) {
				if (m.getX() > currx) {
					camera.RotateClockwiseAboutUpAxis(0.5f);
				} else if (m.getX() < currx) {
					camera.RotateCounterClockwiseAboutUpAxis(0.5f);
				}
				
				if (m.getY() > curry) {
					camera.RotateClockwiseAboutRightAxis(0.5f);
				} else if (m.getY() < curry) {
					camera.RotateCounterClockwiseAboutRightAxis(0.5f);
				}				
				
				currx = m.getX();
				curry = m.getY();
			}
			events.clear();
		} catch (java.util.ConcurrentModificationException c) {
		}
	}

	public void render() {
		// ApplyEvents();
		frames++;
		Graphics2D g1 = (Graphics2D) screen.getGraphics();
		g1.setBackground(new Color(255, 255, 255, 0));
		g1.clearRect(0, 0, 1280, 1000);
		sample.Render(screen);
		for(Cube c : cubes)
			c.Render(screen);
		BufferStrategy bs = Run.frame.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		g.drawImage(screen, 0, 0, screen.getWidth(), screen.getHeight(), null);
		g.drawString("FPS: " + fps, 50, 50);
		
		// temporarily draw camera direction
		g.drawString("Camera location: " + camera.GetLocation().toString(), 50, 85);
		g.drawString("Camera direction: " + camera.GetDirection().toString(), 50, 105);
		
		g.drawString("MS per frame: " + 1000 / fps, 50, 65);
		g.dispose();
		bs.show();
		for (int i = 0; i < 1280; i++) {
			for (int j = 0; j < 1000; j++) {
				distancefromscreen[i][j] = Double.MAX_VALUE;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent m) {
		events.add(m);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		if (m.getPreciseWheelRotation() < 0)
			camera.MoveForward(10);
		else
			camera.MoveBackward(10);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent k) {
		// System.out.println("Key typed!");
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP)
			camera.MoveUp(20.0f);
		else if (k.getKeyCode() == KeyEvent.VK_DOWN)
			camera.MoveDown(20.0f);
		else if (k.getKeyCode() == KeyEvent.VK_CONTROL)
			camera.Print_Info();
		else if (k.getKeyCode() == KeyEvent.VK_A)
			camera.MoveLeft(20.0f);
		else if (k.getKeyCode() == KeyEvent.VK_D)
			camera.MoveRight(20.0f);
		else if (k.getKeyCode() == KeyEvent.VK_W)
			camera.MoveForward(20f);
		else if (k.getKeyCode() == KeyEvent.VK_S)
			camera.MoveBackward(20f);
	}
	
	@Override
	public void keyReleased(KeyEvent k) {
		// System.out.println("Key released!");
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long starttime = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;// 60 times per second
		double delta = 0;
		boolean buffstrat = false;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			if (System.currentTimeMillis() - 1000 > starttime) {
				fps = frames;
				frames = 0;
				starttime = System.currentTimeMillis();
			}
			delta = delta + ((now - lastTime) / ns);
			lastTime = now;
			/*
			 * while (delta >= 1)//Make sure update is only happening 60 times a
			 * second { //handles all of the logic restricted time
			 * ApplyEvents(); delta--; }
			 */
			ApplyEvents();
			if (buffstrat == false) {
				if (Run.frame.getBufferStrategy() == null)
					continue;
				buffstrat = true;
			}
			render();// displays to the screen unrestricted time
		}

	}
}
