import java.time.LocalDate;
import java.util.List;

public class Pessoa {

    public String cpf;
    public String rg;
    public String nome;
    public LocalDate dataNascimento;
    public String cidadeNascimento;

    public Pessoa(){}

    public Pessoa(String cpf, String rg, String nome, LocalDate dataNascimento, String cidadeNascimento) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cidadeNascimento = cidadeNascimento;
    }

    public static void listar(List<Pessoa> pessoas , No[] nos){
        if (nos.length > 0) {
            for (No no : nos) {
                for (int idx : no.indexes) {
                    Pessoa p = pessoas.get(idx);
                    System.out.println("Pessoa encontrada: " + p.cpf + " - " + p.rg + " - " + p.nome + " - " + p.dataNascimento + " - " + p.cidadeNascimento);
                }
            }
        } else {
            System.out.println("Nenhuma pessoa encontrada.");
        }
    }

    public static void listar(List<Pessoa> pessoas , No no){
        if (no != null && no.indexes.length > 0) {
            for (int idx : no.indexes) {
                Pessoa p = pessoas.get(idx);
                System.out.println("Pessoa encontrada: " + p.cpf + " - " + p.rg + " - " + p.nome + " - " + p.dataNascimento + " - " + p.cidadeNascimento);
            }
        } else {
            System.out.println("Nenhuma pessoa encontrada.");
        }
    }
}
