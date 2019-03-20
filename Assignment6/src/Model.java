import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;


class Model
{
    private ArrayList<Sprite> cars = new ArrayList<Sprite>();
    
    Model() throws IOException {
		int i = cars.size() % 2;
		if(i == 0)
			cars.add(new CopCar());
		else
			cars.add(new RobberCar());
    }
    
    public void add(int x, int y) {
    	int i = cars.size() % 2;
    	Car car;
    	
		if(i == 0)
			car = new CopCar();
		else
			car = new RobberCar();
    	
    	car.setX(x);
    	car.setY(y);
    	cars.add(car);
    }

    public void update(Graphics g) {
		for(int i = 0; i < cars.size(); i++)
			cars.get(i).update(g);
    }
    
	public void fillAll() {
		for(int i = 0; i < cars.size(); i++)
			((Car) cars.get(i)).fillUp();
	}
}
