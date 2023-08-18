package com.example.pdfcomprossortools.pdf;

import com.spire.pdf.conversion.compression.ImageCompressionOptions;
import com.spire.pdf.conversion.compression.ImageQuality;
import com.spire.pdf.conversion.compression.PdfCompressor;
import com.spire.pdf.conversion.compression.TextCompressionOptions;

/**
 * @author Milad Mofidi (milad.mofidi@cmas-systems.com)
 * 8/17/2023
 */

public class SpirePdfCompressor {

	public static void main(String[] args) {


		//Load a PDF document while initializing the PdfCompressor object
		PdfCompressor compressor = new PdfCompressor("C:\\pdf\\sample-50mb.pdf");

		//Get text compression options
		TextCompressionOptions textCompression = compressor.getOptions().getTextCompressionOptions();

		//Compress fonts
		textCompression.setCompressFonts(true);

		//Unembed fonts
		//textCompression.setUnembedFonts(true);

		//Get image compression options
		ImageCompressionOptions imageCompression = compressor.getOptions().getImageCompressionOptions();

		//Set the compressed image quality
		imageCompression.setImageQuality( ImageQuality.Low);

		//Resize images
		imageCompression.setResizeImages(true);

		//Compress images
		imageCompression.setCompressImage(true);

		//Save the compressed document to file
		compressor.compressToFile("C:\\pdf\\sample-compressed-SpirePDF.pdf");
	}
}
