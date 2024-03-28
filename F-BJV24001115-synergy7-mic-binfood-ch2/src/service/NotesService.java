package src.service;

public interface NotesService {
    // CREATE
    void create(String notes);

    // READ
    String get();

    // UPDATE
    void update(String notes);

    // DELETE
    void clear();
}
