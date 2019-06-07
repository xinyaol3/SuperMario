package assignment4.mario;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Tile {
	private final int dimension1, col1, row1;
	private int xTileIni1, yTileIni1, xTileFin1, yTileFin1;
	private int leftEdge1, rightEdge1, topEdge1, bottomEdge1;
	private int i = 0, pop;
	Turtle turtle1;
	Cannon cannon1;
	BadMushroom bmushroom1;
	Block block1;
	Rect ground1;
	
	public Tile(int row, int col, int tileDimension) {
		this.row1 = row;
		this.col1 = col;
		this.dimension1 = tileDimension;
	}
	
	private int getRelX() {
		return col1 * dimension1;
	}
	
	private int getRelY() {
		return row1 * dimension1;
	}

	public void draw(Canvas c, int[][] placement, MarioView mv, int dx) {
		i++;	if (i == 100)	i = 0;
		xTileIni1 = getRelX() - dx;
		yTileIni1 = getRelY();
		xTileFin1 = xTileIni1 + dimension1;
		yTileFin1 = yTileIni1 + dimension1;

		switch(placement[row1][col1]) {
		case 1:
			ground1.set(xTileIni1, yTileIni1, xTileFin1, yTileFin1);
			break;
		case 2:
			block1.brkdst1.set(xTileIni1, yTileIni1, xTileFin1, yTileFin1);
			c.drawBitmap(mv.breakable, null, block1.brkdst1, null);
			break;
		case 3:
			if (block1.itmdst1 != null && block1.pop1 && !block1.used1) {
				block1.itmdst1.set(xTileIni1, yTileIni1+block1.yItem1, xTileFin1, yTileFin1+block1.yItem1);
				if (pop == 0)
					c.drawBitmap(mv.greenMushroom, null, block1.itmdst1, null);
				else if (pop == 1)
					c.drawBitmap(mv.mushroom, null, block1.itmdst1, null);
				else if (pop == 2) {
					if (i%4 == 0)
						c.drawBitmap(mv.star1, null, block1.itmdst1, null);
					else if (i%4 == 1)
						c.drawBitmap(mv.star2, null, block1.itmdst1, null);
					else if (i%4 == 2)
						c.drawBitmap(mv.star3, null, block1.itmdst1, null);
					else if (i%4 == 3)
						c.drawBitmap(mv.star4, null, block1.itmdst1, null);
				}		
			}
			
			if (block1.unbrkdst1 == null)
				block1.unbrkdst1 = new Rect();
			block1.unbrkdst1.set(xTileIni1, yTileIni1, xTileFin1, yTileFin1);
			c.drawBitmap(mv.unbreakable, null, block1.unbrkdst1, null);
			break;
		case 4:
			if (block1.quesdst1 != null) {
				block1.quesdst1.set(xTileIni1, yTileIni1, xTileFin1, yTileFin1);
				c.drawBitmap(mv.question, null, block1.quesdst1, null);
			}
			break;
		case 5:
			turtle1.tdst.set(xTileIni1+turtle1.xTurtle1, yTileIni1+turtle1.yTurtle1,
					xTileFin1+turtle1.xTurtle1, yTileFin1+turtle1.yTurtle1+dimension1);
			if(turtle1.dead1) {
				turtle1.tdst.set(xTileIni1+turtle1.xTurtle1, yTileIni1+dimension1+turtle1.yTurtle1,
						xTileFin1+turtle1.xTurtle1, yTileFin1+turtle1.yTurtle1+dimension1);
				c.drawBitmap(mv.enemyTurtleD, null, turtle1.tdst, null);
			}				
			else if (turtle1.LR1) {
				if (i%2 == 0)
					c.drawBitmap(mv.enemyTurtleWR1, null, turtle1.tdst, null);
				else if (i%2 == 1)
					c.drawBitmap(mv.enemyTurtleWR2, null, turtle1.tdst, null);
			} else if (!turtle1.LR1) {
				if (i%2 == 0)
					c.drawBitmap(mv.enemyTurtleWL1, null, turtle1.tdst, null);
				else if (i%2 == 1)
					c.drawBitmap(mv.enemyTurtleWL2, null, turtle1.tdst, null);
			}				
			break;
		case 6:
			bmushroom1.bmdst1.set(xTileIni1+bmushroom1.xBMushroom1, yTileIni1+bmushroom1.yBmushroom1,
					xTileFin1+bmushroom1.xBMushroom1, yTileFin1+bmushroom1.yBmushroom1);
			if(bmushroom1.dead1)
				c.drawBitmap(mv.enemyMushroomD, null, bmushroom1.bmdst1, null);
			else if (i%2 == 0)
				c.drawBitmap(mv.enemyMushroomW1, null, bmushroom1.bmdst1, null);
			else if (i%2 == 1)
				c.drawBitmap(mv.enemyMushroomW2, null, bmushroom1.bmdst1, null);
			break;
		case 7:
			cannon1.cndst1.set(xTileIni1, yTileIni1, xTileFin1, yTileFin1);
			cannon1.cnbdst1.set(xTileIni1+cannon1.xCannonball1, yTileIni1,
					xTileFin1+cannon1.xCannonball1, yTileFin1);
			if (cannon1.LR1) {
				c.drawBitmap(mv.enemyCannonballR, null, cannon1.cnbdst1, null);
			} else if (!cannon1.LR1) {
				c.drawBitmap(mv.enemyCannonballL, null, cannon1.cnbdst1, null);
			}
			c.drawBitmap(mv.enemyCannon, null, cannon1.cndst1, null);
			break;
		case 8: // Items
		
			break;
		}		
	}
	
	public int checkCollision(int collisionType, int[][] placement, Rect mdst, int dx) {
		xTileIni1 	= getRelX() - dx;
		yTileIni1 	= getRelY();
		xTileFin1 	= xTileIni1 + dimension1;
		yTileFin1	= yTileIni1 + dimension1;
		leftEdge1	= xTileIni1 - dimension1 + 1;
		rightEdge1	= xTileIni1 + dimension1;
		topEdge1		= yTileIni1 - dimension1;
		bottomEdge1	= yTileIni1 + dimension1;

		switch(placement[row1][col1]) {
		case 1: // Ground
			if (Mario.yMario1 + dimension1 >= yTileIni1) {
				Mario.yMario1 = yTileIni1 - dimension1;
				collisionType = 3;
			}			
			break;
			
		case 2:
			if ((Mario.yMario1+dimension1 == yTileIni1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1))
				collisionType = 3;
			else if ((Mario.yMario1 == yTileFin1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1)
					&& Mario.rise1) {
				if (Board.marioTransform1 > 1) {
					placement[row1][col1] = 0;
					block1.brkdst1 = null;
				}
				if (Board.marioTransform1 > 1) {
					if (Mario.yMario1-dimension1<= yTileFin1)
						collisionType = 4;
				} else
					collisionType = 4;
			}else if ((Mario.xMario1+dimension1 == xTileIni1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1))
				collisionType = 1;
			else if ((Mario.xMario1 == xTileFin1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1))
				collisionType = 2;
			else
				collisionType = 0;			
			break;
			
		case 3:
			if ((Mario.yMario1+dimension1 == yTileIni1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1))
				collisionType = 3;
			else if ((Mario.yMario1 == yTileFin1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1)
					&& Mario.rise1)
				if (Board.marioTransform1 > 1) {
					if (Mario.yMario1-dimension1 <= yTileFin1)
						collisionType = 4;
				} else
					collisionType = 4;
			else if ((Mario.xMario1+dimension1 == xTileIni1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1))
				collisionType = 1;
			else if ((Mario.xMario1 == xTileFin1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1))
				collisionType = 2;
			else
				collisionType = 0;
			
			if (Rect.intersects(block1.itmdst1, mdst) && !block1.used1 && block1.stop1) {
				if (pop == 0) {
					Board.lifeCount1++;
					Board.score1 += 4000;
				}
				if (pop == 1) {
					Board.score1 += 1200;
					if (Board.marioTransform1 < 2)
						Board.marioTransform1 = 2;
				}
				if (pop == 2) {
					Board.score1 += 1500;
					if (Board.marioTransform1 < 3)
						Board.marioTransform1 = 3;
				}
				block1.used1 = true;
			}
			
			break;
			
		case 4:
			if ((Mario.yMario1+dimension1 == yTileIni1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1))
				collisionType = 3;
			else if ((Mario.yMario1 == yTileFin1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1)
					&& Mario.rise1) {
				if (!block1.pop1)
					pop = block1.popItem();
				placement[row1][col1] = 3;
				block1.quesdst1 = null;
				
				if (Board.marioTransform1 > 1) {
					if (Mario.yMario1-dimension1 <= yTileFin1)
						collisionType = 4;
				} else
					collisionType = 4;				
			} else if ((Mario.xMario1+dimension1 == xTileIni1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1))
				collisionType = 1;
			else if ((Mario.xMario1 == xTileFin1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1))
				collisionType = 2;
			else
				collisionType = 0;			
			break;
			
		case 5: // Turtle enemy
			if (Rect.intersects(turtle1.tdst, mdst) && !turtle1.dead1
					&& Mario.yMario1+36 < turtle1.tdst.exactCenterY()) {
				turtle1.dead1 = true;
				collisionType = 5;
			} else if (Rect.intersects(turtle1.tdst, mdst) && !turtle1.dead1)
				collisionType = 6;
			else
				collisionType = 0;			
			break;
			
		case 6:
			if (Rect.intersects(bmushroom1.bmdst1, mdst) && !bmushroom1.dead1
					&& Mario.yMario1 < bmushroom1.bmdst1.exactCenterY()) {
				bmushroom1.dead1 = true;
				collisionType = 5;
			} else if (Rect.intersects(bmushroom1.bmdst1, mdst) && !bmushroom1.dead1)
				collisionType = 6;
			else
				collisionType = 0;			
			break;
			
		case 7:
			if ((Mario.yMario1+dimension1 == yTileIni1) && (Mario.xMario1 >= leftEdge1) && (Mario.xMario1 <= rightEdge1))
				collisionType = 3;
			else if ((Mario.xMario1+dimension1 == xTileIni1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1)
					&& !Mario.rise1) {
				Mario.yMario1 = yTileIni1;
				collisionType = 1;
			} else if ((Mario.xMario1 == xTileFin1) && (Mario.yMario1 >= topEdge1) && (Mario.yMario1 <= bottomEdge1)
					&& !Mario.rise1) {
				Mario.yMario1 = yTileIni1;
				collisionType = 2;
			} else
				collisionType = 0;
			
			if (Rect.intersects(cannon1.cnbdst1, mdst) && !cannon1.dead1
					&& Mario.yMario1+36 < cannon1.cnbdst1.exactCenterY()) {
				collisionType = 5;
				cannon1.dead1 = true;
			} else if (Rect.intersects(cannon1.cnbdst1, mdst)
					&& !cannon1.dead1) {
				if (Board.marioTransform1 != 3)
					collisionType = 6;
				cannon1.dead1 = true;
			}
			break;
		}

		return collisionType;
	}
	
	public void moveObjects(int[][] placement, Rect mdst, int dx) {		
		xTileIni1 = getRelX() - dx;
		yTileIni1 = getRelY();
		
		switch(placement[row1][col1]) {
		case 1:
			if (ground1 == null)
				ground1 = new Rect();
			break;
		case 2:
			if (block1 == null)
				block1 = new Block();
			break;
		case 3:
			if (block1 == null)
				block1 = new Block();
			if (block1.itmdst1 != null && !block1.stop1)
				block1.moveItem();
			break;
		case 4:
			if (block1 == null)
				block1 = new Block();
			break;
		case 5:
			if (turtle1 == null)
				turtle1 = new Turtle();
			turtle1.move();
			break;
		case 6:
			if (bmushroom1 == null)
				bmushroom1 = new BadMushroom();
			bmushroom1.move();
			break;
		case 7:
			if (cannon1 == null)
				cannon1 = new Cannon();
			cannon1.move(mdst, xTileIni1);
			break;
		}
	}
	
}
