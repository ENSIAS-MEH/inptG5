package QMS;

import java.sql.SQLException;

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
	
}
