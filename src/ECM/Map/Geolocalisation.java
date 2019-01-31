package ECM.Map;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Geolocalisation
{
	public static String getAddress(double lat,double lon)
	{
		String address ="";
		try
		{
			lat = (int) (lat * 10000) / 10000.0;
			lon = (int) (lon * 10000) / 10000.0;

			String url = "https://nominatim.openstreetmap.org/reverse?email=smaraoui2@gmail.com&format=xml&lat=" + lat
					+ "&lon=" + lon + "&addressdetails=1&accept-language=fr";
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("result");
			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			address = CharCoding(eElement.getAttribute("ref"));

			nList = doc.getElementsByTagName("addressparts");
			for (int temp = 0; temp < nList.getLength(); temp++)
			{

				nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					eElement = (Element) nNode;
					if (eElement.getElementsByTagName("road").item(0) != null)
					{
						address +=","+CharCoding(eElement.getElementsByTagName("road").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("city_district").item(0) != null)
					{
						address += ","+CharCoding(eElement.getElementsByTagName("city_district").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("city").item(0) != null)
					{
						address += ","+CharCoding(eElement.getElementsByTagName("city").item(0).getTextContent());
					}
				}
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return address;
	}
	
	public static String CharCoding(String line)
	{
		String ret = "";
		for (char c : line.toCharArray())
		{
			if ((int) c > 0 && (int) c < 255)
			{
				ret += c;
			}
		}
		return ret;
	}
}
