package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;

import base.BaseActor;
import bullets.GreenBullet;

/**
* Class to create BossLevel3
* @author ruben
* @since 22/04/2020
*/
public class BossLevel3 extends BaseActor{
	float facingAngle;	
	long lastGreenBulletShoot;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public BossLevel3(float x, float y, Stage s) {
		super(x, y, s);
		String[] filenames = 
            {"assets/characters/_0000_Layer-1.png", "assets/characters/_0001_Layer-2.png",
            		"assets/characters/_0002_Layer-3.png", "assets/characters/_0003_Layer-4.png"};

        loadAnimationFromFile(filenames, 0.1f, true);
        setSize(80, 80);
	    
	    setBoundaryPolygon(8);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		long time = System.currentTimeMillis();
		if(lastGreenBulletShoot - time < -300) {
			lastGreenBulletShoot = time;
			GreenBullet greenBullet = new GreenBullet(this.getX() + 40, this.getY() - 40, getStage());
			greenBullet.setRotation(this.getFacingAngle());
			greenBullet.setMotionAngle(-90);
			
		}
		
		applyPhysics(dt);
		boundToWorld();
	}
	
	public float getFacingAngle(){
		return facingAngle;
	}
}
