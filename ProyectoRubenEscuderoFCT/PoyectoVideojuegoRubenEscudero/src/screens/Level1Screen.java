package screens;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
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
import bullets.BlueBullet;
import bullets.RedBullet;
import bullets.SmallBullet;
import bullets.SpaceBullet;
import characters.Boss;
import characters.Enemy;
import characters.Player;
import effects.Explosion;
import game.MyGame;
import items.Asteroid;
import items.Coin;
import items.ShieldBall;
import tiled.TilemapActor;
import util.DialogBox;

/**
 * Class to play level 1
 * @author ruben
 * @since 22/04/2020
 */
public class Level1Screen extends BaseScreen{
	private Player player;
	private Music music;
	private long lastShoot;
	private long lastRedBulletShoot;
	private TilemapActor tma;
	private Sound laser;
	private Sound star;
	private Music win;
	private Music lose;
	private Label lifeLabel;
	private Label pointsLabel;
	private int points;
	private DialogBox dialogBox;
	private BaseActor lifeSpace;
	private Boss boss;
	private boolean gameOver;
	private boolean bossDie;
	private Properties properties;
	private int basePoints;
	private float musicVolume;
	private float effectVolume;
	private long lastEnemy;
	private BaseActor youWin;
	private BaseActor gameOverTitle;
	private Button returnButton;
	private ButtonStyle buttonStyleReturn1;
	private Explosion explosion;
	private Sound explosionSound;
	private Sound damage;
	private Coin coin;
	private ShieldBall shieldBall;
	private int shieldBallCount;
	private int starCount;
	private float xBossPosition;
	private boolean xBoss;
	
