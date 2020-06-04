package items;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import base.BaseActor;

/**
* Class to create Shield
* @author ruben
* @since 22/04/2020
*/
public class Shield extends BaseActor {
	
	/**
	 * Constructor
	 * @param x position at x
	 * @param y position at y
	 * @param s stage
	 */
	public Shield(float x, float y, Stage s) {
		super(x, y, s);
		loadAnimationFromSheet("assets/items/shieldB.png", 4, 5, 0.05f, true);
		Action pulse = Actions.sequence(Actions.scaleTo(1.05f, 1.05f, 1), Actions.scaleTo(0.95f, 0.95f, 1));
		addAction(Actions.forever(pulse));
	}
	
}
