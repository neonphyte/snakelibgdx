package Simulation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

// Custom Imports
import SceneManager.MainMenu;
import SceneManager.SceneManager;
import IOManager.AudioManager;

public class SnakeGame extends Game {
	
    private AudioManager audioManager;
    private SceneManager sceneManager;

	
	@Override
	public void create() {
		
		// Intialise our music classes
    	audioManager = new AudioManager();
        
        // Initialise main menu scene with the music classes
        sceneManager = new SceneManager(this, audioManager);

        // Set the main menu as the initial screen
        sceneManager.showMainMenu();
	}

}
