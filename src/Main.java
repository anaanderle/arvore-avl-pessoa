import java.util.Scanner;

public class Main {
    public static void main(String[] args) {




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
                    String valorRaiz = scanner.next();
                    arvore = new Arvore(new No(valorRaiz, null, null, 0, 0, new Pessoa()));
                    System.out.println("Árvore criada com raiz: " + valorRaiz);
                    break;

                case 2:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.print("Digite o valor a ser buscado: ");
                    String valorBusca = scanner.next();
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
                    String valorInserir = scanner.next();
                    arvore.inserir(valorInserir, new Pessoa());
                    System.out.println("Valor " + valorInserir + " inserido.");
                    arvore.printar();
                    break;

                case 4:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.print("Digite o valor a ser excluído: ");
                    String valorExcluir = scanner.next();
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

                case 9:
                    if (arvore == null) {
                        System.out.println("Árvore não criada.");
                        break;
                    }
                    System.out.println("Busca inicial:");
                    String aa = scanner.next();
                    String bb = scanner.next();
                    No[] nos = arvore.buscarIntervalo(aa, bb);

                    for (No no : nos) {
                        System.out.println(no.index);
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}