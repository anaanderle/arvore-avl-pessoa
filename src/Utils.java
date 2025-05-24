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
}
