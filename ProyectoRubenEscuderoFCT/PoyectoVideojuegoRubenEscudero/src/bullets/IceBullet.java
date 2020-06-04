package bullets;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create BlueBullet
* @author ruben
* @since 22/04/2020
*/
public class IceBullet extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public IceBullet(float x, float y, Stage s) {
		super(x, y, s);
		loadTexture("assets/extra/laserBlue10.png");
		setSpeed(300);
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
