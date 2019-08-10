package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Partida.Observer;
import tp.pr4.logica.TableroInmutable;

public class VistaSwing extends JFrame implements Observer {

	//Crea el objeto de tipo VistaSwing para cotrolar la vista
	public VistaSwing(PIzquierda pIzquierda, PDerecha pDerecha) {
		
		//Crea la ventana.
		super("Práctica 4 - TP");
		initGUI(pIzquierda, pDerecha);
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				setVisible(true);
			}
		});
		iniciado = false;
	}
	
	//Inicia la ventana y añade todos sus componentes, además de prepararla para el primer juego.
	private void initGUI(PIzquierda pIzquierda, PDerecha pDerecha) {
		
		//Se inicializa la ventana
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2, 20, 10));

		//Se añaden los componentes a la ventana.
		this.add(pIzquierda, BorderLayout.WEST);
		this.add(pDerecha, BorderLayout.EAST);
	}

	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {
		
		
	}
	
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {
		
	}

	//Se ejecuta al realizar un movimiento no valido, para mostrar la causa del error.
	@SuppressWarnings("static-access")
	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

		JOptionPane alert = new JOptionPane(explicacion);
		alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
	}

	public void onMovimientoStart(Ficha turno) {
		
	}

	//Se ejecuta para avisar de que la partida ha sido reiniciada.
	@SuppressWarnings("static-access")
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {
		
		if (iniciado) {

			JOptionPane alert = new JOptionPane("Partida reiniciada.");
			alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.PLAIN_MESSAGE);
		} else {
			
			iniciado = true;
		}
	}

	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

	}

	//Se ejecuta cuando no se pueden deshacer más movimientos y avisa de que no se pueden deshacer movimientos.
	@SuppressWarnings("static-access")
	public void onUndoNotPossible() {

		JOptionPane alert = new JOptionPane("Imposible deshacer.");
		alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
	}

	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {
		
	}

	private boolean iniciado;
	private static final long serialVersionUID = 6901761972003151399L;
}
