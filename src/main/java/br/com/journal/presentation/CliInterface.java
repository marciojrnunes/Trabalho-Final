
package br.com.journal.presentation;

import br.com.journal.business.JournalService;
import br.com.journal.data.model.Entry;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class CliInterface {
    private final JournalService journalService = new JournalService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            showMenu();
            String input = scanner.nextLine(); 
            try {
                int option = Integer.parseInt(input);

                if (option == 4) {
                    System.out.println("Até logo!");
                    break;
                }
                handleOption(option);

            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- Diário em Linha de Comando ---");
        System.out.println("1. Nova entrada");
        System.out.println("2. Filtrar entradas");
        System.out.println("3. Exportar entradas");
        System.out.println("4. Sair");
        System.out.print("Selecione uma opção: ");
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                handleNewEntry();
                break;
            case 2:
                handleFilterEntries();
                break;
            case 3:
                handleExportEntries();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void handleNewEntry() {
        System.out.print("Digite o texto da sua entrada: ");
        String text = scanner.nextLine();
        System.out.print("Digite as categorias (separadas por vírgula): ");
        String categories = scanner.nextLine();
        journalService.addEntry(text, categories);
        System.out.println("Entrada salva com sucesso!");
    }
    
    private void handleFilterEntries() {
        System.out.println("\n--- Filtro de Entradas ---");
        System.out.println("Deixe o campo em branco para não usar o filtro correspondente.");
        
        System.out.print("Filtrar por texto: ");
        String text = scanner.nextLine();
        
        System.out.print("Filtrar por categoria: ");
        String category = scanner.nextLine();
        
        LocalDate startDate = null;
        LocalDate endDate = null;

        while(true) { 
            try {
                System.out.print("Filtrar por data de início (yyyy-MM-dd): ");
                String startDateStr = scanner.nextLine();
                if (!startDateStr.trim().isEmpty()) {
                    startDate = LocalDate.parse(startDateStr);
                }

                System.out.print("Filtrar por data de fim (yyyy-MM-dd): ");
                String endDateStr = scanner.nextLine();
                if (!endDateStr.trim().isEmpty()) {
                    endDate = LocalDate.parse(endDateStr);
                }

                if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
                    System.out.println("A data de início não pode ser depois da data de fim. Tente novamente.");
                    continue;
                }
                
                break; 

            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }

        List<Entry> results = journalService.filterEntries(text, category, startDate, endDate);
        System.out.println("\n--- Resultados do Filtro ---");
        if (results.isEmpty()) {
            System.out.println("Nenhuma entrada encontrada com esses critérios.");
        } else {
            System.out.println(results.size() + " entrada(s) encontrada(s):");
            results.forEach(System.out::println);
        }
    }

    private void handleExportEntries() {
        System.out.println("\n--- Exportar Entradas ---");
        System.out.print("Digite o formato (csv): ");
        String format = scanner.nextLine();

        if (!"csv".equalsIgnoreCase(format)) {
            System.out.println("Formato inválido ou não implementado. Apenas 'csv' é suportado.");
            return;
        }

        System.out.print("Digite o caminho completo do arquivo (ex: C:\\Users\\theot\\Desktop\\diario.csv): ");
        String filePath = scanner.nextLine();

        if (filePath.trim().isEmpty()) {
            System.out.println("Caminho do arquivo não pode ser vazio.");
            return;
        }

        journalService.exportEntries(format, filePath);
    }
}