package tp.pr4.control;

import java.util.LinkedList;
import java.util.List;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.UtilsOthello;

public class JugadorAleatorioReversi implements Jugador {

	public JugadorAleatorioReversi(FactoriaTipoJuego fact) {
		
		factoria = fact;
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int pos;
		

		List<int[]> posibles = new LinkedList<int[]>();
		posibles = UtilsOthello.getPosibles(tab, color);
		
		pos = (int) ((Math.random()*posibles.size()));
		
		return factoria.creaMovimiento(posibles.get(pos)[0], posibles.get(pos)[1], color);
	}

	private FactoriaTipoJuego factoria;
}
