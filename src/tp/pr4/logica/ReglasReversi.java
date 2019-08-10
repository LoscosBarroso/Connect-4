package tp.pr4.logica;


public class ReglasReversi implements ReglasJuego {

	public Tablero iniciaTablero() {

		Tablero tablero = new Tablero(8, 8);
		
		tablero.setCasilla(4, 4, Ficha.BLANCA);
		tablero.setCasilla(4, 5, Ficha.NEGRA);
		tablero.setCasilla(5, 4, Ficha.NEGRA);
		tablero.setCasilla(5, 5, Ficha.BLANCA);
		
		return tablero;
	}

	public Ficha jugadorInicial() {

		return Ficha.NEGRA;
	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero tablero) {

		if (partidaFinalizada(tablero)) {
			int blancas = 0;
			int negras = 0;
			
			for (int i = 1; i <= tablero.getAncho(); i++) {

				for (int j = 1; j <= tablero.getAlto(); j++) {

					if (tablero.getCasilla(i, j).equals(Ficha.BLANCA)) {

						blancas++;
					} else if (tablero.getCasilla(i, j).equals(Ficha.NEGRA)) {

						negras++;
					}
				}
			}
			
			if (negras == blancas) {

				return Ficha.VACIA;
			} else if (negras > blancas) {

				return Ficha.NEGRA;
			} else {

				return Ficha.BLANCA;
			}
		} else {
			
			return Ficha.VACIA;
		}
	}

	public boolean tablas(Ficha ultimoEnPoner, Tablero tablero) {

		if (partidaFinalizada(tablero)) {
			int blancas = 0;
			int negras = 0;

			for (int i = 1; i <= tablero.getAncho(); i++) {
				for (int j = 1; j <= tablero.getAlto(); j++) {

					if (tablero.getCasilla(i, j).equals(Ficha.BLANCA)) {

						blancas++;
					} else if (tablero.getCasilla(i, j).equals(Ficha.NEGRA)) {

						negras++;
					}
				}
			}
			return (negras == blancas);
		} else {
			
			return false;
		}
	}

	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero tablero) {
		
		switch (ultimoEnPoner) {
		
		case BLANCA: ultimoEnPoner = Ficha.NEGRA; break;
		case NEGRA: ultimoEnPoner = Ficha.BLANCA; break;
		default: ultimoEnPoner = Ficha.VACIA; break;
		}
		
		int i = 1;
		boolean puedePoner = false;
		while (!puedePoner && i <= tablero.getAncho()) {
			int j = 1;
			while (!puedePoner && j <= tablero.getAlto()) {
				
				puedePoner = UtilsOthello.puedePoner(tablero, i, j, ultimoEnPoner);
				
				j++;
			}
			i++;
		}
		
		if(!puedePoner) {

			switch (ultimoEnPoner) {
			
			case BLANCA: ultimoEnPoner = Ficha.NEGRA; break;
			case NEGRA: ultimoEnPoner = Ficha.BLANCA; break;
			default: ultimoEnPoner = Ficha.VACIA; break;
			}
		}
		return ultimoEnPoner;
	}

	//Método auxiliar para averiguar si la partida ha acabado,
	private boolean partidaFinalizada(Tablero tablero) {
		
		int i = 1;
		boolean puedePoner = false;
		while (!puedePoner && i <= tablero.getAncho()) {
			int j = 1;
			while (!puedePoner && j <= tablero.getAlto()) {
				
				puedePoner = puedePoner || UtilsOthello.puedePoner(tablero, i, j, Ficha.BLANCA);
				puedePoner = puedePoner || UtilsOthello.puedePoner(tablero, i, j, Ficha.NEGRA);
				
				j++;
			}
			i++;
		}
		
		return !puedePoner;
	}
}
