package assignment4.mario;

public class Level {
	
	private int level1;
	
	public Level(int level) {
		this.level1 = level;
	}
	public void levelBuild(int[][] placement) {

		switch(level1) {
		case 1:
			for (int row = 0; row < Board.MAX_TILE_Y1; row++) {
				for (int col = 0; col < Board.MAX_TILE_X1; col++) {
					placement[9][col] = 1;
					if (col >= 19 && col <= 26)
						placement[5][col] = 2;
					if (col >= 13  && col <= 16)
						placement[5][col] = 3;
					if (col == 7 || col == 8)
						placement[5][col] = 4;
					if (col == 31 || col == 21 || col == 25)
						placement[7][col] = 5;
					if (col == 15)
						placement[8][col] = 6;
					if (col == 19)
						placement[8][col] = 8;
				}
			}
			break;
		case 2:
			for (int row = 0; row < Board.MAX_TILE_Y1; row++) {
				for (int col = 0; col < Board.MAX_TILE_X1; col++) {
					placement[9][col] = 1;
					if (col >= 8 && col%6 == 5)
						placement[5][col] = 2;
					if (col >= 10 && col%6 == 3)
						placement[5][col] = 3;
					if (col >= 10 && col%6 == 4)
						placement[5][col] = 4;
					if (col >= 10 && col%8 == 3)
						placement[7][col] = 5;
					if (col >= 10 && col%8 == 5)
						placement[8][col] = 6;
					if (col >= 11 && col%16 == 6)
						placement[9][col] = 8;
				}
			}
			break;
		case 3:
			for (int row = 0; row < Board.MAX_TILE_Y1; row++) {
				for (int col = 0; col < Board.MAX_TILE_X1; col++) {
					placement[10][col] = 2;
					if (col >= 11 && col <= 14)
						placement[6][col] = 3;
					if (col >= 9  && col <= 10)
						placement[6][col] = 4;
					if (col == 6)
						placement[6][col] = 5;
					if (col == 23 || col == 21)
						placement[8][col] = 6;
					if (col == 18)
						placement[9][col] = 7;
					if (col == 11 || col == 15 || col == 29)
						placement[9][col] = 8;
				}
			}
			break;			
		}
	}
}
