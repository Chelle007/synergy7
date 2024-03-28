package src.service;

import src.Data;

public class NotesServiceImpl implements NotesService {
    @Override
    public void create(String notes) {
        String[] lines = notes.split("\\r?\\n");
        boolean allEmpty = true;

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                allEmpty = false;
            }
        }

        if (allEmpty) notes = "";

        Data.notes = notes;
    }

    @Override
    public String get() {
        if (Data.notes.isEmpty()) return "-\n";

        return "\n" + Data.notes;
    }

    @Override
    public void update(String notes) {
        Data.notes = notes;
    }

    @Override
    public void clear() {
        Data.notes = "";
    }
}
