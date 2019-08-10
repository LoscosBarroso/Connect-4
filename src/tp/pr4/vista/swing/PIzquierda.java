package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr4.control.ControladorSwing;
import tp.pr4.control.Jugadores;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;

public class PIzquierda extends JPanel implements Observer {
	
	public PIzquierda(final ControladorSwing control, PPartida pPartida) {

		controlador = control;
		
		setLayout(new BorderLayout());
		
		JPanel pAleatorio = new JPanel(new FlowLayout());

		//Boton para realizar un movimiento aleatorio
		aleatorio = new JButton("Poner aleatorio");
		
		aleatorio.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				controlador.ordenAleatorio();
			}
		});
		aleatorio.setIcon(new ImageIcon("src/tp/pr4/vista/swing/iconos/random.png"));
		pAleatorio.add(aleatorio);

		add(pPartida, BorderLayout.CENTER);
		add(pAleatorio, BorderLayout.SOUTH);
	}

	/*Se ejecuta al iniciar el turno para activar o desactivar el botón de movimiento aleatorio
	según el tipo de jugador al que le toque.
	*/
	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {
		
		if (controlador.getJugador().getTipoJugador().equals(Jugadores.Automatico)) {
			
			aleatorio.setEnabled(false);
		} else {
			
			aleatorio.setEnabled(true);
		}
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

	//Se ejecuta al finalizar la partida para desactivar el botón de movimiento aleatorio.
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

		aleatorio.setEnabled(false);
	}

	private ControladorSwing controlador;
	private JButton aleatorio;
	private static final long serialVersionUID = -7741699160107822700L;
}

