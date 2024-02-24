package IOManager;

import java.util.ArrayList;

public class AudioManager {
	
	public ArrayList<GameMusic> musicList;
	private SnakeEatMusic snakeEatMusic;
	private MainMenuMusic mainMenuMusic;
	private PlayMusic playMusic;
	private GameOverMusic gameOverMusic;
	
	public AudioManager() {
		
		this.musicList = new ArrayList<GameMusic>();
		this.snakeEatMusic = new SnakeEatMusic();
		this.mainMenuMusic = new MainMenuMusic();
		this.playMusic = new PlayMusic();
		this.gameOverMusic = new GameOverMusic();
		musicList.add(this.snakeEatMusic);
		musicList.add(this.mainMenuMusic);
		musicList.add(this.playMusic);
		musicList.add(this.gameOverMusic);
	}
	
	public void listen(int musicIndex) {
	    if (musicIndex >= 0 && musicIndex < musicList.size()) {
	        musicList.get(musicIndex).playMusic();
	   
	    }
	}
	
	// For switching scene
	public void stopAllMusic() {
	    for (GameMusic music : musicList) {
	        music.stopMusic(); 
	    
	    }
	}

	
	public void setVolume(float volume) {
	    for (GameMusic music : musicList) {
	        music.setVolume(volume);
	    }
	}
	
	 public void dispose() {
		  for (GameMusic music : musicList) {
		        music.disposeMusic();
		    }
	      
	    }
	    


}
