package tp.pr4.vista.swing;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;

public class PPartida extends JPanel implements Observer {
	
	public PPartida(PTablero pTablero) {
		
		setLayout(new BorderLayout(0, 50));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		add(pTablero, BorderLayout.CENTER);

		//Texto que mostrará el turno y quien gana
		etiqueta = new JLabel("Juegan Blancas", (int) java.awt.Component.CENTER_ALIGNMENT);
		add(pTablero, BorderLayout.CENTER);
		add(etiqueta, BorderLayout.SOUTH);
	}

	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {
		
		
	}

	//Se ejecuta al finalizar un movimiento para mostrar a quien le toca.
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

		switch (siguiente) {
		
		case BLANCA: etiqueta.setText("Juegan blancas"); break;
		case NEGRA: etiqueta.setText("Juegan negras"); break;
		default: break;
		}
	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {

	}

	//Se ejecuta al reiniciar la partida para mostrar a quien le toca.
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		switch (turno) {
		
		case BLANCA: etiqueta.setText("Juegan blancas"); break;
		case NEGRA: etiqueta.setText("Juegan negras"); break;
		default: break;
		}
	}

	//Se ejecuta al deshacer un movimiento para mostrar a quien le toca.
	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

		switch (turno) {
		
		case BLANCA: etiqueta.setText("Juegan blancas"); break;
		case NEGRA: etiqueta.setText("Juegan negras"); break;
		default: break;
		}
	}

	public void onUndoNotPossible() {

	}

	//Se ejecuta al finalizar la partida para mostrar quien ha ganado.
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

		switch (ganador) {
		
		case BLANCA: etiqueta.setText("Ganan las blancas"); break;
		case NEGRA: etiqueta.setText("Ganan las negras"); break;
		default: etiqueta.setText("Partida terminada en tablas."); break;
		}
	}

	private JLabel etiqueta;
	private static final long serialVersionUID = 2585910997255526921L;
}

