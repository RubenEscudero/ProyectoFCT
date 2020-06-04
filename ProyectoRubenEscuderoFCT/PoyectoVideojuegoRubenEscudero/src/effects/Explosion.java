package effects;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create Explosion
* @author ruben
* @since 22/04/2020
*/
public class Explosion extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public Explosion(float x, float y, Stage s) {
		super(x, y, s);
		loadAnimationFromSheet("assets/effects/explosion-6.png", 1, 8, 0.1f, false);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
	}
}
