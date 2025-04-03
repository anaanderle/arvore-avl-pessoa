public class No {
    public int valor;
    public No filhoEsquerdo;
    public No filhoDireito;
    public int fatorBalanceamento;

    public No(int valor, No filhoEsquerdo, No filhoDireito, int fatorBalanceamento) {
        this.valor = valor;
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public String toString() {
        String filhoEsquerdoString = this.filhoEsquerdo == null ? "null" : String.valueOf(this.filhoEsquerdo.valor);
        String filhoDireitoString = this.filhoDireito == null ? "null" : String.valueOf(this.filhoDireito.valor);

        return "No{" +
                "\nvalor=" + valor +
                "\nfilhoEsquerdo=" + filhoEsquerdoString +
                "\nfilhoDireito=" + filhoDireitoString +
                "\nfatorBalanceamento=" + fatorBalanceamento +
                "\n}";
    }

    public void removerFilho(No filho){
        if(filho != null && filhoEsquerdo != null && filhoEsquerdo.valor == filho.valor) {
            filhoEsquerdo = null;
            return;
        }

        filhoDireito = null;
    }
}