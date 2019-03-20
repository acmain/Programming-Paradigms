public class Car {
	private String description;
	private int x;
	private int y;
	private GasTank gasTank;
	private Engine engine;
	
	//constructor
	Car(String description, int capacity, Engine engine){
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
		return description + " (engine: " + engine.getDescription() + "), fuel: " + String.format("%.2f",gasTank.getLevel()) + "/" + gasTank.getCapacity() + ", location (" + x + "," + y + ")";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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
			System.out.printf("Ran out of gas after driving %.2f miles.\n", d);
		}
			
		//calculate and change x and y coords
		//determine distance traveled per 1 movement of xRatio and 1 movement of yRatio
		double hyp = Math.sqrt((xRatio * xRatio) + (yRatio * yRatio));
		//determine number of times this distance is traveled to reach full distance
		double count = (distance / hyp);
		
		//determine each coordinate based on number of movements by each ratio
		x += xRatio * count;
		y += yRatio * count;
		
		return distance;
	}
}
