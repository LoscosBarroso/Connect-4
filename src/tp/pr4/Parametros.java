package tp.pr4;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaReversi;
import tp.pr4.control.FactoriaTipoJuego;

@SuppressWarnings("deprecation")
public class Parametros {
	
	//Clase interna que sirve para devolver los resultados del parser.
	//Se usa en Main();
	public class Modo {
		
		public Modo(FactoriaTipoJuego fact, boolean ventana) {
			
			factoria = fact;
			windowed = ventana;
		}
		
		public FactoriaTipoJuego getFactoria() {
			
			return factoria;
		}
		
		public boolean windowed() {
			
			return windowed;
		}
		
		private FactoriaTipoJuego factoria;
		private boolean windowed;
	}

	//Crea un ojeto Parametros que sirve para interpretar os comandos de inicializacion.
	//Se usa en Main();
	@SuppressWarnings("static-access")
	public Parametros() {
		
		parser = new BasicParser();
		opciones = new Options();

		opciones.addOption(OptionBuilder.hasArg().withArgName("game").withDescription("Tipo de juego (c4, co, gr, rv). Por defecto, c4.").withLongOpt("game").create("g"));
		opciones.addOption("h","help",false, "Muestra esta ayuda.");
		opciones.addOption(OptionBuilder.hasArg().withArgName("tipo").withDescription("Tipo de interfaz (console, window). Por defecto, console.").withLongOpt("ui").create("u"));
		opciones.addOption(OptionBuilder.hasArg().withArgName("columnNumber").withDescription("Número de columnas del tablero (sólo para Gravity). Por defecto, 10.").withLongOpt("tamX").create("x"));
		opciones.addOption(OptionBuilder.hasArg().withArgName("rowNumber").withDescription("Número de filas del tablero (sólo para Gravity). Por defecto, 10.").withLongOpt("tamY").create("y"));
	}
	
	//Función que interpreta los comandos de inicialización y los devuelve a través de un objeto Modo.
	//Se usa en Main();
	public Modo parser (String[] args) throws Exception {

		FactoriaTipoJuego factoria = new FactoriaConecta4();
		boolean window = false;
		
		try{
			CommandLine commandLine = parser.parse(opciones, args);
			
			String game = commandLine.getOptionValue('g');
			String [] otros = commandLine.getArgs();
			String ui = commandLine.getOptionValue('u');
			String tamX = commandLine.getOptionValue('x');
			String tamY = commandLine.getOptionValue('y');
			
			if (commandLine.hasOption("h")) {
				
				HelpFormatter ayuda = new HelpFormatter();
				ayuda.printHelp("tp.pr4.Main",opciones,true);
				factoria = null;
			}
			
			if (commandLine.hasOption("game")) {
				
				if (otros.length != 0) {
					
					String error = "Argumentos no entendidos:";
					
					for (String i: otros) {
						
						error += " " + i;
					}
					
					throw new ParseException(error);	
				} else if (game.equals("c4")) {
					
					factoria = new FactoriaConecta4();
				} else if (game.equals("co")) {
					
					factoria = new FactoriaComplica();
				} else if (game.equals("gr")) {
					
					int X = 10, Y = 10;
					
					if (tamX != null) {
						
						X = Integer.parseInt(tamX);
					}
					
					if (tamY != null) {
						
						Y = Integer.parseInt(tamY);
					}
					
					factoria = new FactoriaGravity(X, Y);
				} else if (game.equals("rv")) {
					
					factoria = new FactoriaReversi();
				} else if (game != null){
					
					throw new ParseException("Juego '" + game + "' incorrecto.");
				}
			}
			
			if (commandLine.hasOption("u")) {

				if (ui.equals("console")) {
					
					window = false;
				} else if (ui.equals("window")) {
					
					window = true;
				} else if (ui.length() != 0) {
				
					throw new ParseException("Interfaz '" + ui + "' incorrecta");
				}
			}
			
			return new Modo(factoria, window);
			
		} catch(ParseException e){
			
			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			throw new Exception();
		} catch(NumberFormatException e) {
			
			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			throw new Exception();
		}
	}
	
	CommandLineParser parser;
	Options opciones;
}
