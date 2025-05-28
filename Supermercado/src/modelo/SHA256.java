package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Clase encargada de generar un hash SHA-256 a partir de una cadena de texto
public class SHA256 {

	// Método estático que recibe una cadena y devuelve su hash SHA-256 en formato hexadecimal
	public static String generateSHA(String a) throws NoSuchAlgorithmException {
		// Se obtiene una instancia del algoritmo SHA-256
		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		// Se aplica el algoritmo a los bytes de la cadena de entrada
		byte[] encoded = digest.digest(a.getBytes());

		// Se utiliza un StringBuilder para construir el hash en formato hexadecimal
		StringBuilder hex = new StringBuilder();

		// Se recorre el arreglo de bytes para convertir cada uno en hexadecimal
		for (byte b : encoded) {
			// Se convierte el byte en un valor hexadecimal sin signo
			String code = Integer.toHexString(0xff & b);

			// Si el valor hexadecimal tiene solo un dígito, se le agrega un cero al principio
			if (code.length() == 1) {
				hex.append('0');
			}

			// Se agrega el valor hexadecimal al resultado final
			hex.append(code);
		}

		// Se devuelve el hash en formato hexadecimal como una cadena de texto
		return hex.toString();
	}
}