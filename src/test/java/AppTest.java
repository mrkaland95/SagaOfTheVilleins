
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;

public class AppTest {
	@Test
	void damageTest(){
		ICharacter Dummy = new Player(0, 0, null, null, null, 20, 0, 0);
		Dummy.setHealth(10, Dummy);
		assertEquals(10, Dummy.getHealth());
	}
}