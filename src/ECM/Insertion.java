package ECM;

import java.awt.Color;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import Database.Call;
import Database.Emergency;
import Database.Patient;
import UserInterface.Swing;

public class Insertion
{
	public static JTextField firstName, lastName, phone, address;
	public static double longitude = 0, latitude = 0;
	public static boolean currentFrame = false;

	public static JPanel Insertion = new JPanel();

	public static JPanel Init(String username)
	{
		currentFrame = true;
		Insertion.add(Swing.NewLabel("Caller First Name", Color.white, 25, 190, 250));
		Insertion.add(Swing.NewLabel("Caller Last Name", Color.white, 25, 190, 300));
		Insertion.add(Swing.NewLabel("Caller's Number", Color.white, 25, 190, 350));
		Insertion.add(Swing.NewLabel("Address", Color.white, 25, 190, 400));
		Insertion.add(Swing.NewLabel("Priority", Color.white, 25, 190, 550));

		firstName = Swing.NewTextField(350, 35, 405, 250);
		lastName = Swing.NewTextField(350, 35, 405, 300);
		phone = Swing.NewTextField(350, 35, 405, 350);
		address = Swing.NewTextField(420, 35, 335, 400);

		String[] priorities ={ "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		JComboBox<String> priority = Swing.NewComboBox(priorities, 400, 550);
		Insertion.add(priority);

		Insertion.add(firstName);
		Insertion.add(lastName);
		Insertion.add(phone);
		Insertion.add(address);

		JButton BInsert = Swing.NewButton("Insert", 20, 200, 40, 250, 800);
		Insertion.add(BInsert);
		JButton Clear = Swing.NewButton("Clear", Color.gray, 15, 200, 40, 520, 800);
		Insertion.add(Clear);

		BInsert.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				String FirstName=firstName.getText();
				String LastName=lastName.getText();
				String Address = address.getText();

				if (isInt(phone.getText()) && FirstName.length() > 0 && LastName.length() > 0 && Address.length() > 0)
				{
					try
					{
						Date date = new Date();
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
						String strDate = dateFormat.format(date);

						Call.insert(FirstName, LastName, date, Emergency.count()+1);
						Emergency.insert(Patient.getId(FirstName, LastName), strDate, Address, latitude, longitude, priority.getSelectedIndex());

						Clear();
					} catch (Exception e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "An error occured", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else
				{
					JOptionPane.showMessageDialog(null, "Please verify your entries", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		Clear.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				Clear();
			}
		});

		JButton Back = Swing.NewButton("Return", Color.black, 15, 150, 40, 410, 850);
		Insertion.add(Back);

		Back.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				currentFrame = false;
				Insertion.removeAll();
				Home.Home.removeAll();
				Window.panel.add(Home.Init(username), "0");
				Window.cl.show(Window.panel, "0");
			}
		});

		Insertion.add(Swing.NewImage("res/ECM/Home/Background.png", 960, 1080, 0, 0));
		Insertion.setLayout(null);
		return Insertion;
	}

	public static void Clear()
	{
		firstName.setText("");
		lastName.setText("");
		phone.setText("");
		address.setText("");
	}

	public static boolean isInt(String str)
	{
		if (str.length() > 0)
		{
			try
			{
				Integer.valueOf(str);
				return true;
			} 
			catch (Exception e){}
		}
		return false;
	}
}
