package dados.exportacao;

import dados.modelo.EntradaDiario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorCSV implements Exportador {


    @Override
    public void exportar(List<EntradaDiario> entradas, String caminho) {
        try(FileWriter writer = new FileWriter(caminho)){
            writer.write("Data,Texto,Categorias\n");
            //for para percorrer e escrever cada entrada no arquivo
            for (EntradaDiario entrada : entradas){
                String categorias = String.join(";", entrada.getCategorias());
                //Como será escrito
                writer.write(String.format("%s,%s,%s\n",
                        entrada.getData().toString(),
                        //implementado para remover virgulas, caso haja no texto e nas categorias, adicionei para evitar quebras do CSV
                        entrada.getTexto().replace(", ", " "),
                        categorias.replace(", ", " ")));
            }
        }catch (IOException e){
            System.out.println("Erro ao exportar: " +e.getMessage());
        }
    }

}
