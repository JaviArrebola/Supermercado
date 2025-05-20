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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VentanaPago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private float vueltas;

	public VentanaPago(float precioFinal) {
		Main.resetearCompra();
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
				if (vueltas > 0) {
				Main.ticket();
				}
			}
		});
		
		JLabel lblTotal = new JLabel("Total: ");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 1;
		contentPane.add(lblTotal, gbc_lblTotal);
		
		JLabel precio = new JLabel();
		GridBagConstraints gbc_precio = new GridBagConstraints();
		gbc_precio.anchor = GridBagConstraints.WEST;
		gbc_precio.insets = new Insets(0, 0, 5, 5);
		gbc_precio.gridx = 2;
		gbc_precio.gridy = 1;
		contentPane.add(precio, gbc_precio);
		
		precio.setText(precioFinal+"");
		
		JLabel lblEntrega = new JLabel("Entrega: ");
		GridBagConstraints gbc_lblEntrega = new GridBagConstraints();
		gbc_lblEntrega.anchor = GridBagConstraints.EAST;
		gbc_lblEntrega.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrega.gridx = 1;
		gbc_lblEntrega.gridy = 2;
		contentPane.add(lblEntrega, gbc_lblEntrega);
		
		JSpinner entrega = new JSpinner();
		
		
		GridBagConstraints gbc_entrega = new GridBagConstraints();
		gbc_entrega.fill = GridBagConstraints.HORIZONTAL;
		gbc_entrega.insets = new Insets(0, 0, 5, 5);
		gbc_entrega.gridx = 2;
		gbc_entrega.gridy = 2;
		contentPane.add(entrega, gbc_entrega);
		
		JLabel lblCambio = new JLabel("Cambio");
		GridBagConstraints gbc_lblCambio = new GridBagConstraints();
		gbc_lblCambio.anchor = GridBagConstraints.EAST;
		gbc_lblCambio.insets = new Insets(0, 0, 5, 5);
		gbc_lblCambio.gridx = 1;
		gbc_lblCambio.gridy = 3;
		contentPane.add(lblCambio, gbc_lblCambio);
		
		JLabel cambio = new JLabel("");
		GridBagConstraints gbc_cambio = new GridBagConstraints();
		gbc_cambio.anchor = GridBagConstraints.WEST;
		gbc_cambio.insets = new Insets(0, 0, 5, 5);
		gbc_cambio.gridx = 2;
		gbc_cambio.gridy = 3;
		contentPane.add(cambio, gbc_cambio);
		GridBagConstraints gbc_btnTicket = new GridBagConstraints();
		gbc_btnTicket.anchor = GridBagConstraints.EAST;
		gbc_btnTicket.insets = new Insets(0, 0, 5, 5);
		gbc_btnTicket.gridx = 1;
		gbc_btnTicket.gridy = 4;
		contentPane.add(btnTicket, gbc_btnTicket);
		
		entrega.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				vueltas = (int)entrega.getValue() - (float) precioFinal;
				cambio.setText(vueltas+"");
			}
		});
		
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