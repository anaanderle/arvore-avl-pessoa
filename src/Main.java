import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // testes
//        ArvoreTeste.rotacaoSimplesDireitaSlide13();
//        ArvoreTeste.rotacaoSimplesDireitaSlide18();
//        ArvoreTeste.rotacaoSimplesEsquerdaSlide20();
//        ArvoreTeste.rotacaoSimplesEsquerdaSlide23();
//        ArvoreTeste.rotacaoDuplaDireitaSlide25();
//        ArvoreTeste.rotacaoDuplaDireitaSlide29();
//        ArvoreTeste.rotacaoDuplaEsquerdaSlide32();
//        ArvoreTeste.exclusaoSlide38SemExcluir();
//        ArvoreTeste.exclusaoSlide38();
//        ArvoreTeste.caso1();
//        ArvoreTeste.caso2();
//        ArvoreTeste.caso3();
//        ArvoreTeste.caso4();
//        ArvoreTeste.caso5();
//        ArvoreTeste.caso6();
//        ArvoreTeste.caso7();
//        ArvoreTeste.caso8();
//        ArvoreTeste.caso9();
//        ArvoreTeste.caso10();
//        ArvoreTeste.caso11();

//        arvore.listarPreOrdem();
//        arvore.listarEmOrdem();
//        arvore.listarPosOrdem();


        // menu aqui
        Scanner scanner = new Scanner(System.in);
        Arvore arvore = null;

        while (true) {
            System.out.println("\n//==============MENU==============//");
            System.out.println("1. Criar Árvore");
            System.out.println("2. Buscar Nó");
            System.out.println("3. Inserir Valor");
            System.out.println("4. Excluir Valor");
            System.out.println("5. Listar em Pré-Ordem");
            System.out.println("6. Listar em Pós-Ordem");
            System.out.println("7. Listar Em Ordem");
            System.out.println("8. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor da raiz: ");
                    int valorRaiz = scanner.nextInt();
                    arvore = new Arvore(new No(valorRaiz, null, null, 0, 0));
                    System.out.println("Árvore criada com raiz: " + valorRaiz);
                    break;

                case 2:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.print("Digite o valor a ser buscado: ");
                    int valorBusca = scanner.nextInt();
                    No noEncontrado = arvore.buscar(valorBusca);
                    if (noEncontrado != null) {
                        arvore.printar();
                        System.out.println("Nó encontrado: " + noEncontrado);
                    } else {
                        System.out.println("Nó não encontrado.");
                    }
                    break;

                case 3:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.print("Digite o valor a ser inserido: ");
                    int valorInserir = scanner.nextInt();
                    arvore.inserir(valorInserir);
                    System.out.println("Valor " + valorInserir + " inserido.");
                    arvore.printar();
                    break;

                case 4:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.print("Digite o valor a ser excluído: ");
                    int valorExcluir = scanner.nextInt();
                    arvore.remover(valorExcluir);
                    System.out.println("Valor " + valorExcluir + " removido.");
                    arvore.printar();
                    break;

                case 5:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.println("Listagem em Pré-Ordem:");
                    arvore.listarPreOrdem();
                    break;

                case 6:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.println("Listagem em Pós-Ordem:");
                    arvore.listarPosOrdem();
                    break;

                case 7:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.println("Listagem em Ordem:");
                    arvore.listarEmOrdem();
                    break;

                case 8:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}