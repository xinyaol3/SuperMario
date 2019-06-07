package assignment4.mario;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Board implements OnTouchListener {

	private MarioView mv1;
	Mario mario1;
	Level lv1;
	final int MAX_X1, MAX_Y1;
	final static int MAX_TILE_X1 = 41, MAX_TILE_Y1 = 11;
	static int i, dx1, marioTransform1, level1, lifeCount1, score1, tileDimension1, xScreen1, yScreen1;
	private int xBoard1, count1, collisionType1 = 0, touchx1 = 1001, touchy1 = 1001;
	static boolean touch1, move1, jump1, fall1, goLeft1, goRight1, goJump1, dead1;
	private int placement1[][];
	private Tile tile1[][];
	Paint textPaint1 = new Paint();
	Paint gameover1 = new Paint();

	private static Rect dst1 = new Rect();
	private static Rect mdst1=	new Rect();

	public Board(MarioView mv) {		
		this.mv1 = mv;
		Board.level1 = MainActivity.level;
		tileDimension1 = mv.getHeight() / MAX_TILE_Y1;
		MAX_X1 = tileDimension1 * MAX_TILE_X1;
		MAX_Y1 = tileDimension1 * MAX_TILE_Y1;
		xScreen1 = mv.getWidth();
		yScreen1 = mv.getHeight();

		System.out.println("Screen  Width  = " + mv.getWidth());
		System.out.println("Screen Height  = " + mv.getHeight());
		System.out.println("Max  Width  = " + MAX_X1);
		System.out.println("Max Height  = " + MAX_Y1);
		System.out.println("Tile Dimension = " + tileDimension1);
		//
		placement1 = new int[MAX_TILE_Y1][MAX_TILE_X1];
		lv1 = new Level(level1);
		lv1.levelBuild(placement1);
		tile1 = new Tile[MAX_TILE_Y1][MAX_TILE_X1];
		for (int row = 0; row < MAX_TILE_Y1; row++) {
			for (int col = 0; col < MAX_TILE_X1; col++) {
				if (placement1[row][col] != 0)
					tile1[row][col] = new Tile( row, col, tileDimension1);
			}
		}
		mario1 = new Mario();
		move1 = false;
		jump1 = false;
		fall1 = true;
		dead1 = false;
		xBoard1 = 0;
		count1 = 0;
		dx1 = 0;
		score1 = 0;
		lifeCount1 = 10;

		System.out.println("Game Board Built!");
	}
	
	public void draw(Canvas c) {
		dst1.set(xBoard1 - dx1, 0, MAX_X1 - dx1, MAX_Y1);
		c.drawBitmap(mv1.background, null, dst1, null);
		dst1.set(0, 0, tileDimension1*4/5, tileDimension1*4/5);
		c.drawBitmap(mv1.mariodead, null, dst1, null);
		textPaint1.setColor(Color.YELLOW);
		textPaint1.setTextSize(50);
		c.drawText(" X "+ lifeCount1, tileDimension1, tileDimension1*2/3, textPaint1);
		c.drawText("Score : "+ score1, 7*tileDimension1, tileDimension1*2/3, textPaint1);
		c.drawText("Level : "+ level1, 13*tileDimension1, tileDimension1*2/3, textPaint1);

		for (int row = 0; row < MAX_TILE_Y1; row++) {
			for (int col = 0; col < MAX_TILE_X1; col++) {
				if (placement1[row][col] != 0)
					tile1[row][col].draw(c, placement1, mv1, dx1);
			}
		}

		mario1.draw(move1, jump1, marioTransform1, mdst1, tileDimension1, touchx1, touchy1, c, mv1);
		
		gameover1.setColor(Color.RED);
		gameover1.setTextSize(101);
		if (lifeCount1 == 0 && dead1)
			c.drawText("GAME OVER !!", 6*tileDimension1, 6*tileDimension1, gameover1);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {		
		switch(event.getAction()) {			    		    	
	    case MotionEvent.ACTION_MOVE:
	    case MotionEvent.ACTION_DOWN:
	    	synchronized (this) {
	    		touchx1 = (int)event.getX();
	    		touchy1 = (int)event.getY();
	    		move1 = true;
	    	}	    	
	    	return true;
	    case MotionEvent.ACTION_UP:
	    	move1 = false;
	    	return true;		    	
		}		
		return false;
	}

	public void updateWorld() {

		synchronized (this) {
			checkLife();
		}

		if (move1 && !dead1) {
			if (touchx1 >= (xScreen1/2) && touchy1 <= (yScreen1*4/5)) {
				if (goRight1) {
					if(Mario.xMario1 < xScreen1*4/7)
						Mario.goRight();
					else if (dx1 < (MAX_X1 - xScreen1 - Mario.vx1))
						dx1 = dx1 + Mario.vx1;
					else if (Mario.xMario1 < (xScreen1 - tileDimension1))
						Mario.goRight();
				}
			}
			if (touchx1 <  (xScreen1/2) && touchy1 <= (yScreen1*4/5)) {
				if (goLeft1) {
					if(Mario.xMario1 > xScreen1*3/7)
						Mario.goLeft();	
					else if (dx1 > 0)
						dx1 = dx1 - Mario.vx1;
					else if (Mario.xMario1 > 0)
						Mario.goLeft();
				}
			}
	   				   		
			if ((touchx1 > xScreen1/2) && (touchy1 > yScreen1*4/5) && touch1) {
	   			jump1 = true;
	   			touch1 = false;
			}
   		}
   		   		
   		if (jump1 && goJump1) {
   			jump1 = mario1.jump(count1);
   			count1++;
  		} else if (fall1) {
   			mario1.gravity();
   		} else if (!fall1) {
   			touch1 = true;
   			count1 = 0;
   		}

   		collisionType1 = 0;
		for (int row = 0; row < MAX_TILE_Y1; row++) {
			for (int col = 0; col < MAX_TILE_X1; col++) {
				if (placement1[row][col] != 0) {
					tile1[row][col].moveObjects(placement1, mdst1, dx1);
						if (collisionType1 == 0)
							collisionType1 = tile1[row][col].checkCollision(collisionType1, placement1, mdst1, dx1);
				}
			}
		}
	}

	private void checkLife() {

		switch(collisionType1) {
		case 0:
			goLeft1 = true;
			goRight1 = true;
			goJump1 = true;
			fall1 = true;
			break;
		case 1:
			goLeft1 = true;
			goRight1 = false;
			goJump1 = true;
			fall1 = true;
			break;
		case 2:
			goLeft1 = false;
			goRight1 = true;
			goJump1 = true;
			fall1 = true;
			break;
		case 3:
			goLeft1 = true;
			goRight1 = true;
			goJump1 = true;
			fall1 = false;
			break;
		case 4:
			goLeft1 = true;
			goRight1 = true;
			goJump1 = false;
			fall1 = true;
			count1 = 1000000;
			break;
		case 5:
			goLeft1 = true;
			goRight1 = true;
			jump1 = true;
			goJump1 = true;
			fall1 = false;
			System.out.println(i);
			score1 += 450;
			break;
		case 6:
			goLeft1 = false;
			goRight1 = false;
			goJump1 = false;
			if (i%2 == 1) {
				if (marioTransform1 > 2)
					marioTransform1 = 2;
				else if (marioTransform1 > 1)
					marioTransform1 = 1;
				else if (marioTransform1 == 1) {
					lifeCount1--;
					if (lifeCount1 > 0)
						mario1 = new Mario();
					else {
						goJump1 = true;
						jump1 = true;
					}
				}
			}
			i++;
			System.out.println(i);
			break;
		}
		if (i == 101)
			i=0;
		if (lifeCount1 == 0) {
			marioTransform1 = 0;
			dead1 = true;
		}
	}

}
