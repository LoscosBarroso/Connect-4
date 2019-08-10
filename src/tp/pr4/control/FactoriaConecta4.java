package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.*;

public class FactoriaConecta4 implements FactoriaTipoJuego {

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		
		return new JugadorHumano(in, this, false);
	}
	
	public JugadorSwing creaJugadorHumanoSwing(ControladorSwing controlador) {
	
		return new JugadorHumanoSwing(controlador);
	}

	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioConecta4(this);
	}

	public JugadorSwing creaJugadorAutomatico() {
		
		return new JugadorAutomaticoSwing();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoConecta4(col, color);
	}

	public ReglasJuego creaReglas() {

		return new ReglasConecta4();
	}

}
