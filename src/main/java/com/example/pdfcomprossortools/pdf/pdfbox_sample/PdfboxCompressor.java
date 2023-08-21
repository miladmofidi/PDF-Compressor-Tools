package com.example.pdfcomprossortools.pdf.pdfbox_sample;

/**
 * @author Milad Mofidi (milad.mofidi@cmas-systems.com)
 * 8/17/2023
 */
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.filetypedetector.FileType;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.io.File;
import java.io.IOException;

public class PdfboxCompressor {

	public static void main(String[] args) throws IOException {
		//compressWithImage();
		saveCompressedPDF();
	}

public static void compressPdfWithImage() throws IOException {
	// Paths for input and output PDFs
	String inputPath = "c:/pdf/sample-10mb.pdf";
	String outputPath = "c:/pdf/sample-compressed-PDFBox.pdf";

	// Load the existing PDF
	PDDocument document =  Loader.loadPDF( new File( inputPath) );

	PDDocument outDoc = new PDDocument();
	outDoc.setDocumentInformation(document.getDocumentInformation());


	// Iterate through the pages and recompress each one
	for (PDPage page : document.getPages()) {

		new PDPageContentStream(outDoc, page,
			PDPageContentStream.AppendMode.APPEND, true).close();
		outDoc.addPage(page);

		PDResources resources = page.getResources();
		if (resources != null) {
			for ( COSName cosName : resources.getXObjectNames()) {
				PDXObject xObject = resources.getXObject(cosName);
				if (xObject instanceof PDImageXObject ) {
					//PDImageXObject imageXObject = (PDImageXObject) xObject;
					// Here, you can apply compression to the imageXObject
					// For simplicity, let's skip the compression in this example
					ImageWriter imageWriter = ImageIO
						.getImageWritersByFormatName( FileType.JPEG.name().toLowerCase()).next();

					ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
					imageWriteParam.setCompressionMode( ImageWriteParam.MODE_EXPLICIT);
					imageWriteParam.setCompressionQuality( 0.5f);


				}
			}
		}

	}

	// Save the compressed PDF
	document.save(outputPath);
	document.close();

	System.out.println("PDF compressed and saved to " + outputPath);
}


	static void saveCompressedPDF() throws IOException {
		PDDocument srcDoc = Loader.loadPDF( new File( "c:/pdf/sample-10mb.pdf" ) );
		PDDocument outDoc = new PDDocument();
		outDoc.setDocumentInformation(srcDoc.getDocumentInformation());
		for (PDPage srcPage : srcDoc.getPages()) {
			new PDPageContentStream(outDoc, srcPage,
				PDPageContentStream.AppendMode.APPEND, true).close();
			outDoc.addPage(srcPage);
		}
		outDoc.save("c:/pdf/sample-compressed-PDFBox.pdf");
		System.out.println("compress is completed...");
		outDoc.close();
	}

}


