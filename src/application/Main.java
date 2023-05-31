package application;

import com.masai.service.IMedicineService;
import com.masai.service.MedicineServiceImp;
import com.masai.dto.*;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;


public class Main extends Application {

    private IMedicineService medServ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        medServ = new MedicineServiceImp();

        primaryStage.setTitle("Medicine Application");

        // Create GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create input fields and buttons
        TextField medIdField = new TextField();
        TextField nameField = new TextField();
        TextField companyField = new TextField();
        TextField priceField = new TextField();
        DatePicker mfgDatePicker = new DatePicker();
        DatePicker expDatePicker = new DatePicker();
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button viewButton = new Button("View All");

        // Add event handlers to buttons
        addButton.setOnAction(e -> addMedicine(medIdField.getText(), nameField.getText(), companyField.getText(),
                Double.parseDouble(priceField.getText()), mfgDatePicker.getValue(), expDatePicker.getValue()));
        updateButton.setOnAction(e -> updateMedicine(medIdField.getText(), nameField.getText(), companyField.getText(),
                Double.parseDouble(priceField.getText()), mfgDatePicker.getValue(), expDatePicker.getValue()));
        deleteButton.setOnAction(e -> deleteMedicine(medIdField.getText()));
        viewButton.setOnAction(e -> viewAllMedicines());

        // Add input fields, buttons to grid pane
        gridPane.add(new Label("Medicine ID:"), 0, 0);
        gridPane.add(medIdField, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(new Label("Company:"), 0, 2);
        gridPane.add(companyField, 1, 2);
        gridPane.add(new Label("Price/Unit:"), 0, 3);
        gridPane.add(priceField, 1, 3);
        gridPane.add(new Label("Manufacturing Date:"), 0, 4);
        gridPane.add(mfgDatePicker, 1, 4);
        gridPane.add(new Label("Expiry Date:"), 0, 5);
        gridPane.add(expDatePicker, 1, 5);
        gridPane.add(addButton, 0, 6);
        gridPane.add(updateButton, 1, 6);
        gridPane.add(deleteButton, 0, 7);
        gridPane.add(viewButton, 1, 7);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addMedicine(String medId, String name, String company, double price, LocalDate mfgDate,
                             LocalDate expDate) {
        Medicine med = new Medicine(medId, name, company, price, mfgDate, expDate);
        try {
            medServ.addMedicine(med);
            System.out.println("Medicine Added Successfully.");
        } catch (SomethingWentWrongException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateMedicine(String medId, String name, String company, double price, LocalDate mfgDate,
                                LocalDate expDate) {
        Medicine med = new Medicine(medId, name, company, price, mfgDate, expDate);
        try {
            medServ.updateMedicine(med, medId);
            System.out.println("Medicine Updated Successfully.");
        } catch (SomethingWentWrongException | NoRecordFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteMedicine(String medId) {
        try {
            medServ.deleteMedicine(medId);
            System.out.println("Medicine Deleted Successfully.");
        } catch (SomethingWentWrongException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllMedicines() {
        try {
            medServ.getAllMedicines().forEach(System.out::println);
        } catch (SomethingWentWrongException | NoRecordFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

