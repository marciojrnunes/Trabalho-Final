package dados.modelo;

import java.util.Date;
import java.util.List;

public class EntradaDiario {
    private String texto;
    private Date data;
    private List<String> categorias;

    public EntradaDiario(String texto, Date data, List<String> categorias) {
        this.texto = texto;
        this.data = data;
        this.categorias = categorias;
    }

    public String getTexto() {
        return texto;
    }

    public Date getData() {
        return data;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    //toString para melhorar a visualização da saida dos dados
    @Override
    public String toString() {
        return "Data: "  + data + "\n" +
                    "Texto: "  + texto + "\n"+
                    "Categorias: " + String.join(", ", categorias);
    }
}