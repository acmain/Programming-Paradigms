import java.awt.Graphics;

public class Car extends Sprite {
	private String description;
	private GasTank gasTank;
	private Engine engine;
	
	//constructor
	Car(String description, int capacity, Engine engine, String jpgName){
		super(jpgName);
		
		//set car description
		if(description.length() == 0)
			this.description = "Generic Car";
		else
			this.description = description;
		
		//set engine info
		if(engine == null)
			this.engine = new Engine("", 0, 0);
		else
			this.engine = engine;
		
		this.gasTank = new GasTank(capacity);
	}
	
	public String getDescription() {
		return description + " (engine: " + engine.getDescription() + "), fuel: " + String.format("%.2f",gasTank.getLevel()) + "/" + gasTank.getCapacity() + ", location (" + getX() + "," + getY() + ")";
	}
	
	public double getFuelLevel() {
		return gasTank.getLevel();
	}
	
	public int getMPG() {
		return engine.getMpg();
	}
	
	public void fillUp() {
		gasTank.setLevel(gasTank.getCapacity());
	}
	
	public int getMaxSpeed() {
		return engine.getSpeed();
	}
	
	public double drive(int distance, double xRatio, double yRatio) {
		//calculate fuel used
		double gal = (double)distance / getMPG();
		double priorFuel = getFuelLevel();
		gasTank.setLevel(priorFuel - gal);
		
		//find distance that can be traveled before running out of fuel if would run out before goal distance reached
		if(getFuelLevel() == 0)
		{
			distance = (int) (priorFuel * getMPG());
			double d = distance;
			if(d != 0)
				System.out.printf("Ran out of gas after driving %.2f miles.\n", d);
		}
			
		//calculate and change x and y coords
		//determine distance traveled per 1 movement of xRatio and 1 movement of yRatio
		double hyp = Math.sqrt((xRatio * xRatio) + (yRatio * yRatio));
		//determine number of times this distance is traveled to reach full distance
		double count = (distance / hyp);
		
		//determine each coordinate based on number of movements by each ratio
		setX((int) (getX() + (xRatio * count)));
		setY((int) (getY() + (yRatio * count)));
		
		return distance;
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
	}
}
