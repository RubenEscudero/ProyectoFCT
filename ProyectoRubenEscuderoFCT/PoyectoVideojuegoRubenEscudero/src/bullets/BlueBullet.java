package bullets;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create BlueBullet
* @author ruben
* @since 22/04/2020
*/
public class BlueBullet extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public BlueBullet(float x, float y, Stage s) {
		super(x, y, s);
		loadAnimationFromSheet("assets/bullets/blueBullets.png", 3, 5, 0.1f, true);
		setSpeed(1000);
		setRotation(90);
		setBoundaryPolygon(6);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		applyPhysics(dt);
	}

}
