package bullets;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create SmallBullet2
* @author ruben
* @since 22/04/2020
*/
public class SmallBullet2 extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public SmallBullet2(float x, float y, Stage s) {
		super(x, y, s);
		loadAnimationFromSheet("assets/bullets/yellowBalls.png", 3, 5, 0.1f, true);
		setSpeed(200);
		setBoundaryPolygon(6);
		setSize(30, 30);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		applyPhysics(dt);
	}

}
