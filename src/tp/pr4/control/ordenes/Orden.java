package tp.pr4.control.ordenes;

import tp.pr4.logica.Partida;

//Interfaz auxiliar para manejar las ordenes introducidas por consola.
public interface Orden {
	
	//Ejecuta la orden.
	public void ejecutaOrden(Partida partida);
	
	//Determina si la orden es la introducida por el usuario
	public Orden parser(String linea);
	
	//Devuelve la ayuda de la orden en cuestion.
	public String getAyuda();
}