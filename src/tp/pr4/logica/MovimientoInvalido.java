package tp.pr4.logica;

//Clase de excepción para tratar los movimientos no validos.
public class MovimientoInvalido extends Exception {

	public MovimientoInvalido() {
		
		mensaje = "";
		argumento = null;
	}
	
	public MovimientoInvalido(java.lang.String msg) {
		
		mensaje = msg;
		argumento = null;
	}
	
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg) {
		
		mensaje = msg;
		argumento = arg;
	}
	
	public MovimientoInvalido(java.lang.Throwable arg) {
		
		mensaje = "";
		argumento = arg;
	}
	
	public java.lang.String getMensaje() {
		
		return mensaje;
	}
	
	public java.lang.Throwable getArgumento() {
		
		return argumento;
	}
	
	private java.lang.String mensaje;
	private java.lang.Throwable argumento;
	private static final long serialVersionUID = -1732744782528886009L;
}
