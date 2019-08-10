package tp.pr4.logica;

public abstract class Movimiento {

	//Ejecuta el movimiento según el juego en cuestión. Modifica el tablero.
	public abstract void ejecutaMovimiento(Tablero tablero) throws MovimientoInvalido;
	
	//Deshace el movimiento.
	public abstract void undo(Tablero tablero);

	//Devuelve la columna del movimiento.
	public abstract int getColumna();
	
	//Devuelve la fila del movimiento.
	public abstract int getFila();
	
	//Devuelve el jugador que ha realiado el movimiento.
	public Ficha getJugador() {
		
		return jugador;
	}
	
	protected Ficha jugador;
}
