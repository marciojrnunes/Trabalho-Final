package dados.exportacao;

import dados.modelo.EntradaDiario;

import java.util.List;

public interface Exportador {
    void exportar(List<EntradaDiario> entradas, String caminho);
}
