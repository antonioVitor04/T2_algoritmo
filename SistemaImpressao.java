import java.util.Scanner;

public class SistemaImpressao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaImpressao fila = new FilaImpressao(5);
        PilhaReimpressao pilha = new PilhaReimpressao(5);

        System.out.println("Sistema de Gerenciamento de Impressão");
        
        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionar documento à fila de impressão");
            System.out.println("2. Imprimir próximo documento");
            System.out.println("3. Consultar documento na fila");
            System.out.println("4. Solicitar reimpressão");
            System.out.println("5. Reimprimir documento");
            System.out.println("6. Consultar documento na pilha de reimpressão");
            System.out.println("7. Visualizar fila de impressão");
            System.out.println("8. Visualizar pilha de reimpressão");
            System.out.println("9. Sair");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.print("Nome do arquivo: ");
                    String arquivo = scanner.nextLine();
                    System.out.print("Nome do usuário: ");
                    String usuario = scanner.nextLine();
                    try {
                        fila.enfileira(new Documento(arquivo, usuario));
                        System.out.println("Documento adicionado à fila de impressão.");
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
                    System.out.print("Nome do arquivo para reimpressão: ");
                    String arquivoReimpressao = scanner.nextLine();
                    System.out.print("Nome do usuário: ");
                    String usuarioReimpressao = scanner.nextLine();
                    try {
                        pilha.push(new Documento(arquivoReimpressao, usuarioReimpressao));
                        System.out.println("Documento adicionado à pilha de reimpressão.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 5:
                    try {
                        Documento reimpresso = pilha.pop();
                        System.out.println("Documento reimpresso: " + reimpresso);
                        System.out.println("Tempo desde solicitação: " + 
                            reimpresso.calcularTempoEspera() + " segundos");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 6:
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
                    
                case 7:
                    System.out.println(fila);
                    break;
                    
                case 8:
                    System.out.println(pilha);
                    break;
                    
                case 9:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}