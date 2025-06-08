package apresentacao;

import dados.exportacao.Exportador;
import dados.exportacao.ExportadorCSV;
import dados.modelo.EntradaDiario;
import negocio.DiarioService;

import java.util.List;
import java.util.Scanner;


public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final DiarioService service = new DiarioService();

    public void execultar(){
        int opcao;
        do{
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao){
                case 1 :
                    novaEntrada();
                    break;
                case 2 :
                    filtrarEntradas();
                    break;
                case 3 :
                    exportarEntradas();
                    break;
                case 4 :
                    System.out.println("saindo...");
                    break;
                default :
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao != 4);
    }

    //Implementando as lógicas das opções do menu
    private void exibirMenu(){
        System.out.println("\n---- Journalind ----");
        System.out.println("1. Nova entrada");
        System.out.println("2. Filtrar entradas");
        System.out.println("3. Exportar entradas");
        System.out.println("4. Sair");
        System.out.println("Escolha uma opção: ");
    }

    private void novaEntrada(){
        try{
            System.out.println("Digite o texto: ");
            String texto = scanner.nextLine();
            System.out.println("Digite a data (dd/MM/yyyy): ");
            String data = scanner.nextLine();
            System.out.println("Digite a(s) categoria(s) (separe com vírgula): ");
            String categorias = scanner.nextLine();

            service.inserirEntrada(texto, data, categorias);
            System.out.println("Adicionada com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao adicionar. " + e.getMessage());
        }
    }

    private void filtrarEntradas() {
        try {
            System.out.print("Texto a buscar (ou deixe em branco): ");
            String texto = scanner.nextLine();

            System.out.print("Data inicial (ou deixe em branco): ");
            String dataInicio = scanner.nextLine();

            System.out.print("Data final (ou deixe em branco): ");
            String dataFim = scanner.nextLine();

            System.out.print("Categoria (ou deixe em branco): ");
            String categoria = scanner.nextLine();

            List<EntradaDiario> resultado = service.filtrarentradas(
                    texto.isEmpty() ? null : texto,
                    dataInicio.isEmpty() ? null : dataInicio,
                    dataFim.isEmpty() ? null : dataFim,
                    categoria.isEmpty() ? null : categoria
            );

            if (resultado.isEmpty()) {
                System.out.println("Nenhuma entrada encontrada.");
            } else {
                System.out.println("\nEntradas filtradas:");
                for (EntradaDiario entrada : resultado) {
                    System.out.println(entrada);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao filtrar: " + e.getMessage());
        }
    }

    private void exportarEntradas() {
        try {
            System.out.print("Caminho do arquivo CSV (ex: C:/meus_journals/saida.csv): ");
            String caminho = scanner.nextLine();

            List<EntradaDiario> entradas = service.listarTodasAsEntradas();


            Exportador exportador = new ExportadorCSV();
            exportador.exportar(entradas, caminho);

            System.out.println("Exportação em CSV realizada com sucesso! ");
        } catch (Exception e) {
            System.out.println("Erro na exportação: " + e.getMessage());
        }
    }
}
