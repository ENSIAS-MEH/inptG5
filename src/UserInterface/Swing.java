package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Main.Main;

public class Swing
{
	static float scalex = Main.scalex;
	static float scaley = Main.scaley;

	// --------------------ScreenShot-----------------------------------------------------------------------------------
	static public BufferedImage screenShot(int x, int y, int w, int h)
	{
		try
		{
			Robot rbt = new Robot();
			BufferedImage background = rbt.createScreenCapture(
					new Rectangle((int) (scalex * x), (int) (scalex * y), (int) (scalex * w), (int) (scalex * h)));
			return background;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	// -------------------NewLabel--------------------------------------------------------------------------------------
	public static JLabel NewLabel(String text, Color color, int size, int width, int height, int x, int y)
	{
		JLabel Label = new JLabel(text);
		Label.setFont(new Font("Century Gothic", Font.ITALIC, (int) (scalex * size)));
		Label.setForeground(color);
		Label.setBounds((int) (scalex * x), (int) (scaley * y), (int) (scalex * width), (int) (scaley * height));
		Label.setHorizontalAlignment(SwingConstants.LEFT);
		return Label;
	}

	public static JLabel NewLabel(String text, int size, int x, int y)
	{
		return NewLabel(text, Color.BLACK, size, x, y);
	}

	public static JLabel NewLabel(String text, Color color, int size, int x, int y)
	{
		return NewLabel(text, color, size, (int) (text.length() * size / 1.3), (int) (size * 1.2), x, y);
	}

	public static JButton NewButton(String text, Color color, int size, int width, int height, int x, int y)
	{
		JButton Button = new JButton(text);
		Button.setBackground(color);
		Button.setFont(new Font("Century Gothic", Font.BOLD, (int) (scalex * size)));
		Button.setForeground(Color.WHITE);
		Button.setLocation((int) (scalex * x), (int) (scaley * y));
		Button.setSize((int) (scalex * width), (int) (scaley * height));
		Button.setFocusPainted(false);

		return Button;
	}

	public static JButton NewButton(String test, int x, int y)
	{
		return NewButton(test, 20, 120, 40, x, y);
	}

	public static JButton NewButton(String test, int size, int x, int y)
	{
		return NewButton(test, size, 120, 40, x, y);
	}

	public static JButton NewButton(String test, Color color, int x, int y)
	{
		return NewButton(test, color, 20, 120, 40, x, y);
	}

	public static JButton NewButton(String test, Color color, int size, int x, int y)
	{
		return NewButton(test, color, size, 120, 40, x, y);
	}

	public static JButton NewButton(String text, int size, int width, int height, int x, int y)
	{
		return NewButton(text, new Color(0, 200, 200), size, width, height, x, y);
	}

	// ----------------------NewTextPane--------------------------------------------------------------------------------

	public static JTextField NewTextField(int width, int height, int x, int y)
	{
		JTextField TextField = new JTextField();
		TextField.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * (height - 13))));
		TextField.setLocation((int) (scalex * x), (int) (scaley * y));
		TextField.setSize((int) (scalex * width), (int) (scaley * height));
		return TextField;
	}

	public static JPasswordField NewPasswordField(int width, int height, int x, int y)
	{
		JPasswordField PasswordFiel = new JPasswordField();
		PasswordFiel.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * (height - 13))));
		PasswordFiel.setLocation((int) (scalex * x), (int) (scaley * y));
		PasswordFiel.setSize((int) (scalex * width), (int) (scaley * height));
		return PasswordFiel;
	}

	// -------------------NewImage--------------------------------------------------------------------------------------
	public static JLabel NewImage(String path, int w, int h, int x, int y)
	{
		ImageIcon img = new ImageIcon(path);
		JLabel label = new JLabel();

		Image image = img.getImage();
		Image newimg = image.getScaledInstance((int) (scalex * w), (int) (scaley * h), java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		label.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
		label.setIcon(img);
		return label;
	}

	public static JLabel NewImage(String path, int x, int y)
	{
		ImageIcon img = new ImageIcon(path);
		return NewImage(path, img.getIconWidth(), img.getIconHeight(), x, y);
	}

	// --------------------NewComboBox----------------------------------------------------------------------------------
	public static JComboBox<String> NewComboBox(String[] list, int w, int h, int x, int y)
	{
		JComboBox<String> ComboBox = new JComboBox<>(list);
		ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 18)));
		ComboBox.setBounds((int) (scalex * x), (int) (scaley * y), (int) (scalex * w), (int) (scaley * h));
		ComboBox.setBackground(Color.white);
		return ComboBox;
	}

	public static JComboBox<String> NewComboBox(String[] list, int x, int y)
	{
		return NewComboBox(list, 350, 35, x, y);
	}

	// --------------------NewCheckBox----------------------------------------------------------------------------------
	public static JCheckBox NewCheckBox(String text, int x, int y)
	{

		JCheckBox CheckBox = new JCheckBox(text);
		CheckBox.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 18)));
		CheckBox.setBounds((int) (scalex * x), (int) (scalex * y), (int) (scalex * text.length() * 12),
				(int) (scalex * 20));
		return CheckBox;
	}
}
