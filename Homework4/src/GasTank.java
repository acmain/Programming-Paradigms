
public class GasTank {
	
	private int capacity;
	private double level;
	
	//constructer
	GasTank(int capacity) {
		//set capacity
		if (capacity < 0)
			this.capacity = 0;
		else
			this.capacity = capacity;
		
		level = 0;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public double getLevel() {
		return level;
	}
	
	public void setLevel(double levelIn) {
		//ensure level is within bounds of empty and full capacity
		if (levelIn < 0)
			level = 0;
		else if (levelIn > capacity)
			level = capacity;
		else
			level = levelIn;
	}

}
