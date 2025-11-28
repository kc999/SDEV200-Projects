import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
public class App extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        Label label = new Label("Color Picker");
        label.setFont(new Font("Times New Roman",36));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(350);
        //Create Scroll Bars
        ScrollBar rScrollBar = new ScrollBar();
        ScrollBar gScrollBar = new ScrollBar();
        ScrollBar bScrollBar = new ScrollBar();
        ScrollBar oScrollBar = new ScrollBar();
        //Set scrollbar orientations
        rScrollBar.setOrientation(Orientation.HORIZONTAL);
        gScrollBar.setOrientation(Orientation.HORIZONTAL);
        bScrollBar.setOrientation(Orientation.HORIZONTAL);
        oScrollBar.setOrientation(Orientation.HORIZONTAL);
        //Set min and max
        rScrollBar.setMax(255);
        gScrollBar.setMax(255);
        bScrollBar.setMax(255);
        oScrollBar.setMax(100);
        oScrollBar.setValue(100);
        rScrollBar.setPrefWidth(225);
        gScrollBar.setPrefWidth(225);
        bScrollBar.setPrefWidth(225);
        oScrollBar.setPrefWidth(225);
        //Create Labels for each Scrollbar
        Label rLabel = new Label("Red:");
        rLabel.setPrefWidth(60);
        Label gLabel = new Label("Green:");
        gLabel.setPrefWidth(60);
        Label bLabel = new Label("Blue:");
        bLabel.setPrefWidth(60);
        Label oLabel = new Label("Opacity");
        oLabel.setPrefWidth(60);
        //Create hboxes and add like elemnts
        HBox rBox = new HBox(10);
        rBox.getChildren().add(rLabel);
        rBox.getChildren().add(rScrollBar);

        HBox gBox = new HBox(10);
        gBox.getChildren().add(gLabel);
        gBox.getChildren().add(gScrollBar);

        HBox bBox = new HBox(10);
        bBox.getChildren().add(bLabel);
        bBox.getChildren().add(bScrollBar);

        HBox oBox = new HBox(10);
        oBox.getChildren().add(oLabel);
        oBox.getChildren().add(oScrollBar);

        VBox box = new VBox(15);

        
        box.setPadding(new Insets(20));
        box.getChildren().add(label);
        box.getChildren().add(rBox);
        box.getChildren().add(gBox);
        box.getChildren().add(bBox);
        box.getChildren().add(oBox);


        //Create Listen event
        ChangeListener<Number> changeColor = (observable,oldValue,newValue) ->{
            int r = (int) rScrollBar.getValue();
            int g = (int) gScrollBar.getValue();
            int b = (int) bScrollBar.getValue();
            double o = (double) oScrollBar.getValue()/100;

            Color labelColor = Color.rgb(r,g,b,o);
            label.setTextFill(labelColor);
        };
        //Bind listen event
        rScrollBar.valueProperty().addListener(changeColor);
        gScrollBar.valueProperty().addListener(changeColor);
        bScrollBar.valueProperty().addListener(changeColor);
        oScrollBar.valueProperty().addListener(changeColor);
        Scene scene = new Scene(box,350,350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Color Picker");
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
