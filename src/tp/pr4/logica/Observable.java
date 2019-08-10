package tp.pr4.logica;

import java.util.LinkedList;

//Clase para administrar observers.
public class Observable<T> {
	
	//Crea la lista de observers
	public Observable() {
		
		observers = new LinkedList<T>();
	}
	
	//Añade un observer que no este en la lista
	public void addObserver(T observer) {

		if (!observers.isEmpty()) {

			boolean existe = observers.contains(observer);
			
			if (!existe) {
				
				observers.add(observer);
			}
		} else {

			observers.add(observer);
		}
	}
	
	//Elimina el observer escogido si es que está dentro de la lista.
	public void removeObserver(T observer) {
		if (!observers.isEmpty()) {
			
			observers.remove(observer);
		}
	}
	
	protected java.util.List<T> observers;
}
