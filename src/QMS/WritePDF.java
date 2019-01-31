package QMS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class WritePDF
{
	 public static final String dest
     = "C:/itextExamples/sample.pdf";


 public static void createPdf(String dest, String f, String l, int a, String g, double h, double w, String b, String p)
	    throws DocumentException, IOException {
     Document document = new Document();
     PdfWriter.getInstance(
     document, new FileOutputStream(dest));
     document.open();
     
     Paragraph preface = new Paragraph("Patient Receipt"); 
     preface.setAlignment(Element.ALIGN_CENTER);
     
     Paragraph first_name = new Paragraph("first name : " + f); 
     Paragraph last_name = new Paragraph("last name : " + l); 
     Paragraph age = new Paragraph("age : " + a); 
     Paragraph gender = new Paragraph("gender : " + g); 
     Paragraph height = new Paragraph("height : " + h +" m"); 
     Paragraph width = new Paragraph("width : " + w +" Kg"); 
     Paragraph blood_type = new Paragraph("blood type : " + b);
     Paragraph purpose = new Paragraph("purpose : " + p); 

     
     
     document.add(preface);
     document.add( Chunk.NEWLINE );
     document.add( Chunk.NEWLINE );
     document.add( Chunk.NEWLINE );
     document.add(first_name);
     document.add( Chunk.NEWLINE );
     document.add(last_name);
     document.add( Chunk.NEWLINE );
     document.add(age);
     document.add( Chunk.NEWLINE );
     document.add(gender);
     document.add( Chunk.NEWLINE );
     document.add(height);
     document.add( Chunk.NEWLINE );
     document.add(width);
     document.add( Chunk.NEWLINE );
     document.add(blood_type);
     document.add( Chunk.NEWLINE );
     document.add(purpose);

     
     document.close();
 }
	
}
