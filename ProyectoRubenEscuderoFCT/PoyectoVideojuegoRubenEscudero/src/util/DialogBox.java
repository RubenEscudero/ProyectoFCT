package util;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

import base.BaseActor;
import base.BaseGame;

/**
 * Class to display text on screen
 * @author libgdx
 */
public class DialogBox extends BaseActor{
	
	private Label dialogLabel;
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	 
	public DialogBox(float x, float y, Stage s) {
		super(x, y, s);
		loadTexture("assets/dialog/dialog-in.png");
		
		dialogLabel = new Label(" ", BaseGame.labelStyle);
		dialogLabel.setWrap(true);
		dialogLabel.setAlignment(Align.center);
		dialogLabel.setPosition(280, 300);
		this.setDialogSize(getWidth(), getHeight());
		this.addActor(dialogLabel);
	}
	
	public void setDialogSize(float width, float height) {
		this.setSize(width, height);
		dialogLabel.setWidth(width - 2 * 150);
		dialogLabel.setHeight(height - 2 * 280);
	}
	
	public void setText(String text) {
		dialogLabel.setText(text);
	}
	
	public void setFontScale(float scale) {
		dialogLabel.setFontScale(scale);
	}
	
	public void setFontColor(Color color) {
		dialogLabel.setColor(color);
	}
	
	public void setBackgroundColor(Color color) {
		this.setColor(color);
	}
	
	public void alignTopLeft() {
		dialogLabel.setAlignment(Align.topLeft);
	}
	
	public void alignCenter() {
		dialogLabel.setAlignment(Align.center);
	}
}
