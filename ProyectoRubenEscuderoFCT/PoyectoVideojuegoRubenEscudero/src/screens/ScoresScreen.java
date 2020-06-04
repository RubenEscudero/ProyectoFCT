package screens;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
import com.rubenescudero.aes.AES;
import com.rubenescudero.rsa.RSA;

import base.BaseActor;
import base.BaseGame;
import base.BaseScreen;
import game.MyGame;

/**
 * Class to show best scores
 * @author ruben
 * @since 21/04/2020
 */
public class ScoresScreen extends BaseScreen{
	private Music music;
	private Properties properties;
	private float musicVolume;
	private Button returnButton;
	private ButtonStyle buttonStyleReturn1;
	private ButtonStyle buttonStyleReturn2;
	private long changeColorButton;
	private boolean changeColorButtonState;
	private BaseActor title;
	private Label playersLabel;
	private Label pointsLabel;
	private Label player1;
	private Label points1;
	private Label player2;
	private Label points2;
	private Label player3;
	private Label points3;
	private Label player4;
	private Label points4;
	private Label player5;
	private Label points5;
	private String[] players;
	private AES aes;
	private byte[] encriptado;
	private String textBase64;
	private String desencriptado;
	private RSA rsa;
	
	/**
	 * Initializes and loads the elements to be used
	 */
	@Override
	public void initialize() {
		BaseActor background = new BaseActor(0, 0, mainStage);
		background.loadTexture("assets/backgrounds/retroScores.jpg");
		background.setSize(514, 700);
		
		title = new BaseActor(60, 580, uiStage);
		title.loadTexture("assets/buttons/tituloScores.png");
		
		players = new String[5];
		properties = new Properties();
		
		try {
			properties.load(new FileInputStream("datos.conf"));
			musicVolume = Float.parseFloat(properties.getProperty("music"));
			for(int i = 0; i < 5; i++) {
				players[i] = properties.getProperty("player" + i);
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String temporal = "";
		 	
		    for (int i = 0; i < players.length ; i++) {
		        for (int j = 0; j < players.length - i - 1; j++) {
		            if (Integer.parseInt(players[j].split("-")[1]) + Integer.parseInt(players[j].split("-")[2])
		            		+ Integer.parseInt(players[j].split("-")[3]) + Integer.parseInt(players[j].split("-")[4])
		            		+ Integer.parseInt(players[j].split("-")[5]) + Integer.parseInt(players[j].split("-")[6]) + Integer.parseInt(players[j].split("-")[7])
		            		> Integer.parseInt(players[j + 1].split("-")[1]) + Integer.parseInt(players[j + 1].split("-")[2])
		            		+ Integer.parseInt(players[j + 1].split("-")[3]) + Integer.parseInt(players[j + 1].split("-")[4])
		            		+ Integer.parseInt(players[j + 1].split("-")[5]) + Integer.parseInt(players[j + 1].split("-")[6]) 
		            		+ Integer.parseInt(players[j + 1].split("-")[7])) {
		                temporal = players[j];
		                players[j] = players[j + 1];
		                players[j + 1] = temporal;
		            }
		        }
		    }
		    
		try {
			conectar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		playersLabel = new Label("PLAYERS"  + "\n", BaseGame.labelStyle);
		pointsLabel = new Label("POINTS"  + "\n", BaseGame.labelStyle);
		
		uiTable.add().expandX();
        uiTable.add(playersLabel);
        uiTable.add().expandX();
        uiTable.add(pointsLabel);
        uiTable.add().expandX();
        uiTable.row();
        
        uiTable.add().expandX();
        uiTable.add(player1);
        uiTable.add().expandX();
        uiTable.add(points1);
        uiTable.add().expandX();
        uiTable.row();
        
        uiTable.add().expandX();
        uiTable.add(player2);
        uiTable.add().expandX();
        uiTable.add(points2);
        uiTable.add().expandX();
        uiTable.row();
        
        uiTable.add().expandX();
        uiTable.add(player3);
        uiTable.add().expandX();
        uiTable.add(points3);
        uiTable.add().expandX();
        uiTable.row();
        
        uiTable.add().expandX();
        uiTable.add(player4);
        uiTable.add().expandX();
        uiTable.add(points4);
        uiTable.add().expandX();
        uiTable.row();
        
        uiTable.add().expandX();
        uiTable.add(player5);
        uiTable.add().expandX();
        uiTable.add(points5);
        uiTable.add().expandX();
        
		initButtons();
		
		
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/Heartbreak3.mp3"));
		music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();
        
        rsa = new RSA();
		
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
				returnButton.setStyle(buttonStyleReturn2);
				changeColorButtonState = false;
			}
			else {
				returnButton.setStyle(buttonStyleReturn1);
				changeColorButtonState = true;
			}
			
		}
	}
	
	private void conectar() throws UnknownHostException, IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		
		System.out.println("Conectado");
		Socket socket = new Socket("localhost", 4444);
		
		//salida
		PrintStream salida = new PrintStream(socket.getOutputStream());
		
		/**
		String mensaje = "recibir";
		
		//rsa.genKeyPair(512);
		
		String file_private = "C:\\Users\\ruben\\OneDrive\\Grado Superior 2019-20\\FCT\\Claves\\rsa.pri";
		String file_public = "C:\\Users\\ruben\\OneDrive\\Grado Superior 2019-20\\FCT\\Claves\\rsa.pub";
		
		rsa.saveToDiskPrivateKey(file_private);
        rsa.saveToDiskPublicKey(file_public);
		
		String cifrado = rsa.Encrypt(mensaje);
		
		System.out.println("Texto cifrado: " + cifrado);
		
		salida.println(cifrado);
		*/
		
		aes = new AES();
		
		encriptado = aes.encriptar("recibir");
		
		System.out.print("Encriptado: ");
		
		for(byte b: encriptado){
            System.out.print(Integer.toHexString(0xFF & b));
        }
		
		System.out.println();
		
		textBase64 = Base64.getEncoder().encodeToString(encriptado);
		
		System.out.println("En base 64: " + textBase64);
		
		
		salida.println(textBase64);
		
		
		
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		
		/**
		rsa.openFromDiskPrivateKey(file_private);
        rsa.openFromDiskPublicKey(file_public);

        String desencriptado = rsa.Decrypt(entrada.readLine());

        System.out.println("Texto descifrado: " + desencriptado);
		
		String mensajeRecibido = desencriptado;
		*/
		
		textBase64 = entrada.readLine();
		
		System.out.println("Respuesta base64: " + textBase64);
		
		encriptado = Base64.getDecoder().decode(textBase64);
		
		desencriptado = aes.desencriptar(encriptado);
		
		String mensajeRecibido = desencriptado;
		
		
		
		System.out.println(mensajeRecibido);
		
		String primero = mensajeRecibido.split(",")[1];
		System.out.println(primero);
		String segundo = mensajeRecibido.split(",")[2];
		String tercero = mensajeRecibido.split(",")[3];
		String cuarto = mensajeRecibido.split(",")[4];
		String quinto = mensajeRecibido.split(",")[5];
		
		player1 = new Label(primero.split("-")[0] + "\n", BaseGame.labelStyleFirst);
		points1 = new Label(primero.split("-")[1] + "\n", BaseGame.labelStyleFirst);
		
		player2 = new Label(segundo.split("-")[0] + "\n", BaseGame.labelStyleSecond);
		points2 = new Label(segundo.split("-")[1] + "\n", BaseGame.labelStyleSecond);
		
		player3 = new Label(tercero.split("-")[0] + "\n", BaseGame.labelStyleThird);
		points3 = new Label(tercero.split("-")[1] + "\n", BaseGame.labelStyleThird);
		
		player4 = new Label(cuarto.split("-")[0] + "\n", BaseGame.labelStyle);
		points4 = new Label(cuarto.split("-")[1] + "\n", BaseGame.labelStyle);
		
		player5 = new Label(quinto.split("-")[0] + "\n", BaseGame.labelStyle);
		points5 = new Label(quinto.split("-")[1] + "\n", BaseGame.labelStyle);
		
		socket.close();
		
	}
}
