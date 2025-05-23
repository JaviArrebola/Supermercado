package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Compra {
	private ArrayList <Producto> compra;
	
	// Le he dejado con constructor por defecto ya que no se que tiene un Arraylist por defecto
	
	public Compra() {
		this.compra = new ArrayList<>();
	}
	
	public ArrayList<Producto> getCompra() {
		return compra;
	}

	public void setCompra(ArrayList<Producto> compra) {
		this.compra = compra;
	}
	
	public void anadirCompra(Producto producto) {
		Iterator <Producto> it = compra.iterator();
		Producto comparar = null;
		int posicion = 0;
		boolean repetido = false;
		while(it.hasNext()) {
			comparar = it.next();
			if(producto.equals(comparar)) {
				repetido = true;
				break;
			}
			posicion++;
		}
		if(repetido == true) {
			comparar.incrementarUnidades();
			compra.set(posicion, comparar);
		}else {
			this.compra.add(producto);
		}
	}
	
	public void eliminarProductoCompra(int posicion) {
		this.compra.remove(posicion);
	}

	@Override
	public String toString() {
		return compra.toString();
	}
	
	public boolean noHayCompra() {
		boolean noVacio = true; // No esta vacio
		if(compra.isEmpty()) {
			noVacio = false; // Esta vacio
		}
		return noVacio;
	}
	
}