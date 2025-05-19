package modelo;

public class Producto {
	private int id;
    private String nombre;
    private double precioUnitario;
    private int unidades;

    public Producto() {}

    public Producto(int id, String nombre, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.unidades = 1;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getId() {
		return id;
	}
	
	public void incrementarUnidades() {
		this.unidades += unidades;
	}
	
	public boolean equals(Producto otro) {
		boolean resultado = false;
		if(this.id == otro.id) {
			resultado = true;
		}
		return resultado;
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio unitario=" + precioUnitario + ", unidades=" + unidades
				+ "]";
	}
    
}