package characters;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import base.BaseActor;
import bullets.BlueBullet;
import bullets.GreenBullet;
import bullets.RedBullet;
import gifdecoder.GifDecoder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
	* Class to create BossLevel5
	* @author ruben
	* @since 22/04/2020
	*/
public class BossLevel5 extends BaseActor{
	private Animation animation1;
	private Animation animation2;
	private Animation animation3;
	private Animation animation4;
	private Animation animation5;
	private Texture texture;
	private float facingAngle;	
	private long lastShoot;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public BossLevel5(float x, float y, Stage s) {
		super(x, y, s);
		
		String fileName = "assets/characters/boss1.png";
		int rows = 1;
		int cols = 1;
		Texture texture = new Texture(Gdx.files.internal(fileName), true);
		int frameWidth = texture.getWidth() / cols;
		int frameHeight = texture.getHeight() / rows;
		float frameDuration = 0.2f;
		
		TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);
		
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		
		for (int c = 0; c < cols; c++) {
			textureArray.add( temp[0][c] );
		}
		animation1 = new Animation(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);
		textureArray.clear();
		
		//animation 2
		String fileName2 = "assets/characters/boss2.png";
		Texture texture2 = new Texture(Gdx.files.internal(fileName2), true);
		
		
		TextureRegion[][] temp2 = TextureRegion.split(texture2, frameWidth, frameHeight);
		
		Array<TextureRegion> textureArray2 = new Array<TextureRegion>();
		
		for (int c = 0; c < cols; c++) {
			textureArray2.add( temp2[0][c] );
		}
		animation2 = new Animation(frameDuration, textureArray2, Animation.PlayMode.LOOP_PINGPONG);
		textureArray2.clear();
		
		//animation3
		String fileName3 = "assets/characters/boss3.png";
		Texture texture3 = new Texture(Gdx.files.internal(fileName3), true);
		
		TextureRegion[][] temp3 = TextureRegion.split(texture3, frameWidth, frameHeight);
		
		Array<TextureRegion> textureArray3 = new Array<TextureRegion>();
		
		for (int c = 0; c < cols; c++) {
			textureArray3.add( temp3[0][c] );
		}
		animation3 = new Animation(frameDuration, textureArray3, Animation.PlayMode.LOOP_PINGPONG);
		textureArray3.clear();
		
		//animation 4
		String fileName4 = "assets/characters/boss4.png";
		Texture texture4 = new Texture(Gdx.files.internal(fileName4), true);
				
		TextureRegion[][] temp4 = TextureRegion.split(texture4, frameWidth, frameHeight);
				
		Array<TextureRegion> textureArray4 = new Array<TextureRegion>();
				
		for (int c = 0; c < cols; c++) {
			textureArray4.add( temp4[0][c] );
		}
		animation4 = new Animation(frameDuration, textureArray4, Animation.PlayMode.LOOP_PINGPONG);
		textureArray4.clear();
				
		//animation 5
		String fileName5 = "assets/characters/boss5.png";
		Texture texture5 = new Texture(Gdx.files.internal(fileName5), true);
				
		TextureRegion[][] temp5 = TextureRegion.split(texture5, frameWidth, frameHeight);
				
		Array<TextureRegion> textureArray5 = new Array<TextureRegion>();
				
		for (int c = 0; c < cols; c++) {
			textureArray5.add( temp5[0][c] );
		}
		animation5 = new Animation(frameDuration, textureArray5, Animation.PlayMode.LOOP_PINGPONG);
		textureArray5.clear();
		
		

        setSize(150, 150);
        
	    setBoundaryPolygon(8);
	}
	
	/**
	 * Method to indicate what does 60 times per second
	 */
	public void act(float dt) {
		super.act(dt);
		
		long time = System.currentTimeMillis();
		System.out.println(getLife());
		if(getLife() >= 3500) {
			setAnimation(animation1);
			if(lastShoot - time < -300) {
				lastShoot = time;
				RedBullet redBullet = new RedBullet(this.getX() + 40, this.getY() + 30, getStage());
				redBullet.setRotation(this.getFacingAngle());
				redBullet.setMotionAngle(-90);
				
				RedBullet redBullet2 = new RedBullet(this.getX() + 145, this.getY() + 30, getStage());
				redBullet2.setRotation(this.getFacingAngle());
				redBullet2.setMotionAngle(-90);
				
				GreenBullet greenBullet = new GreenBullet(0, 0, getStage());
				greenBullet.centerAtActor(this);
				greenBullet.setY(this.getY() - 50);
				greenBullet.setRotation(180);
				greenBullet.setMotionAngle(-90);
				
			}
		}
		else if(getLife() >= 3000) {
			setAnimation(animation2);
			if(lastShoot - time < -600) {
				lastShoot = time;
				RedBullet redBullet = new RedBullet(this.getX() + 40, this.getY() + 30, getStage());
				redBullet.setRotation(this.getFacingAngle());
				redBullet.setMotionAngle(-90);
				
				RedBullet redBullet2 = new RedBullet(this.getX() + 145, this.getY() + 30, getStage());
				redBullet2.setRotation(this.getFacingAngle());
				redBullet2.setMotionAngle(-90);
				
				GreenBullet greenBullet = new GreenBullet(0, 0, getStage());
				greenBullet.centerAtActor(this);
				greenBullet.setY(this.getY() - 50);
				greenBullet.setRotation(180);
				greenBullet.setMotionAngle(-90);
				
			}
		}
		else if(getLife() >= 2500) {
			setAnimation(animation3);
			if(lastShoot - time < -600) {
				lastShoot = time;
				
				GreenBullet greenBullet = new GreenBullet(0, 0, getStage());
				greenBullet.centerAtActor(this);
				greenBullet.setY(this.getY() - 50);
				greenBullet.setRotation(180);
				greenBullet.setMotionAngle(-90);
				
			}
		}
		else if(getLife() >= 1500) {
			setAnimation(animation4);
			if(lastShoot - time < -800) {
				lastShoot = time;
				
				GreenBullet greenBullet = new GreenBullet(0, 0, getStage());
				greenBullet.centerAtActor(this);
				greenBullet.setY(this.getY() - 50);
				greenBullet.setRotation(180);
				greenBullet.setMotionAngle(-90);
				
			}
		}
		else if(getLife() >= 0) {
			setAnimation(animation5);
			if(lastShoot - time < -200) {
				lastShoot = time;
				
				BlueBullet greenBullet = new BlueBullet(0, 0, getStage());
				greenBullet.centerAtActor(this);
				greenBullet.setY(this.getY() - 50);
				greenBullet.setRotation(180);
				greenBullet.setMotionAngle(-90);
				
			}
		}
		
		boundToWorld();
	}
	
	public float getFacingAngle(){
		return facingAngle;
	}
}
