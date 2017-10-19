package marcadorTest;

import static org.junit.Assert.*;

import javax.ejb.AfterCompletion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import marcadoresBean.Marcador;

public class MarcadorTest1 {

	public Marcador marcador;
	
	@Test
	@Before
	public void init() {
		marcador=Marcador.getInstance();
	}
	
	@Test
	public void testUpdateState() {
		marcador.setP1Name("A");
		marcador.setP2Name("B");
		marcador.scorePlayer1();
		marcador.scorePlayer1();
		marcador.scorePlayer2();
		marcador.updateState();
		System.out.println(marcador.getState());
		assertTrue("ventaja jugador A".equals(marcador.getState()));
		
	}

}
