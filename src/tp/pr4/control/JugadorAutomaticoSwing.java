package tp.pr4.control;

public class JugadorAutomaticoSwing implements JugadorSwing {

	public void tableroPulsado(int columna, int fila) {
	
		//Está vacio ya que al pulsar una casilla cuando le toca al jugador automático, no se debe hacer nada.
	}

	public Jugadores getTipoJugador() {
		
		return Jugadores.Automatico;
	}
}
