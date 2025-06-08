package dados.filtros;

import dados.modelo.EntradaDiario;

import java.util.List;

public interface FiltroEntrada {
    List<EntradaDiario> filtrar(List<EntradaDiario> entradas);
}
