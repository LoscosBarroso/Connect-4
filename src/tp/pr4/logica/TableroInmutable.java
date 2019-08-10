package tp.pr4.logica;

public interface TableroInmutable {

	//Devuelve el alto del tablero
	public int getAlto();
	
	//Devuelve el ancho del tablero
	public int getAncho();
	
	//Devuelve el valor de la casilla que se pide.
	public Ficha getCasilla(int x, int y);
}
