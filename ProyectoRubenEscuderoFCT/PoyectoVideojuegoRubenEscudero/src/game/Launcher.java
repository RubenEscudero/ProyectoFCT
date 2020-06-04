package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * Class to launch the video game
 * @author ruben
 * @since 22/04/2020
 */
public class Launcher {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String players[] = new String[5];
		String musicVolume = "1.00f";
		String effectVolume = "1.00f";
		
		Properties properties = new Properties();
		
		properties.load(new FileInputStream("datos.conf"));
		
		for(int i = 0; i < 5 ; i++) {
			players[i] = properties.getProperty("player" + i);
		}
		
		
		for(int i = 0; i < 5 ; i++) {
			properties.setProperty("player" + i, players[i]);
		}
		
		properties.setProperty("effect", effectVolume);
		properties.setProperty("music", musicVolume);
		properties.store(new FileOutputStream("datos.conf"), "Fichero de datos");
		
		
		Game game = new MyGame();
		LwjglApplication launcher = new LwjglApplication(game, "Ships", 514, 700);
	}
}
