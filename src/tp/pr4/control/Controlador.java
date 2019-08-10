package tp.pr4.control;

import tp.pr4.control.ordenes.*;
import tp.pr4.logica.*;

public class Controlador {
	
	//Constructor de la clase Controlador, con sus atributos.
	public Controlador(FactoriaTipoJuego f, Partida p, java.util.Scanner scanner) {

		factoria = f;
		partida = p;
		in = scanner;
		columnas = partida.getTablero().getAncho();
		filas = partida.getTablero().getAlto();
		salir = false;
		jugadores = new Jugador [2];
		jugadores[0] = factoria.creaJugadorHumanoConsola(in);
		jugadores[1] = factoria.creaJugadorHumanoConsola(in);

		orden = new Orden[numOrden];
		orden[0] = new OrdenPoner(in, this);
		orden[1] = new OrdenDeshacer();
		orden[2] = new OrdenReiniciar(this);
		orden[3] = new OrdenJugarC4(in, this);
		orden[4] = new OrdenJugarCo(in, this);
		orden[5] = new OrdenJugarGr(in, this, columnas, filas);
		orden[6] = new OrdenJugarRv(in, this);
		orden[7] = new OrdenCambiarJugador(in, Ficha.BLANCA, "humano", this);
		orden[8] = new OrdenSalir(this);
		orden[9] = new OrdenAyuda(orden, numOrden);
	}
	
	/*Controlador.run() ejecuta las funciones de control de la partida:
	 *Pregunta al usuario por las órdenes.
	 *Comprueba si son válidas y en caso afirmativo llama a las mismas.
	 *Comprueba si la partida ha terminado con o sin ganador.
	 */
	public void run() {

		boolean entendido = false, fin = false;
		String linea;
		
		while (!fin && !salir) {
			
			System.out.print("Qué quieres hacer? ");
			
			linea = in.nextLine();
			linea = linea.toLowerCase();
			
			//Se llama al parser para ver que opción ha sido la seleccionada por el usuario y para ejecutarla.
			entendido = false;
			for (Orden i: orden) {
				
				Orden o = i.parser(linea);
				if (o != null) {
					
					o.ejecutaOrden(partida);
					entendido = true;
					break;
				}
			}
			//Cualquier input no especificado anteriormente dará lugar a que aparezca un mensaje de error sin cambiar a quien le toca jugar.
			if (!entendido) {
				
				System.err.println("No te entiendo.");
			}
			
			fin = partida.isTerminada();
		}
	}
	
	//Devuelve el jugador activo.
	public Jugador getJugador() {
		
		return jugadores[partida.getTurno().ordinal()];
	}
	
	//Cambia el tipo de jugador entre aleatorio y humano.
	public void setJugador(int jugador, int tipo) {
		
		switch(tipo) {
		
		case 0: jugadores[jugador] = factoria.creaJugadorHumanoConsola(in); break;
		case 1: jugadores[jugador] = factoria.creaJugadorAleatorio(); break;
		default: jugadores[jugador] = factoria.creaJugadorHumanoConsola(in); break;
		}
	}
	
	//Cambia la factoria a la del juego seleccionado.
	public void setFactoria(int juego, int cols, int fils) {
		
		switch(juego) {
		
		case 0: factoria = new FactoriaConecta4(); break;
		case 1: factoria = new FactoriaComplica(); break;
		case 2: factoria = new FactoriaGravity(cols, fils); break;
		case 3: factoria = new FactoriaReversi(); break;
		default: factoria = new FactoriaConecta4(); break;
		}
	}
	
	//Modifica el valor de la variable salir para terminar la ejecución del método run();
	public void salir() {
		
		salir = true;
	}
	
	private java.util.Scanner in;
	private FactoriaTipoJuego factoria;
	private Partida partida;
	private Orden [] orden;
	private final int numOrden = 10;
	private int columnas;
	private int filas;
	private boolean salir;
	private Jugador [] jugadores;
}
