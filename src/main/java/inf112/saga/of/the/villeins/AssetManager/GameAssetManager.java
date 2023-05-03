package inf112.saga.of.the.villeins.AssetManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    public final AssetManager manager = new AssetManager();
    public static final String idleWarriorPath = "./assets/Sprites/Warrior/IdleWarrior.png";
    public static final String walkingWarriorPath = "./assets/Sprites/Warrior/WalkingWarrior.png";
    public static final String idleSlimePath = "./assets/Sprites/Slime/Slime_Idle.png";
    public static final String dragonAttackPath = "./assets/Sprites/Dragon/dragon_attack.png";
    public static final String ghostIdlePath = "./assets/Sprites/Ghost/ghost_idle.png";
    public static final String menuBackgroundPath = "./assets/Menu/MenuBackground.png";
    public static final String menuBackgroundPath2 = "./assets/Menu/MenuBackground2.png";
    public static final String uiSkin = "./assets/Skins/glassy/skin/glassy-ui.json";
//    public static final String uiSkin = "./assets/Skins/sgx/skin/sgx-ui.json";

    public static final String testMapPath = "./assets/Maps/SagaOfTheVilleins_Map1.tmx";

    public void load() {
        manager.setLoader(TiledMap.class, new TmxMapLoader(manager.getFileHandleResolver()));
        manager.load(idleWarriorPath, Texture.class);
        manager.load(walkingWarriorPath, Texture.class);
        manager.load(idleSlimePath, Texture.class);
        manager.load(dragonAttackPath, Texture.class);
        manager.load(ghostIdlePath, Texture.class);
        manager.load(menuBackgroundPath, Texture.class);
        manager.load(menuBackgroundPath2, Texture.class);
        manager.load(uiSkin, Skin.class);
        manager.load(testMapPath, TiledMap.class);
    }

    public void dispose() {
        manager.dispose();
    }
}
