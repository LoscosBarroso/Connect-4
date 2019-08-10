package tp.pr4.control.ordenes;

import tp.pr4.control.Controlador;
import tp.pr4.logica.Partida;

public class OrdenSalir implements Orden {

	public OrdenSalir(Controlador control) {
		
		controlador = control;
	}
	
	public void ejecutaOrden(Partida partida) {

		controlador.salir();
	}

	public Orden parser(String linea) {
		
		if (linea.equals("salir")) {
			
			return new OrdenSalir(controlador);
		} else {
			
			return null;
		}
	}
	
	public String getAyuda() {

		System.out.println();
		return OrdenSalir.ayuda;
	}
	
	private Controlador controlador;
	private static String ayuda = "SALIR: termina la aplicación.";
}
