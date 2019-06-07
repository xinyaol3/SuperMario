package assignment4.mario;

import android.graphics.Rect;

public class Cannon {
	static final int vx1 = 26, shootRange1 = 1601;
	int xCannonball1 = 0, yCannonball1 = 0;
	boolean LR1 = false, dead1 = false;
	Rect cndst1 = new Rect();
	Rect cnbdst1 = new Rect();
	
	public void move(Rect mdst, int xTileIni) {

		if ((cndst1.exactCenterX() > mdst.exactCenterX()) &&
			(cndst1.exactCenterX()-mdst.exactCenterX()) <= Board.xScreen1) {
			LR1 = false;
		} else if ((cndst1.exactCenterX() < mdst.exactCenterX()) &&
				((cndst1.exactCenterX()-mdst.exactCenterX()) >= -Board.xScreen1)) {
			LR1 = true;
		} else
			xCannonball1 = 0;

		if (dead1) {
			xCannonball1 = 0;
			dead1 = false;
		} else if (!dead1) {
			if ((cnbdst1.exactCenterX() > xTileIni+shootRange1) ||
					(cnbdst1.exactCenterX() < xTileIni-shootRange1)) {
				xCannonball1 = 0;
			} else if (LR1)
				xCannonball1 = xCannonball1 + vx1;
			else if (!LR1)
				xCannonball1 = xCannonball1 - vx1;
		}
		

			
	}
}
