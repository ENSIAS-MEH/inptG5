package QMS;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class test {

	public static void main(String[] args) {
		
		try {
			WritePDF.createPdf("C:/Users/Rxs/Desktop/receipt.pdf", 
					"Hamid", 
					"Raghini", 
					45, 
					"male", 
					1.85, 
					132.23, 
					"o");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
