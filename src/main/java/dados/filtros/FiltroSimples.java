package dados.filtros;

import dados.modelo.EntradaDiario;

import java.util.Collections;
import java.util.List;

public class FiltroSimples implements FiltroEntrada{

    @Override
    public List<EntradaDiario> filtrar(List<EntradaDiario> entradas) {
        return entradas;
    }
}
