package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.ControladorSwing;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Partida.Observer;
import tp.pr4.logica.TableroInmutable;

public class PDerecha extends JPanel implements Observer {
	
	public PDerecha(final ControladorSwing controlador, PJuego pJuego, PJugadores pJugadores, POpciones pOpciones) {

		setLayout(new BorderLayout());

		JPanel pCentro = new JPanel(new BorderLayout());
		JPanel pCambioJuego = new JPanel(new BorderLayout());
		JPanel pSalir = new JPanel(new FlowLayout());

		//Boton para salir
		salir = new JButton("Salir");
		salir.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent event) {
				
				JOptionPane popup = new JOptionPane("¿Quieres salir?");
				
				if (popup.showConfirmDialog(null, popup.getMessage(), "Atención", popup.YES_NO_OPTION) == 0) {

					controlador.ordenSalir();
				}
			}
		});
		salir.setIcon(new ImageIcon("src/tp/pr4/vista/swing/iconos/exit.png"));
		pSalir.add(salir);

		pCambioJuego.add(pJuego, BorderLayout.NORTH);
		pCentro.add(pJugadores, BorderLayout.NORTH);
		pCentro.add(pCambioJuego, BorderLayout.CENTER);
		
		add(pOpciones, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pSalir, BorderLayout.SOUTH);
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

	private JButton salir;
	private static final long serialVersionUID = -2159154031903564838L;
}
