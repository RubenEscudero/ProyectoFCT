package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;

import base.BaseActor;
import gifdecoder.GifDecoder;

/**
* Class to create BossLevel2
* @author ruben
* @since 22/04/2020
*/
public class BossLevel2 extends BaseActor{
	float facingAngle;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public BossLevel2(float x, float y, Stage s) {
		super(x, y, s);
		
		setAnimation(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/characters/spaceship3.gif").read()));
	        
	        setAcceleration(900);
	        setMaxSpeed(900);
	        setDeceleration(400);
	        
	        
	        setBoundaryPolygon(8);
	    }
	
		/**
		 * Method to indicate what does 60 times per second
		 */
	    public void act(float dt){
	        super.act( dt );
	  
	        applyPhysics(dt); 
	        boundToWorld();
	        
	    }
	
	    public float getFacingAngle(){
			return facingAngle;
		}
	
}
