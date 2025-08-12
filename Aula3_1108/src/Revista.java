import java.time.LocalDate;
import java.util.Objects;

public class Revista extends Publicacao {
    private final String editora;
    private final int edicao;

    public Revista(String titulo, LocalDate data, String editora, int edicao) {
        super(titulo, data);
        this.editora = editora;
        this.edicao = edicao;
    }

    public String getEditora() { return editora; }
    public int getEdicao() { return edicao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revista)) return false;
        Revista other = (Revista) o;
        return edicao == other.edicao
                && Objects.equals(getTitulo(), other.getTitulo())
                && Objects.equals(getData(), other.getData())
                && Objects.equals(editora, other.editora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getData(), editora, edicao);
    }

    @Override
    public String toString() {
        LocalDate d = getData();
        return "Revista: " + getTitulo() + " (" + d.getMonthValue() + "/" + d.getYear()
                + ") - editora " + editora + " - edicao n:" + edicao;
    }
}
