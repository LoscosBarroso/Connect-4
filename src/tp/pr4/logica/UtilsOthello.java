package tp.pr4.logica;

import java.util.LinkedList;

//Clase de utilidades para facilitar el trabajo con el juego reversi.
public class UtilsOthello {

	//Devuelve true si se puede poner en la posición (posX, posY).
	public static boolean puedePoner(TableroInmutable tablero, int posX, int posY, Ficha color) {
		
		boolean puede = false;
		
		int i = 0;
		while(tablero.getCasilla(posX, posY).equals(Ficha.VACIA) && !puede && i < 8) {
			
			puede = puedePoner(tablero, posX, posY, X[i], Y[i], color);
			i++;
		}
		
		return puede;
	}
	
	//Realiza el movimiento y devuelve el número de fichas cambiadas al realizar el movimiento.
	public static int[] poner(Tablero tablero, int posX, int posY, Ficha color) {
		
		int [] cambiadas = new int [8];
		
		tablero.setCasilla(posX, posY, color);
		
		for (int i = 0; i < 8; i++) {
			
			cambiadas[i] = poner(tablero, posX, posY, X[i], Y[i], color);
		}
		
		return cambiadas;
	}
	
	//Deshace el movimiento.
	public static void deshacer(Tablero tablero, int posX, int posY, Ficha color, int[] cambiadas) {

		tablero.setCasilla(posX, posY, Ficha.VACIA);
		
		for (int i = 0; i < 8; i++) {
			
			deshacer(tablero, posX, posY, X[i], Y[i], color, cambiadas[i]);
		}
	}

	//Devuelve una lista con las posibles posiciones donde colocar una ficha.
	public static LinkedList<int[]> getPosibles(Tablero tablero, Ficha color) {
		
		LinkedList<int[]> posibles = new LinkedList<int[]>();
		for (int i = 0; i < tablero.getAncho(); i++) {
			for (int j = 0; j < tablero.getAlto(); j++) {

				if (UtilsOthello.puedePoner(tablero, i + 1, j + 1, color)) {

					int[] posicion = new int[2];
					posicion[0] = i + 1;
					posicion[1] = j + 1;
					posibles.push(posicion);
				}
			}
		}

		return posibles;
	}

	//Devuelve true si se puede poner en la posición (posX, posY).
	private static boolean puedePoner(TableroInmutable tablero, int posX, int posY, int incrX, int incrY, Ficha color) {
		
		int auxX = posX + incrX;
		int auxY = posY + incrY;
		int cambiadas = 0;
		
		while(auxX > 0 && auxY > 0 && auxX <= tablero.getAncho() && auxY <= tablero.getAlto() && tablero.getCasilla(auxX, auxY) != color && tablero.getCasilla(auxX, auxY) != Ficha.VACIA) {
			
			auxX += incrX;
			auxY += incrY;
			cambiadas++;
		}
		
		if (auxX > 0 && auxY > 0 && auxX <= tablero.getAncho() && auxY <= tablero.getAlto() && tablero.getCasilla(auxX, auxY).equals(color) && cambiadas > 0) {
			
			return true;
		} else {
			
			return false;
		}
	}

	//Realiza el movimiento y devuelve el número de fichas cambiadas al realizar el movimiento.
	private static int poner(Tablero tablero, int posX, int posY, int incrX, int incrY, Ficha color) {
		
		if (puedePoner(tablero, posX, posY, incrX, incrY, color)) {
			int auxX = posX + incrX;
			int auxY = posY + incrY;
			int cambiadas = 0;
			
			while (auxX > 0 && auxY > 0 && auxX <= tablero.getAncho() && auxY <= tablero.getAlto() && tablero.getCasilla(auxX, auxY) != color && tablero.getCasilla(auxX, auxY) != Ficha.VACIA) {

				tablero.setCasilla(auxX, auxY, color);
				auxX += incrX;
				auxY += incrY;
				cambiadas++;
			}
			
			return cambiadas;
		} else {
			
			return 0;
		}
	}

	//Deshace el movimiento.
	private static void deshacer(Tablero tablero, int posX, int posY, int incrX, int incrY, Ficha color, int cambiadas) {
		
		if (cambiadas > 0) {
			int auxX = posX + incrX;
			int auxY = posY + incrY;
			
			while (cambiadas > 0) {

				tablero.setCasilla(auxX, auxY, fichaContraria(color));
				auxX += incrX;
				auxY += incrY;
				cambiadas--;
			}
		}
	}
	
	//Devuelve la ficha contraria a la ficha recibida.
	private static Ficha fichaContraria(Ficha color) {

		switch (color) {
		
		case BLANCA: return Ficha.NEGRA;
		case NEGRA: return Ficha.BLANCA;
		default: return Ficha.VACIA;
		}
	}
	
	private static int [] X = {1, 1, 0, -1, -1, -1, 0, 1};
	private static int [] Y = {0, 1, 1, 1, 0, -1, -1, -1};
}
