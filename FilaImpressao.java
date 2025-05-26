import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FilaImpressao {

    // Dados
    private Documento[] dados;
    private int primeiro, ultimo, ocupacao;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Biblioteca SimpleDateFormat

    // Construtor: Passa a Capacidade
    public FilaImpressao(int capacidade) {
        dados = new Documento[capacidade];

        // Por Clareza
        primeiro = 0;
        ultimo = 0;
        ocupacao = 0;
    }

    // Construtor: Capacidade Padrão = 10
    public FilaImpressao() {
        this(10);
    }

    // Fila Vazia
    public boolean filaVazia() {
        return ocupacao == 0;
    }

    // Fila Cheia
    public boolean filaCheia() {
        return ocupacao == dados.length;
    }

    // Próxima Posição
    private int proxima(int pos) {
        return (pos + 1) % dados.length;
    }

    // Enfileira: Adicionar Documento
    public void enfileira(String nomeArquivo, String nomeUsuario, Calendar horarioSolicitacao) {
        if (filaCheia()) {
            throw new RuntimeException("Falha no enfileiramento: Não e possivel adicionar o documento: " + nomeArquivo);
        }

        dados[ultimo] = new Documento(nomeArquivo, nomeUsuario, horarioSolicitacao);
        ultimo = proxima(ultimo);
        ocupacao++;

        System.out.println("Documento: " + nomeArquivo + " adicionado à fila.");
    }

    // Desenfileira: Imprimir Documento
    public Documento desenfileira() {
        if (filaVazia()) {
            throw new RuntimeException("Falha no desenfileiramento: Nenhum documento para imprimir");
        }

        Documento doc = dados[primeiro];
        Calendar horarioImpressao = Calendar.getInstance();
        long tempoEspera = (horarioImpressao.getTimeInMillis() - doc.getHorarioSolicitacao().getTimeInMillis()) / 1000;

        System.out.println(
                "Nome Arquivo.......: " + doc.getNomeArquivo() +
                "\nNome Usuario.......: " + doc.getNomeUsuario() +
                "\nHorario Solicitacao: " + formatador.format(doc.getHorarioSolicitacao().getTime()) +
                "\nHorario Impressao..: " + formatador.format(horarioImpressao.getTime()) +
                "\nTempo de Espera....: " + tempoEspera + " segundos");

        primeiro = proxima(primeiro);
        ocupacao--;
        return doc;
    }

    // Consultar Documento
    public String consultarDocumento(String nomeArquivo) {
        if (filaVazia()) {
            return "Fila vazia";
        }

        int pos = primeiro;

        for (int i = 0; i < ocupacao; i++) {
            if (dados[pos].getNomeArquivo().equals(nomeArquivo)) {

                String s = 
                    "Documento Encontrado.: " + nomeArquivo +
                    "\nPosicao na Fila......: " + (i + 1) +
                    "\nNome Usuario.........: " + dados[pos].getNomeUsuario() +
                    "\nHorário Solicitacao..: " + formatador.format(dados[pos].getHorarioSolicitacao().getTime());

                return s;
            }
            pos = proxima(pos);
        }
        return "Documento " + nomeArquivo + " nao encontrado na fila";
    }

    // Mostrar Fila
    @Override
    public String toString() {
        if (filaVazia()) {
            return "Fila vazia";
        }

        String s = "";

        for (int i = primeiro, cont = 0; cont < ocupacao; cont++) {
            s += (cont +1)+ "-" + dados[i].getNomeArquivo() + " ";
            i = proxima(i);
        }
        return s;
    }

    // Exibir o relatório de como está a fila
    public void relatorioFila() {
        System.out.println("Capacidade Total..: " + dados.length);
        System.out.println("Documentos na Fila: " + ocupacao);
        System.out.println("Posicoes Ocupadas.: " + (ocupacao * 100 / dados.length) + "%");
        System.out.println("Fila..............: " + toString());
    }
}