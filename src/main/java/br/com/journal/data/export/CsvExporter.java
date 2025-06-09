
package br.com.journal.data.export;

import br.com.journal.data.model.Entry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvExporter implements Exporter {
    @Override
    public void export(List<Entry> entries, String filePath) {
    
        List<String> lines = new java.util.ArrayList<>();
        lines.add("data,categorias,texto");

        List<String> entryLines = entries.stream()
            .map(entry -> entry.getDate().toString() + "," +
                          "\"" + String.join(";", entry.getCategories()) + "\"," +
                          "\"" + entry.getText().replace("\"", "\"\"").replace("\n", " ") + "\"")
            .collect(Collectors.toList());
        
        lines.addAll(entryLines);

        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}