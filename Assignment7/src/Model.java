import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class Model
{
	private ArrayList<Sprite> cars = new ArrayList<Sprite>();
	private String lastSprite;

	Model() throws IOException {
		cars.add(new Bank());
		lastSprite = "Bank";
	}

	public synchronized void add(int x, int y) {
		Car car;
		
		//keeps track of last car type to know what to add next if something has been removed from arraylist
		if(lastSprite == "RobberCar") {
			car = new CopCar();
			lastSprite = "CopCar";
		} else {
			car = new RobberCar();
			lastSprite = "RobberCar";
		}

		car.setX(x);
		car.setY(y);
		cars.add(car);
	}

	public synchronized void update(Graphics g) {
		for(Sprite s : cars) {
			s.updateImage(g);
		}
	}

	public synchronized void updateScene(int width, int height) {
		for(Sprite s : cars) {
			s.updateState(width, height);
		}

		Iterator<Sprite> i = cars.iterator();
		while (i.hasNext()) {
			Sprite robber = i.next();

			//iterate through robbercars
			if(robber.getClass().getName() == "RobberCar") {

				//create new iterator
				for (Iterator<Sprite> j = cars.iterator(); j.hasNext();) {
					Sprite cop = j.next();

					//iterate through copcars and test for capture
					if(cop.getClass().getName() == "CopCar") {
						if(cop.overlaps(robber)) {
							((RobberCar) robber).captured();
						}
					}
				}

				//remove robber if escaped
				if(((RobberCar) robber).hasEscaped()) {
					System.out.println("I'm free!");
					i.remove();

				}
			}
		}
	}

	public int getCaptured() {
		RobberCar robber = new RobberCar();
		return robber.getCaptured();
	}

	public int getEscaped() {
		RobberCar robber = new RobberCar();
		return robber.getEscaped();
	}
	
	public synchronized void initialize() {
		//clear array list
		cars.removeAll(cars);
		//constructor
		cars.add(new Bank());
		lastSprite = "Bank";
		//reset robber counts
		RobberCar robber = new RobberCar();
		robber.setCaptured(0);
		robber.setEscaped(0);		
	}
}
