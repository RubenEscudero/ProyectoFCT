package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import bullets.RedBullet;
import gifdecoder.GifDecoder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;


/**
* Class to create BossLevel4
* @author ruben
* @since 22/04/2020
*/
public class BossLevel7 extends BaseActor{
	private float facingAngle;	
	private long lastShoot;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public BossLevel7(float x, float y, Stage s) {
		super(x, y, s);
		setAnimation(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/extra/boss2.gif").read()));
        
	    setBoundaryPolygon(8);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		long time = System.currentTimeMillis();
		if(lastShoot - time < -300) {
			lastShoot = time;
			RedBullet redBullet = new RedBullet(this.getX() + 80, this.getY() + 100, getStage());
			redBullet.setRotation(this.getFacingAngle());
			redBullet.setMotionAngle(-90);
			
			RedBullet redBullet2 = new RedBullet(this.getX() +125, this.getY() + 100, getStage());
			redBullet2.setRotation(this.getFacingAngle());
			redBullet2.setMotionAngle(-90);
			
		}
		
		applyPhysics(dt);
		boundToWorld();
	}
	
	public float getFacingAngle(){
		return facingAngle;
	}
}
