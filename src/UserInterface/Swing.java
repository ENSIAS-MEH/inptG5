package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Main.Main;

/************************************************************
 * Swing is used to create the most used java swing interface elements like JLabel,JButton, JTextField...<BR>
 * 
 * @author SouhailMaraoui
 *****************************/

public class Swing
{
	static float scalex = Main.scalex;
	static float scaley = Main.scaley;

	// -------------------NewLabel--------------------------------------------------------------------------------------
	/**
	 * Constructor .<BR>
	 * Used to create JLabel and specify the text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JLabel content.
	 * @param color  JLabel text color.
	 * @param size   JLabel font size.
	 * @param width  JLabel bounds width.
	 * @param height JLabel bounds height.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JLabel
	 */
	public static JLabel NewLabel(String text, Color color, int size, int width, int height, int x, int y)
	{
		JLabel Label = new JLabel(text);
		Label.setFont(new Font("Century Gothic", Font.ITALIC, (int) (scalex * size)));
		Label.setForeground(color);
		Label.setBounds((int) (scalex * x), (int) (scaley * y), (int) (scalex * width), (int) (scaley * height));
		Label.setHorizontalAlignment(SwingConstants.LEFT);
		return Label;
	}

	/**
	 * Constructor .<BR>
	 * Used to create JLabel and specify the text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JLabel content.
	 * @param size   JLabel font size.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JLabel
	 */
	public static JLabel NewLabel(String text, int size, int x, int y)
	{
		return NewLabel(text, Color.BLACK, size, x, y);
	}

	/**
	 * Constructor .<BR>
	 * Used to create JLabel and specify the text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JLabel content.
	 * @param color  JLabel text color.
	 * @param size   JLabel font size.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JLabel
	 */
	public static JLabel NewLabel(String text, Color color, int size, int x, int y)
	{
		return NewLabel(text, color, size, (int) (text.length() * size / 1.3), (int) (size * 1.2), x, y);
	}

	// ----------------NewButton-------------------------------------------------------------------------------------------
	/**
	 * Constructor .<BR>
	 * Used to create JButton and specify the button text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JButton content.
	 * @param color  JButton text color.
	 * @param size   JButton font size.
	 * @param width  JButton bounds width.
	 * @param height JButton bounds height.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JButton
	 */
	public static JButton NewButton(String text, Color color, int size, int width, int height, int x, int y)
	{
		JButton Button = new JButton(text);
		Button.setBackground(color);
		Button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		Button.setFont(new Font("Century Gothic", Font.BOLD, (int) (scalex * size)));
		Button.setForeground(Color.WHITE);
		Button.setLocation((int) (scalex * x), (int) (scaley * y));
		Button.setSize((int) (scalex * width), (int) (scaley * height));
		Button.setFocusPainted(false);

		return Button;
	}
	/**
	 * Constructor .<BR>
	 * Used to create JButton and specify the button text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JButton text content.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JButton
	 */
	public static JButton NewButton(String text, int x, int y)
	{
		return NewButton(text, 20, 120, 40, x, y);
	}

	/**
	 * Constructor .<BR>
	 * Used to create JButton and specify the button text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JButton text content.
	 * @param size   JButton font size.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JButton
	 */
	public static JButton NewButton(String text, int size, int x, int y)
	{
		return NewButton(text, size, 120, 40, x, y);
	}

	/**
	 * Constructor .<BR>
	 * Used to create JButton and specify the button text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JButton text content.
	 * @param color  JButton text color.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JButton
	 */
	public static JButton NewButton(String text, Color color, int x, int y)
	{
		return NewButton(text, color, 20, 120, 40, x, y);
	}

	/**
	 * Constructor .<BR>
	 * Used to create JButton and specify the button text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JButton text content.
	 * @param color  JButton text color.
	 * @param size   JButton font size.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JButton
	 */
	public static JButton NewButton(String text, Color color, int size, int x, int y)
	{
		return NewButton(text, color, size, 120, 40, x, y);
	}

	/**
	 * Constructor .<BR>
	 * Used to create JButton and specify the button text content, the text color, the font size,
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param text   JButton text content.
	 * @param size   JButton font size.
	 * @param width  JButton bounds width.
	 * @param height JButton bounds height.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JButton
	 */
	public static JButton NewButton(String text, int size, int width, int height, int x, int y)
	{
		return NewButton(text, new Color(0, 200, 255), size, width, height, x, y);
	}

