package QMS;

import java.sql.SQLException;

public class patient {

	String last_name;
	String first_name;
	int age;
	float weight;
	float height;
	String gender;
	String blood_type;
	
	public patient(String l, String f, int a, float w, float h, String g, String b) {
		
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