	/**
	 * Initializes and loads the elements to be used
	 */
	@Override
	public void initialize() {
		BaseActor background = new BaseActor(0, 0, mainStage);
		background.loadTexture("assets/backgrounds/Stars-Big_1_1_PC.png");
		background.setSize(514, 28150);
		BaseActor.setWorldBounds(background);
		
		shieldBallCount = 0;
		starCount = 0;
		xBoss = true;
		
		buttonStyleReturn1 = new ButtonStyle();
		
		Texture buttonTexReturn1 = new Texture(Gdx.files.internal("assets/buttons/buttonReturn1.png"));
		TextureRegion buttonRegionReturn1 = new TextureRegion(buttonTexReturn1);
		buttonStyleReturn1.up = new TextureRegionDrawable(buttonRegionReturn1);
		
		lifeSpace = new BaseActor(80, 645, uiStage);
		lifeSpace.loadTexture("assets/items/spaceshipLife.png");
		
		//Tiled
		tma = new TilemapActor("assets/tiled/Level1.tmx", mainStage);
	   
	    for(MapObject obj: tma.getTileList("Shield")) {
	        MapProperties props = obj.getProperties();
	        new ShieldBall((float)props.get("x"), (float)props.get("y"), mainStage);
	    }
	    
	    for(MapObject obj: tma.getTileList("Finish")) {
	        MapProperties props = obj.getProperties();
	        new Asteroid((float)props.get("x"), (float)props.get("y"), mainStage);
	    }
	        
	    for(MapObject obj: tma.getTileList("Boss")) {
	        MapProperties props = obj.getProperties();
	        Boss boss = new Boss((float)props.get("x"), (float)props.get("y"), mainStage);
	        boss.setRotation(180);
	        boss.setLife(5);
	    }
        
        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        player = new Player((float)startProps.get("x"), (float)startProps.get("y"), mainStage);
        player.setLife(100);
        
        boss = new Boss((float)startProps.get("x"), (float)startProps.get("y") + 560, mainStage);
        boss.setLife(700);
        
        
        //UI
        lifeLabel = new Label("" + player.getLife(), BaseGame.labelStyle);
        pointsLabel = new Label("" + points, BaseGame.labelStyle);
        dialogBox = new DialogBox(0, 0, uiStage);
        
        uiTable.pad(10);
        uiTable.add(lifeLabel);
        uiTable.add().expandX();
        uiTable.add(pointsLabel);
        uiTable.row();
        uiTable.add().expandY();
        uiTable.add(dialogBox).colspan(4);
        
        
		//Sound
        laser = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/laser.ogg"));
        star = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/star.ogg"));
        damage = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/damage2.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/explosion.wav"));
        lose = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/lose.wav"));
        lose.setLooping(false);
       
        win = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/win.wav"));
        win.setLooping(false);
        
        
        properties = new Properties();
        try {
			properties.load(new FileInputStream("datos.conf"));
			basePoints = Integer.parseInt(properties.getProperty("actualpoints1"));
			musicVolume = Float.parseFloat(properties.getProperty("music"));
			effectVolume = Float.parseFloat(properties.getProperty("effect"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        lose.setVolume(musicVolume);
        win.setVolume(musicVolume);
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/Fighting Hearts.mp3"));
		music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();
       
	}
	
	/**
	 * Update what happens in the game
	 */
	@Override
	public void update(float dt) {
		player.accelerateAtAngle(90);
        player.setSpeed(200);
        boss.accelerateAtAngle(90);
        boss.setSpeed(200);
        boss.setY(player.getY() + 590);
        
        
        lifeLabel.setText("" + player.getLife() + "x");
        pointsLabel.setText("" + points + "Pts");
        
        
        
        if (Gdx.input.isKeyPressed(Keys.W)) {
       	 shootPlayer();
        } 
        
        
        for(BaseActor shield: BaseActor.getList(mainStage, ShieldBall.class.getName())) {
        	if(player.overlaps(shield)) {
        		player.setShieldPower(100);
        		shield.remove();
        	}
        }
        
        for(BaseActor redBullet: BaseActor.getList(mainStage, RedBullet.class.getName())) {
        	if(redBullet.overlaps(player)) {
        		if(!gameOver) {
        			damage.play(effectVolume);
        		}
        		if(player.getShieldPower() <= 0) {
        			player.setLife(player.getLife() - 10);
        			redBullet.remove();
        			if(player.getLife() <= 0) {
        				gameOver = true;
        			}
        		}
        		else {
        			player.setShieldPower(player.getShieldPower() - 5);
        			redBullet.remove();
        		}
        	}
        }
        
        for(BaseActor blueBullet: BaseActor.getList(mainStage, BlueBullet.class.getName())) {
        	if(blueBullet.overlaps(player)) {
        		if(!gameOver) {
        			damage.play(effectVolume);
        		}
        		if(player.getShieldPower() <= 0) {
        			player.setLife(player.getLife() - 10);
        			blueBullet.remove();
        			if(player.getLife() <= 0) {
        				gameOver = true;
        			}
        		}
        		else {
        			player.setShieldPower(player.getShieldPower() - 5);
        			blueBullet.remove();
        		}
        	}
        }
        
        for(BaseActor smallBullet: BaseActor.getList(mainStage, SmallBullet.class.getName())) {
        	if(smallBullet.overlaps(player)) {
        		if(!gameOver) {
        			damage.play(effectVolume);
        		}
        		if(player.getShieldPower() <= 0) {
        			player.setLife(player.getLife() - 10);
        			smallBullet.remove();
        			if(player.getLife() <= 0) {
        				gameOver = true;
        			}
        		}
        		else {
        			player.setShieldPower(player.getShieldPower() - 5);
        			smallBullet.remove();
        		}
        	}
        }
        
        for(BaseActor purpleStar: BaseActor.getList(mainStage, Coin.class.getName())) {
        	if(player.overlaps(purpleStar)) {
        		purpleStar.remove();
        		star.play(effectVolume);
        		points = points + 10;
        	}
        }
        
        for(BaseActor asteroid: BaseActor.getList(mainStage, Asteroid.class.getName())) {
        	if(player.overlaps(asteroid)) {
        		explosion = new Explosion(0,0, mainStage);
				explosion.centerAtActor(player);
				player.remove();
				gameOver = true;
			}
        }
        
        for(BaseActor spaceBullet: BaseActor.getList(mainStage, SpaceBullet.class.getName())) {
        	
        	if(spaceBullet.overlaps(boss)) {
        		starCount++;
        		if(starCount == 5) {
        			coin = new Coin(boss.getX(), boss.getY(), mainStage);
        			starCount = 0;
        		}
        		
        		shieldBallCount++;
        		if(shieldBallCount == 10) {
        			shieldBall = new ShieldBall(player.getX(), player.getY() + 600, mainStage);
        			shieldBallCount = 0;
        		}
        		boss.setLife(boss.getLife() - 10);
        		spaceBullet.remove();
        		if(boss.getLife() <= 0) {
        			explosion = new Explosion(0,0, mainStage);
    				explosion.centerAtActor(boss);
    				explosionSound.play(effectVolume);
        			boss.remove();
        			bossDie = true;
        			points = points + 50;
        		}
        	}
        	
        	for(BaseActor enemy: BaseActor.getList(mainStage, Enemy.class.getName())) {
        		if(spaceBullet.overlaps(enemy)) {
        			enemy.setLife(enemy.getLife() - 1);
        			spaceBullet.remove();
        			if(enemy.getLife() <= 0) {
        				explosion = new Explosion(0,0, mainStage);
        				explosion.centerAtActor(enemy);
        				explosionSound.play(effectVolume);
        				enemy.remove();
        				points = points + 5;
        			}
        			
        		}
        		
        	}
        	
        }
        
        for(BaseActor enemy: BaseActor.getList(mainStage, Enemy.class.getName())) {
    		
    		if(player.overlaps(enemy)) {
    			explosion = new Explosion(0,0, mainStage);
				explosion.centerAtActor(enemy);
				explosionSound.play(effectVolume);
    			enemy.remove();
    			points = points + 5;
    			
    		}
    		enemy.setX(player.getX());
        }	
        
        if(!bossDie) {
        	shootRedBullet();
        }
        
        
        if(bossDie) {
        	 player.setShieldPower(100);
        	 music.stop();
        	 player.remove();
        	 youWin = new BaseActor(110, 400, uiStage);
        	 youWin.loadTexture("assets/buttons/youwin.png");
        	 win.play();
            if(points > basePoints) {
            	properties.setProperty("actualpoints1", String.valueOf(points));
            	try {
    				properties.store(new FileOutputStream("datos.conf"), "Fichero de datos");
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
            }
            initButtons();
            
             
        }
        
        
        if(gameOver) {
        	 player.setShieldPower(100);
        	for(BaseActor enemy: BaseActor.getList(mainStage, Enemy.class.getName())) {
        		enemy.remove();
        	}
        	player.setLife(0);
        	music.stop();
        	lose.play();
        	player.remove();
        	gameOverTitle = new BaseActor(70, 400, uiStage);
        	gameOverTitle.loadTexture("assets/buttons/gameover.png");
        	initButtons();
        	
        }
        
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
        	MyGame.setActiveScreen( new LevelScreen() );
        	music.stop();
        }
        
        if(!gameOver && !bossDie) {
        	newEnemy();
        }
        
        
        if(xBoss) {
        	xBossPosition = xBossPosition + 2.50f;
        	if(xBossPosition == 450.00f) {
        		xBoss = false;
        	}
        }
        else {
        	xBossPosition = xBossPosition - 2.50f;
        	if(xBossPosition == 0.00f) {
        		xBoss = true;
        	}
        }
        
        boss.setX(xBossPosition);
	}
	
	/**
	 * Method to create bullets who attack enemies
	 */
	private void shootPlayer() {
		if(!gameOver && !bossDie) {
			long time = System.currentTimeMillis();
			if(lastShoot - time < -300) {
				lastShoot = time;
				SpaceBullet redBullet = new SpaceBullet(player.getX(), player.getY(), mainStage);
				redBullet.centerAtActor(player);
				redBullet.setRotation(player.getFacingAngle());
	        	redBullet.setMotionAngle(90);
	        	laser.play(effectVolume);
			}
		}	
	}
	
	/**
	 * Method to create enemies Enemy
	 */
	private void newEnemy() {
		long time = System.currentTimeMillis();
		if(lastEnemy - time < -4000) {
			lastEnemy = time;
			Enemy enemy = new Enemy(player.getX(), player.getY() + 650, mainStage);
		}
	}
	
	/**
	 * Method to create bullets who attack player
	 */
	private void shootRedBullet() {
		long time = System.currentTimeMillis();
		if(lastRedBulletShoot - time < -500) {
			lastRedBulletShoot = time;
			RedBullet redBullet = new RedBullet(boss.getX() + 25, boss.getY(), mainStage);
			redBullet.setRotation(boss.getFacingAngle());
			redBullet.setMotionAngle(-90);
		}
	}
	

	/**
	 * Load buttonStyles and Buttons
	 */
	private void initButtons() {
		returnButton = new Button(buttonStyleReturn1);
		returnButton.setPosition(200, 300);
		uiStage.addActor(returnButton);
		
		returnButton.addListener(
				(Event e) -> {
					if ( !(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown) )
							return false;
							music.stop();
							MyGame.setActiveScreen( new LevelScreen() );
							return false;
				}
		);
		
	}
	
}