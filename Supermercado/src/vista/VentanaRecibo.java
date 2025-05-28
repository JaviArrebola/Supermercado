package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Main;
import modelo.Compra;
import modelo.GeneradorPdf;
import modelo.Producto;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class VentanaRecibo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCompra;
	private DefaultTableModel modeloCompra;

	// Constructor de la ventana que muestra el recibo
	public VentanaRecibo(Compra compra, String id, float subtotal, float precioTotal) {
		Main.resetearCompra(); // Se reinicia la compra para preparar una nueva

		Iterator<Producto> it = compra.getCompra().iterator();

		// Se configura el modelo de la tabla
		modeloCompra = new DefaultTableModel();
		modeloCompra.addColumn("Ud"); // Unidades
		modeloCompra.addColumn("Articulo");
		modeloCompra.addColumn("Precio"); // Precio unitario
		modeloCompra.addColumn("Importe"); // Precio unitario * Ud

		// Se recorren los productos para agregarlos a la tabla
		while (it.hasNext()) {
			Producto anadir = it.next();
			String productos[] = new String[4];
			productos[0] = anadir.getUnidades() + "";
			productos[1] = anadir.getNombre();
			productos[2] = anadir.getPrecioUnitario() + "";

			// Cálculo del importe redondeado a 2 decimales
			float importe = (float) Math.round((Float.parseFloat(productos[0]) * Float.parseFloat(productos[2])) * 100)
					/ 100;
			productos[3] = importe + "";

			// Se agrega la fila al modelo
			modeloCompra.addRow(productos);
		}

		// Configuración del JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		setTitle("App Supermercado -------> Ventana Recibo");

		// Creación y configuración del contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Layout principal del contentPane
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 30, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 22, 0, 0, 0, 0, 0, 0, 24, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Título de la ventana
		JLabel lblNewLabel = new JLabel("Supermercado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		// Panel con la información del ticket (ID y fecha)
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);

		// Layout del panel de cabecera
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		// Etiquetas para mostrar el código del ticket y la fecha
		JLabel lblCodigoTicket = new JLabel("Codigo ticket: ");
		lblCodigoTicket.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblCodigoTicket = new GridBagConstraints();
		gbc_lblCodigoTicket.insets = new Insets(0, 0, 0, 5);
		gbc_lblCodigoTicket.gridx = 0;
		gbc_lblCodigoTicket.gridy = 0;
		panel_1.add(lblCodigoTicket, gbc_lblCodigoTicket);

		JLabel cod = new JLabel(id);
		cod.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_cod = new GridBagConstraints();
		gbc_cod.insets = new Insets(0, 0, 0, 5);
		gbc_cod.gridx = 1;
		gbc_cod.gridy = 0;
		panel_1.add(cod, gbc_cod);

		JLabel lblCodigoTicketCompra = new JLabel("");
		GridBagConstraints gbc_lblCodigoTicketCompra = new GridBagConstraints();
		gbc_lblCodigoTicketCompra.insets = new Insets(0, 0, 0, 5);
		gbc_lblCodigoTicketCompra.gridx = 2;
		gbc_lblCodigoTicketCompra.gridy = 0;
		panel_1.add(lblCodigoTicketCompra, gbc_lblCodigoTicketCompra);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 0, 5);
		gbc_lblFecha.gridx = 3;
		gbc_lblFecha.gridy = 0;
		panel_1.add(lblFecha, gbc_lblFecha);

		JLabel fecha = new JLabel(LocalDate.now() + "");
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_fecha = new GridBagConstraints();
		gbc_fecha.insets = new Insets(0, 0, 0, 5);
		gbc_fecha.gridx = 4;
		gbc_fecha.gridy = 0;
		panel_1.add(fecha, gbc_fecha);

		JLabel lblFechaCompra = new JLabel("");
		GridBagConstraints gbc_lblFechaCompra = new GridBagConstraints();
		gbc_lblFechaCompra.gridx = 5;
		gbc_lblFechaCompra.gridy = 0;
		panel_1.add(lblFechaCompra, gbc_lblFechaCompra);

		// Scroll pane que contiene la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);

		// Configuración de la tabla de productos
		tableCompra = new JTable();
		tableCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableCompra.setEnabled(false);
		tableCompra.setModel(modeloCompra);
		scrollPane.setViewportView(tableCompra);

		// Panel con información adicional (IVA, subtotal y total)
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;
		contentPane.add(panel, gbc_panel);

		// Layout del panel de totales
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("IVA: 21%");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Subtotal: " + subtotal + "€");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Total: " + precioTotal + "€");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		// Panel con botones de opciones
		JPanel panelOpciones = new JPanel();
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelOpciones.fill = GridBagConstraints.VERTICAL;
		gbc_panelOpciones.gridx = 1;
		gbc_panelOpciones.gridy = 7;
		contentPane.add(panelOpciones, gbc_panelOpciones);

		// Layout del panel de botones
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelOpciones.rowHeights = new int[] { 0, 0 };
		gbl_panelOpciones.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelOpciones.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelOpciones.setLayout(gbl_panelOpciones);

		// Botón para iniciar una nueva compra
		JButton btnVolver = new JButton("Nueva Compra");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 0;
		panelOpciones.add(btnVolver, gbc_btnVolver);

		// Botón para cerrar sesión
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.gridx = 1;
		gbc_btnCerrarSesion.gridy = 0;
		panelOpciones.add(btnCerrarSesion, gbc_btnCerrarSesion);

		// Botón para guardar el recibo en PDF
		JButton btnGuardarPdf = new JButton("Guardar Recibo (PDF)");
		btnGuardarPdf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_btnGuardarPdf = new GridBagConstraints();
		gbc_btnGuardarPdf.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardarPdf.gridx = 2;
		gbc_btnGuardarPdf.gridy = 0;
		panelOpciones.add(btnGuardarPdf, gbc_btnGuardarPdf);

		// Acción para cerrar sesión
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionRecibo();
			}
		});

		// Acción para comenzar una nueva compra
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.volverRecibo();
			}
		});

		// Acción para guardar el recibo en formato PDF
		btnGuardarPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String rutaArchivo = "recibo_" + id + ".pdf";

				// Se genera el PDF llamando a la clase GeneradorPdf
				boolean ok = GeneradorPdf.crearReciboPDF(rutaArchivo, compra.getCompra(), id, subtotal, precioTotal);

				// Se notifica al usuario del resultado
				if (ok) {
					javax.swing.JOptionPane.showMessageDialog(null, "PDF generado correctamente:\n" + rutaArchivo);
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, "Error al generar el PDF.");
				}
			}
		});
	}

}