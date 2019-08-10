package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public interface Jugador {

	//Crea un movimiento del juego en cuestión.
	//Si es un jugador humano, pregunta por las coordenadas de la ficha que se quiere colocar.
	public Movimiento getMovimiento(Tablero tab, Ficha color);
}