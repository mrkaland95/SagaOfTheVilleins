package inf112.saga.of.the.villeins.AssetManager;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    private Music gameMusic;
    private Sound attackSound;
    public SoundManager(Music gameMusic, Sound attackSound) {
        this.gameMusic = gameMusic;
        this.attackSound = attackSound;
    }

    public void playGameMusic() {
        gameMusic.setLooping(true);
        gameMusic.play();
        gameMusic.setVolume(1f);
        System.out.println(gameMusic.getVolume());
    }

    public void stopGameMusic() {
        gameMusic.stop();
    }

    public void playAttackSound() {
        attackSound.play();
    }
}

