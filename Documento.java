import java.util.Calendar;

public class Documento {

    // Dados
    private String nomeArquivo;
    private String nomeUsuario;
    private Calendar horarioSolicitacao;

    // Construtor
    public Documento(String nomeArquivo, String nomeUsuario, Calendar horarioSolicitacao) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioSolicitacao = horarioSolicitacao;
    }

    // Getters
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Calendar getHorarioSolicitacao() {
        return horarioSolicitacao;
    }
}