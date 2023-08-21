package com.example.pdfcomprossortools.pdf.openpdf_sample;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Milad Mofidi (milad.mofidi@cmas-systems.com)
 * 8/17/2023
 */
@RestController
public class OpenPDFGeneratorController {

	@GetMapping("/pdfTobyte")
	public ResponseEntity<byte[]> generateAndReturnPdfAsByteArray() throws IOException {
		// Create a ByteArrayOutputStream to hold the PDF content
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Create a new PDF document using OpenPDF
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, baos);

			// Open the document
			document.open();

			// Add content to the PDF
			document.add(new Paragraph("Hello, this is a sample PDF."));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		// Compress the PDF and create a ResponseEntity
		byte[] pdfBytes = baos.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType( MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "sample.pdf");
		return new ResponseEntity<>(pdfBytes, headers, org.springframework.http.HttpStatus.OK);
	}

	@GetMapping("/generate-pdf")
	public String generatePdf() throws IOException {
		// Create a new PDF document using OpenPDF
		Document document = new Document();
		String filePath = "c:/pdf/compressedByOpenPDF/sample.pdf";
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			PdfWriter.getInstance(document, fos);

			// Open the document
			document.open();

			// Add content to the PDF
			document.add(new Paragraph("Hello, this is a sample PDF."));
		} catch (Exception e) {
			e.printStackTrace();
			return "PDF generation failed.";
		} finally {
			document.close();
		}

		return "PDF generated and saved successfully.";
	}
}
