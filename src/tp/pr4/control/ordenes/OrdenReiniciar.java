package tp.pr4.control.ordenes;

import tp.pr4.control.Controlador;
import tp.pr4.logica.Partida;

public class OrdenReiniciar implements Orden {
	
	public OrdenReiniciar(Controlador control) {
		
		controlador = control;
	}

	public void ejecutaOrden(Partida partida) {

		partida.reset(partida.getReglas());
	}

	public Orden parser(String linea) {
		
		if (linea.equals("reiniciar")) {
			
			return new OrdenReiniciar(controlador);
		} else {
			
			return null;
		}
	}
	
	public String getAyuda() {

		System.out.println();
		return OrdenReiniciar.ayuda;
	}
	
	private Controlador controlador;
	private static String ayuda = "REINICIAR: reinicia la partida.";
}
