package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import bullets.SmallBullet;
import bullets.SpaceBulletSmall;
import gifdecoder.GifDecoder;

/**
* Class to create Enemy
* @author ruben
* @since 22/04/2020
*/
public class EnemyExtra2 extends BaseActor{
	private float facingAngle;
	private long lastSmallBullet;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public EnemyExtra2(float x, float y, Stage s) {
		super(x, y, s);
		setAnimation(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/extra/boss1.gif").read()));
        
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
			SpaceBulletSmall smallBullet = new SpaceBulletSmall(0, 0, getStage());
			smallBullet.centerAtActor(this);
			smallBullet.setMotionAngle(-90);
			smallBullet.setRotation(180);
			smallBullet.setSpeed(300);
			
			SpaceBulletSmall smallBullet2 = new SpaceBulletSmall(0, 0, getStage());
			smallBullet2.centerAtActor(this);
			smallBullet2.setMotionAngle(-90);
			smallBullet2.setRotation(180);
			smallBullet2.setSpeed(300);
		}
		
		applyPhysics(dt);
		boundToWorld();
	}
	
	
}
