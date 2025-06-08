package negocio;

import dados.exportacao.Exportador;
import dados.exportacao.ExportadorCSV;
import dados.exportacao.ExportadorCsvIn;
import dados.exportacao.ExportadorIn;
import dados.filtros.*;
import dados.modelo.EntradaDiario;
import dados.persistencia.RepositorioEntrada;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DiarioService {
    private final RepositorioEntrada repositorio = RepositorioEntrada.getInstance();

    public void inserirEntrada(String texto, String dataString, String categoriasString ) throws ParseException{
        Date data = Conversor.stringParaData(dataString);
        List<String> categorias = Arrays.asList(categoriasString.split(", "));
        EntradaDiario entrada = new EntradaDiario(texto, data, categorias);
        repositorio.adicionarEntrada(entrada);
    }

    public List<EntradaDiario> listarTodasAsEntradas(){
        return repositorio.getTodasAsEntradas();
    }

    public void limparEntradas(){
        repositorio.limparEntradas();
    }

    //metodo para filtragem
    public List<EntradaDiario> filtrarentradas(String texto, String dataInicioString, String dataFimString, String categoria) throws ParseException{
        FiltroEntrada filtro = new FiltroSimples();

        if(texto != null  &&  !texto.isEmpty()){
            filtro = new FiltroTexto(filtro, texto);
        }

        if (dataInicioString != null && dataFimString != null && !dataInicioString.isEmpty() && !dataFimString.isEmpty()){
            Date dataInicio = Conversor.stringParaData(dataInicioString);
            Date dataFim = Conversor.stringParaData(dataFimString);
            filtro = new FiltroData(filtro, dataInicio, dataFim);
        }

        if (categoria != null && !categoria.isEmpty()){
            filtro = new FiltroCategoria(filtro, categoria);
        }

        return filtro.filtrar(repositorio.getTodasAsEntradas());
    }

    //metodo para exportar
    public void exportarAsEntradas(List<EntradaDiario> entradas, String caminho){
        ExportadorIn factory = new ExportadorCsvIn();
        Exportador exportador = factory.criarExportador();
        exportador.exportar(entradas, caminho);
        System.out.println("Exportação em CSV finalizada com sucesso! ");

    }
}
