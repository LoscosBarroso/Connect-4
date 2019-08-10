package tp.pr4.logica;

public class ReglasConecta4 implements ReglasJuego {

	public Tablero iniciaTablero() {

		return new Tablero(7, 6);
	}
	
	public Ficha jugadorInicial() {

		return Ficha.BLANCA;
	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero tablero) {

		int col = ultimoMovimiento.getColumna(), fil = ultimoMovimiento.getFila();
		int der = 0, izq = 0, aba = 0, arr = 0, ari = 0, ard = 0, abi = 0, abd = 0;
		
		der = hayGanador(tablero, col, fil, 1, 0);
		izq = hayGanador(tablero, col, fil, -1, 0);
		aba = hayGanador(tablero, col, fil, 0, 1);
		arr = hayGanador(tablero, col, fil, 0, -1);
		abd = hayGanador(tablero, col, fil, 1, 1);
		ari = hayGanador(tablero, col, fil, -1, -1);
		abi = hayGanador(tablero, col, fil, -1, 1);
		ard = hayGanador(tablero, col, fil, 1, -1);
		
		if (der + izq >= 3 || aba + arr >= 3 || abd + ari >= 3 || abi + ard >= 3) {

			return tablero.getCasilla(ultimoMovimiento.getColumna(), ultimoMovimiento.getFila());
		}
		
		return Ficha.VACIA;
	}
	
	public boolean tablas(Ficha ultimoEnPoner, Tablero tablero) {
		
		int i = 1;
		
		while (i <= tablero.getAncho() && tablero.getCasilla(i, 1) != Ficha.VACIA) {
			
			i++;
		}
		if (i > tablero.getAncho()) {
			
			return true;
		} else {
			return false;
		}
	}
	
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero tablero) {

		switch (ultimoEnPoner) {
		
		case BLANCA: return Ficha.NEGRA;
		case NEGRA: return Ficha.BLANCA;
		default: return Ficha.VACIA;
		}
	}

	//Método auxiliar para averiguar si hay ganador,
	private int hayGanador(Tablero tablero, int col, int fil, int sumCol, int sumFil) {

		int i, j;
		int uno = 0;
		
		i = col + sumCol;
		j = fil + sumFil;
		while (i > 0 && i <= tablero.getAncho() && j > 0 && j <= tablero.getAlto() && tablero.getCasilla(i, j) == tablero.getCasilla(col, fil)) {
			
			uno++;
			i += sumCol;
			j += sumFil;
		}
		return uno;
	}
}
