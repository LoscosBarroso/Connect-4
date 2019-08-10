package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoReversi;
import tp.pr4.logica.ReglasJuego;
import tp.pr4.logica.ReglasReversi;

public class FactoriaReversi implements FactoriaTipoJuego {

	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorHumano(in, this, true);
	}
	
	public JugadorSwing creaJugadorHumanoSwing(ControladorSwing controlador) {
	
		return new JugadorHumanoSwing(controlador);
	}

	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioReversi(this);
	}

	public JugadorSwing creaJugadorAutomatico() {
		
		return new JugadorAutomaticoSwing();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoReversi(col, fila, color);
	}

	public ReglasJuego creaReglas() {

		return new ReglasReversi();
	}
}
