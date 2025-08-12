import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Livro extends Publicacao {
    private final int paginas;
    private final String[] autores;

    public Livro(String titulo, LocalDate data, int paginas, String... autores) {
        super(titulo, data);
        this.paginas = paginas;
        this.autores = autores != null ? Arrays.copyOf(autores, autores.length) : new String[0];
    }

    public int getPaginas() { return paginas; }
    public String[] getAutores() { return Arrays.copyOf(autores, autores.length); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Livro)) return false;
        Livro other = (Livro) o;
        return Objects.equals(getTitulo(), other.getTitulo())
                && Objects.equals(getData(), other.getData())
                && this.paginas == other.paginas
                && Arrays.equals(this.autores, other.autores);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getTitulo(), getData(), paginas);
        result = 31 * result + Arrays.hashCode(autores);
        return result;
    }

    @Override
    public String toString() {
        String autoresStr = String.join(", ", autores);
        return "Livro: " + getTitulo() + " (" + getData().getYear() + ") - " + paginas
                + " paginas - Autores: " + autoresStr;
    }
}
