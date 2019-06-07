package assignment4.mario;

import android.graphics.Rect;

public class BadMushroom {
	static final int vx1 = 13, vy1 = 0;
	int xBMushroom1 = 0, yBmushroom1 = 0;
	boolean LR1 = false, dead1 = false;
	Rect bmdst1 = new Rect();
	
	public void move() {
		if (!dead1) {
			if (LR1) {
				xBMushroom1 = xBMushroom1 + vx1;
				if (xBMushroom1 >= 2*Board.tileDimension1)
					LR1 = false;
			} else if (!LR1){
				xBMushroom1 = xBMushroom1 - vx1;
				if (xBMushroom1 <= -2*Board.tileDimension1)
					LR1 = true;
			}
		}
	}
}
