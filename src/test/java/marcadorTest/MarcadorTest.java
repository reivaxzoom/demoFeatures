package marcadorTest;

import static org.junit.Assert.*;

import javax.ejb.AfterCompletion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import marcadoresBean.Marcador;

public class MarcadorTest {

	public Marcador marcador;
	
	@Test
	@Before
	public void init() {
		marcador=Marcador.getInstance();
	}
	
	@Test
	public void testScorePlayer1() {
		marcador.setP1Name("A");
		marcador.scorePlayer1();
		marcador.scorePlayer1();
		marcador.scorePlayer1();
		assertEquals("Marcador Incrementado incorrectamente", 3, marcador.getPlayer1Score(), 0);
	}

	@Test
	public void testScorePlayer2() {
		marcador.setP1Name("B");
		marcador.scorePlayer2();
		marcador.scorePlayer2();
		assertEquals("Marcador Incrementado incorrectamente", 2, marcador.getPlayer2Score(), 0);
	}

	

}
