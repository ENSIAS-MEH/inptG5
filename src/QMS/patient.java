package QMS;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

/**
 * the patient class represents a patient coming to the Hospital to benefit from a service 
 * @author RAIS Haythem
 * @first_name the first name of the patient that will be registered to benefit from a Hospital service
 * @last_name the last name of the patient that will be registered to benefit from a Hospital service
 * @age the age of the patient
 * @weight the weight of the patient (optional)
 * @height the height of the patient (optional)
 * @gender the gender of the patient (male or female)
 * @blood_type the blood type of the patient (optional), if necessary it will be added in the Doctor Management Software
 */
public class patient {

	/**
	 
	 */
	static String first_name;
	static String last_name;
	static int age;
	static double weight;
	static double height;
	static String gender;
	static String blood_type;
	
	/**
	 * the patient class constructor, it inserts the patient info directly in the database, 
	 * in order to keep the patient's names for an eventual retrieval.
	 * and it also initialize the patient instance variables.
	 * @param f first_name
	 * @param l last_name
	 * @param a age
	 * @param g gender
	 * @param h height
	 * @param w weight
	 * @param b blood_type
	 */
	public patient(String f, String l, int a, String g, double h, double w, String b) {
		
		last_name = l;
		first_name = f;
		age = a;
		weight = w;
		height = h;
		gender = g;
		blood_type = b;
		try {
			Database.Patient.insert(f, l, g, a, w, h, b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * this function prints a receipt for the patient that contains his first_name, last_name, age, gender, height, weight, blood_type,
	 * the purpose of his visit to the Hospital, the doctor that will assist him and how many patients are waiting for this same doctor.
	 * @param purpose the purpose of the patient's visit to the Hospital
	 * @param doctor the doctor who will assist the patient during his treatment period
	 * @param waiting how many patients are waiting for the same doctor that will assist the current patient
	 */
	public static void print_receipt(String purpose, String doctor, int waiting) {
		try {
			WritePDF.createPdf("C:/Users/Rxs/Desktop/receipt.pdf", 
					first_name, last_name, age , gender, height, weight, blood_type,
					purpose, doctor, waiting);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
