package tp.pr4.control;

public class JugadorHumanoSwing implements JugadorSwing {

	public JugadorHumanoSwing(ControladorSwing control) {
		
		controlador = control;
	}

	public void tableroPulsado(int columna, int fila) {
		
		controlador.ordenPoner(columna, fila);
	}

	public Jugadores getTipoJugador() {
		
		return Jugadores.Humano;
	}
	
	private ControladorSwing controlador;
}
