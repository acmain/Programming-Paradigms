public class Engine {

	private String description;
	private int mpg;
	private int speed;
	
	//constructor
	Engine(String description, int mpg, int speed){
		//set engine description
		if(description.length() == 0)
			this.description = "Generic Engine";
		else
			this.description = description;
		
		//set mpg
		if(mpg < 0)
			this.mpg = 0;
		else
			this.mpg = mpg;
		
		//set max speed
		if(speed < 0)
			this.speed = 0;
		else
			this.speed = speed;
	}
	
	public String getDescription() {
		return description + " (MPG: " + getMpg() + ", Max Speed: " + getSpeed() + ")";
	}
	
	public int getMpg() {
		return mpg;
	}
	
	public int getSpeed() {
		return speed;
	}
	
}
