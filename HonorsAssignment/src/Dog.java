import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;

public  class Dog extends Graphic implements Runnable{
	private Random rand = new Random();
	private int speed;
	private int xDirection = rand.nextBoolean() ? 1 : -1;
	private int yDirection = rand.nextBoolean() ? 1 : -1;
	private Thread thread = new Thread(this);
	private static boolean running;

	public Dog(int speed, int width, int height) {
		super("dog.png", width, height);
		this.speed = speed;
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	public synchronized void move(int speed) {
		if(xEdge())
			xDirection *= -1;

		if(yEdge())
			yDirection *= -1;

		setXLoc(getXLoc() + (speed * xDirection));
		setYLoc(getYLoc() + (speed * yDirection));
	}

	public synchronized void start() {
		running = true;
		thread.start();
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public synchronized void run() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(running) {
				move(1);

				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				repaint();
			}
		}
	}
}
