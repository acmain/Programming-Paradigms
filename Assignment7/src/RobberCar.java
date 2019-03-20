import java.awt.Graphics;
import java.util.Random;

public class RobberCar extends Car {
	private static Engine engine = new Engine("Robber Car Engine", 20, 200);
	private Random rand = new Random();
	private int randX;
	private int randY;
	private static int captured;
	private int instanceCaptured = 0;
	private static int escaped;
	private int instanceEscaped = 0;
	
	
	public RobberCar() {
		super("Robber Car", 5000, engine, "red-car.jpg");
		super.fillUp();
		randX = rand.nextInt(11) - 5;
		randY = rand.nextInt(11) - 5;
	}
	
	public int getCaptured() {return captured;}
	public int getEscaped() {return escaped;}
	public void setCaptured(int cap) {captured = cap;}
	public void setEscaped(int esc) {escaped = esc;}
	
	@Override
	public void updateImage(Graphics g) {
		super.updateImage(g);
	}
	
	@Override
	public void updateState(int width, int height) {
		// Move the sprite if it should move
		if(randX != 0 || randY != 0)
			super.drive(4, randX, randY);
		
		//test for escape condition
		if(getX() < 0 || getY() < 0 || getX() > width || getY() > height) {
			
			//update count if instance has not already escaped
			if(instanceEscaped == 0) {
				instanceEscaped++;
				escaped++;
			}
		}
	}
	
	public void captured() {
		randX = 0;
		randY = 0;
		setImage("jail.jpg");
		
		//update count if instance has not already been captured
		if(instanceCaptured == 0) {
			instanceCaptured++;
			captured++;
		}
	}
	
	public boolean isCaptured() {
		if(instanceCaptured == 1)
			return true;
		else
			return false;
	}
	
	public boolean hasEscaped() {
		if(instanceEscaped == 1)
			return true;
		else
			return false;
	}
}
