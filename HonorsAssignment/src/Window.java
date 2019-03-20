
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener{
	private ArrayList<Graphic> graphics = new ArrayList<Graphic>();
	private int points = 0; //score
	//menus
	private Graphic startMenu = new Graphic();
	private Graphic pauseMenu = new Graphic();
	private Graphic gameOver = new Graphic();
	private Random rand = new Random();
	//for keylistener
	private boolean[] horiz = {false,false};
	private boolean[] vert = {false,false};
	//game state
	private boolean isPaused = false;
	private boolean isStart = true;
	private boolean isEnd = false;

	public Window(){
		//initialize frame
		setTitle("Score: " + points);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(143,212,0));
		setVisible(true);
		addKeyListener(this);

		//create menus and initialize game
		createMenu("startmenu.png", startMenu);
		startMenu.setVisible(true);
		createMenu("pausemenu.png", pauseMenu);
		createMenu("gameover.png", gameOver);
		initialize();
	}

	public boolean isEnd() {
		return isEnd;
	}

	//add cat and fish to window
	public void initialize() {
		add(getWidth() / 2, getHeight() / 2, new Cat(getWidth(), getHeight()));
		add(rand.nextInt(getWidth()-256)+128, rand.nextInt(getHeight()-384)+128, new Fish(getWidth(), getHeight()));
	}

	//used for creating text menus
	public void createMenu(String imgName, Graphic menu) {
		menu.setImage(imgName);
		int centerX = (int)(getWidth() / 2) - (menu.getImage().getWidth(null) / 2);
		int centerY = (int)(getHeight() / 2) - (menu.getImage().getHeight(null) / 2);
		menu.setXLoc(centerX);
		menu.setYLoc(centerY);
		menu.setVisible(false);
		add(menu);
	}

	//change the title to reflect score
	public void updateScore() {
		setTitle("Score: " + points);
	}

	//delete all graphics except menus and reinitialize game
	public void reset() {
		for (Iterator<Graphic> g = graphics.iterator(); g.hasNext();) {
			Graphic graphic = g.next();

			g.remove();
			remove(graphic);			
		}
		gameOver.setVisible(true);
		initialize();
	}

	//add graphic to frame
	public void add(int x, int y, Graphic g) {
		g.setXLoc(x);
		g.setYLoc(y);
		getContentPane().add(g);
		setVisible(true);

		graphics.add(g);
	}

	//start cat and dog threads
	public void runAll() {
		for (Iterator<Graphic> g = graphics.iterator(); g.hasNext();) {
			Graphic graphic = g.next();

			if(graphic.getClass().getName() == "Dog") {
				((Dog) graphic).start();
			}
			if(graphic.getClass().getName() == "Cat") {
				((Cat) graphic).start();
			}
		}
	}

	//creates dog and makes sure its far enough away from cat
	public void newDog() {
		Dog dog = new Dog(5, getWidth(), getHeight());
		int catX = graphics.get(0).getXLoc() + graphics.get(0).getImage().getWidth(null) / 2;
		int catY = graphics.get(0).getYLoc() + graphics.get(0).getImage().getHeight(null) / 2;
		int x = catX;
		int y = catY;
		while(x - 100 < catX && x + 100 > catX )
			x = rand.nextInt(getWidth() - 256) + 128;
		while(y - 100 < catY && y + 100 > catY )
			y = rand.nextInt(getHeight() - 384) + 128;
		
		//add to frame and start thread
		add(x, y, dog);
		dog.start();
	}

	//stops the movement of all threads
	public void pause() {
		pauseMenu.setVisible(true);
		for (Iterator<Graphic> g = graphics.iterator(); g.hasNext();) {
			Graphic graphic = g.next();

			if(graphic.getClass().getName() == "Dog") {
				((Dog) graphic).setRunning(false);
				break;
			}
			if(graphic.getClass().getName() == "Cat") {
				((Cat) graphic).setRunning(false);
			}
		}
	}

	//resumes the movement of all threads
	public void resume() {
		pauseMenu.setVisible(false);
		for (Iterator<Graphic> g = graphics.iterator(); g.hasNext();) {
			Graphic graphic = g.next();

			if(graphic.getClass().getName() == "Dog") {
				((Dog) graphic).setRunning(true);
				break;
			}
			if(graphic.getClass().getName() == "Cat") {
				((Cat) graphic).setRunning(true);
			}
		}
	}

	//test if cat is caught and end game if so
	public void catOverlapsDog() {
		for (Iterator<Graphic> g = graphics.iterator(); g.hasNext();) {
			Graphic graphic = g.next();
			if(graphic.getClass().getName() == "Dog")
				if(graphics.get(0).overlaps(graphic)) {
					isEnd = true;
					break;
				}
		}
		if(isEnd) //reset game if end
			reset();
	}

	//test if fish gets eaten
	public void catOverlapsFish() {
		for (Iterator<Graphic> g = graphics.iterator(); g.hasNext();) {
			Graphic graphic = g.next();
			if(graphic.getClass().getName() == "Fish")
				if(graphics.get(0).overlaps(graphic)) {
					//remove fish, new fish, new dog, update score
					g.remove();
					this.remove(graphic);
					add(rand.nextInt(getWidth()-256)+128, rand.nextInt(getHeight()-384)+128, new Fish(getWidth(), getHeight()));
					newDog();
					points++;
					updateScore();
					break;
				}
		}
	}

	//begin keylistener

	@Override
	public void keyPressed(KeyEvent e) {
		if(!isStart && !isEnd && !isPaused) {
			//move cat
			if(e.getKeyChar() == 'd') {
				((Cat) graphics.get(0)).setXDirection(1);
				horiz[1] = true;
			}

			if(e.getKeyChar() == 'a') {
				((Cat) graphics.get(0)).setXDirection(-1);
				horiz[0] = true;
			}

			if(e.getKeyChar() == 'w') {
				((Cat) graphics.get(0)).setYDirection(-1);
				vert[0] = true;
			}

			if(e.getKeyChar() == 's') {
				((Cat) graphics.get(0)).setYDirection(1);
				vert[1] = true;
			}
		}

		//pause or resume
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(isStart) {
				isStart = false;
				startMenu.setVisible(false);
				runAll();
			}
			else if(isEnd) {
				isEnd = false;
				gameOver.setVisible(false);
				points = 0;
				updateScore();
				runAll();
			}
			else {
				isPaused = !(isPaused);
				if(isPaused)
					pause();
				else
					resume();
			}
		}

		//end game if end state 
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(isEnd)
				System.exit(0);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//stop movement or move other direction if opposite key is still pressed
		if(e.getKeyChar() == 'd') {
			horiz[1] = false;
			if(horiz[0])
				((Cat) graphics.get(0)).setXDirection(-1);
			else
				((Cat) graphics.get(0)).setXDirection(0);
		}

		if(e.getKeyChar() == 'a') {
			horiz[0] = false;
			if(horiz[1])
				((Cat) graphics.get(0)).setXDirection(1);
			else
				((Cat) graphics.get(0)).setXDirection(0);
		}

		if(e.getKeyChar() == 'w') {
			vert[0] = false;
			if(vert[1])
				((Cat) graphics.get(0)).setYDirection(1);
			else
				((Cat) graphics.get(0)).setYDirection(0);
		}

		if(e.getKeyChar() == 's') {
			vert[1] = false;
			if(vert[0])
				((Cat) graphics.get(0)).setYDirection(-1);
			else
				((Cat) graphics.get(0)).setYDirection(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
