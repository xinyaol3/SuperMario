package assignment4.mario;

import android.graphics.Rect;

import java.util.Random;

public class Block {
	public int xIteml = 0, yIteml = 0, indexl, countl;
	static final int vxl = 0, vyl = 12;
	boolean popl = false, usedl = false, stopl = false;
	Rect brkdstl = new Rect();
	Rect unbrkdstl = new Rect();
	Rect quesdstl = new Rect();
	Rect itmdstl = new Rect();
	Random rl = new Random();
	

	public int popItem() {
		popl = true;
		
		indexl = rl.nextInt(3);
		return indexl;
	}
	
	public void moveItem() {
		if (vyl*countl <= Board.tileDimensionl) {
			countl++;
			yIteml = yIteml - vyl;
			stopl = false;
		} else
			stopl = true;
	}

}
