// Local: src/main/java/br/com/journal/data/filter/EntryFilter.java
package br.com.journal.data.filter;

import br.com.journal.data.model.Entry;
import java.util.List;

public interface EntryFilter {
    List<Entry> filter(List<Entry> entries);
}