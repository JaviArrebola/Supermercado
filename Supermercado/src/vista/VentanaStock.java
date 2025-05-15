package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;
import modelo.Consultas;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaStock extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaStock;
	private JLabel lblNewLabel;
	private JTextField nombre;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_1;
	private JTextField codigo;
	private JButton btnNewButton_3;
	private int longitud=0;
	private ResultSet rs=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaStock frame = new VentanaStock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaStock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 150, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.volverStock();
			}
		});
		
		lblNewLabel = new JLabel("Nombre del producto:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombre.gridx = 2;
		gbc_nombre.gridy = 1;
		contentPane.add(nombre, gbc_nombre);
		nombre.setColumns(10);
		
		btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					buscarNombre();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 1;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		lblNewLabel_1 = new JLabel("Codigo:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		codigo = new JTextField();
		GridBagConstraints gbc_codigo = new GridBagConstraints();
		gbc_codigo.insets = new Insets(0, 0, 5, 5);
		gbc_codigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_codigo.gridx = 5;
		gbc_codigo.gridy = 1;
		contentPane.add(codigo, gbc_codigo);
		codigo.setColumns(10);
		
		btnNewButton_3 = new JButton("Buscar");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					buscarID();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 6;
		gbc_btnNewButton_3.gridy = 1;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		try {
			rs = Consultas.stock();for (longitud=1; rs.next(); longitud++);
			
			rs = Consultas.stock();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		tablaStock = new JTable(longitud, 4);
		GridBagConstraints gbc_tablaStock = new GridBagConstraints();
		gbc_tablaStock.gridwidth = 4;
		gbc_tablaStock.insets = new Insets(0, 0, 5, 5);
		gbc_tablaStock.fill = GridBagConstraints.BOTH;
		gbc_tablaStock.gridx = 1;
		gbc_tablaStock.gridy = 2;
		contentPane.add(tablaStock, gbc_tablaStock);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		tablaStock.setValueAt("ID", 0, 0);
		tablaStock.setValueAt("Nombre", 0, 1);
		tablaStock.setValueAt("Cantidad", 0, 2);
		tablaStock.setValueAt("Precio unidad", 0, 3);
		
		try {
			for (int i = 1; rs.next(); i++) {
				tablaStock.setValueAt(rs.getInt("id"), i, 0);
				tablaStock.setValueAt(rs.getString("nombre"), i, 1);
				tablaStock.setValueAt(rs.getInt("stock"), i, 2);
				tablaStock.setValueAt(rs.getFloat("precio"), i, 3);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnNewButton_1 = new JButton("CerrarSesión");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionCompra();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 3;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}
	
	public void buscarNombre() throws SQLException {
		rs = Consultas.stockNombre(nombre.toString());
		for (int i = 1; rs.next(); i++) {
			tablaStock.setValueAt(rs.getInt("id"), i, 0);
			tablaStock.setValueAt(rs.getString("nombre"), i, 1);
			tablaStock.setValueAt(rs.getInt("stock"), i, 2);
			tablaStock.setValueAt(rs.getFloat("precio"), i, 3);
		}
	}
	
	public void buscarID() throws SQLException {
		rs = Consultas.stockID(Integer.parseInt(codigo.toString()));
		for (int i = 1; rs.next(); i++) {
			tablaStock.setValueAt(rs.getInt("id"), i, 0);
			tablaStock.setValueAt(rs.getString("nombre"), i, 1);
			tablaStock.setValueAt(rs.getInt("stock"), i, 2);
			tablaStock.setValueAt(rs.getFloat("precio"), i, 3);
		}
	}

}