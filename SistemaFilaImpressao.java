import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

public class SistemaFilaImpressao {
    public static void main(String[] args) {
        FilaImpressao fila = new FilaImpressao(); // Capacidade fixa de 10
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Carregar Documentos do Arquivo entrada.txt");
            System.out.println("2. Adicionar Documento para Impressao");
            System.out.println("3. Imprimir Documento");
            System.out.println("4. Consultar Documento");
            System.out.println("5. Exibir Relatorio");
            System.out.println("6. Sair");
            System.out.print("Opcao: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Digite um numero");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    carregarDocumentosArquivo(fila);
                    break;

                case 2:
                    System.out.print("Nome Arquivo: ");
                    String nomeArquivo = scanner.nextLine();

                    System.out.print("Nome Usuario: ");
                    String nomeUsuario = scanner.nextLine();

                    try {
                        fila.enfileira(nomeArquivo, nomeUsuario, Calendar.getInstance());
                    } catch (RuntimeException e) {
                        System.out.println("Capacidade maxima atingida: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        fila.desenfileira();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Nome do arquivo para consultar: ");
                    String arquivoConsulta = scanner.nextLine();
                    System.out.println(fila.consultarDocumento(arquivoConsulta));
                    break;

                case 5:
                    fila.relatorioFila();
                    break;

                case 6:
                    scanner.close();
                    return;

                default:
                    System.out.println("Opcao invalida");
            }
        }
    }

    private static void carregarDocumentosArquivo(FilaImpressao fila) {
        try {
            File arquivo = new File("entrada.txt");
            Scanner leitorArquivo = new Scanner(arquivo);

            int documentosCarregados = 0;

            while (leitorArquivo.hasNextLine()) {
                String linha = leitorArquivo.nextLine().trim();

                if (!linha.isEmpty()) {
                    // Cada linha deve ter formato: nomeArquivo,nomeUsuario
                    String[] dados = linha.split(",");

                    if (dados.length == 2) {
                        String nomeArquivo = dados[0].trim();
                        String nomeUsuario = dados[1].trim();

                        try {
                            fila.enfileira(nomeArquivo, nomeUsuario, Calendar.getInstance());
                            documentosCarregados++;
                        } catch (RuntimeException e) {
                            System.out.println("Capacidade maxima atingida (10 documentos).");
                            System.out.println("Nao foi possível adicionar mais documentos.");
                            break;
                        }
                    } else {
                        System.out.println("Formato invalido na linha: " + linha);
                        System.out.println("Use o formato: nomeArquivo,nomeUsuario");
                    }
                }
            }

            leitorArquivo.close();
            System.out.println("Total de documentos carregados: " + documentosCarregados);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo entrada.txt nao encontrado");
        }
    }
}