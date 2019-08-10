package tp.pr4;
import java.util.Scanner;

import tp.pr4.control.*;
import tp.pr4.logica.*;
import tp.pr4.vista.consola.VistaConsola;
import tp.pr4.vista.swing.PDerecha;
import tp.pr4.vista.swing.PDimensiones;
import tp.pr4.vista.swing.PIzquierda;
import tp.pr4.vista.swing.PJuego;
import tp.pr4.vista.swing.PJugadores;
import tp.pr4.vista.swing.POpciones;
import tp.pr4.vista.swing.PPartida;
import tp.pr4.vista.swing.PTablero;
import tp.pr4.vista.swing.VistaSwing;

public class Main {

	public static void main (String[] args) {
		
		Parametros parametros = new Parametros();
		
		//Se crea objeto Modo que recibirá los resultados del parser de Parametros.
		Parametros.Modo modo = null;
		
		try {

			modo = parametros.parser(args); //Se reciben los resultados del parser de Parametros.
			//Para la GUI
			if (modo.windowed()) {
				
				//Crea las reglas del juego.
				ReglasJuego reglas = modo.getFactoria().creaReglas();
				
				//Crea la partida.
				Partida partida = new Partida(reglas);
				
				//Lanza el controlador de la partida.
				ControladorSwing controlador = new ControladorSwing(modo.getFactoria(), partida);

				//Crea la vista
				PDimensiones pDimensiones = new PDimensiones(controlador);
				PJuego pJuego = new PJuego(controlador, pDimensiones);
				PJugadores pJugadores = new PJugadores(controlador);
				POpciones pOpciones = new POpciones(controlador);
				PDerecha pDerecha = new PDerecha(controlador, pJuego, pJugadores, pOpciones);
				PTablero pTablero = new PTablero(controlador);
				PPartida pPartida = new PPartida(pTablero);
				PIzquierda pIzquierda = new PIzquierda(controlador, pPartida);
				VistaSwing vista = new VistaSwing(pIzquierda, pDerecha);
				
				//Añade la vista como observadora de la partida
				partida.addObserver(pDimensiones);
				partida.addObserver(pJuego);
				partida.addObserver(pJugadores);
				partida.addObserver(pOpciones);
				partida.addObserver(pDerecha);
				partida.addObserver(pTablero);
				partida.addObserver(pPartida);
				partida.addObserver(pIzquierda);
				partida.addObserver(vista);

				//Muestra por primera vez el tablero.
				controlador.ordenCambiar(pDimensiones.getJuego(), partida.getTablero().getAncho(), partida.getTablero().getAlto());
			//Para la consola
			} else {
				
				//Habilita el scanner.
				Scanner in = new Scanner(System.in);
				
				//Crea las reglas del juego.
				ReglasJuego reglas = modo.getFactoria().creaReglas();
				
				//Crea la partida.
				Partida partida = new Partida(reglas);
				
				//Lanza el controlador de la partida.
				Controlador controlador = new Controlador(modo.getFactoria(), partida, in);

				//Crea la vista
				VistaConsola vista = new VistaConsola();

				//Añade la vista como observadora de la partida
				partida.addObserver(vista);
				
				//Muestra por primera vez el tablero.
				partida.reset(reglas);
				
				//Inicia el juego.
				controlador.run();
				
				//Cierra el scanner.
				in.close();	
			}
		} catch (Exception e) {
			 //Si hay error en el parser, salida con valor 1.
			System.exit(1);
		}
	}
}
