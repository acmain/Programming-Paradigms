import java.awt.Graphics;
import java.util.Random;

public class CopCar extends Car {
	private static Engine engine = new Engine("Cop Car Engine",30, 100);
	private Random rand = new Random();
	private static int randX;
	private static int randY;
	
	public CopCar() {
		super("Cop Car", 30, engine, "cop-car.jpg");
		super.fillUp();
		randX = rand.nextInt(11) - 5;
		randY = rand.nextInt(11) - 5;
	}
	
	@Override
	public void update(Graphics g) {
        // Move the sprite if it should move
		if(randX != 0 || randY != 0)
			super.drive(20, randX, randY);
		super.update(g);
	}
}

