package SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

// Custom Imports
import IOManager.AudioManager;

public class SceneManager {
    
    private Game game;
    private AudioManager audioManager;
    private Screen currentScreen;

    public SceneManager(Game game, AudioManager audioManager) {
        this.game = game;
        this.audioManager = audioManager;
    }

    public void showMainMenu() {
        audioManager.listen(1); // Play MainMenu music
        currentScreen = new MainMenu(game, audioManager);
        game.setScreen(currentScreen);
    }

    public void showGameScreen() {
        audioManager.stopAllMusic(); // Stop all music when leaving the menu
        audioManager.listen(2); // Play GameScreen music
        currentScreen = new GameScreen(audioManager);
        game.setScreen(currentScreen);
    }

    public void disposeCurrentScreen() {
        if (currentScreen != null) {
            currentScreen.dispose();
            currentScreen = null;
        }
    }
}
