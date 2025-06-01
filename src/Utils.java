import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static boolean ehPositivoOuZero(int valor) {
        return valor >= 0;
    }

    public static boolean ehNegativoOuZero(int valor) {
        return valor <= 0;
    }

    public static boolean maior(String a, String b) {
        return a.compareTo(b) > 0;
    }

    public static boolean maiorOuIgual(String a, String b) {
        return a.compareTo(b) >= 0;
    }

    public static boolean menor(String a, String b) {
        return a.compareTo(b) < 0;
    }

    public static boolean menorOuIgual(String a, String b) {
        return a.compareTo(b) <= 0;
    }

    public static int[] concatenar(int[] a, int[] b) {
        int[] resultado = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, resultado, a.length, b.length);
        return resultado;
    }

    public static <T> T[] concatenar(T a, T[] b) {
        T[] resultado = Arrays.copyOf(b, b.length + 1);
        System.arraycopy(b, 0, resultado, 1, b.length);
        resultado[0] = a;
        return resultado;
    }

    public static <T> T[] concatenar(T a, T[] b, T[] c) {
        T[] resultado = Arrays.copyOf(b, b.length + c.length + 1);
        System.arraycopy(b, 0, resultado, 1, b.length);
        System.arraycopy(c, 0, resultado, b.length + 1, c.length);
        resultado[0] = a;
        return resultado;
    }

    public static List<Pessoa> leCsv(){
        String path = "dados.csv";
        String linha;
        List<Pessoa> pessoas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                String cpf = campos[0].trim();
                String rg = campos[1].trim();
                String nome = campos[2].trim();
                String dataNascimento = campos[3].trim();
                String cidadeNascimento = campos[4].trim();

                String[] dataNascimentoSplited = dataNascimento.split("/");
                int ano = Integer.parseInt(dataNascimentoSplited[2]);
                int mes = Integer.parseInt(dataNascimentoSplited[1]);
                int dia = Integer.parseInt(dataNascimentoSplited[0]);
                LocalDate nascimento = LocalDate.of(ano, mes, dia);
                Pessoa novaPessoa = new Pessoa(cpf, rg, nome, nascimento, cidadeNascimento);
                pessoas.add(novaPessoa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pessoas;
    }
}
