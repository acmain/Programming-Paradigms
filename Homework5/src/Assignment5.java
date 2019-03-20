import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Assignment5 {
	
	public static int intParse(String text)
	{
		int i = 0;
		try {
			while(i < 1){
				i = Integer.parseInt(JOptionPane.showInputDialog(text));
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid data entered. Exiting..." );
			System.exit(0);
		}
		
		return i;
	}
	
	public static double doubleParse(String text)
	{
		double d = 0;
		try {
			d = Double.parseDouble(JOptionPane.showInputDialog(text));
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid data entered. Exiting..." );
			System.exit(0);
		}
		
		return d;
	}

	public static void main( String[] args )
	{
		
		
		String carDescription = JOptionPane.showInputDialog("Please enter the car's description");
		int capacity = intParse("Please enter the fuel tank capacity");
		String engDescription = JOptionPane.showInputDialog("Please enter the engine's description");
		int mpg = intParse("Please enter the miles per gallon");
		int speed = intParse("Please eter the max speed");
		
		Engine engine = new Engine(engDescription, mpg, speed);
		Car car = new Car(carDescription, capacity, engine);
		
		JOptionPane.showMessageDialog(null, car.getDescription() );
		
		car.fillUp();
		
		final int legs = intParse("Please enter the number of legs on the trip");
		
		int[] x = new int[legs];
		int[] y = new int[legs];
		
		for(int i = 0; i < legs; i++) {
			int distance = intParse("Please enter the distance for leg " + (i+1) + ":");
			double xRatio = doubleParse("Please enter the x ratio for leg " + (i+1) + ":");
			double yRatio = doubleParse("Please enter the y ratio for leg " + (i+1) + ":");
		
			car.drive(distance, xRatio, yRatio);
			x[i] = car.getX();
			y[i] = car.getY();
		}
		
		DrivePanel DrivePanel = new DrivePanel(x, y, legs);
		
		JFrame display = new JFrame();
		
		display.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
		display.add(DrivePanel);
		display.setSize(600, 600);
		display.setVisible(true);
				
		
				
		
		
		
	}
	
}
