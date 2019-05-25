import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextFieldDemo extends Application{
	protected Text text = new Text(150, 50, "JavaFX Programming");// 文本和位置

	protected BorderPane getPane() {// 可选择放置位置的pane
		HBox paneForButtons = new HBox(20);
		Button btLeft = new Button("Left", new ImageView("I.png"));
		Button btRight = new Button("Right", new ImageView("J.png"));
		paneForButtons.getChildren().addAll(btLeft, btRight);
		paneForButtons.setAlignment(Pos.CENTER);
		paneForButtons.setStyle("-fx-border-color: green");

		BorderPane pane = new BorderPane();
		pane.setBottom(paneForButtons);

		Pane paneForText = new Pane();
		paneForText.getChildren().add(text);
		pane.setCenter(paneForText);

		btLeft.setOnAction(e -> text.setX(text.getX() - 10));
		btRight.setOnAction(e -> text.setX(text.getX() + 10));

		Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
		Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
		Font fontItalic = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20);
		Font fontNormal = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 20);

		text.setFont(fontNormal);//设置字体

		VBox paneForCheckBoxes = new VBox(20);//竖向排列
		paneForCheckBoxes.setPadding(new Insets(10, 10, 10, 10));//设置间隙
		paneForCheckBoxes.setStyle("-fx-border-color: red");
		CheckBox chkBold = new CheckBox("Bold");
		CheckBox chkItalic = new CheckBox("Italic");
		paneForCheckBoxes.getChildren().addAll(chkBold, chkItalic);
		pane.setRight(paneForCheckBoxes);//放置在右方

		EventHandler<ActionEvent> handler = e -> {
			if (chkBold.isSelected() && chkItalic.isSelected()) {
				text.setFont(fontBoldItalic); // Both check boxes checked
			} else if (chkBold.isSelected()) {
				text.setFont(fontBold); // The Bold check box checked
			} else if (chkItalic.isSelected()) {
				text.setFont(fontItalic); // The Italic check box checked
			} else {
				text.setFont(fontNormal); // Both check boxes unchecked
			}
		};

		chkBold.setOnAction(handler);
		chkItalic.setOnAction(handler);
		
		VBox paneForRadioButtons = new VBox(20);
	    paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5)); 
	    paneForRadioButtons.setStyle
	      ("-fx-border-width: 2px; -fx-border-color: green");
	    RadioButton rbRed = new RadioButton("Red");
	    RadioButton rbGreen = new RadioButton("Green");
	    RadioButton rbBlue = new RadioButton("Blue");
	    paneForRadioButtons.getChildren().addAll(rbRed, rbGreen, rbBlue);
	    pane.setLeft(paneForRadioButtons);//添加按钮栏

	    ToggleGroup group = new ToggleGroup();
	    rbRed.setToggleGroup(group);
	    rbGreen.setToggleGroup(group);
	    rbBlue.setToggleGroup(group);//三个键加入同一个组
	    
	    rbRed.setOnAction(e -> {
	      if (rbRed.isSelected()) {
	        text.setFill(Color.RED);
	      }
	    });
	    
	    rbGreen.setOnAction(e -> {
	      if (rbGreen.isSelected()) {
	        text.setFill(Color.GREEN);
	      }
	    });

	    rbBlue.setOnAction(e -> {
	      if (rbBlue.isSelected()) {
	        text.setFill(Color.BLUE);
	      }
	    });
	    
	    BorderPane paneForTextField = new BorderPane();
	    paneForTextField.setPadding(new Insets(5, 5, 5, 5)); 
	    paneForTextField.setStyle("-fx-border-color: yellow");
	    paneForTextField.setLeft(new Label("Enter a new message: "));
	    
	    TextField tf = new TextField();
	    tf.setAlignment(Pos.BOTTOM_RIGHT);
	    paneForTextField.setCenter(tf);
	    pane.setTop(paneForTextField);
	    
	    tf.setOnAction(e -> text.setText(tf.getText()));

		return pane;
	}

	public void start(Stage primaryStage) {
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(getPane(), 450, 200);
	    primaryStage.setTitle("CheckBoxDemo"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }

	public static void main(String[] args) {
		launch(args);
	}
}