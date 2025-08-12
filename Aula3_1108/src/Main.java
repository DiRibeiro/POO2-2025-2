import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Livro l1 = new Livro("titulo legal", LocalDate.of(2012, 1, 1), 154, "Julio Gil", "Carla Silva");
        Revista r1 = new Revista("TituloInteressante", LocalDate.of(2021, 3, 1), "Pamimi", 15);

        Livraria liv = new Livraria("Minha Livraria");
        liv.addEstoque(l1, 2);
        liv.addEstoque(r1, 3);

        System.out.println("Estoque livro: " + liv.getExemplares(l1)); // 2
        System.out.println("Estoque revista: " + liv.getExemplares(r1)); // 3

        Impressao venda = liv.vende(r1);
        System.out.println("Vendido:\n" + venda);

        System.out.println("Estoque revista (apos venda): " + liv.getExemplares(r1)); // 2
    }
}
