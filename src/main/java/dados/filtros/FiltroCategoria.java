package dados.filtros;

import dados.modelo.EntradaDiario;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroCategoria implements FiltroEntrada{
    private FiltroEntrada proximo;
    private String categoria;

    public FiltroCategoria(FiltroEntrada proximo, String categoria) {
        this.proximo = proximo;
        this.categoria = categoria;
    }

    @Override
    public List<EntradaDiario> filtrar(List<EntradaDiario> entradas) {
        List<EntradaDiario> filtradas = proximo.filtrar(entradas);
        return filtradas.stream()
                .filter(e -> e.getCategorias().stream()
                        .anyMatch(cat -> cat.toLowerCase().equals(categoria)))
                .collect(Collectors.toList());
    }
}
