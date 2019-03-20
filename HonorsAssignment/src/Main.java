/*
 * Alex Main
 * 010646808
 * Honors Assignment
 */

public class Main {

	public static void main(String[] args) {
		//create window
		Window window = new Window();
		
		//constant check for overlap
		while(true) {
			if(!(window.isEnd())) {
				window.catOverlapsDog();
				window.catOverlapsFish();
			}
			//if gameover state reached, small delay to check for change in isend
			else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {}
			}
		}
	}

}
