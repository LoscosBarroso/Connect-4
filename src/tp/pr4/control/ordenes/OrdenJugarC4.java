package tp.pr4.control.ordenes;

import tp.pr4.control.Controlador;
import tp.pr4.logica.*;

public class OrdenJugarC4 implements Orden {

	public OrdenJugarC4(java.util.Scanner scanner, Controlador control) {
		
		in = scanner;
		controlador = control;
	}
	
	public void ejecutaOrden(Partida partida) {

		ReglasJuego reglas;
		
		reglas = new ReglasConecta4();
		controlador.setFactoria(0, 0, 0);
		controlador.setJugador(0, 0);
		controlador.setJugador(1, 0);
		partida.reset(reglas);
	}

	public Orden parser(String linea) {

		String [] cadena = new String [5];
		cadena = linea.split(" +");
		
		if (cadena[0].equals("jugar") && cadena.length > 1 && cadena[1].equals("c4")) {
			
			return new OrdenJugarC4(in, controlador);
		} else {
			
			return null;
		}
	}

	public String getAyuda() {

		System.out.println();
		return OrdenJugarC4.ayuda;
	}

	private java.util.Scanner in;
	private Controlador controlador;
	private static String ayuda = "JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.";
}
