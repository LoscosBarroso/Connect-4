package tp.pr4.control;

import java.util.LinkedList;

import javax.swing.SwingUtilities;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasComplica;
import tp.pr4.logica.ReglasConecta4;
import tp.pr4.logica.ReglasGravity;
import tp.pr4.logica.ReglasJuego;
import tp.pr4.logica.ReglasReversi;
import tp.pr4.logica.UtilsOthello;

public class ControladorSwing extends Thread {

	//Constructor de la clase Controlador, con sus atributos.
	public ControladorSwing(FactoriaTipoJuego f, Partida p) {

		factoria = f;
		partida = p;
		juego = null;
		jugadorBlanco = factoria.creaJugadorHumanoSwing(this);
		jugadorNegro = factoria.creaJugadorHumanoSwing(this);
		hebra = new Hebra();
	}
	
	//Crea una hebra para realizar movimientos automáticos.
	public void turnoAutomatico() {

		hebra = new Hebra();
		hebra.start();
	}

	//Crea un movimiento a partir de los datos recibidos de la vista.
	public void ordenPoner(int columna, int fila) {

		try {

			Movimiento movimiento = factoria.creaMovimiento(columna, fila, partida.getTurno());
			partida.ejecutaMovimiento(movimiento);
			partida.isTerminada();
		} catch (MovimientoInvalido mov) {
			
		}
	}
	
	//Crea un movimiento aleatorio en función del juego que se está jugando.
	public void ordenAleatorio() {

		try {
			Jugador jugador = factoria.creaJugadorAleatorio();
			Movimiento movimiento = jugador.getMovimiento(partida.getTablero(), partida.getTurno());
			partida.ejecutaMovimiento(movimiento);
			partida.isTerminada();
		} catch (MovimientoInvalido e) {
			
		}
	}
	
	//Deshace el ultimo movimiento.
	public void ordenDeshacer() {

		hebra.interrupt();
		partida.undo();
	}
	
	//Reinicia la partida.
	public void ordenReiniciar() {

		hebra.interrupt();
		partida.reset(partida.getReglas());
	}
	
	//Cambia de juego en función del String pasado.
	public void ordenCambiar(String SJuego, int cols, int fils) throws NumberFormatException {

		hebra.interrupt();
		ReglasJuego reglas;
		switch(SJuego) {
		case "conecta4":
			reglas = new ReglasConecta4();
			factoria = new FactoriaConecta4();
			juego = TipoJuego.Conecta4;
			partida.reset(reglas);
			break;
		case "complica":
			reglas = new ReglasComplica();
			factoria = new FactoriaComplica();
			juego = TipoJuego.Complica;
			partida.reset(reglas);
			break;
		case "gravity":
			reglas = new ReglasGravity(cols, fils);
			factoria = new FactoriaGravity(cols, fils);
			juego = TipoJuego.Gravity;
			partida.reset(reglas);
			break;
		case "reversi":
			reglas = new ReglasReversi();
			factoria = new FactoriaReversi();
			juego = TipoJuego.Reversi;
			partida.reset(reglas);
			break;
		default: break;
		}
	}
	
	//Termina la aplicacón.
	public void ordenSalir() {

		hebra.interrupt();
		System.exit(0);
	}
	
	//Devuelve el jugador activo.
	public JugadorSwing getJugador() {
		
		switch (partida.getTurno()) {
		case BLANCA: return jugadorBlanco;
		case NEGRA: return jugadorNegro;
		default: return jugadorBlanco;
		}
	}
	
	//Cambia el tipo de jugador entre aleatorio y humano.
	public void setJugador(Ficha jugador, String tipo) {
		
		switch(jugador) {
		case BLANCA:
			switch(tipo) {
			
			case "Humano": jugadorBlanco = factoria.creaJugadorHumanoSwing(this); break;
			case "Automatico": jugadorBlanco = factoria.creaJugadorAutomatico(); break;
			default: jugadorBlanco = factoria.creaJugadorHumanoSwing(this); break;
			}
			break;
		case NEGRA:
			switch(tipo) {
			
			case "Humano": jugadorNegro = factoria.creaJugadorHumanoSwing(this); break;
			case "Automatico": jugadorNegro = factoria.creaJugadorAutomatico(); break;
			default: jugadorNegro = factoria.creaJugadorHumanoSwing(this); break;
			}
			break;
		default:
			switch(tipo) {
			
			case "Humano": jugadorBlanco = factoria.creaJugadorHumanoSwing(this); break;
			case "Automatico": jugadorBlanco = factoria.creaJugadorAutomatico(); break;
			default: jugadorBlanco = factoria.creaJugadorHumanoSwing(this); break;
			}
			break;
		}
		
		if (partida.getTurno().equals(jugador)) {

			hebra.interrupt();
			partida.turnoIniciado();
		}
	}
	
	//Devuelve una lista de posiciones posibles si el juego es reversi. Si no devuelve un NULL.
	public LinkedList<int[]> getPosibles(Ficha color) {
		
		if (juego.equals(TipoJuego.Reversi)) {

			return UtilsOthello.getPosibles(partida.getTablero(), color);
		} else {
			
			return null;
		}
	}
	
	private FactoriaTipoJuego factoria;
	private Partida partida;
	private TipoJuego juego;
	private JugadorSwing jugadorBlanco;
	private JugadorSwing jugadorNegro;
	private Hebra hebra;
	
	//Clase Hebra para manejar las hebras de la función turnoAutomático();
	private class Hebra extends Thread{
		
		public void run() {
			
			try {
				
				sleep(2000);
				SwingUtilities.invokeLater(new Runnable() {
				
					public void run() {

						ordenAleatorio();	
					}
				});
			} catch (InterruptedException e) {
				
				return;
			}
		}
	}
}
