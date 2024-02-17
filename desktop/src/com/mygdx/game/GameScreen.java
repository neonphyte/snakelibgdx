package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {
	
	private GlyphLayout layout;
	private BitmapFont bitmapFont;
	private static final String GAME_OVER = "GAME OVER, Press space bar to restart!";
	
	private Batch batch;
	private ShapeRenderer shape;
	private Snake snake;
	Controller controller;
	Food food;
	private STATE state = STATE.PLAYING;
	
	private static final int SNAKE_SIZE = 32;
	private static final int SNAKE_STEP = SNAKE_SIZE;
	private static final float SNAKE_SPEED = 1F;
	
	// Creating objects
	@Override
	public void show() {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		snake = new Snake(2,10);
		controller = new Controller();
		food = new Food(snake, controller);
		
		layout = new GlyphLayout();
		bitmapFont = new BitmapFont();
	}
	
	// Constant loop, will run when the game starts
	@Override
	public void render(float delta) {
		switch (state) {
			// Default state is PLAYING
			case PLAYING: {
				snake.updateDirection(controller.queryInput());
				state = snake.update(delta);
				food.updatePosition();
				food.checkFoodCollision();
				
			}
			break;
			// Space to restart the game
			case GAMEOVER: {
				if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
					restart();
				}
			}
			break;
			
		}
		clearScreen();
		drawScreen();
		
	}
	
	// Restart function: change the state back to PLAYING, reset the food position, and clear the body part
	public void restart() {
		state = STATE.PLAYING;
		food.reset();
		snake.clearBodyPart();
		}
	
	// Draw objects like screen, snake, food
	private void drawScreen() {
		batch.begin();
        snake.draw(shape);
        snake.drawBodyParts(shape);
        food.draw(shape);
        shape.end();
        
        // If state = GAMEOVER, display a restart text
        if (state == STATE.GAMEOVER) {
        	layout.setText(bitmapFont, GAME_OVER);
        	bitmapFont.setColor(Color.WHITE);
        	// Font not displaying well
        	bitmapFont.draw(batch, GAME_OVER, Gdx.graphics.getHeight() / 2 - layout.width /2,  Gdx.graphics.getHeight() /2 - layout.height /2);
        }
        batch.end();
	}
	

	// Clear the screen
	private void clearScreen() {
		//ScreenUtils.clear(1,0,0,1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}


	// Dispose batch and shape after using
	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
	}

}
