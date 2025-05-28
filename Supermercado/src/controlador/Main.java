package controlador;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import modelo.*;
import vista.VentanaCompra;
import vista.VentanaInicio;
import vista.VentanaPago;
import vista.VentanaRecibo;
import vista.VentanaStock;
import modelo.GeneradorPdf;

public class Main {
	// Declaración de ventanas de la interfaz gráfica y variables de la comp
	private static VentanaInicio ventanaInicio;
	private static VentanaCompra ventanaCompra;
	private static VentanaStock ventanaStock;
	private static VentanaRecibo ventanaRecibo;
	private static VentanaPago ventanaPago;
	private static Compra compra = new Compra();
	private static float subtotal;
	private static float precioTotal;

	public static void main(String[] args) {
		// Se inicia la aplicación mostrando la ventana de inicio
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}

	// Método para validar las credenciales del usuario
	public static String login(String usuario, String password) {
		Connection conn = ConectorDB.getConexion();
		String encoded = "";
		try {
			// Encriptar la contraseña ingresada
			encoded = SHA256.generateSHA(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try (PreparedStatement ps = conn
				.prepareStatement("SELECT e.password, e.username FROM empleados e WHERE username = ?")) {
			ps.setString(1, usuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				// Comparar contraseña encriptada con la almacenada
				if (encoded.equals(rs.getString("e.password"))) {
					return "Usuario validado";
				} else {
					return "Contraseña incorrecta";
				}
			}
			return "Usuario no encontrado";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Conexión no valida";
		}

	}

	// Método que muestra la ventana de compra al iniciar sesión correctamente
	public static void inicioSesion() {
		ventanaInicio.setVisible(false);
		ventanaCompra = new VentanaCompra();
		ventanaCompra.setVisible(true);
	}

	// Finaliza la compra si hay productos agregados
	public static boolean finalizarCompra() {
		boolean finalizar = false; // Esto ayuda a ventanaCompra
		if (compra.noHayCompra()) {
			finalizar = true;

			ventanaCompra.setVisible(false);
			if (ventanaStock != null) {
				ventanaStock.setVisible(false);
			}

			// Calcular subtotal
			Iterator<Producto> it = compra.getCompra().iterator();
			Producto a = it.next();
			float precio = (float) a.getPrecioUnitario();
			int unidades = a.getUnidades();
			subtotal = precio * unidades;

			while (it.hasNext()) {
				a = it.next();
				precio = (float) a.getPrecioUnitario();
				unidades = a.getUnidades();
				subtotal += precio * unidades;
			}
			
			subtotal = (float) Math.round(subtotal * 100) / 100;
			precioTotal = subtotal + (subtotal * 21 / 100);
			precioTotal = (float) Math.round(precioTotal * 100) / 100;

			// Mostrar ventana de pago
			ventanaPago = new VentanaPago(precioTotal);
			ventanaPago.setVisible(true);
		} else {
			System.out.println("No se puede finalizar una compra sin añadir productos.");
			System.out.println("Porfavor, introduzca productos a la compra.");
		}

		return finalizar;
	}

	// Resetea la compra actual
	public static void resetearCompra() {
		compra = new Compra();
	}

	// Muestra la ventana con el stock de productos
	public static void verStock() {
		if (ventanaStock != null) {
			ventanaStock.setVisible(false);
		}
		ResultSet rs;
		try {
			rs = Consultas.stock();
			int i = 0;
			for (; rs.next(); i++)
				;
			rs = Consultas.stock();
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Cierra sesión y vuelve a la pantalla de inicio desde la compra
	public static void sesionCompra() {
		ventanaCompra.setVisible(false);
		if (ventanaStock != null) {
			ventanaStock.setVisible(false);
		}
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}

	// Cierra sesión desde la pantalla de pago
	public static void sesionPago() {
		ventanaPago.setVisible(false);
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}

	// Cierra sesión desde la pantalla del recibo
	public static void sesionRecibo() {
		ventanaRecibo.setVisible(false);
		ventanaInicio = new VentanaInicio();
		ventanaInicio.setVisible(true);
	}

	// Oculta la ventana de stock
	public static void volverStock() {
		ventanaStock.setVisible(false);
	}

	// Registra la venta, resta el stock y muestra el recibo
	public static void ticket() {
		ventanaPago.setVisible(false);
		Consultas.restarStock(compra);
		Consultas.crearVenta(precioTotal);
		Consultas.crearDetalleVenta(compra);
		ResultSet rs = Consultas.getVenta();
		String id = "";
		try {
			id = rs.getInt("id_venta") + "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ventanaRecibo = new VentanaRecibo(compra, id, subtotal, precioTotal);
		ventanaRecibo.setVisible(true);
	}

	// Regresa al menú de compra desde el recibo
	public static void volverRecibo() {
		ventanaRecibo.setVisible(false);
		ventanaCompra = new VentanaCompra();
		ventanaCompra.setVisible(true);
	}

	// Igual que ticket(), pero también genera un PDF del recibo
	public static void ticketpdf() {
		ventanaPago.setVisible(false);
		Consultas.restarStock(compra);
		Consultas.crearVenta(precioTotal);
		Consultas.crearDetalleVenta(compra);
		ResultSet rs = Consultas.getVenta();
		String id = "";
		try {
			id = rs.getInt("id_venta") + "";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean generado = GeneradorPdf.crearReciboPDF("recibo_venta_" + id + ".pdf", compra.getCompra(), id, subtotal,
				precioTotal);

		if (generado) {
			System.out.println("PDF generado correctamente.");
		} else {
			System.err.println("Error al generar el PDF.");
		}

		ventanaRecibo = new VentanaRecibo(compra, id, subtotal, precioTotal);
		ventanaRecibo.setVisible(true);
	}

	// Método para buscar productos en el stock por nombre
	public static void buscarNombre(String nombre) {
		try {
			ResultSet rs = Consultas.stockNombre(nombre);
			int i = 0;
			for (; rs.next(); i++)
				;
			rs = Consultas.stockNombre(nombre);
			ventanaStock.setVisible(false);
			ventanaStock = null;
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para buscar productos en el stock por ID
	public static void buscarID(int id) {
		try {
			ResultSet rs = Consultas.stockID(id);
			int i = 0;
			for (; rs.next(); i++)
				;
			rs = Consultas.stockID(id);
			ventanaStock.setVisible(false);
			ventanaStock = null;
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para buscar productos en el stock por código de barras
	public static void buscarCodigoBarra(String codigoBarra) {
		try {
			ResultSet rs = Consultas.stockCodigoBarra(codigoBarra);
			int i = 0;
			for (; rs.next(); i++)
				;
			rs = Consultas.stockCodigoBarra(codigoBarra);
			ventanaStock.setVisible(false);
			ventanaStock = null;
			ventanaStock = new VentanaStock(rs, i);
			ventanaStock.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Métodos para añadir productos a la compra usando diferentes criterios
	public static Producto anadirProductoACompra(int id) { // Usando id
		ResultSet rs = Consultas.compraID(id);
		Producto nuevo = anadir(rs);
		return nuevo;
	}

	public static Producto anadirProductoACompraCodigoBarra(String codigoBarra) { // Usando codigo de barras
		ResultSet rs = Consultas.compraCodigoBarra(codigoBarra);
		Producto nuevo = anadir(rs);
		return nuevo;
	}

	public static Producto anadirProductoACompra(String nombre) { // Usando nombre del producto
		ResultSet rs = Consultas.compraNombre(nombre);
		Producto nuevo = anadir(rs);
		return nuevo;
	}

	// Elimina un producto de la lista de compra
	public static void eliminarProductoDeCompra(int fila) {
		compra.eliminarProductoCompra(fila);
		System.out.println(compra.toString());
	}

	// Vuelve de la ventana de pago a la de compra
	public static void volverPago() {
		ventanaPago.setVisible(false);
		ventanaCompra.setVisible(true);
	}

	// Método privado auxiliar para añadir un producto al objeto compra desde ResultSet
	private static Producto anadir(ResultSet rs) {
		Producto nuevo = null;
		try {
			if (rs.next()) {
				nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
				int stock = Consultas.compraStock(nuevo.getId());
				compra.anadirCompra(nuevo, stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Compra actual: " + compra.toString());

		return nuevo;
	}
}