import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Labeled;
/**
 * Write a description of class IntersectionBorderPane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntersectionBorderPane extends BorderPane
{
    private Circle circle1;
    private Circle circle2;

    private GridPane circleGridPane;
    
    private TextField circle1X;
    private TextField circle1Y;
    private TextField circle1Radius;
    private TextField circle1Intersection;
    private TextField circle2X;
    private TextField circle2Y;
    private TextField circle2Radius;
    private TextField circle2Intersection;
    
    private HBox circleHBox;
    
    
    
    public IntersectionBorderPane()
    {
       buildUI();
       customizeUI();
       registerHandlers();
    }
    
   
    public void buildUI() {
      circle1 = new Circle(60, 100, 20);
      circle1.setFill(Color.RED);
      
      circle2 = new Circle(60, 60, 20);
      circle2.setFill(Color.GREEN);
      
     getChildren().add(circle1);
     getChildren().add(circle2);
   
      
      circleGridPane = new GridPane();
      
      circle1X = new TextField();
      circle1Y = new TextField();
      circle1Radius = new TextField();
      circle1Intersection = new TextField();
      
      circle2X = new TextField();
      circle2Y = new TextField();
      circle2Radius = new TextField();
      circle2Intersection = new TextField();
      //setting up the look of the outputs//
     circleGridPane.add( new Label("Circle 1 X: "), 0, 0);               circleGridPane.add( circle1X, 1, 0);
     circleGridPane.add( new Label("Circle 1 Y: "), 0, 1);               circleGridPane.add( circle1Y, 1, 1);
     circleGridPane.add( new Label("Circle 1 Radius: "), 0, 2);          circleGridPane.add( circle1Radius, 1, 2);
     circleGridPane.add( new Label("Circle 1 Intersection: "), 0, 3);    circleGridPane.add( circle1Intersection, 1, 3);
      
     circleGridPane.add( new Label("Circle 2 X: "), 3, 0);              circleGridPane.add( circle2X, 4, 0);
     circleGridPane.add( new Label("Circle 2 Y: "), 3, 1);              circleGridPane.add( circle2Y, 4, 1);
     circleGridPane.add( new Label("Circle 2 Radius: "), 3, 2);         circleGridPane.add( circle2Radius, 4, 2);
     circleGridPane.add( new Label("Circle 2 Intersection: "), 3, 3);   circleGridPane.add( circle2Intersection, 4, 3);
      
     //HBox circleHBox = new HBox(10.0, , previousButton);
     
     //circleHBox = new HBox(30.0, circle1HBox, circle2HBox); 
      
      setBottom(circleGridPane);
  
    }
    
    
    private void customizeUI() {
         setPadding( new Insets(20.0, 20.0, 0.0, 20.0) );
         
         
    }
    
     private void registerHandlers() {
          circle1.setOnMouseDragged( e -> moveCircle1(e) );
          circle2.setOnMouseDragged( f -> moveCircle2(f) );
          
          
    }
        
       
    public void moveCircle1(MouseEvent e) {
  
     
     
      circle1.setCenterX( e.getX() );
      circle1.setCenterY( e.getY() );
      
      
      circle1X.setText(Double.toString(circle1.getCenterX() ) );
      circle1Y.setText(Double.toString(circle1.getCenterY() ) );
      circle1Radius.setText(Double.toString(circle1.getRadius() ) );
      
      circle1Intersection.setText( Boolean.toString( checkIntersection() ) );
    }
    
    public void moveCircle2(MouseEvent f) {
        circle2.setCenterX( f.getX() );
        circle2.setCenterY( f.getY() );
        
        
        circle2X.setText(Double.toString(circle2.getCenterX() ) );
        circle2Y.setText(Double.toString(circle2.getCenterY() ) );
        circle2Radius.setText(Double.toString(circle2.getRadius() ) );
        circle2Intersection.setText( Boolean.toString( checkIntersection() ) );
    }
    
    public boolean checkIntersection() {
     double dx = ( circle2.getCenterX() ) - ( circle1.getCenterX() );
     double dy = ( circle2.getCenterY() ) - ( circle1.getCenterY() );
     
      double d = Math.sqrt((dy * dy) + (dx * dx));
      
      if (d > ( (circle1.getRadius() ) + (circle2.getRadius() ) ) ) {
          return false;
        } else if (d < Math.abs( circle1.getRadius() - circle2.getRadius() )) {
              return false;
        } else {
            return true;
        }
}
}