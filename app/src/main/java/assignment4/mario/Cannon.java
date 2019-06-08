package assignment4.mario;

import android.graphics.Rect;

public class Cannon {
	static final int vxl = 25, shootRangel = 1600;
	int xCannonballl = 0, yCannonball = 0;
	boolean LRl = false, deadl = false;
	Rect cndstl = new Rect();	// Cannon
	Rect cnbdstl = new Rect();	// Cannon Ball
	
	public void move(Rect mdst, int xTileIni) {
		// Cannon
		if ((cndstl.exactCenterX() > mdst.exactCenterX()) &&
			(cndstl.exactCenterX()-mdst.exactCenterX()) <= Board.xScreenl) {
			LRl = false;
		} else if ((cndstl.exactCenterX() < mdst.exactCenterX()) &&
				((cndstl.exactCenterX()-mdst.exactCenterX()) >= -Board.xScreenl)) {
			LRl = true;
		} else
			xCannonballl = 0;
		
		// Cannon Ball
		if (deadl) {
			xCannonballl = 0;
			deadl = false;
		} else if (!deadl) {
			if ((cnbdstl.exactCenterX() > xTileIni+shootRangel) ||
					(cnbdstl.exactCenterX() < xTileIni-shootRangel)) {
				xCannonballl = 0;
			} else if (LRl)
				xCannonballl = xCannonballl + vxl;
			else if (!LRl)
				xCannonballl = xCannonballl - vxl;
		}
		

			
	}
}
