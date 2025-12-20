import java.time.LocalDate;

import com.SDEV200Final.utility.UPCDatabase;
import com.SDEV200Final.utility.perishableItem;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import java.time.LocalDate;
public class App extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        //Create the database we will be using
        UPCDatabase upcDB = upcDatabaseInit("db");
        upcDB.loadDatabase("DatabaseFile");
        perishableItem testItem = new perishableItem("10010101",LocalDate.of(2026,12,25),"Test");
        //Create the Vbox used in the database
        VBox listAreaBox = initVBox();
        Label listAreaLabel = initDbListViewLabel();
        ListView<String> dbListView = initDbListView(upcDB);
        listAreaBox.getChildren().addAll(listAreaLabel,dbListView);
        //Create the right side of the top of the screen
        VBox infoVBox = infoVbox();
        Label infoBoxLabel = infoBoxLabel();
        Label infoUpcLabel = infoUpcLabel();
        Label infoNameLabel = infoNameLabel();
        Label infoLocationLabel = infoItemLocation();
        Label infoItemTypeLabel = infoItemType();
        Label infoManufacLabel = infoItemManufacturer();
        Label infoAddedByLabel = infoAddedBy();
        Label infoDatesLabel = infoDates();
        infoVBox.getChildren().addAll(infoBoxLabel, infoUpcLabel,infoNameLabel,infoLocationLabel,infoItemTypeLabel,infoManufacLabel,infoAddedByLabel,infoDatesLabel);
        //Put the top side in an HBOX
        HBox tBox = topBox();
        tBox.setHgrow(infoVBox, Priority.ALWAYS);
        tBox.setHgrow(listAreaBox, Priority.ALWAYS);
        infoVBox.setMaxWidth(Double.MAX_VALUE);
        listAreaBox.setMaxWidth(Double.MAX_VALUE);
        infoVBox.setFillWidth(true);
        listAreaBox.setFillWidth(true);
        tBox.getChildren().addAll(listAreaBox,infoVBox);
        Scene primScene = new Scene(tBox,600,600);
        primaryStage.setTitle("UPCApp");
        primaryStage.setScene(primScene);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    public static UPCDatabase upcDatabaseInit(String fileName)
    {
        //This will initialize the UPC database, and load any data that was saved in it
        UPCDatabase upcDatabase = new UPCDatabase();
        upcDatabase.loadDatabase(fileName);
        return upcDatabase;
    }
    //
    public static void addAndSave(perishableItem item,UPCDatabase upcDatabase, String Filename)
    {
        //Add an item to the database and save it
        upcDatabase.addItem(item);
        upcDatabase.saveDatabase(Filename);
        System.out.println("Item successfully saved to the database.");
    }
    public static Label initDbListViewLabel()
    {
        Label lab = new Label("Items");
        lab.setFont(new Font("Garamond", 16));
        lab.setAlignment(Pos.CENTER);
        lab.setMaxWidth(Double.MAX_VALUE);
        return lab;
    }
    public static ListView<String> initDbListView(UPCDatabase dataB)
    {
        ListView<String> listItems = new ListView<>();
        ObservableList<String> items = dataB.getListViewData();
        listItems.setItems(items);
        return listItems;
    }
    public static VBox initVBox()
    {
        VBox vbox = new VBox();
        return vbox;
    }
    //Create right side of top of screen
    public static VBox infoVbox()
    {
        VBox infoVbox = new VBox(5);
        return infoVbox;
    }
    public static Label infoBoxLabel()
    {
        Label infolab = new Label("Item Info");
        infolab.setFont(new Font("Garamond", 16));
        infolab.setAlignment(Pos.CENTER);
        infolab.setMaxWidth(Double.MAX_VALUE);
        return infolab;
    }
    public static Label infoUpcLabel()
    {
        Label infoUpcLabel = new Label("UPC: ");
        infoUpcLabel.setFont(new Font("Garamond", 10));
        return infoUpcLabel;
    }
    public static Label infoNameLabel()
    {
        Label infoNameLabel = new Label("Item Name: ");
        infoNameLabel.setFont(new Font("Garamond", 10));
        return infoNameLabel;
    }
    public static Label infoItemLocation()
    {
        Label infoLocationLabel = new Label("Item Location: ");
        infoLocationLabel.setFont(new Font("Garamond", 10));
        return infoLocationLabel;
    }
    public static Label infoItemType()
    {
        Label infoItemType = new Label("Item Type: ");
        infoItemType.setFont(new Font("Garamond", 10));
        return infoItemType;
    }
    public static Label infoAddedBy()
    {
        Label infoAddedBy = new Label("Added by: ");
        infoAddedBy.setFont(new Font("Garamond", 10));
        return infoAddedBy;
    }
    public static Label infoItemManufacturer()
    {
        Label infoItemManufac = new Label("Manufacturer: ");
        infoItemManufac.setFont(new Font("Garamond", 10));
        return infoItemManufac;
    }
    public static Label infoDates()
    {
        Label infoDates = new Label("Expiration Dates");
        infoDates.setFont(new Font("Garamond", 10));
        return infoDates;
    }
    //Create the HBOX that holds the top part of the screen
    public static HBox topBox()
    {
        HBox tBox = new HBox();
        tBox.setMaxHeight(175);
        tBox.setMaxWidth(400);
        tBox.setSpacing(20);
        return tBox;
    }
}
