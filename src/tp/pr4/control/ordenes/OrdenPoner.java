package tp.pr4.control.ordenes;

import tp.pr4.control.Controlador;
import tp.pr4.logica.*;

public class OrdenPoner implements Orden {
	
	public OrdenPoner(java.util.Scanner scanner, Controlador control) {
		
		in = scanner;
		controlador = control;
	}

	public void ejecutaOrden(Partida partida) {
		
		try {

			Movimiento movimiento = controlador.getJugador().getMovimiento(partida.getTablero(), partida.getTurno());	
			partida.ejecutaMovimiento(movimiento);
		} catch (MovimientoInvalido mov) {
			
		}
	}

	public Orden parser(String linea) {
		
		if (linea.equals("poner")) {
			
			return new OrdenPoner(in, controlador);
		} else {
			
			return null;
		}
	}
	
	public String getAyuda() {

		System.out.println();
		return OrdenPoner.ayuda;
	}

	private java.util.Scanner in;
	private Controlador controlador;
	private static String ayuda = "PONER: utilízalo para poner la siguiente ficha.";
}
