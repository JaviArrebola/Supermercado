package controlador;

import vista.VentanaCompra;
import vista.VentanaInicio;
import vista.VentanaPago;
import vista.VentanaRecibo;
import vista.VentanaStock;

public class Main {
	private static VentanaInicio ventanaInicio;
	private static VentanaCompra ventanaCompra;
	private static VentanaStock ventanaStock;
	private static VentanaRecibo ventanaRecibo;
	private static VentanaPago ventanaPago;

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}
	
	public static void inicioSesion() {
		ventanaInicio.setVisible(false);
		ventanaCompra = new VentanaCompra();
		ventanaCompra.setVisible(true);
	}
	
	public static void finalizarCompra() {
		ventanaCompra.setVisible(false);
		if (ventanaStock!=null) {
		ventanaStock.setVisible(false);
		}
		ventanaPago = new VentanaPago();
		ventanaPago.setVisible(true);
	}
	
	public static void verStock() {
		ventanaStock = new VentanaStock();
		ventanaStock.setVisible(true);
	}
	
	public static void sesionCompra() {
		ventanaCompra.setVisible(false);
		if (ventanaStock!=null) {
		ventanaStock.setVisible(false);
		}
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}
	
	public static void sesionPago() {
		ventanaPago.setVisible(false);
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);		
	}
	
	public static void sesionRecibo() {
		ventanaRecibo.setVisible(false);
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);		
	}
	
	public static void volverStock() {
		ventanaStock.setVisible(false);
	}
	
	public static void ticket() {
		ventanaPago.setVisible(false);
		ventanaRecibo = new VentanaRecibo();
		ventanaRecibo.setVisible(true);
	}
	
	public static void volverRecibo() {
		ventanaRecibo.setVisible(false);
		ventanaCompra = new VentanaCompra();
		ventanaCompra.setVisible(true);
	}

}