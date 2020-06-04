package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import gifdecoder.GifDecoder;

/**
	* Class to create Coin
	* @author ruben
	* @since 22/04/2020
	*/
public class Coin extends BaseActor{
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public Coin(float x, float y, Stage s) {
		super(x, y, s);
		setAnimation(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/items/purpleStar.gif").read()));
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
