package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Iterator;

public class Consultas {

	public static ResultSet stock() throws SQLException {
		Connection con = ConectorDB.getConexion();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT id, precio, nombre, stock FROM productos");
		return rs;
	}
	
	public static ResultSet stockNombre(String nombre) throws SQLException {
		Connection con = ConectorDB.getConexion();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT id, precio, nombre, stock FROM productos WHERE nombre LIKE '%"+nombre+"%'");
		return rs;
	}
	
	public static ResultSet stockID(int id) throws SQLException {
		Connection con = ConectorDB.getConexion();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT id, precio, nombre, stock FROM productos WHERE id = "+id+"");
		return rs;
	}
	
	public static ResultSet compraID(int id) { // Esto ayuda a la busqueda de un producto para la VentanaCompra
		Connection con = ConectorDB.getConexion();
		Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT id, nombre, precio FROM productos WHERE id = " + id + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet compraNombre(String nombre) { // Esto ayuda a la busqueda de un producto para la VentanaCompra
		Connection con = ConectorDB.getConexion();
		Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT id, nombre, precio FROM productos WHERE nombre = '" + nombre + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void restarStock(Compra compra) {
		Connection con = ConectorDB.getConexion();
		try {
			Statement st = con.createStatement();
			Iterator<Producto> it = compra.getCompra().iterator();
			Producto a = it.next();
			st.executeUpdate("UPDATE productos SET stock = stock - "+a.getUnidades()+" WHERE id = "+a.getId()+";");
			while (it.hasNext()) {
				a = it.next();
				st.executeUpdate("UPDATE productos SET stock = stock - "+a.getUnidades()+" WHERE id = "+a.getId()+";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void crearVenta(float precio) {
		Connection con = ConectorDB.getConexion();
		try {
			Statement st = con.createStatement();

			st.executeUpdate("INSERT INTO ventas(fecha, total) VALUES('"+LocalDateTime.now()+"', "+precio+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void crearDetalleVenta(Compra compra) {
		Connection con = ConectorDB.getConexion();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT max(id_venta) AS id_venta FROM ventas;");
			Iterator<Producto> it = compra.getCompra().iterator();
			Producto a = it.next();
			rs.next();
			int id = rs.getInt("id_venta");
			st.executeUpdate("INSERT INTO detalle_venta VALUES ("+id+", "+a.getId()+", "+a.getUnidades()+", "+a.getPrecioUnitario()+");");
			while (it.hasNext()) {
				a = it.next();
				st.executeUpdate("INSERT INTO detalle_venta VALUES ("+id+", "+a.getId()+", "+a.getUnidades()+", "+a.getPrecioUnitario()+");");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}