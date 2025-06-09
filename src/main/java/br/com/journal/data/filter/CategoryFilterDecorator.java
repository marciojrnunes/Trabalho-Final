package br.com.journal.data.filter;

import br.com.journal.data.model.Entry;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilterDecorator implements EntryFilter {
    private final EntryFilter wrappedFilter;
    private final String category;

    public CategoryFilterDecorator(EntryFilter filter, String category) {
        this.wrappedFilter = filter;
        this.category = category;
    }

    @Override
    public List<Entry> filter(List<Entry> entries) {
        List<Entry> filtered = wrappedFilter.filter(entries);
        return filtered.stream()
            .filter(entry -> entry.getCategories().stream()
                .anyMatch(cat -> cat.trim().equalsIgnoreCase(category.trim())))
            .collect(Collectors.toList());
    }
}