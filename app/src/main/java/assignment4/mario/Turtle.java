package assignment4.mario;

import android.graphics.Rect;

public class Turtle {
	static final int vxl = 8, vyl = 0;
	int xTurtlel = 0, yTurtlel = 0;
	boolean LRl = false, deadl = false;
	Rect tdstl = new Rect();
	
	public void move() {
		if (!deadl) {
			if (LRl) {
				xTurtlel = xTurtlel + vxl;
				if (xTurtlel >= 4*Board.tileDimensionl)
					LRl = false;
			} else if (!LRl){
				xTurtlel = xTurtlel - vxl;
				if (xTurtlel <= -4*Board.tileDimensionl)
					LRl = true;
			}
		}
	}
}
