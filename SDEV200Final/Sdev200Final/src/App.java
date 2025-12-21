import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.SDEV200Final.utility.UPCDatabase;
import com.SDEV200Final.utility.perishableItem;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;

public class App extends Application {
    //Create labels for use later
    TextField upcTextField = new TextField();
    TextField itemNameTextField = new TextField();
    TextField itemLocationTextField = new TextField();
    TextField itemTypeTextField = new TextField();
    TextField itemManufacturerTextField = new TextField();
    TextField itemAddedByTextField = new TextField();
    TextField expiryDatesTextfield = new TextField();
    Label alertsLabel = new Label();
    Label expiryLabel = new Label();
    ListView<perishableItem> dbListView;
    ObservableList<perishableItem> itemList;
    VBox alertsBox;
    //How many weeks to check for in the future for expiration dates
    private int weeksToCheckFor = 4;

    UPCDatabase upcDB = upcDatabaseInit("db");
    @Override
    public void start(Stage primaryStage)
    {
        //Load info in to the database 
        upcDB.loadDatabase("DatabaseFile");
        //Create the left side with the list view
        VBox topLeftVbox = topLeftVboxInit(upcDB);
        //Create the right side of the top of the screen
        VBox topRightVbox = upcEditorVbox();
        //Create the event listener that feeds info to the info box from the listview
        listViewListenerInit();
        //Put the top side in an HBOX
        HBox tBox = topBox();
        tBox.setHgrow(topRightVbox, Priority.ALWAYS);
        tBox.setHgrow(topLeftVbox, Priority.ALWAYS);
        tBox.getChildren().addAll(topLeftVbox,topRightVbox);
        //Creates the bottom part that shows items close to Expiry
        alertsBox = alertsBoxInit();
        ScrollPane scrollableAlert = new ScrollPane();
        scrollableAlert.setContent(alertsBox);
        scrollableAlert.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollableAlert.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollableAlert.setPrefHeight(200);
        scrollableAlert.setFitToWidth(true);
        refreshAlertsBox(alertsBox);
        //This vbox contains both the top hbox, and the bottom vbox.
        VBox masterBox = new VBox();
        masterBox.getChildren().addAll(tBox,scrollableAlert);
        Scene primScene = new Scene(masterBox,600,500);
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
    public ListView<perishableItem> initDbListView(UPCDatabase dataB)
    {
        this.itemList = dataB.getListViewData();
        return new ListView<>(this.itemList);
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
        tBox.setMaxWidth(Double.MAX_VALUE);
        tBox.setSpacing(20);
        return tBox;
    }
    //Create top left vbox that will store the list of all items
    public VBox topLeftVboxInit(UPCDatabase upcDBN)
    {
        //Create the Vbox used in the database
        VBox listAreaBox = initVBox();
        Label listAreaLabel = initDbListViewLabel();
        //Create List view in 
        dbListView = initDbListView(upcDBN);
        listAreaBox.getChildren().addAll(listAreaLabel,dbListView);
        listAreaBox.setMaxWidth(Double.MAX_VALUE);
        listAreaBox.setFillWidth(true);
        return listAreaBox;
    }
    //Creates the top right of the screen where UPCs are edited and viewed
    public VBox upcEditorVbox()
    {
        GridPane upcGrid = new GridPane();
        upcGrid.setHgap(5);
        upcGrid.setVgap(5);
        //Add labels and input fields to gridpane
        upcGrid.add(new Label("UPC:"),0, 0 );
        upcGrid.add(upcTextField,1,0);
        upcGrid.add(new Label("Name:"),0,1);
        upcGrid.add(itemNameTextField,1,1);
        upcGrid.add(new Label("Type:"),0,2);
        upcGrid.add(itemTypeTextField,1,2);
        upcGrid.add(new Label("Location:"),0,3);
        upcGrid.add(itemLocationTextField,1,3);
        upcGrid.add(new Label("Manufacturer:"),0,4);
        upcGrid.add(itemManufacturerTextField,1,4);
        upcGrid.add(new Label("Added By:"),0,5);
        upcGrid.add(itemAddedByTextField,1,5);
        upcGrid.add(new Label("Expiry Dates"),0,6);
        upcGrid.add(expiryDatesTextfield,1,6);
        HBox buttons = new HBox();
        Button saveBtn = new Button("Update Item");
        saveBtn.setOnAction(e->saveButtonUpdate());
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        Button addItemBtn = new Button("Add Item");
        addItemBtn.setMaxWidth(Double.MAX_VALUE);
        addItemBtn.setOnAction(e->addItemButton());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->deleteItemButton());
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        buttons.getChildren().addAll(saveBtn,addItemBtn,deleteButton);
        buttons.setHgrow(saveBtn, Priority.ALWAYS);
        buttons.setHgrow(addItemBtn, Priority.ALWAYS);
        buttons.setHgrow(deleteButton, Priority.ALWAYS);
        upcGrid.add(buttons,0,7,2,1);
        Label infoLabel = new Label("UPC Information");
        VBox topRight = new VBox();
        topRight.setMaxWidth(Double.MAX_VALUE);
        topRight.setFillWidth(true);
        topRight.getChildren().addAll(infoLabel,upcGrid,alertsLabel);
        return topRight;
    }
    private void saveButtonUpdate()
    {
        perishableItem selectedItem = dbListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && upcTextField.getText() != null)
        {
            selectedItem.setItemName(itemNameTextField.getText());
            selectedItem.setUpc(upcTextField.getText());
            selectedItem.setAddedBy(itemAddedByTextField.getText());
            selectedItem.setItemLocation(itemLocationTextField.getText());
            selectedItem.setItemType(itemTypeTextField.getText());
            selectedItem.setItemManufacturer(itemManufacturerTextField.getText());
            splitTextFieldDates(selectedItem);
            upcDB.saveDatabase("DatabaseFile");
            refreshAlertsBox(alertsBox);
            dbListView.refresh();
        }
    }
    //This takes the dates in the text field, splits them, and creates a date object to add to the UPC database
    private void splitTextFieldDates(perishableItem item)
    {
        String input = expiryDatesTextfield.getText();
        String[] dateStrings = input.split(",\\s*");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        item.getExpiryDates().clear();
        for (String date : dateStrings)
        {
            try{
                if (!date.isEmpty())
                {
                    LocalDate newDate = LocalDate.parse(date,dateFormat);
                    item.getExpiryDates().add(newDate);
                }
            }
            catch(DateTimeException e)
            {
                System.out.println ("Invalid Date Format");
                alertsLabel.setText("Invalid Date Format");
            }
        }
    }
    //Create the listener function that will grab info from the item selected in the list view, and populate the textfields
    private void listViewListenerInit()
    {
        dbListView.getSelectionModel().selectedItemProperty().addListener((observable,oldVal,newVal)->{
            if (newVal != null)
            {
                itemNameTextField.setText(newVal.getItemName());
                upcTextField.setText(newVal.getUpc());
                itemManufacturerTextField.setText(newVal.getItemManufacturer());
                itemLocationTextField.setText(newVal.getItemLocation());
                itemTypeTextField.setText(newVal.getItemType());
                itemAddedByTextField.setText(newVal.getAddedBy());
                String expireDates = newVal.getExpiryDates().stream()
                    .map(date -> date.format(DateTimeFormatter.ofPattern("M/d/yyyy")))
                    .collect(Collectors.joining(", "));
                expiryDatesTextfield.setText(expireDates);
            }
            else
            {
                //If a value isnt selected, clear the fields
                clearTextFields();
            }
        });
    }
    private void clearTextFields()
    {
        itemNameTextField.clear();
        upcTextField.clear();
        itemManufacturerTextField.clear();
        itemLocationTextField.clear();
        itemTypeTextField.clear();
        itemAddedByTextField.clear();
        expiryDatesTextfield.clear();
    }
    private void addItemButton()
    {
        //Get the UPC to check the DB
        String upcToAdd = upcTextField.getText();
        if (upcToAdd.isEmpty())
        {
            alertsLabel.setText("The Upc field is empty. Please add a UPC.");
            return;
        }
        //Make sure UPC isn't already in DB
        if (upcDB.db.containsKey(upcToAdd))
        {
            alertsLabel.setText("This Upc is already in the database. You can use Update to change fields.");
            return;
        }
        else{
            perishableItem itemAdd = new perishableItem();
            itemAdd.setUpc(upcTextField.getText());
            itemAdd.setAddedBy(itemAddedByTextField.getText());
            itemAdd.setItemLocation(itemLocationTextField.getText());
            itemAdd.setItemManufacturer(itemManufacturerTextField.getText());
            itemAdd.setItemName(itemNameTextField.getText());
            splitTextFieldDates(itemAdd);
            addAndSave(itemAdd, upcDB, "DatabaseFile");
            itemList.add(itemAdd);
            alertsLabel.setText("Item added successfully");
            refreshAlertsBox(alertsBox);
            clearTextFields();
        }

    }
    private void deleteItemButton()
    {
        perishableItem deleteItem = dbListView.getSelectionModel().getSelectedItem();
        if (deleteItem == null)
        {
            alertsLabel.setText("Please select an item!");
        }
        else{
            Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
            delAlert.setTitle("Are you sure?");
            delAlert.setHeaderText("Deleting item: " + deleteItem);
            delAlert.setContentText("Are you sure you want to delete this item? This can't be undone.");
            
            delAlert.showAndWait().ifPresent(resp ->
                {
                    if (resp == ButtonType.OK)
                    {
                        //Remove from listview
                        itemList.remove(deleteItem);
                        //Remove from DB
                        upcDB.db.remove(deleteItem.getUpc());
                        alertsLabel.setText("Item successfully removed.");
                        clearTextFields();
                        refreshAlertsBox(alertsBox);
                        upcDB.saveDatabase("DatabaseFile");
                    }

                }
            );
        }
    }
    private VBox alertsBoxInit()
    {
        VBox alertsBox = new VBox();
        alertsBox.setPadding(new Insets(20,10,20,10));
        return alertsBox;
    }
    private void refreshAlertsBox(VBox alertsBox)
    {
        //Clear the vbox before adding stuff to it
        alertsBox.getChildren().clear();
        //Create dates to compare toi
        LocalDate today = LocalDate.now();
        LocalDate weeksToCheckForFromToday = today.plusWeeks(weeksToCheckFor);
        //Create a header label 
        Label expiryLabelInfo = new Label("Items close to Expiration Date");
        expiryLabelInfo.setFont(new Font("Garamond", 20));
        alertsBox.getChildren().add(expiryLabelInfo);

        //Loop through the database and look for any items close to the expiry date
        for (perishableItem item: upcDB.db.values())
        {
            for (LocalDate expiry: item.getExpiryDates())
            {
                if (expiry.isEqual(today) || expiry.isAfter(today) && expiry.isBefore(weeksToCheckForFromToday))
                {
                    String alertText = String.format("%s (Expires: %s)", item.getItemName(),expiry.format(DateTimeFormatter.ofPattern("M/d/yyyy")));
                    Label dateAlertLabel = new Label(alertText);
                    alertsBox.getChildren().add(dateAlertLabel);
                }
            }
        }
    }

}   
