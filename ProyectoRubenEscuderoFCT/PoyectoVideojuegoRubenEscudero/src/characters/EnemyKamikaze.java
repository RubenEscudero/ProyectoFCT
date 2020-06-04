package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;

/**
* Class to create EnemyKamikaze
* @author ruben
* @since 22/04/2020
*/
public class EnemyKamikaze extends BaseActor{
	private float facingAngle;	
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public EnemyKamikaze(float x, float y, Stage s) {
		super(x, y, s);
		String[] filenames = 
            {"assets/characters/_0000_Layer-1.png", "assets/characters/_0001_Layer-2.png",
            		"assets/characters/_0002_Layer-3.png", "assets/characters/_0003_Layer-4.png"};

        loadAnimationFromFile(filenames, 0.1f, true);
        setSize(50, 50);
	    
        setSpeed(400);
		accelerateAtAngle(-90);
		setMotionAngle(-90);
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
