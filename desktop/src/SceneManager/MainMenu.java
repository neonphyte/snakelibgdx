package SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import IOManager.AudioManager;

public class MainMenu implements Screen {

    private SpriteBatch batch;
    private Texture img;
    private Stage stage;
    private Skin skin;
    private Table maintable;
    private Game game;
    private Table volumeTable;
    private AudioManager audioManager;
    


    // Constructor for the MainMenu class
    public MainMenu(Game game, AudioManager audioManager) {
        this.game = game;
        this.audioManager = audioManager;
        
        // Skin is for our menu interface
        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));
        stage = new Stage();
    }

    
    /**
     * This method creates a TextButton object with the specified name and adds it to the main table.
     * It returns the created button.
     * We will need it to create buttons for our main menu later below.
     * 
     * @param name of the button that you want to create
     * @return The created TextButton
     */
    private TextButton addButton(String name) {
        TextButton button = new TextButton(name, skin);
        maintable.add(button).width(300).height(60).padBottom(20);
        maintable.row();
        return button;
    }
    
    
    /**
     * show method is called when the MainMenu becomes the current screen in the game.
     * It sets up the UI elements and input processing. 
     * It initializes a SpriteBatch, loads a background image, creates a main table to hold UI elements,
     * and adds buttons for "Play", "Scoreboard", and "Exit".
     *  It also adds a volume slider for the user to control the game's volume.
     */
    @Override
    public void show() {
    	
    	// Plays MainMenu Music
    	audioManager.listen(1);

        batch = new SpriteBatch();
        img = new Texture("background.png");

        maintable = new Table();
        maintable.setFillParent(true);

        stage.addActor(maintable);

        // Create the "Play" button
        addButton("Play").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	   // Stop all music when leaving the menu
            	audioManager.stopAllMusic();
                
            	audioManager.listen(2);
                game.setScreen(new GameScreen(audioManager));
          
              
            }
        });
        
        // Create the "Scoreboard" button
        addButton("Scoreboard").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // WORK IN PROGRESS
            }
        });
        
        // Create the "Exit" button
        addButton("Exit").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");

        volumeTable = new Table();
        volumeTable.setFillParent(true);
        volumeTable.align(Align.bottomRight).padBottom(15).padRight(15);
        stage.addActor(volumeTable);

        Label volumeLabel = new Label("Volume:", labelStyle);
        volumeTable.add(volumeLabel).padBottom(10);
        volumeTable.row();

        final Slider volumeSlider = new Slider(0, 1, 0.1f, false, skin);
        volumeSlider.setValue(audioManager.musicList.get(1).getVolume());

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	audioManager.setVolume(volumeSlider.getValue());
            }
        });
        volumeTable.add(volumeSlider).padBottom(10);
        Gdx.input.setInputProcessor(stage);
    }

    
    /**
     * render method is called continuously to render the screen.
     * It clears the screen, draws the background image using the SpriteBatch,
     * and then updates and draws the Stage which manages the UI.
     * 
     * @param delta The time in seconds since the last render call
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        stage.act();
        stage.draw();
    }
    
    
    // The below methods are empty implementations required by the Screen interface in gdx
    // but they are not used in this specific MainMenu class
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    	audioManager.dispose();
        batch.dispose();
        img.dispose();
        stage.dispose();
        skin.dispose();
    }
}
