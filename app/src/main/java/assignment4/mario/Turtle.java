package assignment4.mario;

import android.graphics.Rect;

public class Turtle {
	static final int vx1 = 9, vy1 = 0;
	int xTurtle1 = 0, yTurtle1 = 0;
	boolean LR1 = false, dead1 = false;
	Rect tdst = new Rect();	// Turtle
	
	public void move() {
		if (!dead1) {
			if (LR1) {
				xTurtle1 = xTurtle1 + vx1;
				if (xTurtle1 >= 4*Board.tileDimension1)
					LR1 = false;
			} else if (!LR1){
				xTurtle1 = xTurtle1 - vx1;
				if (xTurtle1 <= -4*Board.tileDimension1)
					LR1 = true;
			}
		}
	}
}
