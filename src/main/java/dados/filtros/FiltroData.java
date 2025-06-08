package dados.filtros;

import dados.modelo.EntradaDiario;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroData implements FiltroEntrada{
    private FiltroEntrada proximo;
    private Date dataInicio;
    private Date datafim;

    public FiltroData(FiltroEntrada proximo, Date dataInicio, Date datafim) {
        this.proximo = proximo;
        this.dataInicio = dataInicio;
        this.datafim = datafim;
    }


    @Override
    public List<EntradaDiario> filtrar(List<EntradaDiario> entradas) {
        List<EntradaDiario> filtradas = proximo.filtrar(entradas);
        return filtradas.stream()
                .filter(e -> !e.getData().before(dataInicio) && !e.getData().after(datafim))
                .collect(Collectors.toList());
    }
}
