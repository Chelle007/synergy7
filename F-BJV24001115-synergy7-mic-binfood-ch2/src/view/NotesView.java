package src.view;

import src.controller.NotesController;

public class NotesView {
    public void displayNotes() {
        NotesController nc = new NotesController();

        System.out.println("Catatan tambahan: " + nc.displayNotes());
    }
}
