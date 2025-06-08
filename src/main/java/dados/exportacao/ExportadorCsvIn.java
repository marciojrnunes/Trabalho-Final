package dados.exportacao;

public class ExportadorCsvIn implements ExportadorIn{

    @Override
    public Exportador criarExportador() {
        return new ExportadorCSV();
    }
}
