import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        No filhoEsquerdo2 = new No(15, null, null, 0);
        No filhoEsquerdo1 = new No(25, filhoEsquerdo2, null, 0);
        No raiz = new No(50, filhoEsquerdo1, null, 0);

        Arvore arvore = new Arvore(raiz);

        arvore.inserir(30);
        arvore.inserir(35);

        System.out.println(arvore.buscar(50).toString());
        System.out.println(arvore.buscar(30).toString());
        System.out.println(arvore.buscar(25).toString());
        System.out.println(arvore.buscar(15).toString());
        System.out.println(arvore.buscar(35).toString());

        // menu aqui
    }
}