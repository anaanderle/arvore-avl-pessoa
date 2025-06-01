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

        // fazer menu aqui conforme trabalho
        // buscando por cpf, nome inicial ou intervalo de nascimento

        Scanner scanner = new Scanner(System.in);

        arvoreCpf.printar();
        arvoreNome.printar();
        arvoreDataNascimento.printar();

        No[] nos = arvoreDataNascimento.buscarIntervalo("1972-01-02", "2992-12-25");

        int[] todosIndexes = new int[]{};
        for (int i = 0; i < nos.length; i++) {
            int[] indexes = nos[i].indexes;
            todosIndexes = Utils.concatenar(todosIndexes, indexes);
        }

        for (int i = 0; i < todosIndexes.length; i++) {
            Pessoa pessoa = pessoas.get(todosIndexes[i]);
            // fazer função de print em pessoa
            System.out.println("Pessoa encontrada: " + pessoa.nome + " - " + pessoa.cpf + " - " + pessoa.dataNascimento);
        }
    }
}