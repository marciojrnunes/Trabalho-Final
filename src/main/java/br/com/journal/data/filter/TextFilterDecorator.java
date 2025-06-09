// Local: src/main/java/br/com/journal/data/filter/TextFilterDecorator.java
package br.com.journal.data.filter;

import br.com.journal.data.model.Entry;
import java.util.List;
import java.util.stream.Collectors;

public class TextFilterDecorator implements EntryFilter {
    private final EntryFilter wrappedFilter;
    private final String searchText;

    public TextFilterDecorator(EntryFilter filter, String searchText) {
        this.wrappedFilter = filter;
        this.searchText = searchText;
    }

    @Override
    public List<Entry> filter(List<Entry> entries) {
        List<Entry> filtered = wrappedFilter.filter(entries);
        return filtered.stream()
            .filter(entry -> entry.getText().toLowerCase().contains(searchText.toLowerCase()))
            .collect(Collectors.toList());
    }
}