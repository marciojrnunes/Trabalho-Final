// Local: src/main/java/br/com/journal/data/filter/BaseFilter.java
package br.com.journal.data.filter;

import br.com.journal.data.model.Entry;
import java.util.List;

public class BaseFilter implements EntryFilter {
    @Override
    public List<Entry> filter(List<Entry> entries) {
        return entries;
    }
}