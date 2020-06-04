package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import bullets.RedBullet;
import bullets.SmallBullet;
import gifdecoder.GifDecoder;

/**
* Class to create Enemy
* @author ruben
* @since 22/04/2020
*/
public class EnemyExtra1 extends BaseActor{
	private float facingAngle;
	private long lastSmallBullet;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public EnemyExtra1(float x, float y, Stage s) {
		super(x, y, s);
		setAnimation(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/extra/enemy-01.gif").read()));
        
		facingAngle = 270;
		setBoundaryPolygon(8);
		setSize(80, 80);
		
		setSpeed(330);
		accelerateAtAngle(90);
		setMotionAngle(90);
		boundToWorld();
	}
	

	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		long time = System.currentTimeMillis();
		if(lastSmallBullet - time < -600) {
			lastSmallBullet = time;
			RedBullet smallBullet = new RedBullet(0, 0, getStage());
			smallBullet.centerAtActor(this);
			smallBullet.setMotionAngle(-90);
			smallBullet.setSpeed(300);
			smallBullet.setRotation(180);
		}
		
		applyPhysics(dt);
		boundToWorld();
	}
	
	
}
