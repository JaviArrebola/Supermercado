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
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import java.awt.Font;

public class VentanaStock extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaStock;
	private JLabel lblNewLabel;
	private JTextField nombre;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_1;
	private JSpinner codigo;
	private JButton btnNewButton_3;
	private JScrollPane scrollPane;
	private DefaultTableModel tableStock;
	private JLabel lblNewLabel_2;
	private JTextField codigoBarra;
	private JButton btnNewButton_4;
	
	public VentanaStock(ResultSet rs, int longitud) {
		codigoBarra = new JTextField();
		recoverFocus();
		
		tableStock = new DefaultTableModel();
		tableStock.addColumn("ID");
		tableStock.addColumn("Nombre");
		tableStock.addColumn("Cantidad");
		tableStock.addColumn("Precio Unitario");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		setTitle("App Supermercado -------> Ventana Stock");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 121, 0, 0, 0, 0, 0, 0, 31, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.volverStock();
			}
		});
		
		lblNewLabel = new JLabel("Nombre del producto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		nombre = new JTextField();
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.gridx = 2;
		gbc_nombre.gridy = 1;
		contentPane.add(nombre, gbc_nombre);
		nombre.setColumns(10);
		
		btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		codigo = new JSpinner();
		codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigo.setModel(new SpinnerNumberModel(Consultas.minIDProducto(), Consultas.minIDProducto(), Consultas.maxIDProducto(), 1));
		GridBagConstraints gbc_codigo = new GridBagConstraints();
		gbc_codigo.insets = new Insets(0, 0, 5, 5);
		gbc_codigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_codigo.gridx = 5;
		gbc_codigo.gridy = 1;
		contentPane.add(codigo, gbc_codigo);
		
		btnNewButton_3 = new JButton("Buscar");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 6;
		gbc_btnNewButton_3.gridy = 1;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		lblNewLabel_2 = new JLabel("Codigo barra: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 7;
		gbc_lblNewLabel_2.gridy = 1;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		codigoBarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigoBarra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.buscarCodigoBarra(codigoBarra.getText());
				codigoBarra.setText("");
			}
		});
		GridBagConstraints gbc_codigoBarra = new GridBagConstraints();
		gbc_codigoBarra.insets = new Insets(0, 0, 5, 5);
		gbc_codigoBarra.fill = GridBagConstraints.HORIZONTAL;
		gbc_codigoBarra.gridx = 8;
		gbc_codigoBarra.gridy = 1;
		contentPane.add(codigoBarra, gbc_codigoBarra);
		codigoBarra.setColumns(10);
		
		btnNewButton_4 = new JButton("Buscar");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.buscarCodigoBarra(codigoBarra.getText());
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 9;
		gbc_btnNewButton_4.gridy = 1;
		contentPane.add(btnNewButton_4, gbc_btnNewButton_4);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = GridBagConstraints.REMAINDER;
		gbc_scrollPane.gridheight = 1;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		contentPane.add(scrollPane, gbc_scrollPane);

		
				
		
		tablaStock = new JTable(longitud, 4);
		tablaStock.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaStock.setModel(tableStock);
		tablaStock.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		
		scrollPane.setViewportView(tablaStock);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		try {
			while (rs.next()) {
				String productos[] = new String[4];
				productos[0]=rs.getString("id");
				productos[1]=rs.getString("nombre");
				productos[2]=rs.getString("stock");
				productos[3]=rs.getString("precio");
				tableStock.addRow(productos);
				System.out.println(Arrays.toString(productos));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnNewButton_1 = new JButton("CerrarSesión");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionCompra();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 3;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		
	}
	
	public void buscarNombre() throws SQLException {
		Main.buscarNombre(nombre.getText());
	}
	
	public void buscarID() throws SQLException {
		int id = (int) codigo.getValue();
		Main.buscarID(id);
	}
	
	public void recoverFocus() {
		SwingUtilities.invokeLater(() -> {
			codigoBarra.requestFocus();
		});
	}

}