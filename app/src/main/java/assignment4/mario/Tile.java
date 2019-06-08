package assignment4.mario;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Tile {
	private final int dimensionl, coll, rowl;
	private int xTileInil, yTileInil, xTileFinl, yTileFinl;
	private int leftEdgel, rightEdgel, topEdgel, bottomEdgel;
	private int i = 0, pop;
	Turtle turtlel;
	Cannon cannonl;
	BadMushroom bmushrooml;
	Block blockl;
	Rect groundl;
	
	public Tile(int row, int col, int tileDimension) {
		this.rowl = row;
		this.coll = col;
		this.dimensionl = tileDimension;
	}
	
	private int getRelX() {
		return coll * dimensionl;
	}
	
	private int getRelY() {
		return rowl * dimensionl;
	}

	public void draw(Canvas c, int[][] placement, MarioView mv, int dx) {
		i++;	if (i == 100)	i = 0;
		xTileInil = getRelX() - dx;
		yTileInil = getRelY();
		xTileFinl = xTileInil + dimensionl;
		yTileFinl = yTileInil + dimensionl;

		switch(placement[rowl][coll]) {
		case 1:
			groundl.set(xTileInil, yTileInil, xTileFinl, yTileFinl);
			break;
		case 2:
			blockl.brkdstl.set(xTileInil, yTileInil, xTileFinl, yTileFinl);
			c.drawBitmap(mv.breakable, null, blockl.brkdstl, null);
			break;
		case 3:

			if (blockl.itmdstl != null && blockl.popl && !blockl.usedl) {
				blockl.itmdstl.set(xTileInil, yTileInil+blockl.yIteml, xTileFinl, yTileFinl+blockl.yIteml);
				if (pop == 0)
					c.drawBitmap(mv.greenMushroom, null, blockl.itmdstl, null);
				else if (pop == 1)
					c.drawBitmap(mv.mushroom, null, blockl.itmdstl, null);
				else if (pop == 2) {
					if (i%4 == 0)
						c.drawBitmap(mv.star1, null, blockl.itmdstl, null);
					else if (i%4 == 1)
						c.drawBitmap(mv.star2, null, blockl.itmdstl, null);
					else if (i%4 == 2)
						c.drawBitmap(mv.star3, null, blockl.itmdstl, null);
					else if (i%4 == 3)
						c.drawBitmap(mv.star4, null, blockl.itmdstl, null);
				}		
			}
			
			if (blockl.unbrkdstl == null)
				blockl.unbrkdstl = new Rect();
			blockl.unbrkdstl.set(xTileInil, yTileInil, xTileFinl, yTileFinl);
			c.drawBitmap(mv.unbreakable, null, blockl.unbrkdstl, null);
			break;
		case 4:
			if (blockl.quesdstl != null) {
				blockl.quesdstl.set(xTileInil, yTileInil, xTileFinl, yTileFinl);
				c.drawBitmap(mv.question, null, blockl.quesdstl, null);
			}
			break;
		case 5:
			turtlel.tdstl.set(xTileInil+turtlel.xTurtlel, yTileInil+turtlel.yTurtlel,
					xTileFinl+turtlel.xTurtlel, yTileFinl+turtlel.yTurtlel+dimensionl);
			if(turtlel.deadl) {
				turtlel.tdstl.set(xTileInil+turtlel.xTurtlel, yTileInil+dimensionl+turtlel.yTurtlel,
						xTileFinl+turtlel.xTurtlel, yTileFinl+turtlel.yTurtlel+dimensionl);
				c.drawBitmap(mv.enemyTurtleD, null, turtlel.tdstl, null);
			}				
			else if (turtlel.LRl) {
				if (i%2 == 0)
					c.drawBitmap(mv.enemyTurtleWR1, null, turtlel.tdstl, null);
				else if (i%2 == 1)
					c.drawBitmap(mv.enemyTurtleWR2, null, turtlel.tdstl, null);
			} else if (!turtlel.LRl) {
				if (i%2 == 0)
					c.drawBitmap(mv.enemyTurtleWL1, null, turtlel.tdstl, null);
				else if (i%2 == 1)
					c.drawBitmap(mv.enemyTurtleWL2, null, turtlel.tdstl, null);
			}				
			break;
		case 6:
			bmushrooml.bmdstl.set(xTileInil+bmushrooml.xBMushrooml, yTileInil+bmushrooml.yBmushrooml,
					xTileFinl+bmushrooml.xBMushrooml, yTileFinl+bmushrooml.yBmushrooml);
			if(bmushrooml.deadl)
				c.drawBitmap(mv.enemyMushroomD, null, bmushrooml.bmdstl, null);
			else if (i%2 == 0)
				c.drawBitmap(mv.enemyMushroomW1, null, bmushrooml.bmdstl, null);
			else if (i%2 == 1)
				c.drawBitmap(mv.enemyMushroomW2, null, bmushrooml.bmdstl, null);
			break;
		case 7:
			cannonl.cndstl.set(xTileInil, yTileInil, xTileFinl, yTileFinl);
			cannonl.cnbdstl.set(xTileInil+cannonl.xCannonballl, yTileInil,
					xTileFinl+cannonl.xCannonballl, yTileFinl);
			if (cannonl.LRl) {
				c.drawBitmap(mv.enemyCannonballR, null, cannonl.cnbdstl, null);
			} else if (!cannonl.LRl) {
				c.drawBitmap(mv.enemyCannonballL, null, cannonl.cnbdstl, null);
			}
			c.drawBitmap(mv.enemyCannon, null, cannonl.cndstl, null);
			break;
		case 8:
		
			break;
		}		
	}
	
	public int checkCollision(int collisionType, int[][] placement, Rect mdst, int dx) {
		xTileInil 	= getRelX() - dx;
		yTileInil 	= getRelY();
		xTileFinl 	= xTileInil + dimensionl;
		yTileFinl	= yTileInil + dimensionl;
		leftEdgel	= xTileInil - dimensionl + 1;
		rightEdgel	= xTileInil + dimensionl;
		topEdgel		= yTileInil - dimensionl;
		bottomEdgel	= yTileInil + dimensionl;


		switch(placement[rowl][coll]) {
		case 1: // Ground
			if (Mario.yMariol + dimensionl >= yTileInil) {
				Mario.yMariol = yTileInil - dimensionl;
				collisionType = 3;
			}			
			break;
			
		case 2:
			if ((Mario.yMariol+dimensionl == yTileInil) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel))
				collisionType = 3;
			else if ((Mario.yMariol == yTileFinl) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel)
					&& Mario.risel) {
				if (Board.marioTransforml > 1) {
					placement[rowl][coll] = 0;
					blockl.brkdstl = null;
				}
				if (Board.marioTransforml > 1) {
					if (Mario.yMariol-dimensionl<= yTileFinl)
						collisionType = 4;
				} else
					collisionType = 4;
			}else if ((Mario.xMariol+dimensionl == xTileInil) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel))
				collisionType = 1;
			else if ((Mario.xMariol == xTileFinl) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel))
				collisionType = 2;
			else
				collisionType = 0;			
			break;
			
		case 3:
			if ((Mario.yMariol+dimensionl == yTileInil) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel))
				collisionType = 3;
			else if ((Mario.yMariol == yTileFinl) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel)
					&& Mario.risel)
				if (Board.marioTransforml > 1) {
					if (Mario.yMariol-dimensionl <= yTileFinl)
						collisionType = 4;
				} else
					collisionType = 4;
			else if ((Mario.xMariol+dimensionl == xTileInil) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel))
				collisionType = 1;
			else if ((Mario.xMariol == xTileFinl) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel))
				collisionType = 2;
			else
				collisionType = 0;
			
			if (Rect.intersects(blockl.itmdstl, mdst) && !blockl.usedl && blockl.stopl) {
				if (pop == 0) {
					Board.lifeCountl++;
					Board.scorel += 4000;
				}
				if (pop == 1) {
					Board.scorel += 1200;
					if (Board.marioTransforml < 2)
						Board.marioTransforml = 2;
				}
				if (pop == 2) {
					Board.scorel += 1500;
					if (Board.marioTransforml < 3)
						Board.marioTransforml = 3;
				}
				blockl.usedl = true;
			}
			
			break;
			
		case 4: // Question block
			if ((Mario.yMariol+dimensionl == yTileInil) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel))
				collisionType = 3;
			else if ((Mario.yMariol == yTileFinl) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel)
					&& Mario.risel) {
				if (!blockl.popl)
					pop = blockl.popItem();

				placement[rowl][coll] = 3;
				blockl.quesdstl = null;
				
				if (Board.marioTransforml > 1) {
					if (Mario.yMariol-dimensionl <= yTileFinl)
						collisionType = 4;
				} else
					collisionType = 4;				
			} else if ((Mario.xMariol+dimensionl == xTileInil) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel))
				collisionType = 1;
			else if ((Mario.xMariol == xTileFinl) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel))
				collisionType = 2;
			else
				collisionType = 0;			
			break;
			
		case 5:
			if (Rect.intersects(turtlel.tdstl, mdst) && !turtlel.deadl
					&& Mario.yMariol+36 < turtlel.tdstl.exactCenterY()) {
				turtlel.deadl = true;
				collisionType = 5;
			} else if (Rect.intersects(turtlel.tdstl, mdst) && !turtlel.deadl)
				collisionType = 6;
			else
				collisionType = 0;			
			break;
			
		case 6:
			if (Rect.intersects(bmushrooml.bmdstl, mdst) && !bmushrooml.deadl
					&& Mario.yMariol < bmushrooml.bmdstl.exactCenterY()) {
				bmushrooml.deadl = true;
				collisionType = 5;
			} else if (Rect.intersects(bmushrooml.bmdstl, mdst) && !bmushrooml.deadl)
				collisionType = 6;
			else
				collisionType = 0;			
			break;
			
		case 7:
			if ((Mario.yMariol+dimensionl == yTileInil) && (Mario.xMariol >= leftEdgel) && (Mario.xMariol <= rightEdgel))
				collisionType = 3;
			else if ((Mario.xMariol+dimensionl == xTileInil) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel)
					&& !Mario.risel) {
				Mario.yMariol = yTileInil;
				collisionType = 1;
			} else if ((Mario.xMariol == xTileFinl) && (Mario.yMariol >= topEdgel) && (Mario.yMariol <= bottomEdgel)
					&& !Mario.risel) {
				Mario.yMariol = yTileInil;
				collisionType = 2;
			} else
				collisionType = 0;
			
			if (Rect.intersects(cannonl.cnbdstl, mdst) && !cannonl.deadl
					&& Mario.yMariol+36 < cannonl.cnbdstl.exactCenterY()) {
				collisionType = 5;
				cannonl.deadl = true;
			} else if (Rect.intersects(cannonl.cnbdstl, mdst)
					&& !cannonl.deadl) {
				if (Board.marioTransforml != 3)
					collisionType = 6;
				cannonl.deadl = true;
			}
			break;
		}

		return collisionType;
	}
	
	public void moveObjects(int[][] placement, Rect mdst, int dx) {		
		xTileInil = getRelX() - dx;
		yTileInil = getRelY();
		
		switch(placement[rowl][coll]) {
		case 1: // Ground
			if (groundl == null)
				groundl = new Rect();
			break;
		case 2:
			if (blockl == null)
				blockl = new Block();
			break;
		case 3:
			if (blockl == null)
				blockl = new Block();
			if (blockl.itmdstl != null && !blockl.stopl)
				blockl.moveItem();
			break;
		case 4:
			if (blockl == null)
				blockl = new Block();
			break;
		case 5:
			if (turtlel == null)
				turtlel = new Turtle();
			turtlel.move();
			break;
		case 6: // Mushroom enemy
			if (bmushrooml == null)
				bmushrooml = new BadMushroom();
			bmushrooml.move();
			break;
		case 7: // Cannon enemy
			if (cannonl == null)
				cannonl = new Cannon();
			cannonl.move(mdst, xTileInil);
			break;
		}
	}
	
}
