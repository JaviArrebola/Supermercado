package modelo;

public class Producto {
	private int id;
    private String nombre;
    private double precio;
    private int unidades;

    public Producto() {}

    public Producto(int id, String nombre, double precio, int unidades) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int stock) {
		this.unidades = unidades;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", unidades=" + unidades
				+ "]";
	}
    
}