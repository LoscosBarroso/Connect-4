package tp.pr4.control.ordenes;

import tp.pr4.logica.Partida;

public class OrdenAyuda implements Orden {
	
	public OrdenAyuda(Orden ords[], int n) {
		
		ordenes = new Orden[n];
		cantidad = n;
		
		for (int i = 0; i < cantidad; i++) {
			
			ordenes[i] = ords[i];
		}
	}

	public void ejecutaOrden(Partida partida) {

		System.out.println("Los comandos disponibles son:");
		
		for (int i = 0; i < cantidad - 1; i++) {
			
			System.out.print(ordenes[i].getAyuda());
		}

		System.out.println();
		System.out.println(this.getAyuda());
		System.out.println();
	}

	public Orden parser(String linea) {
		
		if (linea.equals("ayuda")) {
			
			return new OrdenAyuda(ordenes, cantidad);
		} else {
			
			return null;
		}
	}
	
	public String getAyuda() {
		
		return OrdenAyuda.ayuda;
	}
	
	private Orden [] ordenes;
	private int cantidad;
	private static String ayuda = "AYUDA: muestra esta ayuda.";

}
