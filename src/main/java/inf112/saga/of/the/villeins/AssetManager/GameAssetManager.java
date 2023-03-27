package inf112.saga.of.the.villeins.AssetManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class GameAssetManager {
    public final AssetManager manager = new AssetManager();
    public static final String idleWarriorPath = "./assets/Sprites/Warrior/IdleWarrior.png";
    public static final String walkingWarriorPath = "./assets/Sprites/Warrior/WalkingWarrior.png";
    public static final String idleSlimePath = "./assets/Sprites/Slime/SlimeIdle.png";
    public static final String testMapPath = "./assets/Maps/TiledRougelikeMap.tmx";
    public static final String menuBackgroundPath = "./assets/MenuAssets/MenuBackground.png";


    public void load() {
        manager.load(idleWarriorPath, Texture.class);
        manager.load(walkingWarriorPath, Texture.class);
        manager.load(idleSlimePath, Texture.class);

        manager.load(menuBackgroundPath, Texture.class);

//        assetManager.load(testMapPath, Map.class);
    }

    public void dispose() {
        manager.dispose();
    }


}
