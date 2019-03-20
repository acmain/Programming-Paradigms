import java.awt.Graphics;
import java.util.Random;

public class RobberCar extends Car {
	private static Engine engine = new Engine("Robber Car Engine", 20, 200);
	private Random rand = new Random();
	private int randX;
	private int randY;
	
	
	public RobberCar() {
		super("Robber Car", 20, engine, "red-car.jpg");
		super.fillUp();
		randX = rand.nextInt(11) - 5;
		randY = rand.nextInt(11) - 5;
		System.out.println(randX + " " + randY);
	}
	
	@Override
	public void update(Graphics g) {
        // Move the sprite if it should move
		if(randX != 0 || randY != 0)
			super.drive(40, randX, randY);
		super.update(g);
	}
}
