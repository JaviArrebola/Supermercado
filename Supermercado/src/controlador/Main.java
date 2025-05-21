package controlador;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;

import modelo.*;
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
	private static Compra compra = new Compra();
	private static float subtotal;
	private static float precioTotal;

	public static void main(String[] args) {
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}
	
	public static String login(String usuario, String password) {
		Connection conn = ConectorDB.getConexion();
		String encoded="";
		try {
			encoded = SHA256.generateSHA(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try(PreparedStatement ps = conn.prepareStatement("SELECT e.password, e.username FROM empleados e WHERE username = ?")) {
			ps.setString(1, usuario);
			rs = ps.executeQuery();
						
			if(rs.next()) {
				if(encoded.equals(rs.getString("e.password"))) {
					return "Usuario validado";
				}
				else {
					return "Contraseña incorrecta";
				}
			}
			return "Usuario no encontrado";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "Conexión no valida";
		}
		
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
		
		Iterator<Producto> it = compra.getCompra().iterator();
		
		Producto a = it.next();
		
		float precio = (float)a.getPrecioUnitario();
		int unidades = a.getUnidades();
		subtotal = precio*unidades;
		
		while (it.hasNext()) {
			a = it.next();
			
			precio = (float)a.getPrecioUnitario();
			unidades = a.getUnidades();
			subtotal += precio*unidades;
		}
		
		precioTotal = subtotal + (subtotal*21/100);
		precioTotal = (float)Math.round(precioTotal*100)/100;
		
		ventanaPago = new VentanaPago(precioTotal);
		ventanaPago.setVisible(true);
	}
	
	public static void resetearCompra() {
		compra = new Compra();
	}
	
	public static void verStock() {
		if (ventanaStock!=null) {
			ventanaStock.setVisible(false);
		}
		ResultSet rs;
		try {
			rs = Consultas.stock();
			int i = 0;
			for (;rs.next();i++);
			rs = Consultas.stock();
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Consultas.restarStock(compra);
		Consultas.crearVenta(precioTotal);
		Consultas.crearDetalleVenta(compra);
		ResultSet rs = Consultas.getVenta();
		String id="";
		try {
			id = rs.getInt("id_venta")+"";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ventanaRecibo = new VentanaRecibo(compra, id);
		ventanaRecibo.setVisible(true);
	}
	
	public static void volverRecibo() {
		ventanaRecibo.setVisible(false);
		ventanaCompra = new VentanaCompra();
		ventanaCompra.setVisible(true);
	}
	
	public static void buscarNombre(String nombre) {
		try {
			ResultSet rs = Consultas.stockNombre(nombre);
			int i = 0;
			for (;rs.next();i++);
			rs = Consultas.stockNombre(nombre);
			ventanaStock.setVisible(false);
			ventanaStock = null;
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void buscarID(int id) {
		try {
			ResultSet rs = Consultas.stockID(id);
			int i = 0;
			for (;rs.next();i++);
			rs = Consultas.stockID(id);
			ventanaStock.setVisible(false);
			ventanaStock = null;
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Producto anadirProductoACompra(int id) {
		ResultSet rs = Consultas.compraID(id);
		Producto nuevo = null;
		try {
			if(rs.next()) {
				nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
				compra.anadirCompra(nuevo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(compra.toString());
		
		return nuevo;
		
	}
	
	public static Producto anadirProductoACompra(String nombre) {
		ResultSet rs = Consultas.compraNombre(nombre);
		Producto nuevo = null;
		try {
			if(rs.next()) {
				nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
				compra.anadirCompra(nuevo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nuevo;
		
	}
	
	public static void eliminarProductoDeCompra(int fila) {
		compra.eliminarProductoCompra(fila);
		System.out.println(compra.toString());
	}
	
}