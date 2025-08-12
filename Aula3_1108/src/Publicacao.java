import java.time.LocalDate;
import java.util.Objects;

public abstract class Publicacao {
    private final String titulo;
    private final LocalDate data;

    protected Publicacao(String titulo, LocalDate data) {
        this.titulo = titulo;
        this.data = data;
    }

    public String getTitulo() { return titulo; }
    public LocalDate getData() { return data; }

    // As subclasses terão equals/hashCode próprios, mas com base também nestes campos.
    @Override
    public int hashCode() {
        return Objects.hash(titulo, data);
    }
}
