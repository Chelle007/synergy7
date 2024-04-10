package src.controller;

import src.service.NotesService;
import src.service.NotesServiceImpl;

public class NotesController {
    public String displayNotes() {
        NotesService ns = new NotesServiceImpl();

        return ns.get();
    }

    public void createNotes(String notes) {
        NotesService ns = new NotesServiceImpl();

        ns.create(notes);
    }
}
