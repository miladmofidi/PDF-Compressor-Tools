package com.example.pdfcomprossortools.pdf.itext_samle;

/**
 * @author Milad Mofidi (milad.mofidi@cmas-systems.com)
 * 8/18/2023
 */
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;

import java.io.IOException;

public class iTextPDFCompressorSimple {

	public static void main(String[] args) throws IOException {
		// Paths for input and output PDFs
		String inputPath = "c:/pdf/sample-100mb.pdf";
		String outputPath = "c:/pdf/sample-compressed-iText.pdf";

		PdfReader reader = new PdfReader(inputPath);
		PdfWriter writer = new PdfWriter(outputPath, new WriterProperties().setFullCompressionMode(true));
		PdfDocument pdfDocument = new PdfDocument(reader, writer);
		pdfDocument.close();

		System.out.println("PDF compressed and saved to " + outputPath);
	}
}

