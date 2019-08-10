package tp.pr4.logica;

public class ReglasComplica implements ReglasJuego {
	
	public Tablero iniciaTablero() {
		
		return new Tablero(4, 7);
	}
	
	public Ficha jugadorInicial() {
		
		return Ficha.BLANCA;
	}
	
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero tablero) {

		int col = ultimoMovimiento.getColumna(), fil = ultimoMovimiento.getFila();
		int der = 0, izq = 0, aba = 0, arr = 0, ari = 0, ard = 0, abi = 0, abd = 0;
		int ganaBlancas = 0, ganaNegras = 0;
		
		while (fil <= tablero.getAlto() && tablero.getCasilla(col, fil) != Ficha.VACIA) {

			der = 0;
			izq = 0;
			aba = 0;
			arr = 0;
			ari = 0;
			ard = 0;
			abi = 0;
			abd = 0;

			der = hayGanador(tablero, col, fil, 1, 0);
			izq = hayGanador(tablero, col, fil, -1, 0);
			
			if (der + izq >= 3) {
				
				switch(tablero.getCasilla(col, fil)) {
				
				case BLANCA: ganaBlancas++; break;
				case NEGRA: ganaNegras++; break;
				default: ;
				}
			}

			aba = hayGanador(tablero, col, fil, 0, 1);
			arr = hayGanador(tablero, col, fil, 0, -1);
			
			if (aba + arr >= 3) {
				
				switch(tablero.getCasilla(col, fil)) {
				
				case BLANCA: ganaBlancas++; break;
				case NEGRA: ganaNegras++; break;
				default: ;
				}
			}

			abd = hayGanador(tablero, col, fil, 1, 1);
			ari = hayGanador(tablero, col, fil, -1, -1);
			
			if (abd + ari >= 3) {
				
				switch(tablero.getCasilla(col, fil)) {
				
				case BLANCA: ganaBlancas++; break;
				case NEGRA: ganaNegras++; break;
				default: ;
				}
			}

			abi = hayGanador(tablero, col, fil, -1, 1);
			ard = hayGanador(tablero, col, fil, 1, -1);
			
			if (abi + ard >= 3) {
				
				switch(tablero.getCasilla(col, fil)) {
				
				case BLANCA: ganaBlancas++; break;
				case NEGRA: ganaNegras++; break;
				default: ;
				}
			}
			
			fil++;
		}
		
		if (ganaBlancas > 0 && ganaNegras == 0) {
			
			return Ficha.BLANCA;
		} else if (ganaNegras > 0 && ganaBlancas == 0) {
			
			return Ficha.NEGRA;
		} else {

			return Ficha.VACIA;	
		}
	}
	
	public boolean tablas(Ficha ultimoEnPoner, Tablero tablero) {
		
		return false;
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
