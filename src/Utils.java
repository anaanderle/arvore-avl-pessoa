import java.util.Arrays;

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

    public static <T> T[] concatenar(T[] a, T[] b) {
        T[] resultado = Arrays.copyOf(a, a.length + b.length);
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
}
