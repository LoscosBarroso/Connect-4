package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public class JugadorAleatorioGravity implements Jugador {

	public JugadorAleatorioGravity(FactoriaTipoJuego fact) {
		
		factoria = fact;
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int col, fila;
		
		col = (int) ((Math.random()*tab.getAncho()) + 1);
		fila = (int) ((Math.random()*tab.getAlto()) + 1);
				
		while (tab.getCasilla(col, fila) != Ficha.VACIA) {
			
			col = (int) ((Math.random()*tab.getAncho()) + 1);
			fila = (int) ((Math.random()*tab.getAlto()) + 1);
		}
		
		return factoria.creaMovimiento(col, fila, color);
	}
	
	private FactoriaTipoJuego factoria;
}
