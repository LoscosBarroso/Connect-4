package tp.pr4.vista.consola;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Partida.Observer;
import tp.pr4.logica.TableroInmutable;

public class VistaConsola implements Observer {

	public VistaConsola() {
		
		iniciado = false;
	}
	
	//Ofrece una expresión gráfica del tablero.
	public void muestraTablero(TableroInmutable tablero) {
		
		for (int i = 1; i <= tablero.getAlto(); i++) {
			System.out.print("|");
			for (int j = 1; j <= tablero.getAncho(); j++) {
				
				System.out.print(tablero.getCasilla(j, i).toString());
			}
			System.out.println("|");
		}
		
		System.out.print("+");
		for (int j = 1; j <= tablero.getAncho(); j++) {
			
			System.out.print("-");
		}
		System.out.println("+");
		System.out.print(" ");
		for (int j = 1; j <= tablero.getAncho(); j++) {
			
			System.out.print(j % 10);
		}
		System.out.println();
		System.out.println();
		
	}

	public void turnoIniciado(TableroInmutable estadoTablero, Ficha turno) {
		
		
	}
	
	//Se ejecuta al finalizar un movimiento para mostrar el tablero actualizado.
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {
		
		muestraTablero(estadoTablero);
		
		switch (siguiente) {
		
		case BLANCA: System.out.println("Juegan blancas"); break;
		case NEGRA: System.out.println("Juegan negras"); break;
		default: break;
		}
	}

	//Se ejecuta al realizar un movimiento no valido para mostrar la causa del error.
	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {
		
		System.err.println(explicacion);
	}

	public void onMovimientoStart(Ficha turno) {

	}

	//Se ejecuta al reiniciar la partida para mostrar el tablero y avisar de que la partida ha sido reiniciada.
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		muestraTablero(estadoInicial);
		
		switch (turno) {
		
		case BLANCA: System.out.println("Juegan blancas"); break;
		case NEGRA: System.out.println("Juegan negras"); break;
		default: break;
		}
		
		if (iniciado) {

			System.out.println("Partida reiniciada.");
		} else {

			iniciado = true;
		}
	}

	//Se ejecuta al deshacer un movimiento para mostrar el nuevo tablero.
	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {
		
		muestraTablero(estadoTablero);
		
		switch (turno) {
		
		case BLANCA: System.out.println("Juegan blancas"); break;
		case NEGRA: System.out.println("Juegan negras"); break;
		default: break;
		}
	}

	//Se ejecuta cuando no se pueden deshacer más movimientos y avisa de que no se pueden deshacer movimientos.
	public void onUndoNotPossible() {

		System.err.println("Imposible deshacer.");
	}

	//Se ejecuta al finalizar la partida para mostrar el tablero final y quien ha ganado.
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {
		
		muestraTablero(tableroFinal);
		
		switch (ganador) {
		
		case BLANCA: System.out.println("Ganan las blancas"); break;
		case NEGRA: System.out.println("Ganan las negras"); break;
		default: System.out.println("Partida terminada en tablas."); break;
		}
	}
	
	private boolean iniciado;
}
