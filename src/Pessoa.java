import java.time.LocalDate;

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
}
