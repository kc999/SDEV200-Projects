import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.scene.image.*;
import javafx.scene.Scene;
public class App extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        //Create new grid
        GridPane grid = new GridPane();
        //Create images
        Image image1 = new Image(getClass().getResourceAsStream("/images/flag1.gif"));
        Image image2 = new Image(getClass().getResourceAsStream("/images/flag2.gif"));
        Image image3 = new Image(getClass().getResourceAsStream("/images/flag6.gif"));
        Image image4 = new Image(getClass().getResourceAsStream("/images/flag7.gif"));

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        //France flag is a little small, make it bigger without messing up aspect ratio
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(400);
        ImageView imageView4 = new ImageView(image4);
        //Add images to grid
        grid.add(imageView1,0,0);
        grid.add(imageView2,0,1);
        grid.add(imageView3,1,0);
        grid.add(imageView4,1,1);
        //Create new Scene for grid to live in
        Scene primaryScene = new Scene(grid,850,425);
        //Set gaps and position in center
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        primaryStage.setTitle("Hello!");
        
    }
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
