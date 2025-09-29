package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Note;
import model.Notebook;

public class NoteController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<Note> notesList;

    private Notebook notebook;

    @FXML
    private void initialize() {
        notebook = new Notebook();
        notesList.setItems(notebook.getNotes());

        // Optional: load note into fields when selected
        notesList.getSelectionModel().selectedItemProperty().addListener((obs, oldNote, newNote) -> {
            if (newNote != null) {
                titleField.setText(newNote.getTitle());
                contentArea.setText(newNote.getContent());
            }
        });
    }

    @FXML
    private void handleAddNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note(title, content);
            notebook.addNote(note);
            titleField.clear();
            contentArea.clear();
        } else {
            showAlert("Please enter both title and content.");
        }
    }

    @FXML
    private void handleEditNote() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            selectedNote.setTitle(titleField.getText().trim());
            selectedNote.setContent(contentArea.getText().trim());
            notesList.refresh(); // update ListView display
        } else {
            showAlert("Please select a note to edit.");
        }
    }

    @FXML
    private void handleDeleteNote() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            notebook.removeNote(selectedNote);
            titleField.clear();
            contentArea.clear();
        } else {
            showAlert("Please select a note to delete.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Note App");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
