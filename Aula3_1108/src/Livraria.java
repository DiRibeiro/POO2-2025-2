import java.util.*;

public class Livraria {
    // Estoque por publicação, mantendo fila FIFO de impressões
    private final Map<Publicacao, Deque<Impressao>> estoque = new HashMap<>();

    public Livraria(String nome) {
    }

    public void addEstoque(Publicacao p) {
        estoque.computeIfAbsent(p, k -> new ArrayDeque<>()).addLast(new Impressao(p));
    }

    public void addEstoque(Publicacao p, int quantidade) {
        for (int i = 0; i < quantidade; i++) addEstoque(p);
    }

    public int getExemplares(Publicacao p) {
        Deque<Impressao> fila = estoque.get(p);
        return fila == null ? 0 : fila.size();
    }

    public Impressao vende(Publicacao p) {
        Deque<Impressao> fila = estoque.get(p);
        if (fila == null || fila.isEmpty()) return null;
        Impressao imp = fila.pollFirst(); // FIFO (L6, depois L7, etc.)
        if (fila.isEmpty()) estoque.remove(p);
        return imp;
    }
}