	// ----------------------NewTextPane--------------------------------------------------------------------------------

	/**
	 * Constructor .<BR>
	 * Used to create JtextField and specify its width and height
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param width  JtextField bounds width.
	 * @param height JtextField bounds height.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JtextField
	 */
	public static JTextField NewTextField(int width, int height, int x, int y)
	{
		JTextField TextField = new JTextField();
		TextField.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * (height - 13))));
		TextField.setLocation((int) (scalex * x), (int) (scaley * y));
		TextField.setSize((int) (scalex * width), (int) (scaley * height));
		return TextField;
	}

	/**
	 * Constructor .<BR>
	 * Used to create JPasswordField and specify its width and height
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param width  JPasswordField bounds width.
	 * @param height JPasswordField bounds height.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JPasswordField
	 */
	public static JPasswordField NewPasswordField(int width, int height, int x, int y)
	{
		JPasswordField PasswordFiel = new JPasswordField();
		PasswordFiel.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * (height - 13))));
		PasswordFiel.setLocation((int) (scalex * x), (int) (scaley * y));
		PasswordFiel.setSize((int) (scalex * width), (int) (scaley * height));
		return PasswordFiel;
	}

	// -------------------NewImage--------------------------------------------------------------------------------------
	/**
	 * Constructor .<BR>
	 * Used to create JLabel containing an image from a path and specify its width and height
	 * and the x et y positions in the frame.<BR>
	 * 
	 * @param path 	The image path or location.
	 * @param w		Image width.
	 * @param h		Image height.
	 * @param x     The horizontal coordinate in the frame.
	 * @param y     The vertical coordinate in the frame.
	 *
	 * @return The created JLabel
	 */
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
	/**
	 * Constructor .<BR>
	 * Used to create JLabel containing an image from a path and specify its width and height
	 * and the x et y positions in the frame.<BR>
	 * 	 
	 * @param path 	 The image path or location.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JLabel
	 */
	public static JLabel NewImage(String path, int x, int y)
	{
		ImageIcon img = new ImageIcon(path);
		return NewImage(path, img.getIconWidth(), img.getIconHeight(), x, y);
	}

	// --------------------NewComboBox----------------------------------------------------------------------------------
	/**
	 * Constructor .<BR>
	 * Used to create JComboBox and specify its content, width and height
	 * and the x et y positions in the frame.<BR>
	 * 
 	 * @param list 	The string content of the combobox.
	 * @param w		JComboBox width.
	 * @param h		JComboBox height.
	 * @param x     The horizontal coordinate in the frame.
	 * @param y     The vertical coordinate in the frame.
	 *
	 * @return The created JComboBox
	 */
	public static JComboBox<String> NewComboBox(String[] list, int w, int h, int x, int y)
	{
		JComboBox<String> ComboBox = new JComboBox<>(list);
		ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 18)));
		ComboBox.setBounds((int) (scalex * x), (int) (scaley * y), (int) (scalex * w), (int) (scaley * h));
		ComboBox.setBackground(Color.white);
		return ComboBox;
	}

	/**
	 * Constructor .<BR>
	 * Used to create JComboBox and specify its content
	 * and the x et y positions in the frame.<BR>
	 * 
 	 * @param list 	 The string content of the combobox.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JComboBox
	 */
	public static JComboBox<String> NewComboBox(String[] list, int x, int y)
	{
		return NewComboBox(list, 350, 35, x, y);
	}

	// --------------------NewCheckBox----------------------------------------------------------------------------------
	
	/**
	 * Constructor .<BR>
	 * Used to create JCheckBox and specify its text and x et y positions in the frame.<BR>
	 * 
 	 * @param text 	 The string content of the combobox.
	 * @param x      The horizontal coordinate in the frame.
	 * @param y      The vertical coordinate in the frame.
	 *
	 * @return The created JComboBox
	 */
	public static JCheckBox NewCheckBox(String text, int x, int y)
	{

		JCheckBox CheckBox = new JCheckBox(text);
		CheckBox.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 18)));
		CheckBox.setBounds((int) (scalex * x), (int) (scalex * y), (int) (scalex * text.length() * 12),
				(int) (scalex * 20));
		return CheckBox;
	}
}
