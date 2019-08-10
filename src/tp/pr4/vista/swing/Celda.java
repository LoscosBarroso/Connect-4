package tp.pr4.vista.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr4.control.*;
import tp.pr4.logica.*;

//Clase auxiliar para implementar la vista Swing facilitando el trabajo con las celdas del tablero.
public class Celda extends JButton{

	//Crea un objeto de tipo Celda.
	public Celda(final ControladorSwing controlador, int posX, int posY) {
		
		setBackground(Color.green);
		color = Ficha.VACIA;
		columna = posX;
		fila = posY;
		
		addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				controlador.getJugador().tableroPulsado(columna, fila);
				}
			}
		);
	}
	
	//Cambia el color de la celda.
	public void setColor(Ficha colr) {

		switch (colr) {
		case BLANCA: color = Ficha.BLANCA; setBackground(Color.white); break;
		case NEGRA: color = Ficha.NEGRA; setBackground(Color.black); break;
		default: color = Ficha.VACIA; setBackground(Color.green); break;
		}
	}
	
	//Cambia el color de la celda a rojo para señalar que es un movimiento posible.
	public void setPosible() {
		
		setBackground(Color.red);
	}
	
	//Devuelve si la celda ha sido señalada como movimiento posible o no.
	public boolean isPosible() {
		
		if (getBackground().equals(Color.red)) {
			
			return true;
		} else {
			
			return false;
		}
	}
	
	//Devuelve la columna de la celda.
	public int getCol(){
		
		return columna;
	}
	
	//Devuelve la fila de la celda.
	public int getFil() {
		
		return fila;
	}
	
	//Devuelve el color de la celda.
	public Ficha getColor() {
		
		return color;
	}
	
	private int columna;
	private int fila;
	private Ficha color;
	private static final long serialVersionUID = 4026449246830280495L;
}
