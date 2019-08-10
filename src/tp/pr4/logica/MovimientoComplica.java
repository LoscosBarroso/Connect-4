package tp.pr4.logica;

public class MovimientoComplica extends Movimiento {

	public MovimientoComplica(int donde, Ficha color) {

		fila = 1;
		columna = donde;
		jugador = color;
		eliminada = null;
	}
	
	public void ejecutaMovimiento(Tablero tablero) throws MovimientoInvalido {

		if (columna > 0 && columna <= tablero.getAncho()) {
			
			while (fila <= tablero.getAlto() && tablero.getCasilla(columna, fila).equals(Ficha.VACIA)) {
				
				fila++;
			}
			
			if (fila == 1) {
				
				eliminada = tablero.getCasilla(columna, tablero.getAlto());
				
				for (int I = tablero.getAlto(); I > 1; I--) {
					
					tablero.setCasilla(columna, I, tablero.getCasilla(columna, I - 1));
				}

				tablero.setCasilla(columna, fila, jugador);
			} else {
				
				tablero.setCasilla(columna, fila - 1, jugador);
			}
			
		} else {

			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 4.");
		}
	}

	public void undo(Tablero tablero) {
		
		if (eliminada != null) {
			
			for (int I = 1; I < tablero.getAlto(); I++) {
				
				tablero.setCasilla(columna, I, tablero.getCasilla(columna, I + 1));
			}
			
			tablero.setCasilla(columna, tablero.getAlto(), eliminada);
		} else {

			tablero.setCasilla(columna, fila - 1,Ficha.VACIA);
		}
		
	}

	public int getColumna() {
		
		return columna;
	}
	
	public int getFila() {
		
		if (fila == 1) {
			
			return fila;
		} else {

			return fila - 1;	
		}
	}
	
	private int columna;
	private int fila;
	private Ficha eliminada;
}
