public class PilhaReimpressao {
    private Documento[] vetor;
    private int topo;
    private final int capacidade;

    public PilhaReimpressao(int tamanho) {
        vetor = new Documento[tamanho];
        topo = 0;
        capacidade = tamanho;
    }

    public PilhaReimpressao() {
        this(10);
    }

    public void push(Documento doc) {
        if (estaCheio()) throw new RuntimeException("Pilha de reimpressão cheia!");
        vetor[topo++] = doc;
    }

    public Documento pop() {
        if (estaVazio()) throw new RuntimeException("Pilha de reimpressão vazia!");
        Documento doc = vetor[--topo];
        doc.registrarImpressao();
        return doc;
    }

    public boolean estaCheio() {
        return topo == capacidade;
    }

    public boolean estaVazio() {
        return topo == 0;
    }

    public boolean contem(String nomeArquivo) {
        for (int j = 0; j < topo; j++) {
            if (vetor[j].getNomeArquivo().equals(nomeArquivo)) {
                return true;
            }
        }
        return false;
    }

    public int indiceDe(String nomeArquivo) {
        for (int j = topo-1; j >= 0; j--) {
            if (vetor[j].getNomeArquivo().equals(nomeArquivo)) {
                return topo - j; // posição a partir do topo
            }
        }
        return -1;
    }

    public Documento consultarDocumento(String nomeArquivo) {
        for (int j = topo-1; j >= 0; j--) {
            if (vetor[j].getNomeArquivo().equals(nomeArquivo)) {
                return vetor[j];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "Pilha de Reimpressão:\n-----------\n";
        if (estaVazio()) {
            s += "vazia\n";
        } else {
            for (int i = topo-1; i >= 0; i--) {
                s += (topo-i) + ". " + vetor[i] + "\n";
            }
        }
        return s + "-----------\n";
    }
}