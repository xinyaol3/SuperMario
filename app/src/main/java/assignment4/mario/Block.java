package assignment4.mario;

import android.graphics.Rect;

import java.util.Random;

public class Block {
	public int xItem1 = 0, yItem1 = 0, index1, count1;
	static final int vx1 = 0, vy1 = 13;
	boolean pop1 = false, used1 = false, stop1 = false;
	Rect brkdst1 = new Rect();
	Rect unbrkdst1 = new Rect();
	Rect quesdst1 = new Rect();
	Rect itmdst1 = new Rect();
	Random r1 = new Random();

	public int popItem() {
		pop1 = true;
		
		index1 = r1.nextInt(3);
		return index1;
	}
	
	public void moveItem() {
		if (vy1*count1 <= Board.tileDimension1) {
			count1++;
			yItem1 = yItem1 - vy1;
			stop1 = false;
		} else
			stop1 = true;
	}

}
