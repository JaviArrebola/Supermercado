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
	
	public void anadirCompra(Producto producto, int stock) {
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
		if(repetido == true) { // Si el producto ya esta en la compra
			
			if(comparar.getUnidades() < stock) { // Si las unidades deseadas es menor que lo que hay en el stock
				comparar.incrementarUnidades();
				compra.set(posicion, comparar);
			}else { // Si las unidades deseadas es mayor que lo que hay en el stock
				System.out.println("Se ha superado la cantidad de stock del producto. No se puede añadir mas de ese producto.");
			}

		}else { // Si el producto aun no esta en la compra
			if(stock > 0) { // Si hay al menos una unidad del producto deseado
				this.compra.add(producto);
			}else { // Si no hay unidades del producto deseado
				System.out.println("Se ha superado la cantidad de stock del producto. No se puede añadir mas de ese producto.");
			}
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