package assignment4.mario;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Mario {
	
	static int xMariol, yMariol;
	static final int vxl = 18, vyl = 24, jumpHeightl = 4*Board.tileDimensionl;
	static boolean risel;
	static int i = 0;
	
	public Mario() {
		xMariol = 2*Board.tileDimensionl;
		yMariol = 8*Board.tileDimensionl; //Ground level
		Board.marioTransforml = 1;
		Board.dxl = 0;
	}
	
	public static void goRight() {		
		xMariol = xMariol + vxl;
	}

	public static void goLeft() {		
		xMariol = xMariol - vxl;
	}
	
	public boolean jump(int count) {
		if (vyl*count <= jumpHeightl) {	// Jump up
			risel = true;
			yMariol = yMariol - vyl;
		} else {
			risel = false;
			return false;
		}
		
		return true;		
	}
	
	public void gravity() {
			yMariol = yMariol + vyl;
	}

	public void draw(boolean move, boolean jump, int marioTransform, Rect mdst,
			int tileDimension, int touchx, int touchy, Canvas c, MarioView mv) {		
		i++;	if (i == 100)	i = 0;	//Reset counter for animation

		switch(marioTransform) {
		// Dead
		case 0:
			mdst.set(xMariol, yMariol, xMariol + tileDimension, yMariol + tileDimension);
			c.drawBitmap(mv.mariodead, null, mdst, null);
			break;
		// Regular small Mario
		case 1:
			mdst.set(xMariol, yMariol, xMariol + tileDimension, yMariol + tileDimension);
			if (touchx < (Board.xScreenl/2)) {
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
				
			if (touchx >= (Board.xScreenl/2)) {
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
		// Big Mario after eating mushroom
		case 2:
			mdst.set(xMariol, yMariol - tileDimension, xMariol + tileDimension, yMariol + tileDimension);
			if (touchx < (Board.xScreenl/2)) {
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
				
			if (touchx >= (Board.xScreenl/2)) {
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
		// Golden Super Mario after eating star
		case 3:
			mdst.set(xMariol, yMariol - tileDimension, xMariol + tileDimension, yMariol + tileDimension);
			if (touchx < (Board.xScreenl/2)) {
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
				
			if (touchx >= (Board.xScreenl/2)) {
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
