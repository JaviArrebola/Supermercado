package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class VentanaCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldNombreProducto;
	private JTextField fieldCodigoProducto;
	private JTable tableListaCompra;

	public VentanaCompra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		setTitle("App Supermercado");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelIntroducionManual = new JPanel();
		GridBagConstraints gbc_panelIntroducionManual = new GridBagConstraints();
		gbc_panelIntroducionManual.insets = new Insets(0, 0, 5, 5);
		gbc_panelIntroducionManual.gridx = 1;
		gbc_panelIntroducionManual.gridy = 1;
		contentPane.add(panelIntroducionManual, gbc_panelIntroducionManual);
		GridBagLayout gbl_panelIntroducionManual = new GridBagLayout();
		gbl_panelIntroducionManual.columnWidths = new int[]{0, 124, 0, 0, 0, 121};
		gbl_panelIntroducionManual.rowHeights = new int[]{0, 0, 0};
		gbl_panelIntroducionManual.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelIntroducionManual.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelIntroducionManual.setLayout(gbl_panelIntroducionManual);
		
		JLabel lblNombreProducto = new JLabel("Nombre del producto: ");
		GridBagConstraints gbc_lblNombreProducto = new GridBagConstraints();
		gbc_lblNombreProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombreProducto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreProducto.gridx = 0;
		gbc_lblNombreProducto.gridy = 0;
		panelIntroducionManual.add(lblNombreProducto, gbc_lblNombreProducto);
		
		fieldNombreProducto = new JTextField();
		GridBagConstraints gbc_fieldNombreProducto = new GridBagConstraints();
		gbc_fieldNombreProducto.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNombreProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNombreProducto.gridx = 1;
		gbc_fieldNombreProducto.gridy = 0;
		panelIntroducionManual.add(fieldNombreProducto, gbc_fieldNombreProducto);
		fieldNombreProducto.setColumns(10);
		
		JButton btnNombreProducto = new JButton("Añadir");
		GridBagConstraints gbc_btnNombreProducto = new GridBagConstraints();
		gbc_btnNombreProducto.insets = new Insets(0, 0, 5, 5);
		gbc_btnNombreProducto.gridx = 2;
		gbc_btnNombreProducto.gridy = 0;
		panelIntroducionManual.add(btnNombreProducto, gbc_btnNombreProducto);
		
		JLabel lblCodigoProducto = new JLabel("Codigo del producto: ");
		GridBagConstraints gbc_lblCodigoProducto = new GridBagConstraints();
		gbc_lblCodigoProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCodigoProducto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoProducto.gridx = 4;
		gbc_lblCodigoProducto.gridy = 0;
		panelIntroducionManual.add(lblCodigoProducto, gbc_lblCodigoProducto);
		
		fieldCodigoProducto = new JTextField();
		GridBagConstraints gbc_fieldCodigoProducto = new GridBagConstraints();
		gbc_fieldCodigoProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldCodigoProducto.insets = new Insets(0, 0, 5, 5);
		gbc_fieldCodigoProducto.gridx = 5;
		gbc_fieldCodigoProducto.gridy = 0;
		panelIntroducionManual.add(fieldCodigoProducto, gbc_fieldCodigoProducto);
		fieldCodigoProducto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tableListaCompra = new JTable();
		scrollPane.setViewportView(tableListaCompra);
		tableListaCompra.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Cantidad", "Precio unitario", "Eliminar"
			}
		));
		
		JButton btnCodigoProducto = new JButton("Añadir");
		btnCodigoProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.anadirProductoACompra(Integer.parseInt(fieldCodigoProducto.getText()));
				// tableListaCompra.addRow(new Object[][] {"Hola", "Hola2", "HOLAAAAAAAA", "No"});
			}
		});
		
		GridBagConstraints gbc_btnCodigoProducto = new GridBagConstraints();
		gbc_btnCodigoProducto.insets = new Insets(0, 0, 5, 0);
		gbc_btnCodigoProducto.gridx = 6;
		gbc_btnCodigoProducto.gridy = 0;
		panelIntroducionManual.add(btnCodigoProducto, gbc_btnCodigoProducto);
		
		JPanel panelOpciones = new JPanel();
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.anchor = GridBagConstraints.NORTH;
		gbc_panelOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelOpciones.gridx = 1;
		gbc_panelOpciones.gridy = 4;
		contentPane.add(panelOpciones, gbc_panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelOpciones.rowHeights = new int[]{0, 0};
		gbl_panelOpciones.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		JButton btnFinalizarCompra = new JButton("Finalizar compra");
		GridBagConstraints gbc_btnFinalizarCompra = new GridBagConstraints();
		gbc_btnFinalizarCompra.insets = new Insets(0, 0, 0, 5);
		gbc_btnFinalizarCompra.gridx = 0;
		gbc_btnFinalizarCompra.gridy = 0;
		panelOpciones.add(btnFinalizarCompra, gbc_btnFinalizarCompra);
		
		JButton btnStock = new JButton("Stock");
		GridBagConstraints gbc_btnStock = new GridBagConstraints();
		gbc_btnStock.insets = new Insets(0, 0, 0, 5);
		gbc_btnStock.gridx = 1;
		gbc_btnStock.gridy = 0;
		panelOpciones.add(btnStock, gbc_btnStock);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.gridx = 2;
		gbc_btnCerrarSesion.gridy = 0;
		panelOpciones.add(btnCerrarSesion, gbc_btnCerrarSesion);
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionCompra();
			}
		});
		btnStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.verStock();
			}
		});
		btnFinalizarCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.finalizarCompra();
			}
		});
	}

}