package bullets;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create RedBullet
* @author ruben
* @since 22/04/2020
*/
public class RedBullet extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public RedBullet(float x, float y, Stage s) {
		super(x, y, s);
		loadTexture("assets/bullets/laser.png");
		setSpeed(600);
		setRotation(90);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		applyPhysics(dt);
	}

}
