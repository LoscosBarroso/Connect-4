package tp.pr4.logica;


public class MovimientoReversi extends Movimiento {

	public MovimientoReversi(int col, int fil, Ficha color) {

		fila = fil;
		columna = col;
		jugador = color;
	}
	
	public void ejecutaMovimiento(Tablero tablero) throws MovimientoInvalido {

		if (tablero.getCasilla(columna, fila) != Ficha.VACIA) {
			
			throw new MovimientoInvalido("Casilla ocupada.");
		} else if (columna > 0 && columna <= tablero.getAncho() && fila > 0 && fila <= tablero.getAlto()) {
			
			if (UtilsOthello.puedePoner(tablero, columna, fila, jugador)) {
				
				cambiadas = UtilsOthello.poner(tablero, columna, fila, jugador);
			} else {
				
				throw new MovimientoInvalido("Jugada no válida.");
			}
		} else {
			
			throw new MovimientoInvalido("Posición incorrecta.");
		}
	}

	public void undo(Tablero tablero) {

		UtilsOthello.deshacer(tablero, columna, fila, jugador, cambiadas);
	}

	public int getColumna() {

		return columna;
	}

	public int getFila() {

		return fila;
	}

	private int columna;
	private int fila;
	private int [] cambiadas;
}
