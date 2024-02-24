package SceneManager;

import PlayerControlManager.InputController; // Import InputController from its package
import PlayerControlManager.KeyboardController; // Import KeyboardController from its package
import Simulation.STATE;
import PlayerControlManager.DIRECTION;

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

// Custom Imports
import com.mygdx.game.*;

import CollisionManager.CollisionManager;
import EntityManager.EntityManager;
import EntityManager.Food;
import EntityManager.Snake;
import IOManager.AudioManager;


public class GameScreen extends ScreenAdapter {
	
	private GlyphLayout layout;
	private BitmapFont bitmapFont;
	private static final String GAME_OVER = "GAME OVER, Press space bar to restart!";
	
	private Batch batch;
	private ShapeRenderer shape;
	private Snake snake;
	private KeyboardController controller;
	private Food food;
	private STATE state = STATE.PLAYING;
	private EntityManager entityManager;
	
	private AudioManager audioManager;
	private boolean gameOverMusicPlayed = false;

	public GameScreen(AudioManager audioManager) {
		this.audioManager = audioManager;
	}

	// Creating objects
	@Override
	public void show() {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();	
		layout = new GlyphLayout();
		bitmapFont = new BitmapFont();
		controller = new KeyboardController();
		
		entityManager = new EntityManager();
		audioManager = new AudioManager();

	}
	
	// Constant loop, will run when the game starts
	@Override
	public void render(float delta) {
		switch (state) {
			// Default state is PLAYING
			case PLAYING: {
				snake = (Snake) entityManager.entityList.get(0);
				food = (Food) entityManager.entityList.get(1);
				entityManager.userMovement(delta);
				state = snake.update(delta);
				CollisionManager.checkCollisions(snake,food, audioManager);
				entityManager.randomSpawn();
			}
			break;
			case GAMEOVER: {
				if (!gameOverMusicPlayed) {
					audioManager.stopAllMusic();
					audioManager.listen(3); // Assuming 3 is the correct index for GameOverMusic
			        gameOverMusicPlayed = true;
				}
				

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
		gameOverMusicPlayed = false; // Reset the flag
	}
	
	// Draw objects like screen, snake, food
	private void drawScreen() {
		entityManager.draw(shape);
		entityManager.entityList.get(0).drawBodyParts(shape);
        shape.end();
        
        // If state = GAMEOVER, display a restart text
        if (state == STATE.GAMEOVER) {
        	batch.begin();
        	layout.setText(bitmapFont, GAME_OVER);
        	bitmapFont.setColor(Color.WHITE);
        	bitmapFont.draw(batch, GAME_OVER, Gdx.graphics.getHeight() / 2 - layout.width /2,  Gdx.graphics.getHeight() /2 - layout.height /2);
        	batch.end();
        }
       
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
		audioManager.dispose();
		batch.dispose();
		shape.dispose();
	}

}
