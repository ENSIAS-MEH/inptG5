package doctors;

import java.sql.DriverManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
/************************************
 * une classe main pour se connecter à la base de donnée et contient les deux attributs scalex et scaley 
 * ils vont étre utilisés pour permettre à l'utilisateur de bien afficher l'application s'il était en plein 
 * écran sur n'importe quel écran (4:3 ou 16:9)
 * @author User
 *
 */
public class Main
{
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static float scalex = (float) screenSize.getWidth() / 1920;
	public static float scaley = (float) screenSize.getHeight() / 1080;
	
	private static Connection connection;
	
	public static void main(String[] args)
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:database.db");
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
}
