package screens;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import base.BaseActor;
import base.BaseGame;
import base.BaseScreen;
import game.MyGame;

/**
 * Class to configure the game
 * @author ruben
 * @since 21/04/2020
 */
public class OptionsScreen extends BaseScreen{
	private Properties properties;
	private float musicVolume;
	private Button returnButton;
	private ButtonStyle buttonStyleReturn1;
	private ButtonStyle buttonStyleReturn2;
	private ButtonStyle buttonStyleHigh1;
	private ButtonStyle buttonStyleHigh2;
	private ButtonStyle buttonStyleMedium1;
	private ButtonStyle buttonStyleMedium2;
	private ButtonStyle buttonStyleLow1;
	private ButtonStyle buttonStyleLow2;
	private ButtonStyle buttonStyleMute1;
	private ButtonStyle buttonStyleMute2;
	private long changeColorButton;
	private boolean changeColorButtonState;
	private Music music;
	private Label musicLabel;
	private Label effectLabel;
	private Button highMusicButton;
	private Button mediumMusicButton;
	private Button lowMusicButton;
	private Button muteMusicButton;
	private Button highEffectButton;
	private Button mediumEffectButton;
	private Button lowEffectButton;
	private Button muteEffectButton;
	private boolean highMusic;
	private boolean mediumMusic;
	private boolean lowMusic;
	private boolean muteMusic;
	private boolean highEffect;
	private boolean mediumEffect;
	private boolean lowEffect;
	private boolean muteEffect;
	
