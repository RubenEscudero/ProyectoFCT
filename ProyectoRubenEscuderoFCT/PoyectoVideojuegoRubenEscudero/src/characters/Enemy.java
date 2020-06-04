package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import bullets.SmallBullet;

/**
* Class to create Enemy
* @author ruben
* @since 22/04/2020
*/
public class Enemy extends BaseActor{
	private float facingAngle;
	private long lastSmallBullet;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public Enemy(float x, float y, Stage s) {
		super(x, y, s);
		String[] filenames = 
            {"assets/characters/ballred1.png", "assets/characters/ballred2.png",
            		"assets/characters/ballred3.png", "assets/characters/ballred4.png"};

        loadAnimationFromFile(filenames, 0.1f, true);
        
		facingAngle = 270;
		setBoundaryPolygon(8);
		setSize(40, 40);
		
		setSpeed(300);
		accelerateAtAngle(90);
		setMotionAngle(90);
		boundToWorld();
	}
	

	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		long time = System.currentTimeMillis();
		if(lastSmallBullet - time < -600) {
			lastSmallBullet = time;
			SmallBullet smallBullet = new SmallBullet(0, 0, getStage());
			smallBullet.centerAtActor(this);
			smallBullet.setMotionAngle(-90);
			smallBullet.setSpeed(300);
			
		}
		
		applyPhysics(dt);
		boundToWorld();
	}
	
	
}
