package assignment4.mario;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Mario {
	
	static int xMario1, yMario1;
	static final int vx1 = 17, vy1 = 23, jumpHeight1 = 5*Board.tileDimension1;
	static boolean rise1;
	static int i = 0;
	
	public Mario() {
		xMario1 = 3*Board.tileDimension1;
		yMario1 = 9*Board.tileDimension1;
		Board.marioTransform1 = 1;
		Board.dx1 = 0;
	}
	
	public static void goRight() {		
		xMario1 = xMario1 + vx1;
	}

	public static void goLeft() {		
		xMario1 = xMario1 - vx1;
	}
	
	public boolean jump(int count) {
		if (vy1*count <= jumpHeight1) {
			rise1 = true;
			yMario1 = yMario1 - vy1;
		} else {
			rise1 = false;
			return false;
		}
		
		return true;		
	}
	
	public void gravity() {
			yMario1 = yMario1 + vy1;
	}

	public void draw(boolean move, boolean jump, int marioTransform, Rect mdst,
			int tileDimension, int touchx, int touchy, Canvas c, MarioView mv) {		
		i++;	if (i == 100)	i = 0;

		switch(marioTransform) {
		// Dead
		case 0:
			mdst.set(xMario1, yMario1, xMario1 + tileDimension, yMario1 + tileDimension);
			c.drawBitmap(mv.mariodead, null, mdst, null);
			break;

		case 1:
			mdst.set(xMario1, yMario1, xMario1 + tileDimension, yMario1 + tileDimension);
			if (touchx < (Board.xScreen1/2)) {
				if (move && !jump) {
					if (i%2 == 0)
						c.drawBitmap(mv.marioRL1, null, mdst, null);
					else if (i%2 == 1)
						c.drawBitmap(mv.marioRL2, null, mdst, null);
				} else if (jump)
					c.drawBitmap(mv.marioJL, null, mdst, null);
				else
					c.drawBitmap(mv.marioSL, null, mdst, null);	
			}
				
			if (touchx >= (Board.xScreen1/2)) {
				if (move && !jump) {
					if (i%2 == 0)
						c.drawBitmap(mv.marioRR1, null, mdst, null);
					else if (i%2 == 1)
						c.drawBitmap(mv.marioRR2, null, mdst, null);
				} else if (jump)
					c.drawBitmap(mv.marioJR, null, mdst, null);
				else
					c.drawBitmap(mv.marioSR, null, mdst, null);
			}
			break;

		case 2:
			mdst.set(xMario1, yMario1 - tileDimension, xMario1 + tileDimension, yMario1 + tileDimension);
			if (touchx < (Board.xScreen1/2)) {
				if (move && !jump) {
					if (i%2 == 0)
						c.drawBitmap(mv.bigmarioRL1, null, mdst, null);
					else if (i%2 == 1)
						c.drawBitmap(mv.bigmarioRL2, null, mdst, null);
				} else if (jump)
					c.drawBitmap(mv.bigmarioJL, null, mdst, null);
				else
					c.drawBitmap(mv.bigmarioSL, null, mdst, null);	
			}
				
			if (touchx >= (Board.xScreen1/2)) {
				if (move && !jump) {
					if (i%2 == 0)
						c.drawBitmap(mv.bigmarioRR1, null, mdst, null);
					else if (i%2 == 1)
						c.drawBitmap(mv.bigmarioRR2, null, mdst, null);
				} else if (jump)
					c.drawBitmap(mv.bigmarioJR, null, mdst, null);
				else
					c.drawBitmap(mv.bigmarioSR, null, mdst, null);
			}
			break;

		case 3:
			mdst.set(xMario1, yMario1 - tileDimension, xMario1 + tileDimension, yMario1 + tileDimension);
			if (touchx < (Board.xScreen1/2)) {
				if (move && !jump) {
					if (i%2 == 0)
						c.drawBitmap(mv.goldmarioRL1, null, mdst, null);
					else if (i%2 == 1)
						c.drawBitmap(mv.goldmarioRL2, null, mdst, null);
				} else if (jump)
					c.drawBitmap(mv.goldmarioJL, null, mdst, null);
				else
					c.drawBitmap(mv.goldmarioSL, null, mdst, null);	
			}
				
			if (touchx >= (Board.xScreen1/2)) {
				if (move && !jump) {
					if (i%2 == 0)
						c.drawBitmap(mv.goldmarioRR1, null, mdst, null);
					else if (i%2 == 1)
						c.drawBitmap(mv.goldmarioRR2, null, mdst, null);
				} else if (jump)
					c.drawBitmap(mv.goldmarioJR, null, mdst, null);
				else
					c.drawBitmap(mv.goldmarioSR, null, mdst, null);
			}
			break;
		}
		
	}
	
}
