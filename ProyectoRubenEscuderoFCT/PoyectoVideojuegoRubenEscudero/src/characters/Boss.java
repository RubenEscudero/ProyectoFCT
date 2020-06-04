package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create Boss
* @author ruben
* @since 22/04/2020
*/
public class Boss extends BaseActor{
	float facingAngle;	
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public Boss(float x, float y, Stage s) {
		super(x, y, s);
		String[] filenames = 
            {"assets/characters/botred1.png", "assets/characters/botred2.png",
            		"assets/characters/botred3.png", "assets/characters/botred4.png"};

        loadAnimationFromFile(filenames, 0.1f, true);
        
		setBoundaryPolygon(8);
		
		setAcceleration(900);
	    setMaxSpeed(900);
	    
	    setBoundaryPolygon(8);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		applyPhysics(dt);
		boundToWorld();
	}
	
	public float getFacingAngle(){
		return facingAngle;
	}
}
