import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SistemaImpressao {
    
    private static List<String> linhasArquivo = new ArrayList<>();
    private static int indiceAtual = 0; // Controla a próxima linha a ser lida

    private static void carregarArquivo(String caminhoArquivo) {
        try (Scanner fileScanner = new Scanner(new File(caminhoArquivo))) {
            while (fileScanner.hasNextLine()) {
                linhasArquivo.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo de entrada não encontrado.");
            System.exit(1);
        }
    }

    // Método para obter o próximo documento do arquivo
    private static Documento obterProximoDocumento() {
        if (indiceAtual + 1 >= linhasArquivo.size()) {
            throw new RuntimeException("Não há mais documentos no arquivo de entrada.");
        }
        String nomeArquivo = linhasArquivo.get(indiceAtual);
        String usuario = linhasArquivo.get(indiceAtual + 1);
        indiceAtual += 2; 
        return new Documento(nomeArquivo, usuario);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaImpressao fila = new FilaImpressao(5);
        PilhaReimpressao pilha = new PilhaReimpressao(5);

        String caminhoArquivo = args.length > 0 ? args[0] : "entradas.txt";
        carregarArquivo(caminhoArquivo);

        System.out.println("Sistema de Gerenciamento de Impressão");

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gerenciar Impressão");
            System.out.println("2. Gerenciar Reimpressão");
            System.out.println("3. Visualizar fila de impressão");
            System.out.println("4. Visualizar pilha de reimpressão");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");

            int opcaoPrincipal = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcaoPrincipal) {
                case 1: // Submenu de Impressão
                    System.out.println("\nGerenciamento de Impressão:");
                    System.out.println("1. Adicionar documento à fila de impressão");
                    System.out.println("2. Imprimir próximo documento");
                    System.out.println("3. Consultar documento na fila");
                    System.out.println("4. Voltar ao menu principal");
                    System.out.print("Escolha: ");

                    int opcaoImpressao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoImpressao) {
                        case 1:
                            try {
                                Documento doc = obterProximoDocumento();
                                fila.enfileira(doc);
                                System.out.println("Documento adicionado à fila de impressão: " + doc);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                Documento impresso = fila.desenfileira();
                                System.out.println("Documento impresso: " + impresso);
                                System.out.println("Tempo de espera: " + impresso.calcularTempoEspera() + " segundos");
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 3:
                            System.out.print("Nome do arquivo a consultar: ");
                            String arquivoConsulta = scanner.nextLine();
                            if (fila.contem(arquivoConsulta)) {
                                System.out.println("Documento encontrado na posição " +
                                    fila.indiceDe(arquivoConsulta) + " da fila.");
                                System.out.println(fila.consultarDocumento(arquivoConsulta));
                            } else {
                                System.out.println("Documento não encontrado na fila.");
                            }
                            break;

                        case 4:
                            break; 

                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;

                case 2: 
                    System.out.println("\nGerenciamento de Reimpressão:");
                    System.out.println("1. Solicitar reimpressão");
                    System.out.println("2. Reimprimir documento");
                    System.out.println("3. Consultar documento na pilha de reimpressão");
                    System.out.println("4. Voltar ao menu principal");
                    System.out.print("Escolha: ");

                    int opcaoReimpressao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoReimpressao) {
                        case 1:
                            try {
                                Documento doc = obterProximoDocumento();
                                pilha.push(doc);
                                System.out.println("Documento adicionado à pilha de reimpressão: " + doc);
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                Documento reimpresso = pilha.pop();
                                System.out.println("Documento reimpresso: " + reimpresso);
                                System.out.println("Tempo desde solicitação: " +
                                    reimpresso.calcularTempoEspera() + " segundos");
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 3:
                            System.out.print("Nome do arquivo a consultar: ");
                            String arquivoConsultaPilha = scanner.nextLine();
                            if (pilha.contem(arquivoConsultaPilha)) {
                                System.out.println("Documento encontrado na posição " +
                                    pilha.indiceDe(arquivoConsultaPilha) + " a partir do topo.");
                                System.out.println(pilha.consultarDocumento(arquivoConsultaPilha));
                            } else {
                                System.out.println("Documento não encontrado na pilha de reimpressão.");
                            }
                            break;

                        case 4:
                            break; 

                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;

                case 3:
                    System.out.println("\nFila de Impressão:");
                    System.out.println(fila);
                    break;

                case 4:
                    System.out.println("\nPilha de Reimpressão:");
                    System.out.println(pilha);
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}