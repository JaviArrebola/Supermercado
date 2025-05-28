package vista;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Main;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

//Clase VentanaInicio para crear una ventana de login
public class VentanaInicio extends JFrame {

	private static final long serialVersionUID = 1L;

	// Panel principal del contenido
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	// Constructor de la clase
	public VentanaInicio() {
		// Reinicia los datos de la compra al iniciar la ventana
		Main.resetearCompra();

		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("App Supermercado -------> Login");

		// Inicialización del panel de contenido y configuración de márgenes
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Uso del layout GridBagLayout para mayor control del diseño
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 157, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Etiqueta principal de la ventana
		JLabel lblNewLabel = new JLabel("Inicio sesión");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		// Etiqueta que podría mostrar el estado de la sesión
		JLabel lblEstadoSesion = new JLabel("");
		GridBagConstraints gbc_lblEstadoSesion = new GridBagConstraints();
		gbc_lblEstadoSesion.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstadoSesion.gridx = 1;
		gbc_lblEstadoSesion.gridy = 3;
		contentPane.add(lblEstadoSesion, gbc_lblEstadoSesion);

		// Etiqueta y campo para el nombre de usuario
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		// Etiqueta y campo para la contraseña
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 7;
		contentPane.add(passwordField, gbc_passwordField);
		passwordField.setColumns(10);

		// Botón para iniciar sesión
		JButton btnNewButton = new JButton("Iniciar sesión");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));

		// Evento al hacer clic en el botón de iniciar sesión
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se obtienen los valores ingresados
				String usuario = textField.getText();
				String password = new String(passwordField.getPassword());

				// Se valida el login llamando al método correspondiente
				String login = Main.login(usuario, password);

				// Manejo del resultado del login
				switch (login) {
				case "Usuario validado":
					// Si es correcto, se redirige a la siguiente ventana
					Main.inicioSesion();
					break;
				case "Contraseña incorrecta":
					// Si la contraseña no es correcta, se informa al usuario
					JOptionPane.showMessageDialog(VentanaInicio.this, "Contraseña incorrecta.",
							"Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
					break;
				case "Usuario no encontrado":
					// Si no se encuentra el usuario, se muestra un mensaje
					JOptionPane.showMessageDialog(VentanaInicio.this, "Usuario no encontrado.",
							"Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
					break;
				default:
					// Si hay otro error, se muestra el mensaje retornado
					JOptionPane.showMessageDialog(VentanaInicio.this, login, "Error de inicio de sesión",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		});

		// Posicionamiento del botón dentro del layout
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 8;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}

}