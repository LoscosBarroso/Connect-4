package tp.pr4.logica;

public class MovimientoConecta4 extends Movimiento {

	public MovimientoConecta4(int donde, Ficha color) {

		fila = 1;
		columna = donde;
		jugador = color;
	}
	
	public void ejecutaMovimiento(Tablero tablero) throws MovimientoInvalido {
		
		if (columna > 0 && columna <= tablero.getAncho()) {

			if (tablero.getCasilla(columna, 1).equals(Ficha.VACIA)) {
				
				while (fila <= tablero.getAlto() && tablero.getCasilla(columna, fila).equals(Ficha.VACIA)) {
					
					fila++;
				}
				
				tablero.setCasilla(columna, fila - 1, jugador);
				
			} else {
				
				throw new MovimientoInvalido("Columna llena.");
			}
		} else {
			
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 7.");
		}
	}

	public void undo(Tablero tablero) {

		tablero.setCasilla(columna, fila - 1, Ficha.VACIA);
	}

	public int getColumna() {
		
		return columna;
	}
	
	public int getFila() {
		
		return fila - 1;
	}
	
	private int columna;
	private int fila;
}
