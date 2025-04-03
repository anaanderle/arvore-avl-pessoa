import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        No filhoEsquerdo2 = new No(15, null, null, 0);
        No filhoEsquerdo1 = new No(25, null, null, 0);
        No raiz = new No(50, filhoEsquerdo1, null, 0);

        Arvore arvore = new Arvore(raiz);

        arvore.inserir(30);
        arvore.inserir(35);
        arvore.inserir(17);
        arvore.inserir(14);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(45);
        arvore.inserir(22);

        arvore.atualizarBalanceamento(raiz);
        arvore.printar();

//        arvore.remover(15);
//
//        System.out.println("Depois de remover");
//        arvore.printar();


        // menu aqui
    }
}