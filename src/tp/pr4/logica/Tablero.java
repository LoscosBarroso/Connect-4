package tp.pr4.logica;

public class Tablero implements TableroInmutable {

	//Constructor de un tablero w por h donde se ejecutará la partida.
	public Tablero(int w, int h) {

		ancho = Math.max(w, 1);
		alto = Math.max(h, 1);
		tablero = new Ficha [ancho][alto];
		
		for (int i = 0; i < ancho; i++) {
			
			for (int j = 0; j < alto; j++){
				
				tablero[i][j] = Ficha.VACIA;
			}
		}
	}
	
	//Metodos para acceder a los atributos de ancho y alto del tablero.
	public int getAncho() {
		
		return ancho;
	}
	
	public int getAlto() {
		
		return alto;
	}
	
	//Método para comprobar el tipo de Ficha de la posición (w,h) del tablero.
	public Ficha getCasilla(int w, int h) {
		
		Ficha ficha;
		
		if (w > ancho || h > alto || w <= 0 || h <= 0) {
			
			ficha = Ficha.VACIA;
		} else {
			
			ficha = tablero[w - 1][h - 1];
		}
		
		return ficha;
	}
	
	//En el caso de tableros con anchos o altos inválidos, creamos este construcctor por defecto.
	public boolean setCasilla(int w, int h, Ficha ficha) {
		
		if (w > ancho || h > alto || w <= 0 || h <= 0) {
			
			return false;
		}
				
		tablero[w - 1][h - 1] = ficha;
		return true;
	}
	
	private Ficha [][] tablero;
	private int ancho;
	private int alto;
}
