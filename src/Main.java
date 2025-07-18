import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Pessoa> pessoas = Utils.leCsv();

        Arvore arvoreCpf = new Arvore(null);
        Arvore arvoreNome = new Arvore(null);
        Arvore arvoreDataNascimento = new Arvore(null);

        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoa = pessoas.get(i);
            arvoreCpf.inserir(pessoa.cpf, new int[]{i});
            arvoreNome.inserir(pessoa.nome, new int[]{i});
            arvoreDataNascimento.inserir(pessoa.dataNascimento.toString(), new int[]{i});
        }

        Scanner scanner;
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Consultar pessoa por CPF");
            System.out.println("2. Consultar pessoas por início do nome");
            System.out.println("3. Consultar pessoas por intervalo de datas de nascimento");
            System.out.println("4. Exibir árvore de CPF");
            System.out.println("5. Exibir árvore de nome");
            System.out.println("6. Exibir árvore de data de nascimento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                scanner.nextLine();
                continue;
            }
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    No noCpf = arvoreCpf.buscar(cpf);
                    Pessoa.listar(pessoas, noCpf);
                    break;
                case 2:
                    System.out.print("Digite o início do nome: ");
                    String inicioNome = scanner.nextLine();
                    No[] nosNome = arvoreNome.buscarInicial(inicioNome);
                    Pessoa.listar(pessoas, nosNome);
                    break;
                case 3:
                    System.out.print("Digite a data inicial (AAAA-MM-DD): ");
                    String dataIni = scanner.nextLine();
                    System.out.print("Digite a data final (AAAA-MM-DD): ");
                    String dataFim = scanner.nextLine();
                    No[] nosData = arvoreDataNascimento.buscarIntervalo(dataIni, dataFim);
                    Pessoa.listar(pessoas, nosData);
                    break;
                case 4:
                    arvoreCpf.printar();
                    break;
                case 5:
                    arvoreNome.printar();
                    break;
                case 6:
                    arvoreDataNascimento.printar();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}