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
import ECM.Map.Map;

/************************************************************
 * Insert is used to Initialize the panel Insert<BR>
 * 
 * @author SouhailMaraoui
 *****************************/
public class Insert
{
	public static JButton BInsert;
	public static JTextField firstName, lastName, phone, address;
	public static double longitude = 0, latitude = 0;
	public static boolean currentFrame = false;
	public static boolean canInsert;

	public static JPanel Insertion = new JPanel();

	/************************************************************
	 * Method.<BR>
	 * 
	 * Initialize the panel Insert where we can insert a new emergency.
	 * 
	 * 	@param username 	the username of the current connected user.
	 * 
	 *  @return Jpanel containing all the buttons, labels .. of the Insert panel
	 *****************************/
	public static JPanel Init(String username)
	{
		currentFrame = true;
		canInsert=false;
		Insertion.add(Swing.NewLabel("Patient First Name", Color.white, 25, 175, 300));
		Insertion.add(Swing.NewLabel("Patient Last Name", Color.white, 25, 175, 350));
		Insertion.add(Swing.NewLabel("Caller's Number", Color.white, 25, 175, 400));
		Insertion.add(Swing.NewLabel("Address", Color.white, 25, 175, 450));
		Insertion.add(Swing.NewLabel("Priority", Color.white, 25, 175, 550));

		firstName = Swing.NewTextField(350, 35, 405, 300);
		lastName = Swing.NewTextField(350, 35, 405, 350);
		phone = Swing.NewTextField(350, 35, 405, 400);
		address = Swing.NewTextField(450, 30, 305, 450);

		String[] priorities ={ "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		JComboBox<String> priority = Swing.NewComboBox(priorities, 400, 550);
		Insertion.add(priority);

		Insertion.add(firstName);
		Insertion.add(lastName);
		Insertion.add(phone);
		Insertion.add(address);
		address.setEditable(false);
		BInsert = Swing.NewButton("Insert",Color.gray, 20, 200, 40, 250, 700);

		Insertion.add(BInsert);
		JButton Clear = Swing.NewButton("Clear", Color.gray, 15, 200, 40, 520, 700);
		Clear.setIcon(new ImageIcon("res/ECM/Insert/ClearIcon.png"));
		Insertion.add(Clear);

		BInsert.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				if(canInsert)
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
							int idPatient=Patient.getId(FirstName, LastName);
							if(idPatient==-1) 
								{
									idPatient=Patient.count()+1;
									Patient.insert(FirstName, LastName, null, 0, 0, 0, null);
								}
							Emergency.insert(idPatient, strDate, Address, latitude, longitude, priority.getSelectedIndex()+1);

							BInsert.setBackground(Color.GRAY);
							BInsert.setIcon(null);
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
				else
				{
					JOptionPane.showMessageDialog(null, "Please right click on the map to select an adress", "Error",
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
		Back.setIcon(new ImageIcon("res/ECM/Insert/ReturnIcon.png"));
		Insertion.add(Back);

		Back.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				currentFrame = false;
				canInsert=false;
				Insertion.removeAll();
				Home.Home.removeAll();
				Window.panel.add(Home.Init(username), "0");
				Window.cl.show(Window.panel, "0");
				Map.deleteWaypoint();
			}
		});

		Insertion.add(Swing.NewImage("res/ECM/Insert/Background.png", 1920, 1080, 0, 0));
		Insertion.setLayout(null);
		return Insertion;
	}

	/************************************************************
	 * Method.<BR>
	 * 
	 * Clear inserted information.
	 * 
	 *****************************/
	public static void Clear()
	{
		firstName.setText("");
		lastName.setText("");
		phone.setText("");
		address.setText("");
	}
	/************************************************************
	 * Method.<BR>
	 * 
	 * Check if a string is an integer or not.
	 * 
	 * 	@param str 	The string we want to check.
	 *  @return is the string entered true or false.
	 *****************************/
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
