package assignment4.mario;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MarioThread extends Thread {
		
	final Board boardl;
	final SurfaceHolder shl;
	boolean gamestillrunningl = true;
	
	public MarioThread(SurfaceHolder sh, Board board) {
		this.boardl = board;
		this.shl = sh;
	}
	
	@Override
	public void run() {			
		while(gamestillrunningl) {
			
    		boardl.updateWorld();

			Canvas c=shl.lockCanvas();
			
			if (c!=null) {
				boardl.draw(c);
				shl.unlockCanvasAndPost(c);
			}	
		}		
	}
	

	public synchronized void stopGame() {
		gamestillrunningl = false;
	}	
}
