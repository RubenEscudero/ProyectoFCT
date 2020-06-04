package bullets;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create GreenBullet
* @author ruben
* @since 22/04/2020
*/
public class GreenBullet extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public GreenBullet(float x, float y, Stage s) {
		super(x, y, s);
		loadTexture("assets/bullets/greenLaser.png");
		setSpeed(300);
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
