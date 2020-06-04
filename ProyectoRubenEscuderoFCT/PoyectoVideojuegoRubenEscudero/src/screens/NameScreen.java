package screens;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import base.BaseActor;
import base.BaseGame;
import base.BaseScreen;
import game.MyGame;

/**
 * Class to insert your name
 * @author ruben
 * @since 22/04/2020
 */
public class NameScreen extends BaseScreen{
	private boolean changeColorButtonState;
	private Button startLevels;
	private long changeColorButton;
	private Button returnButton;
	private ButtonStyle buttonStyleReturn1;
	private ButtonStyle buttonStyleReturn2;
	private Music music;
	private BaseActor title;
	private Properties properties;
	private float musicVolume;
	private ButtonStyle buttonStyle;
	private ButtonStyle buttonStyle2;
	private String name;
	private Label nameLabel;
	private String[] basePlayers;
	private BaseActor titleName;
	private BaseActor arrow;
	
	/**
	 * Initializes and loads the elements to be used
	 */
	@Override
	public void initialize() {
		BaseActor background = new BaseActor(0, 0, mainStage);
		background.loadTexture("assets/backgrounds/retroShip.jpg");
		background.setSize(514, 700);
		
		titleName = new BaseActor(30, 450, uiStage);
		titleName.loadTexture("assets/buttons/tituloName.png");
		arrow = new BaseActor(230, 380, uiStage);
		arrow.loadTexture("assets/buttons/arrow.png");
		arrow.setSize(40, 40);
		
		initButtons();
		
		properties = new Properties();
		basePlayers = new String[5];
		try {
			properties.load(new FileInputStream("datos.conf"));
			musicVolume = Float.parseFloat(properties.getProperty("music"));
			for(int i = 0; i < 5; i++) {
				basePlayers[i] = properties.getProperty("player" + i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/Heartbreak2.mp3"));
		music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();

        name = "";
        
        nameLabel = new Label(name, BaseGame.labelStyle);
	    uiTable.add(nameLabel);
	}
	
	/**
	 * Update what happens in the game
	 */
	@Override
	public void update(float dt) {
		changeButton();
		
		if(name.length() >= 10) {
			
		}
		else {
			selectLetter();
		}
		 if (Gdx.input.isKeyJustPressed(Keys.DEL)) {
	    	   name = "";
	    	   nameLabel.remove();
	    	   nameLabel = new Label(name, BaseGame.labelStyle);
				
			   uiTable.add(nameLabel);
	       }
		
	}
	
	/**
	 * Method who writes the typed letters
	 */
	private void selectLetter() {
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			  name = name + "Q";
			  nameLabel.remove();
			  nameLabel = new Label(name, BaseGame.labelStyle);
		      uiTable.add(nameLabel);
	    }   
	       else if (Gdx.input.isKeyJustPressed(Keys.W)) {
	    	   name = name + "W";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
		
			        uiTable.add(nameLabel);
			      
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.E)) {
	    	   name = name + "E";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			        
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.R)) {
	    	   name = name + "R";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			       
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.T)) {
	    	   name = name + "T";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			       
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.Y)) {
	    	   name = name + "Y";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			       
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.U)) {
	    	   name = name + "U";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			      
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.I)) {
	    	   name = name + "I";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			        
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.O)) {
	    	   name = name + "O";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			       
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.P)) {
	    	   name = name + "P";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			        
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.A)) {
	    	   name = name + "A";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			       
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.S)) {
	    	   name = name + "S";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			       
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.D)) {
	    	   name = name + "D";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			     
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.F)) {
	    	   name = name + "F";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			    
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.G)) {
	    	   name = name + "G";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			      
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.H)) {
	    	   name = name + "H";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			       
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.J)) {
	    	   name = name + "J";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			       
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.K)) {
	    	   name = name + "K";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
			
			        uiTable.add(nameLabel);
			      
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.L)) {
	    	   name = name + "L";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			    
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
	    	   name = name + "Z";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			      
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.X)) {
	    	   name = name + "X";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				 
			        uiTable.add(nameLabel);
			    
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.C)) {
	    	   name = name + "C";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
			
			        uiTable.add(nameLabel);
			 
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.V)) {
	    	   name = name + "V";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				  
			        uiTable.add(nameLabel);
			      
	       }
	       else if (Gdx.input.isKeyJustPressed(Keys.B)) {
	    	   name = name + "B";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				  
			        uiTable.add(nameLabel);
			       
	       }   
	       else if (Gdx.input.isKeyJustPressed(Keys.N)) {
	    	   name = name + "N";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			       
	       }
	       else if (Gdx.input.isKeyJustPressed((Keys.M))) {
	    	   name = name + "M";
	    	   nameLabel.remove();
				  nameLabel = new Label(name, BaseGame.labelStyle);
				
			        uiTable.add(nameLabel);
			      
	       }
	     	
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
		
		startLevels = new Button(buttonStyle);
		startLevels.setPosition(170, 200);
		uiStage.addActor(startLevels);
		
		startLevels.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							if(name.equalsIgnoreCase("")) {
								
							}
							else {
								boolean exists = false;
								for(int i= 0; i < 5; i++) {
									if(basePlayers[i].split("-")[0].equalsIgnoreCase(name)) {
										properties.setProperty("actualname", name);
										properties.setProperty("actualpoints1", basePlayers[i].split("-")[1]);
										properties.setProperty("actualpoints2", basePlayers[i].split("-")[2]);
										properties.setProperty("actualpoints3", basePlayers[i].split("-")[3]);
										properties.setProperty("actualpoints4", basePlayers[i].split("-")[4]);
										properties.setProperty("actualpoints5", basePlayers[i].split("-")[5]);
										properties.setProperty("actualpoints6", basePlayers[i].split("-")[6]);
										properties.setProperty("actualpoints7", basePlayers[i].split("-")[7]);
										try {
											properties.store(new FileOutputStream("datos.conf"), "Fichero de datos");
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										exists = true;
									}
								}
								if(!exists) {
									properties.setProperty("actualname", name);
									properties.setProperty("actualpoints1", "0");
									properties.setProperty("actualpoints2", "0");
									properties.setProperty("actualpoints3", "0");
									properties.setProperty("actualpoints4", "0");
									properties.setProperty("actualpoints5", "0");
									properties.setProperty("actualpoints6", "0");
									properties.setProperty("actualpoints7", "0");
									try {
										properties.store(new FileOutputStream("datos.conf"), "Fichero de datos");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								music.stop();
								MyGame.setActiveScreen( new LevelScreen() );
							}
							
							return false;
				}
		);
		
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
							MyGame.setActiveScreen( new MenuScreen() );
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
				returnButton.setStyle(buttonStyleReturn1);
				changeColorButtonState = false;
			}
			else {
				startLevels.setStyle(buttonStyle2);
				returnButton.setStyle(buttonStyleReturn2);
				changeColorButtonState = true;
			}
			
			
		}
	}
}
