import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Graphic extends Component{
	private BufferedImage image;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int x; //x coordinate
	private int y; //y coordinate
	private int width = screen.width;
	private int height = screen.height;

	public Graphic(String name, int width, int height) {
		setImage(name);
		x = 0;
		y = 0;
		this.width = width;
		this.height = height;
	}
	
	public Graphic() {
		x = 0;
		y = 0;
	}
	
	public void setXLoc(int x) { this.x = x; }
	public void setYLoc(int y) { this.y = y; }
	public int getXLoc() { return x; }
	public int getYLoc() { return y; }
	public int getHeight() { return height; }
	public int getWidth() { return width; }

	public void setImage(String name) {
		try {
            image = ImageIO.read(new File(name));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
	}
	
	public Image getImage() { return image; }
	
	public boolean xEdge() {
		if(getXLoc() < 0)
			return true;
		else if (getXLoc() > width  - image.getWidth())
			return true;
		else
			return false;
	}
	
	public boolean yEdge() {
		if(getYLoc() < 0)
			return true;
		else if (getYLoc() > height - image.getHeight())
			return true;
		else
			return false;
	}
	
	public void paint(Graphics g) {
		g.drawImage(getImage(), getXLoc(), getYLoc(), null);
	}

	public boolean overlaps(Graphic g) {
		Rectangle r1 = new Rectangle(x,y,image.getWidth(),image.getHeight());
		Rectangle r2 = new Rectangle(g.getXLoc(),g.getYLoc(),g.getImage().getWidth(null),g.getImage().getHeight(null));
		if(r1.intersects(r2))
			return true;
		else
			return false;
	}
}
