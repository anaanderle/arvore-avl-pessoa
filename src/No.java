public class No {
    public String index;

    public No filhoEsquerdo;
    public No filhoDireito;
    public int nivelEsquerdo;
    public int nivelDireito;

    public No(String index, No filhoEsquerdo, No filhoDireito, int nivelEsquerdo, int nivelDireito) {
        this.index = index;
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
        this.nivelEsquerdo = nivelEsquerdo;
        this.nivelDireito = nivelDireito;
    }

    public String toString() {
        String filhoEsquerdoString = this.filhoEsquerdo == null ? "null" : this.filhoEsquerdo.index;
        String filhoDireitoString = this.filhoDireito == null ? "null" : this.filhoDireito.index;

        return "No {" +
                "\n\tIndex = " + index +
                "\n\tFilho Esquerdo = " + filhoEsquerdoString +
                "\n\tFilho Direito = " + filhoDireitoString +
                "\n\tNivel Esquerdo = " + nivelEsquerdo +
                "\n\tNivel Direito = " + nivelDireito +
                "\n\tFator Balanceamento = " + fatorBalanceamento() +
                "\n}";
    }

    public void removerFilho(No filho){
        if(filho != null && filhoEsquerdo != null && filhoEsquerdo.index.equals(filho.index)) {
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