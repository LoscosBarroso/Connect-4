package tp.pr4.vista.swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr4.control.ControladorSwing;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;

public class POpciones extends JPanel implements Observer {
	
	public POpciones(final ControladorSwing controlador) {

		setLayout(new FlowLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Partida", TitledBorder.LEFT,TitledBorder.TOP));
		
		//Boton para deshacer
		deshacer = new JButton("Deshacer");
		deshacer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {

				controlador.ordenDeshacer();
			}
		});
		deshacer.setIcon(new ImageIcon("src/tp/pr4/vista/swing/iconos/undo.png"));
		this.add(deshacer);

		//Boton para reiniciar
		reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {

				controlador.ordenReiniciar();
			}
		});
		reiniciar.setIcon(new ImageIcon("src/tp/pr4/vista/swing/iconos/reiniciar.png"));
		this.add(reiniciar);
	}

	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {
		
		
	}

	//Se ejecuta al finalizar un movimiento para activar el botón de deshacer.
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

		deshacer.setEnabled(true);
	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {

	}

	//Se ejecuta al reiniciar la partida para desactivar el botón de deshacer.
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		deshacer.setEnabled(false);
	}

	/*Se ejecuta al deshacer un movimiento para activar o desactivar el botón de deshacer
	según queden o no movimientos a deshacer.
	*/
	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

		
		if (hayMas) {
			
			deshacer.setEnabled(true);
		} else {

			deshacer.setEnabled(false);
		}
	}

	public void onUndoNotPossible() {

	}

	//Se ejecuta al finalizar la partida para desactivar el botón de deshacer.
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

		deshacer.setEnabled(false);
	}

	private JButton deshacer;
	private JButton reiniciar;
	private static final long serialVersionUID = 3500050791765216741L;
}
