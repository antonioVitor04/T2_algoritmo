import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PilhaReimpressao {

    // Dados
    private Documento[] dados;
    private int topo;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Biblioteca SimpleDateFormat

    // Construtor: Passa a Capacidade
    public PilhaReimpressao(int capacidade) {
        dados = new Documento[capacidade];
        topo = 0;
    }

    // Construtor: Capacidade Padrao = 10
    public PilhaReimpressao() {
        this(10);
    }

    // Pilha Vazia
    public boolean pilhaVazia() {
        return topo == 0;
    }

    // Pilha Cheia
    public boolean pilhaCheia() {
        return topo == dados.length;
    }

    // Push: Adicionar Documento ao Topo
    public void push(String nomeArquivo, String nomeUsuario, Calendar horarioSolicitacao) {
        if (pilhaCheia()) {
            throw new RuntimeException("Falha no empilhamento: Nao e possivel adicionar o documento: " + nomeArquivo);
        }

        dados[topo] = new Documento(nomeArquivo, nomeUsuario, horarioSolicitacao);
        topo++;

        System.out.println("Documento: " + nomeArquivo + " adicionado a pilha de reimpressao");
    }

    // Pop: Reimprimir Documento do Topo
    public Documento pop() {
        if (pilhaVazia()) {
            throw new RuntimeException("Falha na reimpressao: Nenhum documento para reimprimir");
        }

        topo--;
        Documento doc = dados[topo];
        Calendar horarioReimpressao = Calendar.getInstance();
        long tempoDecorrido = (horarioReimpressao.getTimeInMillis() - doc.getHorarioSolicitacao().getTimeInMillis()) / 1000;

        System.out.println(
                "Nome Arquivo.......: " + doc.getNomeArquivo() +
                "\nNome Usuario.......: " + doc.getNomeUsuario() +
                "\nHorario Solicitacao: " + formatador.format(doc.getHorarioSolicitacao().getTime()) +
                "\nHorario Reimpressao: " + formatador.format(horarioReimpressao.getTime()) +
                "\nTempo Decorrido....: " + tempoDecorrido + " segundos");

        return doc;
    }

    // Consultar Documento
    public String consultarDocumento(String nomeArquivo) {
        if (pilhaVazia()) {
            return "Pilha vazia";
        }

        for (int i = topo - 1; i >= 0; i--) {
            if (dados[i].getNomeArquivo().equals(nomeArquivo)) {
                int posicaoDoTopo = topo - i;
                
                String s = 
                    "Documento Encontrado.: " + nomeArquivo +
                    "\nPosicao do Topo......: " + posicaoDoTopo +
                    "\nNome Usuario.........: " + dados[i].getNomeUsuario() +
                    "\nHorario Solicitacao..: " + formatador.format(dados[i].getHorarioSolicitacao().getTime());

                return s;
            }
        }
        return "Documento " + nomeArquivo + " nao encontrado na pilha";
    }

    // Mostrar Pilha
    @Override
    public String toString() {
        if (pilhaVazia()) {
            return "Pilha vazia";
        }

        String s = "";

        for (int i = topo - 1, count = 1; i >= 0; i--, count++) {
            s += count + "-" + dados[i].getNomeArquivo() + " ";
        }
        return s;
    }

    // Exibir o relatorio de como esta a pilha
    public void relatorioPilha() {
        System.out.println("Capacidade Total...: " + dados.length);
        System.out.println("Documentos na Pilha: " + topo);
        System.out.println("Posicoes Ocupadas..: " + (topo * 100 / dados.length) + "%");
        System.out.println("Pilha..............: " + toString());
    }
}