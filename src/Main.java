import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        No filhoEsquerdo2 = new No(15, null, null, 0);
//        No filhoEsquerdo1 = new No(25, null, null, 0);
        No raiz = new No(50, null, null, 0, 0);

        Arvore arvore = new Arvore(raiz);

        arvore.inserir(30);
        arvore.inserir(35);
        arvore.inserir(17);
        arvore.inserir(14);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(45);
        arvore.inserir(24);
        arvore.inserir(75);
        arvore.inserir(58);
        arvore.inserir(15);
        arvore.inserir(12);

//        arvore.atualizarBalanceamento(raiz);
        arvore.listarPreOrdem();
        arvore.listarEmOrdem();
        arvore.listarPosOrdem();
        arvore.printar();

//        arvore.remover(50);

        arvore.atualizarBalanceamento();
        arvore.printar();

//        arvore.remover(15);
//
//        System.out.println("Depois de remover");
//        arvore.printar();


        // menu aqui
    }
}