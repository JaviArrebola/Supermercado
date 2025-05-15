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
import javax.swing.JTextField;

public class VentanaPago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldEntrega;

	public VentanaPago() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("App Supermercado");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnTicket = new JButton("Ticket");
		btnTicket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.ticket();
			}
		});
		
		JLabel lblTotal = new JLabel("Total: ");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 1;
		contentPane.add(lblTotal, gbc_lblTotal);
		
		JLabel lblTotalCompra = new JLabel("");
		GridBagConstraints gbc_lblTotalCompra = new GridBagConstraints();
		gbc_lblTotalCompra.anchor = GridBagConstraints.WEST;
		gbc_lblTotalCompra.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCompra.gridx = 2;
		gbc_lblTotalCompra.gridy = 1;
		contentPane.add(lblTotalCompra, gbc_lblTotalCompra);
		
		JLabel lblEntrega = new JLabel("Entrega: ");
		GridBagConstraints gbc_lblEntrega = new GridBagConstraints();
		gbc_lblEntrega.anchor = GridBagConstraints.EAST;
		gbc_lblEntrega.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrega.gridx = 1;
		gbc_lblEntrega.gridy = 2;
		contentPane.add(lblEntrega, gbc_lblEntrega);
		
		fieldEntrega = new JTextField();
		GridBagConstraints gbc_fieldEntrega = new GridBagConstraints();
		gbc_fieldEntrega.anchor = GridBagConstraints.WEST;
		gbc_fieldEntrega.insets = new Insets(0, 0, 5, 5);
		gbc_fieldEntrega.gridx = 2;
		gbc_fieldEntrega.gridy = 2;
		contentPane.add(fieldEntrega, gbc_fieldEntrega);
		fieldEntrega.setColumns(10);
		
		JLabel lblCambio = new JLabel("Cambio");
		GridBagConstraints gbc_lblCambio = new GridBagConstraints();
		gbc_lblCambio.anchor = GridBagConstraints.EAST;
		gbc_lblCambio.insets = new Insets(0, 0, 5, 5);
		gbc_lblCambio.gridx = 1;
		gbc_lblCambio.gridy = 3;
		contentPane.add(lblCambio, gbc_lblCambio);
		
		JLabel lblCambioCompra = new JLabel("");
		GridBagConstraints gbc_lblCambioCompra = new GridBagConstraints();
		gbc_lblCambioCompra.anchor = GridBagConstraints.WEST;
		gbc_lblCambioCompra.insets = new Insets(0, 0, 5, 5);
		gbc_lblCambioCompra.gridx = 2;
		gbc_lblCambioCompra.gridy = 3;
		contentPane.add(lblCambioCompra, gbc_lblCambioCompra);
		GridBagConstraints gbc_btnTicket = new GridBagConstraints();
		gbc_btnTicket.anchor = GridBagConstraints.EAST;
		gbc_btnTicket.insets = new Insets(0, 0, 5, 5);
		gbc_btnTicket.gridx = 1;
		gbc_btnTicket.gridy = 4;
		contentPane.add(btnTicket, gbc_btnTicket);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionPago();
			}
		});
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.insets = new Insets(0, 0, 5, 5);
		gbc_btnCerrarSesion.anchor = GridBagConstraints.WEST;
		gbc_btnCerrarSesion.gridx = 2;
		gbc_btnCerrarSesion.gridy = 4;
		contentPane.add(btnCerrarSesion, gbc_btnCerrarSesion);
	}

}