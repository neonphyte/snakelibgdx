package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Controller {
		
	public DIRECTION queryInput() {
		// Take in key inputs
		boolean rightKey = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean leftKey = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean upKey = Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean downKey = Gdx.input.isKeyPressed(Input.Keys.DOWN);
		if (rightKey) {
			return DIRECTION.RIGHT;
		}
		if (leftKey) {
			return DIRECTION.LEFT;
		}
		if (upKey) {
			return DIRECTION.UP;
		}
		if (downKey) {
			return DIRECTION.DOWN;
		}
		return DIRECTION.NONE;

	}
	
	// Check for restart when hit space bar
	public boolean checkForRestart() {
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			return true;
		}
		return false;
	}
	

	
	

}
