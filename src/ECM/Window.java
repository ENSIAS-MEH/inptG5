package ECM;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ECM.Home;

class Window
{
	static JFrame frame;
	static JPanel map;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static void init(String username)
	{
		float scalex = Login.scalex;
		float scaley = Login.scaley;
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.add(Home.Init(username));
		frame.setSize((int) (1920 * scalex), (int) (1080 * scaley));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}

	