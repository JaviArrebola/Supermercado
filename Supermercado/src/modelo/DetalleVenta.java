package modelo;

public class DetalleVenta {
	private int idVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;

    public DetalleVenta(int idVenta, int idProducto, int cantidad, double precioUnitario) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public int getIdProducto() {
		return idProducto;
	}

	@Override
	public String toString() {
		return "DetalleVenta [idVenta=" + idVenta + ", idProducto=" + idProducto + ", cantidad=" + cantidad
				+ ", precioUnitario=" + precioUnitario + "]";
	}
    
}