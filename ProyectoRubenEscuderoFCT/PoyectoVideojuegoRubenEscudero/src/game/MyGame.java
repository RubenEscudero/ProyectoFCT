package game;

import base.BaseGame;
import screens.MenuScreen;

/**
 * Class to create the game and active MenuScreen
 * @author ruben
 * @since 22/04/2020
 */
public class MyGame extends BaseGame{
	
	/**
	 * Active MenuScreen
	 */
	public void create() {
		super.create();
		setActiveScreen(new MenuScreen());
	}
}
