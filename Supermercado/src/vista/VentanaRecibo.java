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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class VentanaRecibo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCompra;

	public VentanaRecibo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("App Supermercado");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblCodigoTicket = new JLabel("Codigo ticket: ");
		GridBagConstraints gbc_lblCodigoTicket = new GridBagConstraints();
		gbc_lblCodigoTicket.insets = new Insets(0, 0, 0, 5);
		gbc_lblCodigoTicket.gridx = 0;
		gbc_lblCodigoTicket.gridy = 0;
		panel_1.add(lblCodigoTicket, gbc_lblCodigoTicket);
		
		JLabel lblCodigoTicketCompra = new JLabel("");
		GridBagConstraints gbc_lblCodigoTicketCompra = new GridBagConstraints();
		gbc_lblCodigoTicketCompra.insets = new Insets(0, 0, 0, 5);
		gbc_lblCodigoTicketCompra.gridx = 1;
		gbc_lblCodigoTicketCompra.gridy = 0;
		panel_1.add(lblCodigoTicketCompra, gbc_lblCodigoTicketCompra);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 0, 5);
		gbc_lblFecha.gridx = 2;
		gbc_lblFecha.gridy = 0;
		panel_1.add(lblFecha, gbc_lblFecha);
		
		JLabel lblFechaCompra = new JLabel("");
		GridBagConstraints gbc_lblFechaCompra = new GridBagConstraints();
		gbc_lblFechaCompra.gridx = 3;
		gbc_lblFechaCompra.gridy = 0;
		panel_1.add(lblFechaCompra, gbc_lblFechaCompra);
		
		JLabel lblNewLabel = new JLabel("Supermercado");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tableCompra = new JTable();
		tableCompra.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Ud", "Articulo", "Precio", "Importe"
			}
		));
		scrollPane.setViewportView(tableCompra);
		
		JPanel panelOpciones = new JPanel();
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelOpciones.fill = GridBagConstraints.VERTICAL;
		gbc_panelOpciones.gridx = 1;
		gbc_panelOpciones.gridy = 4;
		contentPane.add(panelOpciones, gbc_panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{0, 0, 0};
		gbl_panelOpciones.rowHeights = new int[]{0, 0};
		gbl_panelOpciones.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		JButton btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 0;
		panelOpciones.add(btnVolver, gbc_btnVolver);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.gridx = 1;
		gbc_btnCerrarSesion.gridy = 0;
		panelOpciones.add(btnCerrarSesion, gbc_btnCerrarSesion);
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionRecibo();
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.volverRecibo();
			}
		});
	}

}