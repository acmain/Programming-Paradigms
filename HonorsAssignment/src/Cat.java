import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cat extends Graphic implements Runnable{	
	private int xDirection = 0;
	private int yDirection = 0;
	private Thread thread;
	private boolean running;
	
	public Cat(int width, int height) {
		super("cat.png", width, height);
		thread = new Thread(this);
	}
	
	public void setXDirection(int x) {
		xDirection = x;
	}
	
	public void setYDirection(int y) {
		yDirection = y;
	}
	
	public int getXDirection() {
		return xDirection;
	}
	
	public int getYDirection() {
		return yDirection;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}

	public synchronized void move(int speed) {
		if(!xEdge())
			if(!(getXLoc() == 0 && xDirection == -1))
				if(!(getXLoc() == getWidth() - getImage().getWidth(null) && xDirection == 1))
					setXLoc(getXLoc() + (speed * xDirection));
				
		
		if(!yEdge())
			if(!(getYLoc() == 0 && yDirection == -1))
				if(!(getYLoc() >= getHeight() - getImage().getHeight(null) && yDirection == 1))
					setYLoc(getYLoc() + (speed * yDirection));
	}
	
	public synchronized void start() {
		running = true;
		thread.start();
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
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				repaint();
			}
		}
	}
	
}
