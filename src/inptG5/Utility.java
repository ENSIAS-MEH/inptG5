package inptG5;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.ArrayList;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Utility {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static float scalex = (float) screenSize.getWidth() / 1920;
	public static float scaley = (float) screenSize.getHeight() / 1080;
	
	//Button With Shadow
	public Button Bwsh(String t, ImageView iv) {
		Button b1 = new Button(t, iv);
        DropShadow shadow = new DropShadow();
        
        
        //------------Adding the shadow when the mouse cursor is on
        b1.addEventHandler(MouseEvent.MOUSE_ENTERED, 
        		new EventHandler<MouseEvent>() {
              		@Override public void handle(MouseEvent e) {
              			b1.setEffect(shadow);
              		}
        });
        //------------Removing the shadow when the mouse cursor is off
        b1.addEventHandler(MouseEvent.MOUSE_EXITED, 
        		new EventHandler<MouseEvent>() {
              		@Override public void handle(MouseEvent e) {
              			b1.setEffect(null);
              		}
        });
        return b1;
	}
	
	
	//Hbox
	public static ArrayList<TextField> tf(String[] list) {
		ArrayList<TextField> b= new ArrayList<TextField>();
		for (String i : list){
			TextField t = new TextField();
			t.setMinSize(60, 20);
			t.setPromptText(i);
			b.add(t);
		}
		return b;
	}
	
	public static VBox vb(VBox v, ArrayList<TextField> t ) {
		for (TextField tf : t) {
			v.getChildren().add(tf);
		}
		v.setSpacing(20);
		v.setPadding(new Insets(10,10,10,10));
		return v;
	}
	
	
	//Close Button
	class Windowclose extends FlowPane {
		public Windowclose(Connection Conn, String Action, Stage stage) {
			HBox h = new HBox();
			Label text=new Label(Action+" ");
			Label cls = new Label("X");
			text.setTextFill(Color.BLACK);
			text.setStyle("-fx-font: 15 vedrana");
			cls.setTextFill(Color.BLACK);
			cls.setStyle("-fx-font: 20 verdana;-fx-font-weight: Bold; -fx-backgound-color: red;");
			h.setPadding(new Insets(3,3,3,3));
			if(Action.equals("Log out")) {
				ImageView i = Imgr("sign-out.png");
				h.getChildren().addAll(text,i);
			}
			else {
				h.getChildren().addAll(text,cls);
			}
			
			//Coloring label with RED when the mouse cursor is on
	        h.addEventHandler(MouseEvent.MOUSE_ENTERED, 
	        		new EventHandler<MouseEvent>() {
	              		@Override public void handle(MouseEvent e) {
	              			cls.setTextFill(Color.RED);
	              			text.setTextFill(Color.RED);
	              		}
	        });
	        //Removing the RED when the mouse cursor is off
	        h.addEventHandler(MouseEvent.MOUSE_EXITED, 
	        		new EventHandler<MouseEvent>() {
	              		@Override public void handle(MouseEvent e) {
	              			cls.setTextFill(Color.BLACK);
	              			text.setTextFill(Color.BLACK);
	              		}
	        });
			h.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent ae) {
					if(Action.equals("Log out")) {
						stage.close();
						Login L = new Login();
						Stage s = new Stage();
						try {
							L.start(s);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else {
						stage.close();
					}
					}
				}
			);			
			this.getChildren().addAll(h);
			this.setAlignment(Pos.CENTER_RIGHT);
			DBconnect.Close(Conn);
		}
	}

	//Image Ready
	public ImageView Imgr(String Name) {
			Image image = new Image("/images/"+Name);
			ImageView iv = new ImageView(image);
			return iv;
		}
		
	
}
