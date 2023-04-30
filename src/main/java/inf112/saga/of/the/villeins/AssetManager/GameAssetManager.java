package inf112.saga.of.the.villeins.AssetManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    public final AssetManager manager = new AssetManager();
    public static final String idleWarriorPath = "./assets/Sprites/Warrior/IdleWarrior.png";
    public static final String walkingWarriorPath = "./assets/Sprites/Warrior/WalkingWarrior.png";
    public static final String idleSlimePath = "./assets/Sprites/Slime/Slime_Idle.png";
    public static final String dragonAttackPath = "./assets/Sprites/Dragon/dragon_attack.png";
    public static final String ghostIdlePath = "./assets/Sprites/Ghost/ghost_idle.png";
//    public static final String testMapPath = "./assets/Maps/TiledRougelikeMap.tmx";
    public static final String menuBackgroundPath = "./assets/MenuAssets/MenuBackground.png";
    public static final String menuBackgroundPath2 = "./assets/MenuAssets/MenuBackground2.png";
    public static final String uiSkin = "./assets/Skins/glassy/skin/glassy-ui.json";


    public void load() {
        manager.load(idleWarriorPath, Texture.class);
        manager.load(walkingWarriorPath, Texture.class);
        manager.load(idleSlimePath, Texture.class);
        manager.load(dragonAttackPath, Texture.class);
        manager.load(ghostIdlePath, Texture.class);
        manager.load(menuBackgroundPath, Texture.class);
        manager.load(menuBackgroundPath2, Texture.class);
        manager.load(uiSkin, Skin.class);
    }

    public void dispose() {
        manager.dispose();
    }
}
