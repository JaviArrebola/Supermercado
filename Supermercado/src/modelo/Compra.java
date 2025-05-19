package modelo;

import java.util.ArrayList;

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
		this.compra.add(producto);
	}
	
	public void eliminarProductoCompra(int posicion) {
		this.compra.remove(posicion);
	}

	@Override
	public String toString() {
		return compra.toString();
	}
	
}