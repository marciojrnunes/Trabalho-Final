package br.com.journal.data.filter;

import br.com.journal.data.model.Entry;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateFilterDecorator implements EntryFilter {
    private final EntryFilter wrappedFilter;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateFilterDecorator(EntryFilter filter, LocalDate startDate, LocalDate endDate) {
        this.wrappedFilter = filter;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public List<Entry> filter(List<Entry> entries) {
        List<Entry> filtered = wrappedFilter.filter(entries);
        return filtered.stream()
            .filter(entry -> !entry.getDate().isBefore(startDate) && !entry.getDate().isAfter(endDate))
            .collect(Collectors.toList());
    }
}