	/**
	 * Initializes and loads the elements to be used
	 */
	@Override
	public void initialize() {
		
		BaseActor background = new BaseActor(0, 0, mainStage);
		background.loadTexture("assets/backgrounds/fondoSound.jfif");
		background.setSize(514, 700);
		
		initButtons();
		
		properties = new Properties();
		
		try {
			properties.load(new FileInputStream("datos.conf"));
			musicVolume = Float.parseFloat(properties.getProperty("music"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/Heartbreak3.mp3"));
		music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();
        
        musicLabel = new Label("\n \n \n Music volume", BaseGame.labelStyle);
        effectLabel = new Label("\n \n \n Effects volume", BaseGame.labelStyle);
        
       
        uiTable.top();
        uiTable.add().expandX();
        uiTable.add(musicLabel);
        uiTable.add().expandX();
        uiTable.add(effectLabel);
        uiTable.add().expandX();
       
	}


	/**
	 * Update what happens in the game
	 */
	@Override
	public void update(float dt) {
		changeButton();
		
		if(highMusic) {
			highMusicButton.setStyle(buttonStyleHigh2);
		}
		else {
			highMusicButton.setStyle(buttonStyleHigh1);
		}
		if(mediumMusic) {
			mediumMusicButton.setStyle(buttonStyleMedium2);
		}
		else {
			mediumMusicButton.setStyle(buttonStyleMedium1);
		}
		if(lowMusic) {
			lowMusicButton.setStyle(buttonStyleLow2);
		}
		else {
			lowMusicButton.setStyle(buttonStyleLow1);
		}
		if(muteMusic) {
			muteMusicButton.setStyle(buttonStyleMute2);
		}
		else {
			muteMusicButton.setStyle(buttonStyleMute1);
		}
		
		if(highEffect) {
			highEffectButton.setStyle(buttonStyleHigh2);
		}
		else {
			highEffectButton.setStyle(buttonStyleHigh1);
		}
		if(mediumEffect) {
			mediumEffectButton.setStyle(buttonStyleMedium2);
		}
		else {
			mediumEffectButton.setStyle(buttonStyleMedium1);
		}
		if(lowEffect) {
			lowEffectButton.setStyle(buttonStyleLow2);
		}
		else {
			lowEffectButton.setStyle(buttonStyleLow1);
		}
		if(muteEffect) {
			muteEffectButton.setStyle(buttonStyleMute2);
		}
		else {
			muteEffectButton.setStyle(buttonStyleMute1);
		}
	}
	
	/**
	 * Load buttonStyles and Buttons
	 */
	private void initButtons() {
		buttonStyleReturn1 = new ButtonStyle();
		
		Texture buttonTexReturn1 = new Texture(Gdx.files.internal("assets/buttons/buttonReturn1.png"));
		TextureRegion buttonRegionReturn1 = new TextureRegion(buttonTexReturn1);
		buttonStyleReturn1.up = new TextureRegionDrawable(buttonRegionReturn1);
		
		buttonStyleReturn2 = new ButtonStyle();
		
		Texture buttonTexReturn2 = new Texture(Gdx.files.internal("assets/buttons/buttonReturn2.png"));
		TextureRegion buttonRegionReturn2 = new TextureRegion(buttonTexReturn2);
		buttonStyleReturn2.up = new TextureRegionDrawable(buttonRegionReturn2);
		
		
		returnButton = new Button(buttonStyleReturn2);
		returnButton.setPosition(15, 640);
		uiStage.addActor(returnButton);
		
		returnButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							music.stop();
							if(highMusic) {
								properties.setProperty("music", "1.00f");
							}
							if(mediumMusic) {
								properties.setProperty("music", "0.50f");
							}
							if(lowMusic) {
								properties.setProperty("music", "0.10f");
							}
							if(muteMusic) {
								properties.setProperty("music", "0.00f");
							}
							if(highEffect) {
								properties.setProperty("effect", "1.00f");
							}
							if(mediumEffect) {
								properties.setProperty("effect", "0.50f");
							}
							if(lowEffect) {
								properties.setProperty("effect", "0.10f");
							}
							if(muteEffect) {
								properties.setProperty("effect", "0.00f");
							}
							try {
								properties.store(new FileOutputStream("datos.conf"), "Fichero de datos");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							MyGame.setActiveScreen( new MenuScreen() );
							return false;
				}
		);
		
		buttonStyleHigh1 = new ButtonStyle();
		
		Texture buttonTexHigh1 = new Texture(Gdx.files.internal("assets/buttons/high1.png"));
		TextureRegion buttonRegionHigh1 = new TextureRegion(buttonTexHigh1);
		buttonStyleHigh1.up = new TextureRegionDrawable(buttonRegionHigh1);
		
		buttonStyleHigh2 = new ButtonStyle();
		
		Texture buttonTexHigh2 = new Texture(Gdx.files.internal("assets/buttons/high2.png"));
		TextureRegion buttonRegionHigh2 = new TextureRegion(buttonTexHigh2);
		buttonStyleHigh2.up = new TextureRegionDrawable(buttonRegionHigh2);
		
		highMusicButton = new Button(buttonStyleHigh1);
		highMusicButton.setPosition(70, 500);
		uiStage.addActor(highMusicButton);
		
		highEffectButton = new Button(buttonStyleHigh1);
		highEffectButton.setPosition(300, 500);
		uiStage.addActor(highEffectButton);
		
		highMusicButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highMusic = true;
							mediumMusic = false;
							lowMusic = false;
							muteMusic = false;
							return false;
				}
		);
		
		highEffectButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highEffect = true;
							mediumEffect = false;
							lowEffect = false;
							muteEffect = false;
							return false;
				}
		);
		
		buttonStyleMedium1 = new ButtonStyle();
		
		Texture buttonTexMedium1 = new Texture(Gdx.files.internal("assets/buttons/Medium1.png"));
		TextureRegion buttonRegionMedium1 = new TextureRegion(buttonTexMedium1);
		buttonStyleMedium1.up = new TextureRegionDrawable(buttonRegionMedium1);
		
		buttonStyleMedium2 = new ButtonStyle();
		
		Texture buttonTexMedium2 = new Texture(Gdx.files.internal("assets/buttons/Medium2.png"));
		TextureRegion buttonRegionMedium2 = new TextureRegion(buttonTexMedium2);
		buttonStyleMedium2.up = new TextureRegionDrawable(buttonRegionMedium2);
		
		mediumMusicButton = new Button(buttonStyleMedium1);
		mediumMusicButton.setPosition(70, 400);
		uiStage.addActor(mediumMusicButton);
		
		mediumEffectButton = new Button(buttonStyleMedium1);
		mediumEffectButton.setPosition(300, 400);
		uiStage.addActor(mediumEffectButton);
		
		mediumMusicButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highMusic = false;
							mediumMusic = true;
							lowMusic = false;
							muteMusic = false;
							return false;
				}
		);
		
		mediumEffectButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highEffect = false;
							mediumEffect = true;
							lowEffect = false;
							muteEffect = false;
							return false;
				}
		);
		
		
		buttonStyleLow1 = new ButtonStyle();
		
		Texture buttonTexLow1 = new Texture(Gdx.files.internal("assets/buttons/Low1.png"));
		TextureRegion buttonRegionLow1 = new TextureRegion(buttonTexLow1);
		buttonStyleLow1.up = new TextureRegionDrawable(buttonRegionLow1);
		
		buttonStyleLow2 = new ButtonStyle();
		
		Texture buttonTexLow2 = new Texture(Gdx.files.internal("assets/buttons/Low2.png"));
		TextureRegion buttonRegionLow2 = new TextureRegion(buttonTexLow2);
		buttonStyleLow2.up = new TextureRegionDrawable(buttonRegionLow2);
		
		lowMusicButton = new Button(buttonStyleLow1);
		lowMusicButton.setPosition(70, 300);
		uiStage.addActor(lowMusicButton);
		
		lowEffectButton = new Button(buttonStyleLow1);
		lowEffectButton.setPosition(300, 300);
		uiStage.addActor(lowEffectButton);
		
		lowMusicButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highMusic = false;
							mediumMusic = false;
							lowMusic = true;
							muteMusic = false;
							return false;
				}
		);
		
		lowEffectButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highEffect = false;
							mediumEffect = false;
							lowEffect = true;
							muteEffect = false;
							return false;
				}
		);
		
		buttonStyleMute1 = new ButtonStyle();
		
		Texture buttonTexMute1 = new Texture(Gdx.files.internal("assets/buttons/Mute1.png"));
		TextureRegion buttonRegionMute1 = new TextureRegion(buttonTexMute1);
		buttonStyleMute1.up = new TextureRegionDrawable(buttonRegionMute1);
		
		buttonStyleMute2 = new ButtonStyle();
		
		Texture buttonTexMute2 = new Texture(Gdx.files.internal("assets/buttons/Mute2.png"));
		TextureRegion buttonRegionMute2 = new TextureRegion(buttonTexMute2);
		buttonStyleMute2.up = new TextureRegionDrawable(buttonRegionMute2);
		
		muteMusicButton = new Button(buttonStyleMute1);
		muteMusicButton.setPosition(70, 200);
		uiStage.addActor(muteMusicButton);
		
		muteEffectButton = new Button(buttonStyleMute1);
		muteEffectButton.setPosition(300, 200);
		uiStage.addActor(muteEffectButton);
		
		muteMusicButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highMusic = false;
							mediumMusic = false;
							lowMusic = false;
							muteMusic = true;
							return false;
				}
		);
		
		muteEffectButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							highEffect = false;
							mediumEffect = false;
							lowEffect = false;
							muteEffect = true;
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
				returnButton.setStyle(buttonStyleReturn2);
				changeColorButtonState = false;
			}
			else {
				returnButton.setStyle(buttonStyleReturn1);
				changeColorButtonState = true;
			}
			
		}
	}

}
