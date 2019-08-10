package tp.pr4.control;

public interface JugadorSwing {

	//Crea un movimiento del juego en cuestión.
	public void tableroPulsado(int columna, int fila);
	
	//Devuelve el tipo de jugador.
	public Jugadores getTipoJugador();
}
