package assignment4.mario;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MarioThread extends Thread {
		
	final Board board1;
	final SurfaceHolder sh1;
	boolean gamestillrunning1 = true;
	
	public MarioThread(SurfaceHolder sh, Board board) {
		this.board1 = board;
		this.sh1 = sh;
	}
	
	@Override
	public void run() {			
		while(gamestillrunning1) {
			
    		board1.updateWorld();

			Canvas c=sh1.lockCanvas();
			
			if (c!=null) {
				board1.draw(c);
				sh1.unlockCanvasAndPost(c);
			}	
		}		
	}

	public synchronized void stopGame() {
		gamestillrunning1 = false;
	}	
}
