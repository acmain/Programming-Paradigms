import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class Sprite
{
	private int locationX;
	private int locationY;
	private Image image;

	public Sprite(String jpgName)
	{
		setImage(jpgName);
		locationX = 0;
		locationY = 0;
		
	}
	
	public int getX() {	return locationX; }
	public int getY() {	return locationY; }
	public void setX(int x) { locationX = x; }
	public void setY(int y) { locationY = y; }
	
	public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
	}
	public Image getImage() { return image; }
	
	public void updateImage(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), 60, 60, null);
	}
	
	public void updateState(int width, int length) {
		
	}
	
	public boolean overlaps(Sprite s) {
		//treat sprites as rectangles to easily determine intersect	
		Rectangle sprite1 = new Rectangle(getX(),getY(),60,60);
		Rectangle sprite2 = new Rectangle(s.getX(),s.getY(),60,60);
		
		if (sprite1.intersects(sprite2))
			return true;
		else
			return false;
	}
}