package tp.pr4.logica;


public class Partida extends Observable<Partida.Observer> {

	//Interfaz observer para manejar los observadores de la partida.
	public static interface Observer {

		public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno);
		public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente);
		public void onMovimientoIncorrecto(java.lang.String explicacion, TableroInmutable estadoTablero, Ficha turno);
		public void onMovimientoStart(Ficha turno);
		public void onReset(TableroInmutable estadoInicial, Ficha turno);
		public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas);
		public void onUndoNotPossible();
		public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador);
	}
	
	//Contructor de una partida en función de las reglas escogidas
	public Partida(ReglasJuego regla) {

		reglas = regla;
		tablero = reglas.iniciaTablero();
		terminada = false;
		tablas = false;
		ganador = Ficha.VACIA;
		turno = reglas.jugadorInicial();
		ultimoMovimiento = new Movimiento [10];
		
		for (int i = 0; i < 10; i++) {
				
			ultimoMovimiento[i] = null;
		}
		contadorUndo = 0;
	}
	
	//Reinicia la partida con las reglas escogidas y avisa del reinicio a los observers.
	public void reset(ReglasJuego regla) {

		reglas = regla;
		tablero = reglas.iniciaTablero();
		terminada = false;
		tablas = false;
		ganador = Ficha.VACIA;
		turno = reglas.jugadorInicial();
		
		for (int i = 0; i < 10; i++) {
				
			ultimoMovimiento[i] = null;
		}
		contadorUndo = 0;
		
		for (Partida.Observer o : observers) {

			o.onReset(tablero, turno);
		}
		
		turnoIniciado();
	}
	
	/*Este método permite cambiar una ficha VACIA por una BLANCA o NEGRA 
	 * Almacena el movimiento en la lista de 10 ultimos movimientos 
	 * Comprueba si es un movimiento ganador
	 * Cambia el turno.
	 * Avisa a los observers de que se ha iniciado y terminado el movimiento.
	 * En caso de movimiento erroneo, avisa a los observers del mismo.
	 */
	public void ejecutaMovimiento(Movimiento movimiento) throws MovimientoInvalido {

		for (Partida.Observer o : observers) {

			o.onMovimientoStart(turno);
		}
		
		if (movimiento.getJugador() != turno){
			
			for (Partida.Observer o : observers) {

				o.onMovimientoIncorrecto("Turno Incorrecto", tablero, turno);	
			}
			throw new MovimientoInvalido("la ficha no se corresponde con el turno");
		}
		
		if (terminada == true){
			
			for (Partida.Observer o : observers) {

				o.onMovimientoIncorrecto("Partida Terminada", tablero, turno);	
			}
			throw new MovimientoInvalido("La partida ha terminado");
		}
		
		try {
			
			movimiento.ejecutaMovimiento(tablero);
		} catch (MovimientoInvalido mov) {
			
			for (Partida.Observer o : observers) {

				o.onMovimientoIncorrecto(mov.getMensaje(), tablero, turno);
			}
			
			throw mov;
		}
		if (contadorUndo < 10) {
			for (int j = contadorUndo; j > 0; j--) {
				
				ultimoMovimiento[j] = ultimoMovimiento[j - 1];
			}
			contadorUndo++;
		} else {

			for (int j = contadorUndo - 1; j > 0; j--) {
				
				ultimoMovimiento[j] = ultimoMovimiento[j - 1];
			}
		}
		ultimoMovimiento[0] = movimiento;

		ganador = reglas.hayGanador(movimiento, tablero);
		
		if (ganador != Ficha.VACIA) {
			
			terminada = true;
		}
		
		tablas = reglas.tablas(movimiento.getJugador(), tablero);

		if (!terminada && tablas) {
			
			terminada = true;
		}
		
		turno = reglas.siguienteTurno(movimiento.getJugador(), tablero);
		if (!terminada) {

			for (Partida.Observer o : observers) {

				o.onMovimientoEnd(tablero, reglas.siguienteTurno(movimiento.getJugador(), tablero), turno);
			}
			
			turnoIniciado();
		}
	}
	
	/*Implementación del método deshacer.
	 *Recurre a la lista de los 10 últimos movimientos.
	 *Deshace el último y modifica la lista.
	 *Avisa a los observers de que se ha deshecho el último movimiento.
	 *En caso de no poder deshacer más movimientos, avisa a los observers de este hecho.
	 */
	public boolean undo() {

		if (ultimoMovimiento[0] != null) {
			
			ultimoMovimiento[0].undo(tablero);
			for (int j = 0; j < contadorUndo - 1; j++) {
				
				ultimoMovimiento[j] = ultimoMovimiento[j + 1];
			}
			ultimoMovimiento[contadorUndo - 1] = null;
			contadorUndo--;
			
			turno = reglas.siguienteTurno(turno, tablero);
			
			for (Partida.Observer o : observers) {

				o.onUndo(tablero, turno, contadorUndo > 0);
			}
			
			turnoIniciado();
			
			return true;	
		} else {
			
			for (Partida.Observer o : observers) {

				o.onUndoNotPossible();
			}
			
			return false;
		}
	}
	
	//Devuelve el jugador al que le toca jugar.
	public Ficha getTurno() {
		
		return turno;
	}
	
	//Devuelve el ganador de la partida si lo hay. En caso de no haberlo devolverá una Ficha.VACIA
	public Ficha getGanador() {
			
		return ganador;
	}
	
	//Devuelve si la partida ha terminado o no y en caso afirmativo avisa a los observers.
	public boolean isTerminada() {

		if (terminada) {
			
			for (Partida.Observer o : observers) {

				o.partidaTerminada(tablero, ganador);
			}
		}
		
		return terminada;
	}
	
	//Devuelve el tablero.
	public Tablero getTablero() {
		
		return tablero;
	}
	
	//Devuelve las reglas con las que se está jugando.
	public ReglasJuego getReglas() {
		
		return reglas;
	}
	
	//Avisa a los observers de que se ha iniciado un nuevo turno.
	public void turnoIniciado() {
		
		for (Partida.Observer o : observers) {

			o.turnoIniciado(tablero, turno);
		}
	}
	
	private Tablero tablero;
	private boolean terminada;
	private boolean tablas;
	private Ficha ganador;
	private Ficha turno;
	private Movimiento ultimoMovimiento [];
	private int contadorUndo;
	private ReglasJuego reglas;
}
