package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Notebook {
    private ObservableList<Note> notes;

    public Notebook() {
        notes = FXCollections.observableArrayList();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }


    public ObservableList<Note> getNotes() {
        return notes;
    }
}
