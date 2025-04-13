public class No {
    public int valor;
    public No filhoEsquerdo;
    public No filhoDireito;
    public int nivelEsquerdo;
    public int nivelDireito;

    public No(int valor, No filhoEsquerdo, No filhoDireito, int nivelEsquerdo, int nivelDireito) {
        this.valor = valor;
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
        this.nivelEsquerdo = nivelEsquerdo;
        this.nivelDireito = nivelDireito;
    }

    public String toString() {
        String filhoEsquerdoString = this.filhoEsquerdo == null ? "null" : String.valueOf(this.filhoEsquerdo.valor);
        String filhoDireitoString = this.filhoDireito == null ? "null" : String.valueOf(this.filhoDireito.valor);

        return "No{" +
                "\n\tvalor=" + valor +
                "\n\tfilhoEsquerdo=" + filhoEsquerdoString +
                "\n\tfilhoDireito=" + filhoDireitoString +
                "\n\tnivelEsquerdo=" + nivelEsquerdo +
                "\n\tnivelDireito=" + nivelDireito +
                "\n\tfatorBalanceamento=" + fatorBalanceamento() +
                "\n}";
    }

    public void removerFilho(No filho){
        if(filho != null && filhoEsquerdo != null && filhoEsquerdo.valor == filho.valor) {
            filhoEsquerdo = null;
            nivelEsquerdo = 0;
            return;
        }

        filhoDireito = null;
        nivelDireito = 0;
    }

    public void adicionarFilhoEsquerdo(No no){
        filhoEsquerdo = no;
        nivelEsquerdo = no == null ? 0 : (no.maiorNivelSubArvore() + 1);
    }

    public void adicionarFilhoDireito(No no){
        filhoDireito = no;
        nivelDireito = no == null ? 0 : (no.maiorNivelSubArvore() + 1);
    }

    public int fatorBalanceamento(){
        return nivelEsquerdo - nivelDireito;
    }

    public boolean fatorBalanceamentoAceitavel(){
        int fatorBalanceamento = fatorBalanceamento();
        return fatorBalanceamento >= -1 && fatorBalanceamento <= 1;
    }

    public No getMaiorSubArvore() {
        if(nivelEsquerdo == 0 && nivelDireito == 0) return null;
        if(nivelDireito > nivelEsquerdo) return filhoDireito;
        return filhoEsquerdo;
    }

    public No getMenorSubArvore() {
        if(nivelEsquerdo == 0 && nivelDireito == 0) return null;
        if(nivelDireito > nivelEsquerdo) return filhoEsquerdo;
        return filhoDireito;
    }

    public int maiorNivelSubArvore(){
        return Math.max(nivelDireito, nivelEsquerdo);
    }
}