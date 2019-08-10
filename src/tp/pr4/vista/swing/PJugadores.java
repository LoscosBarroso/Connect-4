package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr4.control.ControladorSwing;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Partida.Observer;
import tp.pr4.logica.TableroInmutable;

public class PJugadores extends JPanel implements Observer {

	public PJugadores(final ControladorSwing control) {

		controlador = control;
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Gestion de Jugadores", TitledBorder.LEFT,TitledBorder.TOP));
		
		JPanel pNorte = new JPanel(new FlowLayout());
		JPanel pSur = new JPanel(new FlowLayout());
		
		String opciones [] = {"Humano", "Automatico"};

		//Selector del jugador 1.
		pNorte.add(new JLabel("Jugador de Blancas"));
		jugador1 = new JComboBox<String>(opciones);
		jugador1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				controlador.setJugador(Ficha.BLANCA, jugador1.getSelectedItem().toString());
			}
		});
		pNorte.add(jugador1);

		//Selector del jugador 2.
		pSur.add(new JLabel("Jugador de Negras"));
		jugador2 = new JComboBox<String>(opciones);
		jugador2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				controlador.setJugador(Ficha.NEGRA, jugador2.getSelectedItem().toString());
			}
		});
		pSur.add(jugador2);
		
		add(pNorte, BorderLayout.NORTH);
		add(pSur, BorderLayout.SOUTH);
	}

	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {

	}
	
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {
		
	}

	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

	}

	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

	}

	public void onUndoNotPossible() {

	}

	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

	}
	
	private ControladorSwing controlador;
	private JComboBox<String> jugador1;
	private JComboBox<String> jugador2;
	private static final long serialVersionUID = -7373496664211064623L;
}
