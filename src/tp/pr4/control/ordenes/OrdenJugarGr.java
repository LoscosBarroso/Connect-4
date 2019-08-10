package tp.pr4.control.ordenes;

import tp.pr4.control.Controlador;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasGravity;
import tp.pr4.logica.ReglasJuego;

public class OrdenJugarGr implements Orden {
	
	public OrdenJugarGr(java.util.Scanner scanner, Controlador control, int cols, int fils) {
		
		in = scanner;
		controlador = control;
		columnas = cols;
		filas = fils;
	}

	public void ejecutaOrden(Partida partida) {
		
		ReglasJuego reglas;
		
		reglas = new ReglasGravity(columnas, filas);
		controlador.setFactoria(2, columnas, filas);
		controlador.setJugador(0, 0);
		controlador.setJugador(1, 0);
		partida.reset(reglas);
	}

	public Orden parser(String linea) {

		String [] cadena = new String [5];
		cadena = linea.split(" +");
		
		if (cadena[0].equals("jugar") && cadena.length > 3 && cadena[1].equals("gr")) {

			columnas = Integer.parseInt(cadena[2]);
			filas = Integer.parseInt(cadena[3]);
			
			return new OrdenJugarGr(in, controlador, columnas, filas);
		} else {
			
			return null;
		}
	}
	
	public String getAyuda() {
		
		return OrdenJugarGr.ayuda;
	}
	
	private java.util.Scanner in;
	private Controlador controlador;
	private int columnas;
	private int filas;
	private static String ayuda = "";
}
