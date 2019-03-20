import java.awt.Graphics;
import java.util.Random;

public class CopCar extends Car {
	private static Engine engine = new Engine("Cop Car Engine",30, 100);
	private Random rand = new Random();
	private static int randX;
	private static int randY;
	//these two keep track of the sign of randX and randY for the instance
	private int xDir = 1;
	private int yDir = 1;
	
	public CopCar() {
		super("Cop Car", 5000, engine, "cop-car.jpg");
		super.fillUp();
		randX = rand.nextInt(11) - 5;
		randY = rand.nextInt(11) - 5;
	}
	
	@Override
	public void updateImage(Graphics g) {
		super.updateImage(g);
	}
	
	@Override
	public void updateState(int width, int height) {

		//flip variable to negative of itself if bounce is needed
		if(super.getX() <= 0 || super.getX() >= width)
			xDir *= -1;
		if(super.getY() <= 0 || super.getY() >= height)
			yDir *= -1;
		
		//decide if instance moves in + or - of each ratio
		int x = randX * xDir;
		int y = randY * yDir;
		
		// Move the sprite if it should move
		if(randX != 0 || randY != 0)
			super.drive(2, x, y);			
	}
}

