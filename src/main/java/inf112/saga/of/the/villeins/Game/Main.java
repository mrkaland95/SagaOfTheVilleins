package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class Main {

	// Temp variable that specifies at what speed characters should move at.
	public final static float globalDefaultMoveSpeed = 100.0f;

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(30);
		config.setTitle("Saga Of The Villeins");
		config.setWindowedMode(800, 600);
		new Lwjgl3Application(new sagaOfTheVilleinsGame(), config);
	}
}