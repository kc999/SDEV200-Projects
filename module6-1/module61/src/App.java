import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.Scene;
import java.sql.*;
import java.sql.DriverManager;
import org.sqlite.JDBC;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class App extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        //Initialize Database
        String url = "jdbc:sqlite:module61.db";
        //Create Table if not already there
        String sqlStatement = """
        CREATE TABLE IF NOT EXISTS Staff(
        id CHAR(9) NOT NULL,
        lastName VARCHAR(15),
        firstName VARCHAR(15),
        mi CHAR(1),
        address VARCHAR(20),
        city VARCHAR(20),
        state CHAR(2),
        telephone CHAR(10),
        email VARCHAR(40),
        primary key (id)
        );
        """;
        try (Connection con = DriverManager.getConnection(url); Statement stat = con.createStatement())
        {
            stat.execute(sqlStatement);
            System.out.println("Staff Table created/Already exists");

            DatabaseMetaData dbMeta = con.getMetaData();
            

                    
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        //Create gridpane to hold all elements
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        //Create the visual component
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        grid.add(idLabel,0,0);
        grid.add(idField,0,1);
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        grid.add(lastNameLabel,1,0);
        grid.add(lastNameField,1,1);
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        grid.add(firstNameLabel,2,0);
        grid.add(firstNameField,2,1);
        Label middleLabel = new Label("MI:");
        TextField middleField = new TextField();
        grid.add(middleLabel,0,2);
        grid.add(middleField,0,3);
        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        grid.add(addressLabel,1,2);
        grid.add(addressField,1,3);
        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();
        grid.add(cityLabel,2,2);
        grid.add(cityField,2,3);
        Label stateLabel = new Label("State:");
        TextField stateField = new TextField();
        grid.add(stateLabel,2,4);
        grid.add(stateField,2,5);
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        grid.add(phoneLabel,1,4);
        grid.add(phoneField,1,5);
        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        grid.add(emailLabel,0,4);
        grid.add(emailField,0,5);
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");
        Label errorLabel = new Label("Messages will be diplayed here.");
        //Create hBox to hold buttons
        HBox hboxM = new HBox();
        hboxM.setSpacing(10);
        hboxM.getChildren().addAll(viewButton,insertButton,updateButton,clearButton);
        hboxM.setAlignment(javafx.geometry.Pos.CENTER);
        //VBox
        VBox vboxM = new VBox();
        vboxM.getChildren().addAll(grid,hboxM,errorLabel);
        vboxM.setSpacing(25);
        Scene mainScene = new Scene(vboxM,470,300);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Java Assignment Module 6");
        primaryStage.show();
        //Event listeners for button presses
        insertButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            //Create string used for adding to table
            String addString = "INSERT INTO staff(id,firstName,lastName,mi,address,city,state,telephone,email) VALUES(?,?,?,?,?,?,?,?,?)";
            try (Connection con = DriverManager.getConnection(url);
            PreparedStatement iStatement = con.prepareStatement(addString))
            {
                iStatement.setString(1, idField.getText());
                iStatement.setString(2,firstNameField.getText());
                iStatement.setString(3,lastNameField.getText());
                iStatement.setString(4,middleField.getText());
                iStatement.setString(5,addressField.getText());
                iStatement.setString(6,cityField.getText());
                iStatement.setString(7, stateField.getText());
                iStatement.setString(8,phoneField.getText());
                iStatement.setString(9,emailField.getText());
                int rowAffected = iStatement.executeUpdate();
                System.out.println( rowAffected + " inserted");
            }
            catch (SQLException e)
            {
                System.out.println(e);
                errorLabel.setText("SQL error: " + e);
            }
        });
        //Event Listener For View button
        viewButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String searchId = idField.getText();
            String searchQuery = "SELECT * FROM STAFF WHERE id = ?";

            try (Connection con = DriverManager.getConnection(url);
            PreparedStatement idStatement = con.prepareStatement(searchQuery);
            )
            {
                    idStatement.setString(1,searchId);

                    ResultSet results = idStatement.executeQuery();
                    if (results.next())
                    {
                        lastNameField.setText(results.getString("lastName"));
                        firstNameField.setText(results.getString("firstName"));
                        middleField.setText(results.getString("mi"));
                        addressField.setText(results.getString("address"));
                        cityField.setText(results.getString("city"));
                        stateField.setText(results.getString("state"));
                        phoneField.setText(results.getString("telephone"));
                        emailField.setText(results.getString("email"));
                        errorLabel.setText("User found!");
                    }
                    else
                    {
                        errorLabel.setText("User not found");
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e);
                    errorLabel.setText("SQL error: " + e);
                }

        });
        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            //Create string used for adding to table
            String addString = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";
            try (Connection con = DriverManager.getConnection(url);
            PreparedStatement iStatement = con.prepareStatement(addString))
            {
                iStatement.setString(1,lastNameField.getText());
                iStatement.setString(2,firstNameField.getText());
                iStatement.setString(3,middleField.getText());
                iStatement.setString(4,addressField.getText());
                iStatement.setString(5,cityField.getText());
                iStatement.setString(6, stateField.getText());
                iStatement.setString(7,phoneField.getText());
                iStatement.setString(8,emailField.getText());
                iStatement.setString(9,idField.getText());
                int rowAffected = iStatement.executeUpdate();
                if (rowAffected > 0) errorLabel.setText( rowAffected + " updated.");
                else errorLabel.setText("Row not found");
            }
            catch (SQLException e)
            {
                System.out.println(e);
                errorLabel.setText("SQL error: " + e);
            }
        });
        clearButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            idField.setText("");
            lastNameField.setText("");
            firstNameField.setText("");
            middleField.setText("");
            addressField.setText("");
            cityField.setText("");
            stateField.setText("");
            phoneField.setText("");
            emailField.setText("");
            
            errorLabel.setText("Menu Cleared");
        });
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    
}

