package assignment4.mario;

import android.graphics.Rect;

public class BadMushroom {
	static final int vxl = 12, vyl = 0;
	int xBMushrooml = 0, yBmushrooml = 0;
	boolean LRl = false, deadl = false;
	Rect bmdstl = new Rect();	// Bad Mushroom
	
	public void move() {
		if (!deadl) {
			if (LRl) {
				xBMushrooml = xBMushrooml + vxl;
				if (xBMushrooml >= 2*Board.tileDimensionl)
					LRl = false;
			} else if (!LRl){
				xBMushrooml = xBMushrooml - vxl;
				if (xBMushrooml <= -2*Board.tileDimensionl)
					LRl = true;
			}
		}
	}
}
