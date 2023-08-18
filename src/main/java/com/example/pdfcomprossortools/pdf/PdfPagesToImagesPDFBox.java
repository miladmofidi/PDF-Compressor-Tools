package com.example.pdfcomprossortools.pdf;

/**
 * @author Milad Mofidi (milad.mofidi@cmas-systems.com)
 * 8/17/2023
 */

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PdfPagesToImagesPDFBox {

	public PdfPagesToImagesPDFBox() {

	}

	public static void main(String[] args) throws IOException {

		convertPagesToImage();

	}

	private static void convertPagesToImage() {
		try {

			PDDocument pdDocument = new PDDocument();
			PDDocument oDocument =  Loader.loadPDF( new File( "c:/pdf/sample-6mb.pdf" ) );
			PDFRenderer pdfRenderer = new PDFRenderer(oDocument);
			int numberOfPages = oDocument.getNumberOfPages();
			PDPage page = null;

			for (int i = 0; i < numberOfPages; i++) {
				page = new PDPage(PDRectangle.LETTER);
				BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
				PDImageXObject pdImage = JPEGFactory.createFromImage(pdDocument, bim);
				PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);
				float newHeight = PDRectangle.LETTER.getHeight();
				float newWidth = PDRectangle.LETTER.getWidth();
				contentStream.drawImage(pdImage, 0, 0, newWidth, newHeight);
				contentStream.close();

				pdDocument.addPage(page);
			}

			pdDocument.save("c:/pdf/sample-compressed-PDFBox.pdf");
			System.out.println("Compress has been done");
			pdDocument.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
