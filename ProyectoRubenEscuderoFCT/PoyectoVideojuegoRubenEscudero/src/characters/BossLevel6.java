package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import bullets.BlueBullet;
import bullets.GreenBullet;
import bullets.IceBullet;
import bullets.RedBullet;
import gifdecoder.GifDecoder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;


/**
* Class to create BossLevel4
* @author ruben
* @since 22/04/2020
*/
public class BossLevel6 extends BaseActor{
	private float facingAngle;
	private boolean left;
	private boolean right;
	private boolean center;
	private long lastShootLeft;
	private long lastLight;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public BossLevel6(float x, float y, Stage s) {
		super(x, y, s);
		setAnimation(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/extra/bossLevel6.gif").read()));
	    setBoundaryPolygon(8);
	    left = true;
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		long time = System.currentTimeMillis();
			if(lastShootLeft - time < -300) {
				lastShootLeft = time;
				GreenBullet iceBullet = new GreenBullet(this.getX() + 70, this.getY(), getStage());
				iceBullet.setRotation(this.getFacingAngle());
				iceBullet.setMotionAngle(-90);
				iceBullet.setSpeed(300);
			}
		applyPhysics(dt);
		boundToWorld();
	}
	
	public float getFacingAngle(){
		return facingAngle;
	}
}
