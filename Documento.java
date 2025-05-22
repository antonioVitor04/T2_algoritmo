import java.util.Date;

public class Documento {
    private String nomeArquivo;
    private String nomeUsuario;
    private Date horarioSolicitacao;
    private Date horarioImpressao;

    public Documento(String nomeArquivo, String nomeUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioSolicitacao = new Date(); // Captura o horário atual
    }

    public void registrarImpressao() {
        this.horarioImpressao = new Date(); // Registra o horário atual da impressão
    }

    public long calcularTempoEspera() {
        Date fim = (horarioImpressao == null) ? new Date() : horarioImpressao;
        return (fim.getTime() - horarioSolicitacao.getTime()) / 1000; // Diferença em segundos
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Date getHorarioSolicitacao() {
        return horarioSolicitacao;
    }

    @Override
    public String toString() {
        return "Documento: " + nomeArquivo + " | Usuário: " + nomeUsuario + 
               " | Solicitado em: " + horarioSolicitacao;
    }
}