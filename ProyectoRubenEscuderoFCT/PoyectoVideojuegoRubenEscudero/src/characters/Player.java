package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import base.BaseActor;
import items.Shield;

/**
* Class to create Player
* @author ruben
* @since 22/04/2020
*/
public class Player extends BaseActor{
	private float facingAngle;
	private Shield shield;
	private int shieldPower;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public Player(float x, float y, Stage s) {
			super(x, y, s);
		
			String[] filenames = 
				{"assets/characters/spaceship1.png", "assets/characters/spaceship2.png",
	            			"assets/characters/spaceship3.png"};

			loadAnimationFromFile(filenames, 0.1f, true);
	    
			shield = new Shield(0, 0, s);
			addActor(shield);
			shield.centerAtPosition(getWidth()/2, getHeight()/2);
			shieldPower = 100;
	    
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
	        

	       if (Gdx.input.isKeyPressed(Keys.A)) {
	        	 accelerateAtAngle(180);
	       }   
	       else if (Gdx.input.isKeyPressed(Keys.D)) {
	        	accelerateAtAngle(0);
	       }
	       else if (Gdx.input.isKeyPressed(Keys.UP)) {
	        	 accelerateAtAngle(90);
	       }   
	       else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
	        	accelerateAtAngle(270);
	       }
	        
	       shield.setOpacity(shieldPower / 100f);    
	        
	       applyPhysics(dt);

	       setRotation(90);   
		
	       alignCamera();
	       boundToWorld();
	       applyPhysics(dt);
	}
	
    public float getFacingAngle(){
			return facingAngle;
	}

	public int getShieldPower() {
		return shieldPower;
	}

	public void setShieldPower(int shieldPower) {
		this.shieldPower = shieldPower;
	}
	
    
}
