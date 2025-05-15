package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			rs = st.executeQuery("SELECT nombre, precio FROM productos WHERE id = " + id + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}