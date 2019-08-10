package tp.pr4.logica;

public class MovimientoGravity extends Movimiento {

	public MovimientoGravity(int col, int fil, Ficha color) {

		fila = fil;
		columna = col;
		jugador = color;
	}

	public void ejecutaMovimiento(Tablero tablero) throws MovimientoInvalido {

		if (tablero.getCasilla(columna, fila) != Ficha.VACIA) {
			
			throw new MovimientoInvalido("Casilla ocupada.");
		} 
		else if (columna > 0 && columna <= tablero.getAncho() && fila > 0 && fila <= tablero.getAlto()) {
			int distUp = fila -1;
			int distDown = tablero.getAlto() - fila;
			int distLeft = columna -1;
			int distRight = tablero.getAncho() - columna;
			
			//Arriba
			if((distUp < distDown)&&((distUp < distLeft && distUp < distRight)||distLeft == distRight)){
				
				ejecutaMovimiento(tablero, 0, -1);
			}
			
			//Arriba-Derecha
			else if((distRight == distUp)&&(distRight < distLeft && distUp < distDown)){
				
				ejecutaMovimiento(tablero, 1, -1);
			}
			
			//Derecha
			else if((distRight < distLeft)&&((distRight < distUp && distRight < distDown)||distUp == distDown)){
				
				ejecutaMovimiento(tablero, 1, 0);
			}
			
			//Abajo-Derecha
			else if((distRight == distDown)&&(distRight < distLeft && distDown < distUp)){
				
				ejecutaMovimiento(tablero, 1, 1);
			}
			
			//Abajo
			else if((distDown < distUp)&&((distDown < distLeft && distDown < distRight)||distLeft == distRight)){
				
				ejecutaMovimiento(tablero, 0, 1);
			}
			
			//Abajo-Izquierda
			else if((distLeft == distDown)&&(distLeft < distRight && distDown < distUp)){
				
				ejecutaMovimiento(tablero, -1, 1);
			}
			
			//Izquierda
			else if((distLeft < distRight)&&((distLeft < distUp && distLeft < distDown)||distUp == distDown)){
				
				ejecutaMovimiento(tablero, -1, 0);
			}
			
			//Arriba-Izquierda
			else if((distLeft == distUp)&&(distLeft < distRight && distUp < distDown)){
				
				ejecutaMovimiento(tablero, -1, -1);
			}
			
			//Centro
			else {}
			
			tablero.setCasilla(columna, fila, jugador);
		} 
		
		else {
			
			throw new MovimientoInvalido("Posición incorrecta.");
		}
	}


	public void ejecutaMovimiento(Tablero tablero, int sumCol, int sumFil) {
		
		while (columna > 1 && columna < tablero.getAncho() && fila > 1 && fila < tablero.getAlto() && tablero.getCasilla(columna + sumCol, fila + sumFil).equals(Ficha.VACIA)) {
			
			columna += sumCol;
			fila += sumFil;
		}
	}

	public void undo(Tablero tablero) {

		tablero.setCasilla(columna, fila, Ficha.VACIA);
	}

	public int getColumna() {

		return columna;
	}

	public int getFila() {

		return fila;
	}

	private int columna;
	private int fila;
}