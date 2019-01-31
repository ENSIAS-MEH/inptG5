package QMS;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

public class patient {

	String first_name;
	String last_name;
	int age;
	double weight;
	double height;
	String gender;
	String blood_type;
	
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
	public static void print_receipt(String purpose) {
		try {
			WritePDF.createPdf("dest", "first", "last", 45 , "male", 1.85, 145, "o", "Scan");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
