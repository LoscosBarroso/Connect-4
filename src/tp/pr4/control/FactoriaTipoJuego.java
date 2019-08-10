package tp.pr4.control;

import tp.pr4.logica.*;

public interface FactoriaTipoJuego {

	//Crea un jugador humano configurado para el juego correspondiente para vista consola.
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in);

	//Crea un jugador humano configurado para el juego correspondiente para vista Swing.
	public JugadorSwing creaJugadorHumanoSwing(ControladorSwing controlador);
	
	//Crea un jugador aleatorio para el juego correspondiente.
	public Jugador creaJugadorAleatorio();

	//Crea un jugador automatico para el juego correspondiente.
	public JugadorSwing creaJugadorAutomatico();
	
	//Crea un movimiento para el juego correspondiente.
	public Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	//Crea las reglas del juego correspondiente.
	public ReglasJuego creaReglas();
}
