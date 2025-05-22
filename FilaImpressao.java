public class FilaImpressao {
    private Documento[] dados;
    private int primeiro, ultimo, ocupacao;
    private final int capacidade;

    public FilaImpressao(int capacidade) {
        this.capacidade = capacidade;
        dados = new Documento[capacidade];
        primeiro = 0;
        ultimo = 0;
        ocupacao = 0;
    }

    public FilaImpressao() {
        this(10);
    }

    public boolean filaVazia() {
        return ocupacao == 0;
    }

    public boolean filaCheia() {
        return ocupacao == capacidade;
    }

    private int proxima(int pos) {
        return (pos + 1) % capacidade;
    }

    public void enfileira(Documento doc) {
        if (filaCheia()) throw new RuntimeException("Fila de impressão cheia!");
        dados[ultimo] = doc;
        ultimo = proxima(ultimo);
        ocupacao++;
    }

    public Documento desenfileira() {
        if (filaVazia()) throw new RuntimeException("Fila de impressão vazia!");
        Documento temp = dados[primeiro];
        temp.registrarImpressao();
        primeiro = proxima(primeiro);
        ocupacao--;
        return temp;
    }

    public boolean contem(String nomeArquivo) {
        for (int i = primeiro, cont = 0; cont < ocupacao; cont++) {
            if (dados[i].getNomeArquivo().equals(nomeArquivo)) {
                return true;
            }
            i = proxima(i);
        }
        return false;
    }

    public int indiceDe(String nomeArquivo) {
        int pos = 1;
        for (int i = primeiro, cont = 0; cont < ocupacao; cont++) {
            if (dados[i].getNomeArquivo().equals(nomeArquivo)) {
                return pos;
            }
            pos++;
            i = proxima(i);
        }
        return -1;
    }

    public Documento consultarDocumento(String nomeArquivo) {
        for (int i = primeiro, cont = 0; cont < ocupacao; cont++) {
            if (dados[i].getNomeArquivo().equals(nomeArquivo)) {
                return dados[i];
            }
            i = proxima(i);
        }
        return null;
    }

    @Override
    public String toString() {
        if (filaVazia()) return "Fila de impressão vazia";
        String s = "Fila de Impressão:\n";
        for (int i = primeiro, cont = 0; cont < ocupacao; cont++) {
            s += (cont+1) + ". " + dados[i] + "\n";
            i = proxima(i);
        }
        return s;
    }

    public String stringVetor() {
        String s = "";
        int i;
        if (filaVazia()) {
            for (i = 0; i < capacidade; i++) {
                s += "_ ";
            }
        } else if (filaCheia()) {
            for (i = 0; i < capacidade; i++) {
                s += dados[i].getNomeArquivo() + " ";
            }
        } else if (primeiro < ultimo) {
            for (i = 0; i < primeiro; i++) {
                s += "_ ";
            }
            for (i = primeiro; i < ultimo; i++) {
                s += dados[i].getNomeArquivo() + " ";
            }
            for (i = ultimo; i < capacidade; i++) {
                s += "_ ";
            }
        } else {
            for (i = 0; i < ultimo; i++) {
                s += dados[i].getNomeArquivo() + " ";
            }
            for (i = ultimo; i < primeiro; i++) {
                s += "_ ";
            }
            for (i = primeiro; i < capacidade; i++) {
                s += dados[i].getNomeArquivo() + " ";
            }
        }
        return s;
    }
}