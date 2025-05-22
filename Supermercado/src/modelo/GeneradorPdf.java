package modelo;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeneradorPdf {

    public static boolean crearReciboPDF(String rutaArchivo, List<Producto> productos, String idVenta, double subtotal, double total) {
        Document doc = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(rutaArchivo));
            doc.open();

            Font fontTitulo = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font fontTexto = new Font(Font.HELVETICA, 12);
            Font fontTotales = new Font(Font.HELVETICA, 14, Font.BOLD);

            Paragraph titulo = new Paragraph("RECIBO DE COMPRA", fontTitulo);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(titulo);
            doc.add(new Paragraph(" "));

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaHoraFormateada = ahora.format(formatoFechaHora);

            Paragraph idYFecha = new Paragraph("ID Venta: " + idVenta + "\t\tFecha y hora: " + fechaHoraFormateada, fontTexto);
            doc.add(idYFecha);
            doc.add(new Paragraph(" "));

            PdfPTable tablaProductos = new PdfPTable(4);
            tablaProductos.setWidthPercentage(100);
            tablaProductos.setWidths(new float[]{1, 4, 2, 2});

            tablaProductos.addCell(new Phrase("Ud", fontTexto));
            tablaProductos.addCell(new Phrase("Artículo", fontTexto));
            tablaProductos.addCell(new Phrase("Precio", fontTexto));
            tablaProductos.addCell(new Phrase("Importe", fontTexto));

            for (Producto p : productos) {
                tablaProductos.addCell(new Phrase(String.valueOf(p.getUnidades()), fontTexto));
                tablaProductos.addCell(new Phrase(p.getNombre(), fontTexto));
                tablaProductos.addCell(new Phrase(String.format("%.2f €", p.getPrecioUnitario()), fontTexto));
                double importe = p.getUnidades() * p.getPrecioUnitario();
                tablaProductos.addCell(new Phrase(String.format("%.2f €", importe), fontTexto));
            }

            doc.add(tablaProductos);
            doc.add(new Paragraph(" "));

            PdfPTable tablaTotales = new PdfPTable(3);
            tablaTotales.setWidthPercentage(100);
            tablaTotales.setWidths(new float[]{1, 1, 1});

            PdfPCell iva = new PdfPCell(new Phrase("IVA: 21%", fontTotales));
            PdfPCell sub = new PdfPCell(new Phrase("Subtotal: " + String.format("%.2f €", subtotal), fontTotales));
            PdfPCell totalFinal = new PdfPCell(new Phrase("Total: " + String.format("%.2f €", total), fontTotales));

            iva.setBorder(Rectangle.NO_BORDER);
            sub.setBorder(Rectangle.NO_BORDER);
            totalFinal.setBorder(Rectangle.NO_BORDER);

            tablaTotales.addCell(iva);
            tablaTotales.addCell(sub);
            tablaTotales.addCell(totalFinal);

            doc.add(tablaTotales);
            doc.add(new Paragraph(" "));

            Paragraph gracias = new Paragraph("Gracias por su compra", fontTexto);
            gracias.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(gracias);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            doc.close();
        }
    }
}
