package src.service;

public class NotesServiceImpl implements NotesService {
    private String notes = "";

    @Override
    public void create(String notes) {
        String[] lines = notes.split("\\r?\\n");
        boolean allEmpty = true;

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                allEmpty = false;
                break;
            }
        }

        if (allEmpty) notes = "";

        this.notes = notes;
    }

    @Override
    public String get() {
        if (this.notes.isEmpty()) return "-\n";

        return "\n" + this.notes;
    }

    @Override
    public void clear() {
        this.notes = "";
    }
}
