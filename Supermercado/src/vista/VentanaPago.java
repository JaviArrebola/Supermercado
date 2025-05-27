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
import javax.swing.SpinnerNumberModel;
import java.awt.Font;

public class VentanaPago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private float vueltas=-1;

	public VentanaPago(float precioFinal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("App Supermercado -------> Ventana Pago");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnTicket = new JButton("Ticket");
		btnTicket.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnTicket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (vueltas >= 0) {
				Main.ticket();
				}
			}
		});
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 1;
		contentPane.add(lblTotal, gbc_lblTotal);
		
		JLabel precio = new JLabel();
		precio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_precio = new GridBagConstraints();
		gbc_precio.gridwidth = 2;
		gbc_precio.anchor = GridBagConstraints.EAST;
		gbc_precio.insets = new Insets(0, 0, 5, 5);
		gbc_precio.gridx = 2;
		gbc_precio.gridy = 1;
		contentPane.add(precio, gbc_precio);
		
		precio.setText(precioFinal+"");
		
		JLabel lblEntrega = new JLabel("Entrega: ");
		lblEntrega.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblEntrega = new GridBagConstraints();
		gbc_lblEntrega.anchor = GridBagConstraints.EAST;
		gbc_lblEntrega.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrega.gridx = 1;
		gbc_lblEntrega.gridy = 2;
		contentPane.add(lblEntrega, gbc_lblEntrega);
		
		JSpinner entrega = new JSpinner();
		entrega.setFont(new Font("Tahoma", Font.PLAIN, 17));
		entrega.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		
		
		GridBagConstraints gbc_entrega = new GridBagConstraints();
		gbc_entrega.gridwidth = 2;
		gbc_entrega.fill = GridBagConstraints.HORIZONTAL;
		gbc_entrega.insets = new Insets(0, 0, 5, 5);
		gbc_entrega.gridx = 2;
		gbc_entrega.gridy = 2;
		contentPane.add(entrega, gbc_entrega);
		
		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblCambio = new GridBagConstraints();
		gbc_lblCambio.anchor = GridBagConstraints.EAST;
		gbc_lblCambio.insets = new Insets(0, 0, 5, 5);
		gbc_lblCambio.gridx = 1;
		gbc_lblCambio.gridy = 3;
		contentPane.add(lblCambio, gbc_lblCambio);
		
		JLabel cambio = new JLabel("");
		cambio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_cambio = new GridBagConstraints();
		gbc_cambio.gridwidth = 2;
		gbc_cambio.anchor = GridBagConstraints.EAST;
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
				vueltas = (float)entrega.getValue() - (float) precioFinal;
				vueltas = (float)Math.round(vueltas*100)/100;
				cambio.setText(vueltas+"");
			}
		});
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.sesionPago();
			}
		});
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.volverPago();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.insets = new Insets(0, 0, 5, 5);
		gbc_btnCerrarSesion.anchor = GridBagConstraints.WEST;
		gbc_btnCerrarSesion.gridx = 3;
		gbc_btnCerrarSesion.gridy = 4;
		contentPane.add(btnCerrarSesion, gbc_btnCerrarSesion);
	}

}