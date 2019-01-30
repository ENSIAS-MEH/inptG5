package QMS;
	import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group; 
	import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color; 
	import javafx.stage.Stage;  

	public class window extends Application { 
	   @Override     
	   public void start(Stage primaryStage) throws Exception {            
	      //creating a Group object 
	      Group group = new Group(); 
	       
	      //Creating a Scene by passing the group object, height and width   
	      Scene scene = new Scene(group ,600, 300); 
	      
	      //setting color to the scene 
	      scene.setFill(Color.CYAN);  
	      
	      //new Button
	      Button btn = new Button();
	        btn.setText("Say 'Hello World'");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });
	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	        
	      //Setting the title to Stage. 
	      primaryStage.setTitle("Welcome !"); 
	   
	      //Adding the scene to Stage 
	      primaryStage.setScene(scene); 
	       
	      //Displaying the contents of the stage 
	      primaryStage.show(); 
	   }    
	   public static void main(String args[]){          
	      launch(args);     
	   }         
	} 
