package tp.pr4.control.ordenes;

import tp.pr4.control.Controlador;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Partida;

public class OrdenCambiarJugador implements Orden {

	public OrdenCambiarJugador(java.util.Scanner scanner, Ficha color, String tip, Controlador control) {
		
		in = scanner;
		jugador = color;
		tipo = tip;
		tipo.toLowerCase();
		controlador = control;
	}
	
	public void ejecutaOrden(Partida partida) {

		switch (tipo) {
		
		case "humano": controlador.setJugador(jugador.ordinal(), 0); break;
		case "aleatorio": controlador.setJugador(jugador.ordinal(), 1); break;
		}
	}

	public Orden parser(String linea) {

		String [] cadena = new String [5];
		cadena = linea.split(" +");
		
		if (cadena[0].equals("jugador")) {
			
			switch (cadena[1]) {
			
			case "blancas": jugador = Ficha.BLANCA; break;
			case "negras": jugador = Ficha.NEGRA; break;
			default: return null;
			}
			
			tipo = cadena[2];
			if (tipo.equals("humano") || tipo.equals("aleatorio")) {

				return new OrdenCambiarJugador(in, jugador, tipo, controlador);
			}
			
			return null;
		} else {
			
			return null;
		}
	}
	
	public String getAyuda() {

		System.out.println();
		return OrdenCambiarJugador.ayuda;
	}

	java.util.Scanner in;
	private Ficha jugador;
	private String tipo;
	private Controlador controlador;
	private static String ayuda = "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.";
}
