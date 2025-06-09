
package br.com.journal.data.export;

public class CsvExporterFactory implements ExporterFactory {
    @Override
    public Exporter createExporter() {
        return new CsvExporter();
    }
}