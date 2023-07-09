/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abu Dozana Tahmid
 */
public class FXMLDocumentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList(DataHandler.loadData());
        listView.setItems(list);
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    Button addButton;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField addTextBox;
    @FXML
    ListView<LocalEvent> listView;
    @FXML
    Button saveButton;
    @FXML
    Button closeButton;
    @FXML
    Button deleteButton;

    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @FXML
    private void addEvent(Event e) {
        list.add(new LocalEvent(datePicker.getValue(), addTextBox.getText()));
        listView.setItems(list);
        refresh();
    }

    public void refresh() {
        datePicker.setValue(LocalDate.now());
        addTextBox.setText(null);
    }

    
    private void saveFileParmanent(Event e) {
        DataHandler.saveData(list);
    }

    @FXML
    private void saveFile(Event e) {
        // Create a new FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Todo List");

        // Set the initial directory and suggested file name
        fileChooser.setInitialFileName("todo_list.txt");

        // Show the Save File dialog
        Stage stage = (Stage) addButton.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Iterate over the list and write each event to the file
                for (LocalEvent event : list) {
                    writer.write(event.toString());
                    writer.newLine();
                }

                System.out.println("Data saved successfully.");

            } catch (IOException ex) {
                System.err.println("Error saving data: " + ex.getMessage());
            }
        }
    }

    @FXML
    private void closeApp(Event e) {
        saveFileParmanent(e);
        Platform.exit();
    }

    @FXML
    private void deleteSpecificTask(Event e) {
        // Get the selected task from the ListView
        LocalEvent selectedEvent = listView.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            // Remove the selected task from the list
            list.remove(selectedEvent);
            // Clear the selection in the ListView
            listView.getSelectionModel().clearSelection();

            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("No task selected.");
        }
    }

    @FXML
    private void todayEvent(Event e) {
        // Create a filtered list to hold only today's tasks
        ObservableList<LocalEvent> todayTasks = FXCollections.observableArrayList();

        // Iterate over the original list and add today's tasks to the filtered list
        for (LocalEvent event : list) {
            if (event.getDate().isEqual(LocalDate.now())) {
                todayTasks.add(event);
            }
        }

        // Sort the filtered list in descending order
        todayTasks.sort((task1, task2) -> task2.getDate().compareTo(task1.getDate()));

        // Set the filtered and sorted list as the items of the ListView
        listView.setItems(todayTasks);
    }

    @FXML
    private void allTask(Event e) {
        // Set the original list as the items of the ListView
        listView.setItems(list);
    }

}
