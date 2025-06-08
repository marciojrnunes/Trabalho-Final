package dados.filtros;

import dados.modelo.EntradaDiario;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroTexto implements FiltroEntrada{
    private FiltroEntrada proximo;
    private String texto;

    public FiltroTexto(FiltroEntrada proximo, String texto) {
        this.proximo = proximo;
        this.texto = texto;
    }

    public List<EntradaDiario> filtrar(List<EntradaDiario> entradas){
        List<EntradaDiario> filtradas = proximo.filtrar(entradas);
        return filtradas.stream()
                .filter(e -> e.getTexto().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }
}
