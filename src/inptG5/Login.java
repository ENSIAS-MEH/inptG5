package inptG5;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {
	static Stage classStage = new Stage();
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) throws Exception {
		
		Utility u = new Utility();
		
		//-------------Setting up database Conncetion
		Connection connection=null;
//		Connection connection=DBconnect.get_connection();
		
		
		//-------------title of window
		primaryStage.setTitle("Login");
		
		
		//-------------Form
		Label l1 = new Label(" Welcome to Admins App ");
		l1.setFont(new Font("Centry Gothic",22));
		l1.setStyle("-fx-font-weight: bold;");
		Label l2 = new Label("Username:");
		TextField username = new TextField();
		username.setPromptText("Enter username...");
		Label l3 = new Label("Password:");
		PasswordField passwd   = new PasswordField();
		passwd.setPromptText("Enter password...");
		Label l4 = new Label("STATUS");
		l4.setTextFill(Color.FORESTGREEN);
		ImageView imageView = u.Imgr("login.png");
        Button b1= u.Bwsh("Log in", imageView);
        b1.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		b1.setText("Please wait...");
        		b1.setDisable(true);
        		if(DBconnect.get_connection()==null) {
        			l4.setText("[SRV_ERR] Communication with Database ERROR");
        			l4.setTextFill(Color.RED);
        			b1.setTextFill(Color.BROWN);
        			b1.setText("Log in");
        			b1.setDisable(false);
        		}
        		else {
	        		ResultSet rs = DBconnect.get_result(DBconnect.get_connection(), "Select Username, Password, IdType FROM HumanResource;");
	        		try {
	        			boolean s=false;
	        			while(rs.next() && s==false) {
	        				if ( Integer.toString(rs.getInt(3)).equals("0") && rs.getString(1).equals(username.getText()) && rs.getString(2).equals(passwd.getText())) {
	        					b1.setText("Logged in succesfully");
	        					Loggedin lg = new Loggedin();
		    					lg.start(primaryStage);
	        					s=true;
	        				}}
	    				if(s==false) {
	    					l4.setText("[AUTH_ERR] Username / Password incorrect");
	    					l4.setTextFill(Color.RED);
	    					b1.setText("log in");
	    					b1.setDisable(false);
	    				}
	        			}catch (Exception e) {
	        			e.printStackTrace();
	        			}
	        		passwd.clear();
        		}
        	}
        }
     );
		
			
		//Layout
		HBox h1 = new HBox();
		h1.setSpacing(7);
		h1.getChildren().addAll(l2, username);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox();
		h2.setSpacing(9);
		h2.getChildren().addAll(l3, passwd);
		h2.setAlignment(Pos.CENTER);
		VBox v1 = new VBox();
		v1.setPadding(new Insets(20,20,20,20));
		v1.setAlignment(Pos.CENTER);
		v1.setSpacing(10);
		
		v1.getChildren().addAll(l1, h1, h2, b1, l4);
		
		//LOGO
		ImageView iv = u.Imgr("logo.png");
				
		//CloseButton and ending Server Connection
		Utility.Windowclose CloseB = u.new Windowclose(connection, "Fermer", primaryStage);
		CloseB.setAlignment(Pos.CENTER_RIGHT); // where the button should be layouted (right, left)

		
		//BorderPane : set the layout form
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color: aliceblue");
		bp.setLeft(iv);
		bp.setTop(CloseB);
		bp.setCenter(v1);
		
		//Scene
		Scene sc = new Scene(bp, 800, 400);
		primaryStage.setScene(sc);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}
}