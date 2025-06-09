
package br.com.journal.data.export;

import br.com.journal.data.model.Entry;
import java.util.List;

public interface Exporter {
    void export(List<Entry> entries, String filePath);
}