package br.com.journal.business;

// IMPORTS QUE FALTAVAM:
import br.com.journal.data.model.Entry;
import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;

// IMPORTS QUE JÁ DEVERIAM ESTAR LÁ:
import br.com.journal.data.repository.EntryRepository;
import br.com.journal.data.filter.*;
import br.com.journal.data.export.*;


public class JournalService {

    private final EntryRepository repository = EntryRepository.getInstance();

    public void addEntry(String text, String categoriesStr) {
        List<String> categories = Arrays.asList(categoriesStr.split(","));
        Entry newEntry = new Entry(text, LocalDate.now(), categories);
        repository.addEntry(newEntry);
    }

    public List<Entry> getEntries() {
        return repository.getAllEntries();
    }

    public List<Entry> filterEntries(String text, String category, LocalDate startDate, LocalDate endDate) {
        EntryFilter filter = new BaseFilter();

        if (text != null && !text.trim().isEmpty()) {
            filter = new TextFilterDecorator(filter, text);
        }
        if (category != null && !category.trim().isEmpty()) {
            filter = new CategoryFilterDecorator(filter, category);
        }
        if (startDate != null && endDate != null) {
            filter = new DateFilterDecorator(filter, startDate, endDate);
        }

        return filter.filter(repository.getAllEntries());
    }

    public void exportEntries(String format, String filePath) {
        ExporterFactory factory;

        if ("csv".equalsIgnoreCase(format)) {
            factory = new CsvExporterFactory();
        } 
        else {
            System.out.println("Formato de exportação desconhecido ou não implementado.");
            return;
        }

        Exporter exporter = factory.createExporter();
        exporter.export(repository.getAllEntries(), filePath);
        System.out.println("Dados exportados com sucesso para: " + filePath);
    }
}