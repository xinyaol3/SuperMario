package assignment4.mario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MarioView extends SurfaceView implements SurfaceHolder.Callback  {
	
	private Board boardl;
	private MarioThread mt1l, mt2l;
		
	// Background
	Bitmap background=BitmapFactory.decodeResource(getResources(), R.drawable.background);

	// Regular Mario
	Bitmap mariodead=BitmapFactory.decodeResource(getResources(), R.drawable.mario_dead);
	Bitmap marioSL=BitmapFactory.decodeResource(getResources(), R.drawable.mario_stand_left);
	Bitmap marioSR=BitmapFactory.decodeResource(getResources(), R.drawable.mario_stand_right);
	Bitmap marioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.mario_run_left1);
	Bitmap marioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.mario_run_left2);
	Bitmap marioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.mario_run_right1);
	Bitmap marioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.mario_run_right2);
	Bitmap marioJL=BitmapFactory.decodeResource(getResources(), R.drawable.mario_jump_left);
	Bitmap marioJR=BitmapFactory.decodeResource(getResources(), R.drawable.mario_jump_right);
	
	// Big Mario
	Bitmap bigmarioSL=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_stand_left);
	Bitmap bigmarioSR=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_stand_right);
	Bitmap bigmarioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_run_left1);
	Bitmap bigmarioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_run_left2);
	Bitmap bigmarioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_run_right1);
	Bitmap bigmarioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_run_right2);
	Bitmap bigmarioJL=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_jump_left);
	Bitmap bigmarioJR=BitmapFactory.decodeResource(getResources(), R.drawable.bigmario_jump_right);
	
	// Golden Mario
	Bitmap goldmarioSL=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_stand_left);
	Bitmap goldmarioSR=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_stand_right);
	Bitmap goldmarioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_run_left1);
	Bitmap goldmarioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_run_left2);
	Bitmap goldmarioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_run_right1);
	Bitmap goldmarioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_run_right2);
	Bitmap goldmarioJL=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_jump_left);
	Bitmap goldmarioJR=BitmapFactory.decodeResource(getResources(), R.drawable.goldmario_jump_right);
	
	// Axe Mario
	Bitmap axemarioSL=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_stand_left);
	Bitmap axemarioSR=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_stand_right);
	Bitmap axemarioRL1=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_run_left1);
	Bitmap axemarioRL2=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_run_left2);
	Bitmap axemarioRR1=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_run_right1);
	Bitmap axemarioRR2=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_run_right2);
	Bitmap axemarioJL=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_jump_left);
	Bitmap axemarioJR=BitmapFactory.decodeResource(getResources(), R.drawable.axemario_jump_right);
	
	// Enemies
	Bitmap enemyMushroomW1=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_mushroom_walk1);
	Bitmap enemyMushroomW2=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_mushroom_walk2);
	Bitmap enemyMushroomD=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_mushroom_dead);
	Bitmap enemyTurtleWL1=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_turtle_walk_left1);
	Bitmap enemyTurtleWL2=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_turtle_walk_left2);
	Bitmap enemyTurtleWR1=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_turtle_walk_right1);
	Bitmap enemyTurtleWR2=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_turtle_walk_right2);
	Bitmap enemyTurtleD=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_turtle_dead);
	Bitmap enemyCannon=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_cannon);
	Bitmap enemyCannonballL=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_cannonball_left);
	Bitmap enemyCannonballR=BitmapFactory.decodeResource(getResources(), R.drawable.enemy_cannonball_right);
	
	// Blocks
	Bitmap breakable=BitmapFactory.decodeResource(getResources(), R.drawable.block_breakable);
	Bitmap unbreakable=BitmapFactory.decodeResource(getResources(), R.drawable.block_unbreakable);
	Bitmap question=BitmapFactory.decodeResource(getResources(), R.drawable.block_question);
	
	// Item
	Bitmap mushroom=BitmapFactory.decodeResource(getResources(), R.drawable.item_bigmario);
	Bitmap greenMushroom=BitmapFactory.decodeResource(getResources(), R.drawable.item_greenmushroom);
	Bitmap star1=BitmapFactory.decodeResource(getResources(), R.drawable.item_star1);
	Bitmap star2=BitmapFactory.decodeResource(getResources(), R.drawable.item_star2);
	Bitmap star3=BitmapFactory.decodeResource(getResources(), R.drawable.item_star3);
	Bitmap star4=BitmapFactory.decodeResource(getResources(), R.drawable.item_star4);	
	
	public MarioView(Context context) {
		super(context);
		this.getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		System.out.println("Surface Changed!");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		boardl = new Board(this);
		setOnTouchListener(boardl);
		mt1l = new MarioThread(this.getHolder(), boardl);
		mt2l = new MarioThread(this.getHolder(), boardl);
		mt1l.start();
		System.out.println("Mario Thread1 Started!");
		mt2l.start();
		System.out.println("Mario Thread2 Started!");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mt1l.stopGame();
		mt2l.stopGame();
		try {
			mt1l.join();
			System.out.println("Mario Thread1 Terminated!");
			System.out.println("Mario Thread1 is alvie: "+mt1l.isAlive());
			mt2l.join();
			System.out.println("Mario Thread2 Terminated!");
			System.out.println("Mario Thread2 is alvie: "+mt2l.isAlive());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
}