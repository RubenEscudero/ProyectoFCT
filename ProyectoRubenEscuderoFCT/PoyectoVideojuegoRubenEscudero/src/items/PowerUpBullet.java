package items;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
	* Class to create PowerUpBullets
	* @author ruben
	* @since 22/04/2020
	*/
public class PowerUpBullet extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public PowerUpBullet(float x, float y, Stage s) {
		super(x, y, s);
		loadTexture("assets/items/orb_blue.png");
		setBoundaryPolygon(6);
		setSize(40, 40);
	}
	
	/**
	 * Method to indicate what does per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		applyPhysics(dt);
	}

}
