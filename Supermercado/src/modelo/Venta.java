
package modelo;

import java.time.LocalDateTime;

public class Venta {
	    private int idVenta;
	    private LocalDateTime fecha;
	    private double total;

	    public Venta(int idVenta, LocalDateTime fecha, double total) {
	        this.idVenta = idVenta;
	        this.fecha = fecha;
	        this.total = total;
	    }

		public LocalDateTime getFecha() {
			return fecha;
		}

		public void setFecha(LocalDateTime fecha) {
			this.fecha = fecha;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public int getIdVenta() {
			return idVenta;
		}

		@Override
		public String toString() {
			return "Venta [idVenta=" + idVenta + ", fecha=" + fecha + ", total=" + total + "]";
		}

}
