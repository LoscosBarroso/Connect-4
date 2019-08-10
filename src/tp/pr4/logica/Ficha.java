package tp.pr4.logica;

//Enumerado con los tres tipos de ficha y un método para tener una expresión en texto de ellos.
public enum Ficha {

	BLANCA, NEGRA, VACIA;
	
	public String toString() {
		
		switch (this) {
		
		case BLANCA: return "O";
		case NEGRA: return "X";
		default: return " ";
		}
	}
}