package assignment4.mario;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class Board implements OnTouchListener {

	private MarioView mvl;
	Mario mariol;
	Level lvl;
	final int MAX_Xl, MAX_Yl;
	final static int MAX_TILE_Xl = 40, MAX_TILE_Yl = 10;
	static int i, dxl, marioTransforml, levell, lifeCountl, scorel, tileDimensionl, xScreenl, yScreenl;
	private int xBoardl, countl, collisionTypel = 0, touchxl = 1000, touchyl = 1000;
	static boolean touchl, movel, jumpl, falll, goLeftl, goRightl, goJumpl, deadl;
	private int placementl[][];
	private Tile tilel[][];
	Paint textPaintl = new Paint();
	Paint gameoverl = new Paint();

	private static Rect dst = new Rect();		// General use
	private static Rect mdst=	new Rect();		// Mario

	public Board(MarioView mv) {		
		this.mvl = mv;
		Board.levell = MainActivity.levell;
		tileDimensionl = mv.getHeight() / MAX_TILE_Yl;
		MAX_Xl = tileDimensionl * MAX_TILE_Xl;
		MAX_Yl = tileDimensionl * MAX_TILE_Yl;
		xScreenl = mv.getWidth();
		yScreenl = mv.getHeight();
		//Display values for debugging
		System.out.println("Screen  Width  = " + mv.getWidth());
		System.out.println("Screen Height  = " + mv.getHeight());
		System.out.println("Max  Width  = " + MAX_Xl);
		System.out.println("Max Height  = " + MAX_Yl);
		System.out.println("Tile Dimension = " + tileDimensionl);
		//
		placementl = new int[MAX_TILE_Yl][MAX_TILE_Xl];
		lvl = new Level(levell);
		lvl.levelBuild(placementl);
		tilel = new Tile[MAX_TILE_Yl][MAX_TILE_Xl];
		for (int row = 0; row < MAX_TILE_Yl; row++) {
			for (int col = 0; col < MAX_TILE_Xl; col++) {
				if (placementl[row][col] != 0)
					tilel[row][col] = new Tile( row, col, tileDimensionl);

			}
		}
		

		mariol = new Mario();
		movel = false;
		jumpl = false;
		falll = true;
		deadl = false;
		xBoardl = 0;
		countl = 0;
		dxl = 0;
		scorel = 0;
		lifeCountl = 10;
		///////////////////////////////
		System.out.println("Game Board Built!");
	}
	
	public void draw(Canvas c) {
		dst.set(xBoardl - dxl, 0, MAX_Xl - dxl, MAX_Yl);
		c.drawBitmap(mvl.background, null, dst, null);
		dst.set(0, 0, tileDimensionl*4/5, tileDimensionl*4/5);
		c.drawBitmap(mvl.mariodead, null, dst, null);
		textPaintl.setColor(Color.YELLOW);
		textPaintl.setTextSize(50);
		c.drawText(" X "+ lifeCountl, tileDimensionl, tileDimensionl*2/3, textPaintl);
		c.drawText("Score : "+ scorel, 7*tileDimensionl, tileDimensionl*2/3, textPaintl);
		c.drawText("Level : "+ levell, 13*tileDimensionl, tileDimensionl*2/3, textPaintl);
		
		// Drawing items and enemies
		for (int row = 0; row < MAX_TILE_Yl; row++) {
			for (int col = 0; col < MAX_TILE_Xl; col++) {
				if (placementl[row][col] != 0)
					tilel[row][col].draw(c, placementl, mvl, dxl);
			}
		}
		
		// Drawing Mario
		mariol.draw(movel, jumpl, marioTransforml, mdst, tileDimensionl, touchxl, touchyl, c, mvl);
		
		gameoverl.setColor(Color.RED);
		gameoverl.setTextSize(100);
		if (lifeCountl == 0 && deadl)
			c.drawText("GAME OVER !!", 5*tileDimensionl, 5*tileDimensionl, gameoverl);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {		
		switch(event.getAction()) {			    		    	
	    case MotionEvent.ACTION_MOVE:
	    case MotionEvent.ACTION_DOWN:
	    	synchronized (this) {
	    		touchxl = (int)event.getX();
	    		touchyl = (int)event.getY();
	    		movel = true;
	    	}	    	
	    	return true;
	    case MotionEvent.ACTION_UP:
	    	movel = false;
	    	return true;		    	
		}		
		return false;
	}

	public void updateWorld() {
		// Check life and transformation
		synchronized (this) {
			checkLife();
		}
		// Movement of Mario
		if (movel && !deadl) {
			if (touchxl >= (xScreenl/2) && touchyl <= (yScreenl*4/5)) {
				if (goRightl) {
					if(Mario.xMariol < xScreenl*4/7)
						Mario.goRight();
					else if (dxl < (MAX_Xl - xScreenl - Mario.vxl))
						dxl = dxl + Mario.vxl;
					else if (Mario.xMariol < (xScreenl - tileDimensionl))
						Mario.goRight();
				}
			}
			if (touchxl <  (xScreenl/2) && touchyl <= (yScreenl*4/5)) {
				if (goLeftl) {
					if(Mario.xMariol > xScreenl*3/7)
						Mario.goLeft();	
					else if (dxl > 0)
						dxl = dxl - Mario.vxl;
					else if (Mario.xMariol > 0)
						Mario.goLeft();
				}
			}
	   				   		
			if ((touchxl > xScreenl/2) && (touchyl > yScreenl*4/5) && touchl) {
	   			jumpl = true;
	   			touchl = false;
			}
   		}
   		   		
   		if (jumpl && goJumpl) {
   			jumpl = mariol.jump(countl);
   			countl++;
  		} else if (falll) {
   			mariol.gravity();
   		} else if (!falll) {
   			touchl = true;
   			countl = 0;
   		}
		

   		collisionTypel = 0;
		for (int row = 0; row < MAX_TILE_Yl; row++) {
			for (int col = 0; col < MAX_TILE_Xl; col++) {
				if (placementl[row][col] != 0) {
					tilel[row][col].moveObjects(placementl, mdst, dxl);
						if (collisionTypel == 0)
							collisionTypel = tilel[row][col].checkCollision(collisionTypel, placementl, mdst, dxl);
				}
			}
		}
		

	}

	private void checkLife() {

		switch(collisionTypel) {
		case 0: // While in the air
			goLeftl = true;
			goRightl = true;
			goJumpl = true;
			falll = true;
			break;
		case 1:
			goLeftl = true;
			goRightl = false;
			goJumpl = true;
			falll = true;
			break;
		case 2:
			goLeftl = false;
			goRightl = true;
			goJumpl = true;
			falll = true;
			break;
		case 3:
			goLeftl = true;
			goRightl = true;
			goJumpl = true;
			falll = false;
			break;
		case 4:
			goLeftl = true;
			goRightl = true;
			goJumpl = false;
			falll = true;
			countl = 1000000;
			break;
		case 5:
			goLeftl = true;
			goRightl = true;
			jumpl = true;
			goJumpl = true;
			falll = false;
			System.out.println(i);
			scorel += 450;
			break;
		case 6:
			goLeftl = false;
			goRightl = false;
			goJumpl = false;
			if (i%2 == 1) {
				if (marioTransforml > 2)
					marioTransforml = 2;
				else if (marioTransforml > 1)
					marioTransforml = 1;
				else if (marioTransforml == 1) {
					lifeCountl--;
					if (lifeCountl > 0)
						mariol = new Mario();
					else {
						goJumpl = true;
						jumpl = true;
					}
				}
			}
			i++;
			System.out.println(i);
			break;
		}
		if (i == 100)
			i=0;
		if (lifeCountl == 0) {
			marioTransforml = 0;
			deadl = true;
		}
	}

}
