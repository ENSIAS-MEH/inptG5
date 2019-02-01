package QMS;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

public class patient {

	static String first_name;
	static String last_name;
	static int age;
	static double weight;
	static double height;
	static String gender;
	static String blood_type;
	
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
