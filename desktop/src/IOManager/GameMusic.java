package IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class GameMusic {
	
    protected Music gameMusic;
    protected float volume;
    protected boolean loop;
    
    public GameMusic(String musicFilePath, float volume, boolean loop) {

        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFilePath));
        this.volume = volume;
        this.loop = loop;
        this.gameMusic.setVolume(this.volume);
        this.gameMusic.setLooping(this.loop);

    }
    
    public void setVolume(float volume) {
        this.volume = volume;
        this.gameMusic.setVolume(this.volume);
    }

    public float getVolume() {
        return this.volume;
    }

    public void playMusic() {
        this.gameMusic.play();
    }

    public void stopMusic() {
        if (gameMusic.isPlaying()) {
            gameMusic.stop();
        }
    }


    public boolean isPlayingMusic() { // Check for music playing
        return this.gameMusic.isPlaying();
    }
    
    public void disposeMusic() {
        gameMusic.dispose();
    }
    
 


}
