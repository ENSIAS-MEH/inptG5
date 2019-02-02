package QMS;

import java.io.File;

import java.io.FileOutputStream;
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
import Database.Queue;

/**
 * this class allows us to create a receipt for the patient in a PDF format
 * @author RAIS Haythem
 *
 */
public class WritePDF
{


/**
 *  this is the function that allows to create a PDF with the help of iText library that has its own language to create the PDF content
 * @param dest the destination where the PDF file will be saved after being created
 * @param f the first_name of the patient
 * @param l the last_name of the patient
 * @param a the age of the patient
 * @param g the gender of the patient
 * @param h the height of the patient
 * @param w the weight of the patient
 * @param b the blood_type of the patient
 * @param p the purpose of the patient's visit to the Hospital
 * @param d the doctor who will assist the patient, and who has the lesser number of waiting patients
 * @param waiting the number of the waiting patients who came before the current patient
 * @throws DocumentException
 * @throws IOException
 */
 public static void createPdf(String dest, String f, String l, int a, 
		 String g, double h, double w, String b, String p, String d, int waiting)
		    throws DocumentException, IOException {
	     Document document = new Document();
	     PdfWriter.getInstance(
	     document, new FileOutputStream(dest));
	     document.open();
	     
	     DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	     Date today = Calendar.getInstance().getTime();        
	     String date = dateformat.format(today);
	     Paragraph dt = new Paragraph(date); 

	     Paragraph welcome = new Paragraph("Welcome to your Hospital"); 
	     welcome.setAlignment(Element.ALIGN_CENTER);
	     Paragraph preface = new Paragraph("Patient Receipt"); 
	     preface.setAlignment(Element.ALIGN_CENTER);
	     
	     Paragraph first_name = new Paragraph("first name : " + f); 
	     Paragraph last_name = new Paragraph("last name : " + l); 
	     Paragraph age = new Paragraph("age : " + a); 
	     Paragraph gender = new Paragraph("gender : " + g); 
	     Paragraph height = new Paragraph("height : " + h +" m"); 
	     Paragraph width = new Paragraph("width : " + w +" Kg"); 
	     Paragraph blood_type = new Paragraph("blood type : " + b);
	     Paragraph purpose = new Paragraph("the purpose of your visit is : " + p); 
	     Paragraph doctor = new Paragraph("the doctor that will assist you is  : " + d);
	     Paragraph areWaiting = new Paragraph(waiting + " are waiting for the service");
	     Paragraph QueueNo = new Paragraph("you will be called by the number " + Queue.count() + 1);


	     
	     document.add(dt);
	     document.add( Chunk.NEWLINE );
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
	     document.add( Chunk.NEWLINE );
	     document.add(doctor);
	     document.add( Chunk.NEWLINE );
	     document.add(QueueNo);
	     document.add( Chunk.NEWLINE );
	     document.add(areWaiting);
	     
	     document.close();
	 }
}
