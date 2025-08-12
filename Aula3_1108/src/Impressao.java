import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Impressao {
    private static int sequenciaGlobal = 0;

    private final String codigo;
    private final Publicacao publicacao;
    private final LocalDate data;

    public Impressao(Publicacao publicacao) {
        this.publicacao = publicacao;
        this.data = LocalDate.now();
        this.codigo = (publicacao instanceof Revista ? "R" : "L") + (sequenciaGlobal++);
    }

    public String getCodigo() { return codigo; }
    public Publicacao getPublicacao() { return publicacao; }
    public LocalDate getData() { return data; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "exemplar: " + codigo + "| Data impressao: " + data.format(fmt) + "\n"
                + publicacao.toString();
    }
}
