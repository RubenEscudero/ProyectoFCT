package screens;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

import base.BaseActor;
import base.BaseScreen;
import game.MyGame;

/**
 * 
 * @author ruben
 * @since 22/04/2020
 */
public class MenuScreen extends BaseScreen{
	
	private boolean changeColorButtonState;
	private Button startLevels;
	private Button buttonScores;
	private Button buttonOptions;
	private ButtonStyle buttonStyle;
	private ButtonStyle buttonStyle2;
	private ButtonStyle buttonStyle3;
	private ButtonStyle buttonStyle4;
	private ButtonStyle buttonStyle5;
	private ButtonStyle buttonStyle6;
	private long changeColorButton;
	private Music music;
	private BaseActor title;
	private Properties properties;
	private float musicVolume;
	
	/**
	 * Initializes and loads the elements to be used
	 */
	@Override
	public void initialize() {
		changeColorButton = System.currentTimeMillis();
		
		BaseActor background = new BaseActor(0, 0, mainStage);
		background.loadTexture("assets/backgrounds/retro.jpg");
		background.setSize(514, 700);
		
		initButtons();
		
		title = new BaseActor(30, 600, uiStage);
		title.loadTexture("assets/buttons/titulo.png");
		
		properties = new Properties();
		try {
			properties.load(new FileInputStream("datos.conf"));
			musicVolume = Float.parseFloat(properties.getProperty("music"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/Heartbreak2.mp3"));
		music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();
        
	}

	/**
	 * Update what happens in the game
	 */
	@Override
	public void update(float dt) {
		changeButton();
	}
	
	
	/**
	 * Load buttonStyles and Buttons
	 */
	private void initButtons() {
		buttonStyle = new ButtonStyle();
		
		Texture buttonTex = new Texture(Gdx.files.internal("assets/buttons/buttonLevels1.png"));
		TextureRegion buttonRegion = new TextureRegion(buttonTex);
		buttonStyle.up = new TextureRegionDrawable(buttonRegion);
		
		buttonStyle2 = new ButtonStyle();
		
		Texture buttonTex2 = new Texture(Gdx.files.internal("assets/buttons/buttonLevels2.png"));
		TextureRegion buttonRegion2 = new TextureRegion(buttonTex2);
		buttonStyle2.up = new TextureRegionDrawable(buttonRegion2);


		buttonStyle3 = new ButtonStyle();
		
		Texture buttonTex3 = new Texture(Gdx.files.internal("assets/buttons/buttonScores1.png"));
		TextureRegion buttonRegion3 = new TextureRegion(buttonTex3);
		buttonStyle3.up = new TextureRegionDrawable(buttonRegion3);
		
		buttonStyle4 = new ButtonStyle();
		
		Texture buttonTex4 = new Texture(Gdx.files.internal("assets/buttons/buttonScores2.png"));
		TextureRegion buttonRegion4 = new TextureRegion(buttonTex4);
		buttonStyle4.up = new TextureRegionDrawable(buttonRegion4);
		
		buttonStyle5 = new ButtonStyle();
		
		Texture buttonTex5 = new Texture(Gdx.files.internal("assets/buttons/buttonOption1.png"));
		TextureRegion buttonRegion5 = new TextureRegion(buttonTex5);
		buttonStyle5.up = new TextureRegionDrawable(buttonRegion5);
		
		buttonStyle6 = new ButtonStyle();
		
		Texture buttonTex6 = new Texture(Gdx.files.internal("assets/buttons/buttonOption2.png"));
		TextureRegion buttonRegion6 = new TextureRegion(buttonTex6);
		buttonStyle6.up = new TextureRegionDrawable(buttonRegion6);
		
		startLevels = new Button(buttonStyle);
		startLevels.setPosition(170, 470);
		uiStage.addActor(startLevels);
		
		buttonScores = new Button(buttonStyle3);
		buttonScores.setPosition(170, 340);
		uiStage.addActor(buttonScores);
		
		buttonOptions = new Button(buttonStyle5);
		buttonOptions.setPosition(165, 210);
		uiStage.addActor(buttonOptions);
		
		startLevels.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							music.stop();
							MyGame.setActiveScreen( new NameScreen() );
							return false;
				}
		);
		
		buttonScores.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							music.stop();
							MyGame.setActiveScreen( new ScoresScreen() );
							return false;
				}
		);
		
		buttonOptions.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							music.stop();
							MyGame.setActiveScreen( new OptionsScreen() );
							return false;
				}
		);
	}
	
	/**
	 * Method to change the style of the buttons
	 */
	private void changeButton() {
		long time = System.currentTimeMillis();
		if(changeColorButton - time < -500){
			changeColorButton = time;
			if(changeColorButtonState) {
				startLevels.setStyle(buttonStyle);
				buttonScores.setStyle(buttonStyle3);
				buttonOptions.setStyle(buttonStyle5);
				changeColorButtonState = false;
			}
			else {
				startLevels.setStyle(buttonStyle2);
				buttonScores.setStyle(buttonStyle4);
				buttonOptions.setStyle(buttonStyle6);
				changeColorButtonState = true;
			}
			
			
		}
	}
	

}
