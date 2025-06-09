package br.com.journal.data.model;

import java.time.LocalDate;
import java.util.List;

public class Entry {
    private String text;
    private LocalDate date;
    private List<String> categories;

    public Entry(String text, LocalDate date, List<String> categories) {
        this.text = text;
        this.date = date;
        this.categories = categories;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "date=" + date +
                ", categories=" + categories +
                ", text='" + text + '\'' +
                '}';
    }
}