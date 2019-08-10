package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public class JugadorAleatorioComplica implements Jugador {

	public JugadorAleatorioComplica(FactoriaTipoJuego fact) {
		
		factoria = fact;
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		return factoria.creaMovimiento( (int) ((Math.random()*tab.getAncho()) + 1), 0, color);
	}
	
	private FactoriaTipoJuego factoria;
}
