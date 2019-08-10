package tp.pr4.control.ordenes;

import tp.pr4.logica.Partida;

public class OrdenDeshacer implements Orden {

	public void ejecutaOrden(Partida partida) {

		partida.undo();
	}

	public Orden parser(String linea) {
		
		if (linea.equals("deshacer")) {
			
			return new OrdenDeshacer();
		} else {
			
			return null;
		}
	}

	public String getAyuda() {

		System.out.println();
		return OrdenDeshacer.ayuda;
	}

	private static String ayuda = "DESHACER: deshace el último movimiento hecho en la partida.";
}
