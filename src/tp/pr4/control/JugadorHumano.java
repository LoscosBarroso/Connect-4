package tp.pr4.control;

import java.util.InputMismatchException;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public class JugadorHumano implements Jugador {

	public JugadorHumano(java.util.Scanner input, FactoriaTipoJuego fact, boolean fil) {
		
		in = input;
		factoria = fact;
		filas = fil;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		try {
			int col, fila;
			
			System.out.print("Introduce la columna: ");

			col = in.nextInt();
			in.nextLine();
			
			if (filas) {
				System.out.print("Introduce la fila: ");

				fila = in.nextInt();
				in.nextLine();
			} else {
				
				fila = 0;
			}

			return factoria.creaMovimiento(col, fila, color);
		} catch (InputMismatchException e) {
			
			in.nextLine();
			return factoria.creaMovimiento(-1, 0, color);
		}
	}
	
	private java.util.Scanner in;
	private FactoriaTipoJuego factoria;
	private boolean filas;
}
