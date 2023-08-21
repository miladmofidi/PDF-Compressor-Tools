package com.example.pdfcomprossortools.pdf.openpdf_sample;

/**
 * @author Milad Mofidi (milad.mofidi@cmas-systems.com)
 * 8/17/2023
 */

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class OpenPDFCompressor {
	public static void main(String[] args) {
		String inputFilePath = "c:/pdf/sample-10mb.pdf";
		String outputFilePath = "c:/pdf/sample-compressed-openPDF.pdf";

		try {
			PdfReader reader = new PdfReader(inputFilePath);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFilePath));

			// Set compression level (0-9)
			int compressionLevel = 9; // Maximum compression
			stamper.getWriter().setCompressionLevel(compressionLevel);
			stamper.setFullCompression();

			stamper.close();
			reader.close();

			System.out.println("PDF compression complete.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
