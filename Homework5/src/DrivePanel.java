import java.awt.Graphics;
import javax.swing.JPanel;

public class DrivePanel extends JPanel {
	
	private int[] x;
	private int[] y;
	private int legs;
	
	public DrivePanel(int[] x, int[] y, int legs)
	{
		this.x = x;
		this.y = y;
		this.legs = legs;
		
	}
	
	public void paintComponent( Graphics g ) {
		
		super.paintComponent(g);
	
		int height = getHeight();
		
		for(int i = 0; i < legs; i++) {
			if(i == 0)
				g.drawLine(0, height, x[0], height - y[0]);
			else
				g.drawLine(x[i-1], height - y[i-1], x[i], height - y[i]);
		
			g.drawString("(" + x[i] + "," + y[i] + ")", x[i] + 10, height - y[i]);
		}
	}
}
