package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Main;
import modelo.Consultas;
import modelo.Producto;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;

//Clase principal que define la ventana de compra
public class VentanaCompra extends JFrame {

	private static final long serialVersionUID = 1L;

	// Componentes de la ventana
	private JPanel contentPane;
	private JTextField fieldNombreProducto;
	private JSpinner fieldCodigoProducto;
	private JTable tableListaCompra;
	private DefaultTableModel modeloCompra;
	private JTextField fieldCodigoBarras;

	// Constructor de la ventana
	public VentanaCompra() {

		// Modelo de la tabla para los productos añadidos a la compra
		modeloCompra = new DefaultTableModel();
		modeloCompra.addColumn("Nombre");
		modeloCompra.addColumn("Cantidad");
		modeloCompra.addColumn("Precio unitario");

		// Configuración inicial del JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 600);
		setTitle("App Supermercado ------> Ventana Compra");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Diseño general del layout principal
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, -255, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Panel para la introducción manual de productos
		JPanel panelIntroducionManual = new JPanel();
		GridBagConstraints gbc_panelIntroducionManual = new GridBagConstraints();
		gbc_panelIntroducionManual.insets = new Insets(0, 0, 5, 5);
		gbc_panelIntroducionManual.gridx = 1;
		gbc_panelIntroducionManual.gridy = 1;
		contentPane.add(panelIntroducionManual, gbc_panelIntroducionManual);

		// Layout para los elementos del panel
		GridBagLayout gbl_panelIntroducionManual = new GridBagLayout();
		// Configuración de columnas y filas
		gbl_panelIntroducionManual.columnWidths = new int[] { 0, 124, 0, 0, 0, 121, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelIntroducionManual.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelIntroducionManual.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0 };
		gbl_panelIntroducionManual.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelIntroducionManual.setLayout(gbl_panelIntroducionManual);

		// Campo para introducir el nombre del producto
		JLabel lblNombreProducto = new JLabel("Nombre del producto: ");
		lblNombreProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNombreProducto = new GridBagConstraints();
		gbc_lblNombreProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombreProducto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreProducto.gridx = 0;
		gbc_lblNombreProducto.gridy = 0;
		panelIntroducionManual.add(lblNombreProducto, gbc_lblNombreProducto);

		fieldNombreProducto = new JTextField();
		fieldNombreProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_fieldNombreProducto = new GridBagConstraints();
		gbc_fieldNombreProducto.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNombreProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNombreProducto.gridx = 1;
		gbc_fieldNombreProducto.gridy = 0;
		panelIntroducionManual.add(fieldNombreProducto, gbc_fieldNombreProducto);
		fieldNombreProducto.setColumns(10);

		// Botón para añadir producto por nombre
		JButton btnNombreProducto = new JButton("Añadir"); // ESTO ES EL BOTON NOMBRE PRODUCTO
		btnNombreProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNombreProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Llama a Main para obtener el producto por nombre y añadirlo a la tabla
				Producto anadir = Main.anadirProductoACompra(fieldNombreProducto.getText());
				anadirProductoACompra(anadir); // De ventanaCompra
			}
		});
		GridBagConstraints gbc_btnNombreProducto = new GridBagConstraints();
		gbc_btnNombreProducto.insets = new Insets(0, 0, 5, 5);
		gbc_btnNombreProducto.gridx = 2;
		gbc_btnNombreProducto.gridy = 0;
		panelIntroducionManual.add(btnNombreProducto, gbc_btnNombreProducto);

		// Campo y botón para añadir producto por código ID
		JLabel lblCodigoProducto = new JLabel("Codigo del producto: ");
		lblCodigoProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCodigoProducto = new GridBagConstraints();
		gbc_lblCodigoProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCodigoProducto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoProducto.gridx = 4;
		gbc_lblCodigoProducto.gridy = 0;
		panelIntroducionManual.add(lblCodigoProducto, gbc_lblCodigoProducto);

		fieldCodigoProducto = new JSpinner();
		fieldCodigoProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldCodigoProducto
				.setModel(new SpinnerNumberModel(1, Consultas.minIDProducto(), Consultas.maxIDProducto(), 1));
		GridBagConstraints gbc_fieldCodigoProducto = new GridBagConstraints();
		gbc_fieldCodigoProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldCodigoProducto.insets = new Insets(0, 0, 5, 5);
		gbc_fieldCodigoProducto.gridx = 5;
		gbc_fieldCodigoProducto.gridy = 0;
		panelIntroducionManual.add(fieldCodigoProducto, gbc_fieldCodigoProducto);

		// Tabla para mostrar los productos añadidos a la compra
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		tableListaCompra = new JTable();
		tableListaCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(tableListaCompra);
		tableListaCompra.setModel(modeloCompra);

		// Botón para añadir producto por código ID
		JButton btnCodigoProducto = new JButton("Añadir");
		btnCodigoProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCodigoProducto.addMouseListener(new MouseAdapter() { // Es el [BOTON ID PRODUCTO]
			@Override
			public void mouseClicked(MouseEvent e) {
				Producto anadir = Main.anadirProductoACompra((int) fieldCodigoProducto.getValue());
				anadirProductoACompra(anadir); // De ventanaCompra
			}
		});

		GridBagConstraints gbc_btnCodigoProducto = new GridBagConstraints();
		gbc_btnCodigoProducto.insets = new Insets(0, 0, 5, 5);
		gbc_btnCodigoProducto.gridx = 6;
		gbc_btnCodigoProducto.gridy = 0;
		panelIntroducionManual.add(btnCodigoProducto, gbc_btnCodigoProducto);

		// Botón para eliminar el producto seleccionado de la tabla
		JButton btnEliminarSeleccion = new JButton("Eliminar producto seleccionado");
		btnEliminarSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminarSeleccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableListaCompra.getSelectedRow();
				if (fila < 0) { // Si no hay ningun producto seleccionado
					JOptionPane.showMessageDialog(VentanaCompra.this,
							"No hay ningun producto seleccionado. Seleccione un producto para eliminarlo.",
							"Producto no seleccionado.", JOptionPane.ERROR_MESSAGE);
					System.out.println("No hay ningun producto seleccionado. Seleccione un producto para eliminar.");
				} else { // Si hay algun producto seleccionado
					int seleccion = JOptionPane.showConfirmDialog(VentanaCompra.this,
							"¿Esta seguro que quieres eliminar ese producto?", "Eliminar producto seleccionado.",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (seleccion == 0) { // Si se confirma la eliminacion
						Main.eliminarProductoDeCompra(fila);
						modeloCompra.removeRow(fila);
					}

				}

			}
		});

		// Campo para introducir código de barras
		JLabel lblCodigoBarras = new JLabel("Codigo de barras: ");
		lblCodigoBarras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCodigoBarras = new GridBagConstraints();
		gbc_lblCodigoBarras.anchor = GridBagConstraints.EAST;
		gbc_lblCodigoBarras.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoBarras.gridx = 8;
		gbc_lblCodigoBarras.gridy = 0;
		panelIntroducionManual.add(lblCodigoBarras, gbc_lblCodigoBarras);

		fieldCodigoBarras = new JTextField();
		fieldCodigoBarras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldCodigoBarras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto anadir = Main.anadirProductoACompraCodigoBarra(fieldCodigoBarras.getText());
				anadirProductoACompra(anadir); // De ventanaCompra
				fieldCodigoBarras.setText("");
			}
		});
		GridBagConstraints gbc_fieldCodigoBarras = new GridBagConstraints();
		gbc_fieldCodigoBarras.insets = new Insets(0, 0, 5, 5);
		gbc_fieldCodigoBarras.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldCodigoBarras.gridx = 9;
		gbc_fieldCodigoBarras.gridy = 0;
		panelIntroducionManual.add(fieldCodigoBarras, gbc_fieldCodigoBarras);
		fieldCodigoBarras.setColumns(10);

		// Botón para añadir producto por código de barras
		JButton btnCodigoBarras = new JButton("Añadir");
		btnCodigoBarras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCodigoBarras.addMouseListener(new MouseAdapter() { // Boton codigo barras
			@Override
			public void mouseClicked(MouseEvent e) {
				Producto anadir = Main.anadirProductoACompraCodigoBarra(fieldCodigoBarras.getText());
				anadirProductoACompra(anadir); // De ventanaCompra
			}
		});
		GridBagConstraints gbc_btnCodigoBarras = new GridBagConstraints();
		gbc_btnCodigoBarras.insets = new Insets(0, 0, 5, 5);
		gbc_btnCodigoBarras.gridx = 10;
		gbc_btnCodigoBarras.gridy = 0;
		panelIntroducionManual.add(btnCodigoBarras, gbc_btnCodigoBarras);

		GridBagConstraints gbc_btnEliminarSeleccion = new GridBagConstraints();
		gbc_btnEliminarSeleccion.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarSeleccion.gridx = 12;
		gbc_btnEliminarSeleccion.gridy = 0;
		panelIntroducionManual.add(btnEliminarSeleccion, gbc_btnEliminarSeleccion);

		// Panel inferior con opciones adicionales
		JPanel panelOpciones = new JPanel();
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.anchor = GridBagConstraints.NORTH;
		gbc_panelOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelOpciones.gridx = 1;
		gbc_panelOpciones.gridy = 4;
		contentPane.add(panelOpciones, gbc_panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelOpciones.rowHeights = new int[] { 0, 0 };
		gbl_panelOpciones.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelOpciones.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelOpciones.setLayout(gbl_panelOpciones);

		// Botón para finalizar compra
		JButton btnFinalizarCompra = new JButton("Finalizar compra");
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnFinalizarCompra = new GridBagConstraints();
		gbc_btnFinalizarCompra.insets = new Insets(0, 0, 0, 5);
		gbc_btnFinalizarCompra.gridx = 0;
		gbc_btnFinalizarCompra.gridy = 0;
		panelOpciones.add(btnFinalizarCompra, gbc_btnFinalizarCompra);

		// Botón para ver stock
		JButton btnStock = new JButton("Stock");
		btnStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnStock = new GridBagConstraints();
		gbc_btnStock.insets = new Insets(0, 0, 0, 5);
		gbc_btnStock.gridx = 1;
		gbc_btnStock.gridy = 0;
		panelOpciones.add(btnStock, gbc_btnStock);

		// Botón para cerrar sesión
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.gridx = 2;
		gbc_btnCerrarSesion.gridy = 0;
		panelOpciones.add(btnCerrarSesion, gbc_btnCerrarSesion);

		// Eventos para botones inferiores
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
				boolean finalizar = Main.finalizarCompra();
				if (finalizar != true) {
					JOptionPane.showMessageDialog(VentanaCompra.this,
							"No hay ningun producto en la compra, introduza al menos un producto a la compra para poder finalizarla.",
							"Compra incompleta.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// Método que añade un producto a la tabla de compra
	private void anadirProductoACompra(Producto anadir) {
		int filas = tableListaCompra.getRowCount();
		boolean repetido = false;

		if (anadir != null) { // Si hay que añadir algun producto
			int stock = Consultas.compraStock(anadir.getId());

			// Revisa si el producto ya está en la tabla
			for (int i = 0; i < filas; i++) {
				if (tableListaCompra.getValueAt(i, 0).equals(anadir.getNombre())) {
					int unidades = Integer.parseInt(tableListaCompra.getValueAt(i, 1).toString()) + 1;
					repetido = true;

					if (unidades <= stock) { // Si el numero de unidades no supera el stock
						tableListaCompra.setValueAt(unidades, i, 1);
					} else { // Si el numero de unidades supera el stock
						JOptionPane.showMessageDialog(VentanaCompra.this,
								"Se ha superado la cantidad de unidades de este producto, no se puede añadir mas.",
								"Cantidad maxima superada.", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
			}

			// Si es nuevo producto, lo añade a la tabla
			if (repetido != true) { // Si el nombre del producto que se quiere añadir no esta en la tabla
				if (stock > 0) { // Si hay al menos una unidad del producto deseado en el stock
					String productos[] = new String[3];
					productos[0] = anadir.getNombre();
					productos[1] = anadir.getUnidades() + "";
					productos[2] = anadir.getPrecioUnitario() + "";
					modeloCompra.addRow(productos);
				} else { // Si no hay ni una unidad del producto deseado en el stock
					JOptionPane.showMessageDialog(VentanaCompra.this,
							"No quedan mas unidades de este producto. No se puede añadir a la compra.",
							"Cantidad maxima superada.", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else { // Si no hay ningun producto que añadir
			JOptionPane.showMessageDialog(VentanaCompra.this, "No se ha encontrado el producto seleccionado.",
					"Producto no encontrado.", JOptionPane.ERROR_MESSAGE);
			System.out.println("No se ha añadido ningun producto. Introduzca un producto para añadirlo.");
		}

	}

}