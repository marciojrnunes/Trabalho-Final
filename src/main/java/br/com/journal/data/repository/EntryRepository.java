package br.com.journal.data.repository;

import br.com.journal.data.model.Entry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntryRepository {

    private static EntryRepository instance;
    private List<Entry> entries;

    private static final String STORAGE_FILE = "journal.txt";
    private static final String SEPARATOR = "|||"; 

    private EntryRepository() {
        this.entries = loadEntriesFromFile();
    }

    public static synchronized EntryRepository getInstance() {
        if (instance == null) {
            instance = new EntryRepository();
        }
        return instance;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
        saveEntriesToFile();
    }

    public List<Entry> getAllEntries() {
        return new ArrayList<>(this.entries);
    }

    private void saveEntriesToFile() {
        List<String> lines = this.entries.stream()
            .map(entry -> entry.getDate().toString() + SEPARATOR +
                          String.join(",", entry.getCategories()) + SEPARATOR +
                          entry.getText().replace("\n", "\\n"))
            .collect(Collectors.toList());
        
        try {
            Files.write(Paths.get(STORAGE_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Entry> loadEntriesFromFile() {
        List<Entry> loadedEntries = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(STORAGE_FILE));
            for (String line : lines) {
                String[] parts = line.split("\\|\\|\\|");
                if (parts.length == 3) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    List<String> categories = Arrays.asList(parts[1].split(","));
                    String text = parts[2].replace("\\n", "\n");
                    loadedEntries.add(new Entry(text, date, categories));
                }
            }
        } catch (IOException e) {
            // Arquivo não existe ainda, retorna lista vazia.
        }
        return loadedEntries;
    }
}