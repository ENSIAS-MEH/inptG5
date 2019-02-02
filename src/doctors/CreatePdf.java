package doctors;
import java.io.File;

import sun.reflect.Reflection;


import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import Database.Queue;
/***********************************************************************************
 * cette classe permet de generer un fichier pdf qui contient des informations 
 * @author User
 *
 */
public class CreatePdf {
	
		/*****************************************************************
		 * la destination du fichier pdf à créer
		 */
		 public static final String dest ="C:/Users/User/Desktop/itextexamples/sample.pdf";
		 
		


		/**************************************************
		 * méthode qui permet de céer un pdf en utilisant les paramétres suivants qui vont etre dans le contenu du pdf:
		 * @param a
		 * @param b
		 * @param c
		 * @param d
		 * @param e
		 * @param f
		 * @throws DocumentException
		 * @throws IOException
		 */
	 public static void createPdf(String a,String b,String c,String d,String e,String f)
		    throws DocumentException, IOException {
	     Document document = new Document();
	     PdfWriter.getInstance(
	     document, new FileOutputStream(dest));
	     document.open();
	     
	     Paragraph preface = new Paragraph("Treatement Receipt"); 
	     preface.setAlignment(Element.ALIGN_CENTER);
	     
	     Paragraph id = new Paragraph("id : " +a); 
	     Paragraph doctor = new Paragraph("id doctor : "+b); 
	     Paragraph Disease = new Paragraph("Disease: "+ c); 
	     Paragraph Treatement = new Paragraph("Treatement : "+ d); 
	     Paragraph Visitdate = new Paragraph("visit date: " + e +" m"); 
	     Paragraph Amount= new Paragraph("Amount: " + f +" DH"); 
	    

	     
	     
	     document.add(preface);
	     document.add( Chunk.NEWLINE );
	     document.add( Chunk.NEWLINE );
	     document.add( Chunk.NEWLINE );
	     document.add(id);
	     document.add( Chunk.NEWLINE );
	     document.add(doctor);
	     document.add( Chunk.NEWLINE );
	     document.add(Disease);
	     document.add( Chunk.NEWLINE );
	     document.add(Treatement);
	     document.add( Chunk.NEWLINE );
	     document.add(Visitdate);
	     document.add( Chunk.NEWLINE );
	     document.add(Amount);
	    
	     
	     document.close();
	}
	

}
