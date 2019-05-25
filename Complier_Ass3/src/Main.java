import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
 


/** File: wk10ec
 *  Name: Sean Mathews
 *  Revised: <date revised>
 *  Course: CS150 - Introduction to Computing II
 *
 *  Desc: <a description of what Main does>
 */
public class Main
{

    public void buildUI()
    {
        // NOTE: This method can be customized to your specific needs.
        // Create the stage.
        Stage primaryStage = new Stage();
        IntersectionBorderPane pane = new IntersectionBorderPane();

        Scene scene = new Scene( pane , 300, 300 );
        
        primaryStage.setTitle("wk10");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //this allows the change of color for each object//
       // pane.requestFocus();
        
//         new Button("Welcome to JavaFX")
    }// end buildUI()
    
    public void launchFX()
    {
        // NOTE: This method should not be changed.
        
        // Initialize JavaFX.
        new JFXPanel();
        // Makes sure that JavaFX doesn't exit when the first window is closed.
        
        Platform.setImplicitExit(false);
        // Runs initialization on the JavaFX thread.
        
        Platform.runLater( () -> buildUI() );
    }// end launchFX()
    
    public static void main(String[] args)
    {
        Main app = new Main();
        app.launchFX();
    }// end main()
}