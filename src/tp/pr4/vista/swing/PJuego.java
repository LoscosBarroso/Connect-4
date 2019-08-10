package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr4.control.ControladorSwing;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;

public class PJuego extends JPanel implements Observer {
	
	public PJuego(final ControladorSwing controlador, final PDimensiones pDimensiones) {

		setLayout(new BorderLayout(0, 10));
		
		JPanel pCambio = new JPanel(new BorderLayout());
		JPanel pCambiar = new JPanel(new FlowLayout());
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Cambio de Juego", TitledBorder.LEFT,TitledBorder.TOP));


		//Boton para cambiar de juego
		cambiar = new JButton("Cambiar");
		cambiar.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent event) {
				
				try {
					
					controlador.ordenCambiar(pDimensiones.getJuego(), pDimensiones.getColumnas(), pDimensiones.getFilas());
				} catch (NumberFormatException e) {

					JOptionPane alert = new JOptionPane("Columnas y/o filas no válidas.");
					alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		cambiar.setIcon(new ImageIcon("src/tp/pr4/vista/swing/iconos/aceptar.png"));
		pCambiar.add(cambiar);
		
		pCambio.add(pDimensiones, BorderLayout.CENTER);
		pCambio.add(pCambiar, BorderLayout.SOUTH);
		add(pCambio, BorderLayout.NORTH);
	}

	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {
		
		
	}

	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {

	}

	//Se ejecuta al reiniciar la partida para activar el botón de cambiar.
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		cambiar.setEnabled(true);
	}

	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

	}

	public void onUndoNotPossible() {

	}

	//Se ejecuta al finalizar la partida para desactivar el botón de cambiar.
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

		cambiar.setEnabled(false);
	}

	private JButton cambiar;
	private static final long serialVersionUID = 5154447299955625528L;
}
