package tp.pr4.logica;

public interface ReglasJuego {
	
	//Inicia un tablero para el juego seleccionado
	public Tablero iniciaTablero();
	
	//Devuelve el jugador inicial del juego en cuestion
	public Ficha jugadorInicial();

	//Devuelve la ficha correspondiente al ganador de la partida o Ficha.VACIA en caso de no haber ganador.
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero tablero);

	//Devuelve true si se han producido tablas, en cuyo caso, terminaremos la partida sin ganador.
	public boolean tablas(Ficha ultimoEnPoner, Tablero tablero);
	
	//Devuelve el jugador que juega a continuación.
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero tablero);
}
