package modelo;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Clase encargada de generar un recibo de compra en formato PDF
public class GeneradorPdf {

	public static boolean crearReciboPDF(String rutaArchivo, List<Producto> productos, String idVenta, double subtotal,
			double total) {
		Document doc = new Document(PageSize.A4);

		try {
			// Asocia el documento con un archivo de salida
			PdfWriter.getInstance(doc, new FileOutputStream(rutaArchivo));
			doc.open();

			// Definición de fuentes para el texto del documento
			Font fontTitulo = new Font(Font.HELVETICA, 14, Font.BOLD);
			Font fontTexto = new Font(Font.HELVETICA, 12);
			Font fontTotales = new Font(Font.HELVETICA, 14, Font.BOLD);

			// Título centrado del recibo
			Paragraph titulo = new Paragraph("RECIBO DE COMPRA", fontTitulo);
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(titulo);
			doc.add(new Paragraph(" "));

			// Obtiene la fecha y hora actual y la formatea
			LocalDateTime ahora = LocalDateTime.now();
			DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String fechaHoraFormateada = ahora.format(formatoFechaHora);

			// Añade información del código de ticket y la fecha/hora al documento
			Paragraph idYFecha = new Paragraph("Codigo Ticket: " + idVenta + "\t\tFecha y hora: " + fechaHoraFormateada,
					fontTexto);
			doc.add(idYFecha);
			doc.add(new Paragraph(" "));

			// Crea la tabla para listar productos
			PdfPTable tablaProductos = new PdfPTable(4);
			tablaProductos.setWidthPercentage(100);
			tablaProductos.setWidths(new float[] { 1, 4, 2, 2 });

			// Cabeceras de la tabla
			tablaProductos.addCell(new Phrase("Ud", fontTexto));
			tablaProductos.addCell(new Phrase("Artículo", fontTexto));
			tablaProductos.addCell(new Phrase("Precio", fontTexto));
			tablaProductos.addCell(new Phrase("Importe", fontTexto));

			// Itera sobre la lista de productos y los añade a la tabla
			for (Producto p : productos) {
				tablaProductos.addCell(new Phrase(String.valueOf(p.getUnidades()), fontTexto));
				tablaProductos.addCell(new Phrase(p.getNombre(), fontTexto));
				tablaProductos.addCell(new Phrase(String.format("%.2f €", p.getPrecioUnitario()), fontTexto));
				double importe = p.getUnidades() * p.getPrecioUnitario();
				tablaProductos.addCell(new Phrase(String.format("%.2f €", importe), fontTexto));
			}

			doc.add(tablaProductos); // Añade la tabla de productos al documento
			doc.add(new Paragraph(" ")); // Espacio en blanco

			// Crea una nueva tabla para mostrar los totales
			PdfPTable tablaTotales = new PdfPTable(3);
			tablaTotales.setWidthPercentage(100);
			tablaTotales.setWidths(new float[] { 1, 1, 1 });

			// Celdas para el IVA, subtotal y total
			PdfPCell iva = new PdfPCell(new Phrase("IVA: 21%", fontTotales));
			PdfPCell sub = new PdfPCell(new Phrase("Subtotal: " + String.format("%.2f €", subtotal), fontTotales));
			PdfPCell totalFinal = new PdfPCell(new Phrase("Total: " + String.format("%.2f €", total), fontTotales));

			// Quita los bordes a las celdas
			iva.setBorder(Rectangle.NO_BORDER);
			sub.setBorder(Rectangle.NO_BORDER);
			totalFinal.setBorder(Rectangle.NO_BORDER);

			// Añade las celdas a la tabla
			tablaTotales.addCell(iva);
			tablaTotales.addCell(sub);
			tablaTotales.addCell(totalFinal);

			doc.add(tablaTotales); // Añade la tabla de totales al documento
			doc.add(new Paragraph(" ")); // Espacio en blanco

			// Mensaje de agradecimiento centrado
			Paragraph gracias = new Paragraph("Gracias por su compra", fontTexto);
			gracias.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(gracias);

			return true; // Retorna true si el PDF se genera correctamente

		} catch (Exception e) {
			e.printStackTrace(); // Imprime el error en consola
			return false; // Retorna false en caso de fallo
		} finally {
			doc.close(); // Cierra el documento siempre, ocurra o no una excepción
		}
	}
}
