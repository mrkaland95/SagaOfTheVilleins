
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;

public class AppTest {
	@Test
	void damageTest(){
		ICharacter Dummy = new Player(0, 0,20, 0, 0);
		Dummy.setHealth(10, Dummy);
		assertEquals(10, Dummy.getCurrentHealth());
	}
}