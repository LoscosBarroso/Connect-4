package tp.pr4.vista.swing;

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import tp.pr4.control.ControladorSwing;
import tp.pr4.control.Jugadores;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;

public class PTablero extends JPanel implements Observer {
	
	public PTablero(ControladorSwing control) {
		
		controlador = control;
		setLayout(new GridLayout(1, 1));
	}

	/*Se ejecuta al principio del turno para actualizar el tablero
	y mostrar los posibles movimientos o realizar un movimiento automático,
	según el tipo de jugador al que le toca.
	*/
	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {

		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (celdas[i][j].isPosible()) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
			}
		}
		
		if (controlador.getJugador().getTipoJugador().equals(Jugadores.Humano)) {

			List<int[]> posibles = new LinkedList<int[]>();
			posibles = controlador.getPosibles(turno);
			if (posibles != null) {

				int[] posicion;
				while (!posibles.isEmpty()) {
					posicion = posibles.remove(0);
					celdas[posicion[0] - 1][posicion[1] - 1].setPosible();
				}
			}
		} else {

			controlador.turnoAutomatico();
		}
	}

	//Se ejecuta al finalizar un movimiento para actualizar el tablero.
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (estadoTablero.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
			}
		}
	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {

	}

	//Se ejecuta al reiniciar la partida para actualizar el tablero.
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		removeAll();
		setLayout(new GridLayout(estadoInicial.getAlto(), estadoInicial.getAncho()));
		iniciaCeldas(estadoInicial.getAncho(), estadoInicial.getAlto());

		for (int i = 0; i < estadoInicial.getAncho(); i++) {
			for (int j = 0; j < estadoInicial.getAlto(); j++) {
				
				if (estadoInicial.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(estadoInicial.getCasilla(i+1, j+1));
				}
			}
		}
		
		validate();
		repaint();
	}

	//Se ejecuta al deshacer un movimiento para actualizar el tablero.
	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (estadoTablero.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
			}
		}
	}

	public void onUndoNotPossible() {

	}

	//Se ejecuta al finalizar la partida para actualizar el tablero.
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {


		for (int i = 0; i < tableroFinal.getAncho(); i++) {
			for (int j = 0; j < tableroFinal.getAlto(); j++) {
				
				if (tableroFinal.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(tableroFinal.getCasilla(i+1, j+1));
				}
			}
		}
	}
	
	//Crea las celdas del nuevo tablero.
	private void iniciaCeldas(int ancho, int alto) {
		
		celdas = new Celda [ancho][alto];
		
		for (int i = 0; i < alto; i++) {
			
			for (int j = 0; j < ancho; j++) {
				
				celdas[j][i] = new Celda(controlador, j+1, i+1);
				this.add(celdas[j][i]);
			}
		}
	}

	private ControladorSwing controlador;
	private Celda[][] celdas;
	private static final long serialVersionUID = 7376027550623146398L;
}
