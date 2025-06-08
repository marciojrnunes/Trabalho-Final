package dados.persistencia;

import dados.modelo.EntradaDiario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RepositorioEntrada {
    private static RepositorioEntrada instancia;
    private List<EntradaDiario> entradas;

    public RepositorioEntrada() {
        entradas = new ArrayList<>();
    }

    public static RepositorioEntrada getInstance(){
        if(instancia == null){
            instancia = new RepositorioEntrada();
        }
        return instancia;
    }

    public void adicionarEntrada(EntradaDiario entrada){
        entradas.add(entrada);
    }

    public List<EntradaDiario> getTodasAsEntradas(){
        return Collections.unmodifiableList(entradas);
    }

    public void limparEntradas(){
        entradas.clear();
    }
}
