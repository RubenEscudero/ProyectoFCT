package items;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create ShieldBall
* @author ruben
* @since 22/04/2020
*/
public class ShieldBall extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public ShieldBall(float x, float y, Stage s) {
		super(x, y, s);
		loadTexture("assets/items/shield_plat.png");
		setSize(35, 35);
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
