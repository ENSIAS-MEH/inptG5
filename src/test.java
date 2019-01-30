import UserInterface.JavaFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class test extends Application
{
	String[] list= {"option1","option2","option3"};
	
	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Title");
		Pane layout = new Pane();
		
		Label l			=	JavaFX.NewLabel("Test",20, 0, 0);						layout.getChildren().add(l);
		TextField t		=	JavaFX.NewTextField(20, 150, 0, 50);					layout.getChildren().add(t);
		PasswordField p	=	JavaFX.NewPasswordField(20, 150, 0, 100);				layout.getChildren().add(p);
		Button b		=	JavaFX.NewButton("test", 200, 50);						layout.getChildren().add(b);
		Button b2		=	JavaFX.NewButton("test",Color.rgb(150, 0, 100),200,100);layout.getChildren().add(b2);
		ImageView i		=	JavaFX.NewImage("res/image.png",50,50,250,200);			layout.getChildren().add(i);
	   ComboBox<String>c=	JavaFX.NewComboBox(list,150,0, 150);					layout.getChildren().add(c);
		CheckBox g		=	JavaFX.NewCheckBox("checkbox", 0, 200);					layout.getChildren().add(g);
		
		Scene scene = new Scene (layout,300,250);
		stage.setScene(scene);
		stage.show();
	}
}
