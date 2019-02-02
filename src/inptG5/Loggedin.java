package inptG5;

import java.sql.Connection;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Loggedin extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Connection connection=null;
		//Connection connection=DBconnect.get_connection();
		Utility u = new Utility();
		stage.setTitle("Administration");

		
		//Buttons Choices
		ImageView mr = u.Imgr("ambulance.png");
        Button MR= u.Bwsh("   Material Resources", mr);
        
        ImageView hr = u.Imgr("interview.png");
        Button HR = u.Bwsh("   Human Resources", hr);
        
        ImageView lg = u.Imgr("log.png");
		Button log = u.Bwsh("  Log", lg);
		
		ImageView eg = u.Imgr("warning.png");
		Button emr = u.Bwsh("  Emergency", eg);
		
		ImageView am = u.Imgr("ambulance.png");
        Button amb= u.Bwsh("   Ambulance", am);
        
        ImageView rm = u.Imgr("room.png");
        Button RM= u.Bwsh("   Room", rm);
		
        ImageView home = u.Imgr("homepage.png");
        Button Home = u.Bwsh("", home);
        
        ImageView cl = u.Imgr("call.png");
        Button call = u.Bwsh("   Call", cl);
        
        ImageView hs = u.Imgr("clock.png");
        Button hist = u.Bwsh("  History", hs);
        
        
        
		MR.setMinSize(300, 100);
		HR.setMinSize(300, 100);
		log.setMinSize(300, 100);
		emr.setMinSize(300, 100);
		RM.setMinSize(300, 100);
		amb.setMinSize(300, 100);
		call.setMinSize(300, 100);
		hist.setMinSize(300, 100);
		
		//Log out
		Utility.Windowclose CloseB = u.new Windowclose(connection, "Log out", stage);
		CloseB.setAlignment(Pos.CENTER_RIGHT); // where the button should be layouted (right, left)
		
		
		//Welcoming
		Label l1 = new Label("Welcome ADMIN, please choose a Type");
		l1.setStyle("-fx-font: 26 CenturyGothic; -fx-font-weight: Bold;");
		
       	//HBoxes
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		VBox v1 = new VBox();
		
		
		h1.setSpacing(20);
		h2.setSpacing(20);
		v1.setSpacing(20);
		v1.setPadding(new Insets(10,10,10,10));
		v1.setAlignment(Pos.CENTER);
		Home.setPadding(new Insets(2,2,2,2));
		
		h1.getChildren().addAll(MR, HR);
		h2.getChildren().addAll(log, emr);
		v1.getChildren().addAll(l1, h1, h2);
		

		//LOGO
				ImageView iv = u.Imgr("logo.png");
		//Border
				BorderPane bp = new BorderPane();
				bp.setTop(CloseB);
				bp.setLeft(v1);
				bp.setRight(iv);
				bp.setBottom(Home);
				BorderPane.setAlignment(Home, Pos.BOTTOM_RIGHT);
				BorderPane.setMargin(Home, new Insets(8,8,8,8));
				BorderPane.setAlignment(v1, Pos.CENTER_LEFT);
				BorderPane.setAlignment(iv, Pos.CENTER_RIGHT);
				bp.setStyle("-fx-background-color: aliceblue");

		//Set Timer
				
				// create transition for logout
			    Duration delay = Duration.seconds(10);
			    PauseTransition transition = new PauseTransition(delay);
				// setting up action on inactivity
			    transition.setOnFinished(evt -> logout(stage));
			    
	  //Scene
		Scene sc = new Scene(bp);
		
			    // restart transition on user interaction
			    sc.addEventFilter(InputEvent.ANY, evt -> transition.playFromStart());
				
		//setting up Stage
		stage.setScene(sc);
		stage.setOnCloseRequest(event -> {
			logout(stage);
		});
		stage.setResizable(false);
		stage.show();
		
		
		//ButtonAction
		Home.setOnAction(e->{
			h1.getChildren().clear();
			h2.getChildren().clear();
			v1.getChildren().clear();
			h1.getChildren().addAll(MR, HR);
			h2.getChildren().addAll(log, emr);
			v1.getChildren().addAll(l1, h1, h2);
			bp.getChildren().clear();
			bp.setTop(CloseB);
			bp.setLeft(v1);
			bp.setRight(iv);
			bp.setBottom(Home);
			BorderPane.setAlignment(Home, Pos.BOTTOM_RIGHT);
			BorderPane.setMargin(Home, new Insets(8,8,8,8));
			BorderPane.setAlignment(v1, Pos.CENTER_LEFT);
			BorderPane.setAlignment(iv, Pos.CENTER_RIGHT);
			stage.setFullScreen(false);
		});
		
		HR.setOnAction(e->{
			HumanResource.display(bp, stage);
			
		});
		MR.setOnAction(e ->{
			l1.setText("What Material Resource do you want .. :");
			h1.getChildren().clear();
			h1.getChildren().addAll(RM, amb);
			v1.getChildren().clear();
			v1.getChildren().addAll(l1 ,h1);
		});
		
		log.setOnAction(e ->{
			l1.setText("What history do you want .. :");
			h1.getChildren().clear();
			h1.getChildren().addAll(call, hist);
			v1.getChildren().clear();
			v1.getChildren().addAll(l1 ,h1);
		});
		
		emr.setOnAction(e->{
			Emergency.display(bp, stage);
		});
		
		call.setOnAction(e ->{
			Call.display(bp, stage);
		});
		
		amb.setOnAction(e ->{
			Ambulance.display(bp, stage);
		});
	}
	
	public void logout(Stage stage) {
		Login l = new Login();
		Stage s= new Stage();
		try {
			stage.close();
			l.start(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}