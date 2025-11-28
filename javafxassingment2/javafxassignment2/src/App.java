import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
public class App extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        //Create circle
        Circle circ = new Circle(100);
        circ.setFill(Color.BLACK);
        circ.setCenterX(100);
        circ.setCenterY(100);

        //Add mouse click events to circle
        circ.setOnMousePressed(event->{
            circ.setFill(Color.BLACK);
        });

        circ.setOnMouseReleased(event->{
            circ.setFill(Color.WHITE);
        });
        //Create stack pane to hold circle
        StackPane stack = new StackPane(circ);
        stack.setStyle("-fx-background-color: green;");
        //Create scene to hold stack pane
        Scene scene = new Scene(stack,500,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circle test");
        primaryStage.show();
        
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
