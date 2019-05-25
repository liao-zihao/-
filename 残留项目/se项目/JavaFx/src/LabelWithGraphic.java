import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;

public class LabelWithGraphic extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ImageView us = new ImageView(new Image("I.png"));//加载图片
    Label lb1 = new Label("US", us);//图片的标签
    lb1.setStyle("-fx-border-color: green; -fx-border-width: 2");//方框风格
    lb1.setContentDisplay(ContentDisplay.BOTTOM);//内容显示在底部
    lb1.setTextFill(Color.RED);//文字是红色
    
    Label lb2 = new Label("Circle", new Circle(50, 50, 25));//圆形标签
    lb2.setContentDisplay(ContentDisplay.TOP);//圆在上面
    lb2.setTextFill(Color.ORANGE);//字体橙色

    Label lb3 = new Label("Retangle", new Rectangle(10, 10, 50, 25));
    lb3.setContentDisplay(ContentDisplay.RIGHT);
    
    Label lb4 = new Label("Ellipse", new Ellipse(50, 50, 50, 25));
    lb4.setContentDisplay(ContentDisplay.LEFT);

    Ellipse ellipse = new Ellipse(50, 50, 50, 25);
    ellipse.setStroke(Color.GREEN);//绿色曲线
    ellipse.setFill(Color.WHITE);//白色填充
    StackPane stackPane = new StackPane();//栈的方式加入面板
    stackPane.getChildren().addAll(ellipse, new Label("JavaFX"));
    Label lb5 = new Label("A pane inside a label", stackPane);
    lb5.setContentDisplay(ContentDisplay.BOTTOM);
    
    HBox pane = new HBox(50);//20代表间隔空间
    pane.getChildren().addAll(lb1, lb2, lb3, lb4, lb5);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 900, 300);
    primaryStage.setTitle("LabelWithGraphic"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

