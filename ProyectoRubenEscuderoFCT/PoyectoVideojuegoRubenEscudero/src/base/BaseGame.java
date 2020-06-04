package base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Base class to create the game
 * @author libgdx
 *
 */
public abstract class BaseGame extends Game {
	
	private static BaseGame game;
	public static LabelStyle labelStyle;
	public static LabelStyle labelStyleFirst;
	public static LabelStyle labelStyleSecond;
	public static LabelStyle labelStyleThird;
	
	public BaseGame() {
		game = this;
	}
	
	public static void setActiveScreen(BaseScreen s){
		game.setScreen(s);
	}
	
	public void create() {
		InputMultiplexer im = new InputMultiplexer();
		Gdx.input.setInputProcessor(im);
		//Normal labelStyle
		labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont(Gdx.files.internal("assets/dialogos/Adalgard.fnt"));
		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/dialogos/alagard.ttf"));
		
		FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
		fontParameters.size = 30;
		fontParameters.color = Color.PURPLE;
		fontParameters.borderWidth = 2;
		fontParameters.borderColor = Color.BLACK;
		fontParameters.borderStraight = true;
		fontParameters.minFilter = TextureFilter.Linear;
		fontParameters.magFilter = TextureFilter.Linear;
		
		BitmapFont customFont = fontGenerator.generateFont(fontParameters);
		labelStyle.font = customFont;
		
		//First LabelStyle
		labelStyleFirst = new LabelStyle();
		labelStyleFirst.font = new BitmapFont(Gdx.files.internal("assets/dialogos/Adalgard.fnt"));
		FreeTypeFontGenerator fontGeneratorFirst = new FreeTypeFontGenerator(Gdx.files.internal("assets/dialogos/alagard.ttf"));
		
		FreeTypeFontParameter fontParametersFirst = new FreeTypeFontParameter();
		fontParametersFirst.size = 30;
		fontParametersFirst.color = Color.GOLD;
		fontParametersFirst.borderWidth = 2;
		fontParametersFirst.borderColor = Color.BLACK;
		fontParametersFirst.borderStraight = true;
		fontParametersFirst.minFilter = TextureFilter.Linear;
		fontParametersFirst.magFilter = TextureFilter.Linear;
		
		BitmapFont customFontFirst = fontGeneratorFirst.generateFont(fontParametersFirst);
		labelStyleFirst.font = customFontFirst;
		
		
		//Second LabelStyle
		labelStyleSecond = new LabelStyle();
		labelStyleSecond.font = new BitmapFont(Gdx.files.internal("assets/dialogos/Adalgard.fnt"));
		FreeTypeFontGenerator fontGeneratorSecond = new FreeTypeFontGenerator(Gdx.files.internal("assets/dialogos/alagard.ttf"));
		
		FreeTypeFontParameter fontParametersSecond = new FreeTypeFontParameter();
		fontParametersSecond.size = 30;
		fontParametersSecond.color = Color.LIGHT_GRAY;
		fontParametersSecond.borderWidth = 2;
		fontParametersSecond.borderColor = Color.BLACK;
		fontParametersSecond.borderStraight = true;
		fontParametersSecond.minFilter = TextureFilter.Linear;
		fontParametersSecond.magFilter = TextureFilter.Linear;
		
		BitmapFont customFontSecond = fontGeneratorSecond.generateFont(fontParametersSecond);
		labelStyleSecond.font = customFontSecond;
		
		//Third LabelStyle
		labelStyleThird = new LabelStyle();
		labelStyleThird.font = new BitmapFont(Gdx.files.internal("assets/dialogos/Adalgard.fnt"));
		FreeTypeFontGenerator fontGeneratorThird = new FreeTypeFontGenerator(Gdx.files.internal("assets/dialogos/alagard.ttf"));
		
		FreeTypeFontParameter fontParametersThird = new FreeTypeFontParameter();
		fontParametersThird.size = 30;
		fontParametersThird.color = Color.BROWN;
		fontParametersThird.borderWidth = 2;
		fontParametersThird.borderColor = Color.BLACK;
		fontParametersThird.borderStraight = true;
		fontParametersThird.minFilter = TextureFilter.Linear;
		fontParametersThird.magFilter = TextureFilter.Linear;
		
		BitmapFont customFontThird = fontGeneratorThird.generateFont(fontParametersThird);
		labelStyleThird.font = customFontThird;
		
	}
	
	
}