public class Arvore {

    private No raiz;

    public Arvore(No raiz) {
        this.raiz = raiz;
    }

    public No buscar(int valor) {
        return buscar(raiz, valor);
    }

    private No buscar(No no, int valor) {
        if(no == null) return null;

        if(no.valor == valor) return no;
        if(no.filhoEsquerdo != null && no.valor >= valor) return buscar(no.filhoEsquerdo, valor);
        if(no.filhoDireito != null && no.valor <= valor) return buscar(no.filhoDireito, valor);

        return null;
    }

    public No buscarPai(int valor) {
        return buscarPai(raiz, valor);
    }

    private No buscarPai(No no, int valor) {
        if(no == null || (no.filhoDireito == null && no.filhoEsquerdo == null)) return null;

        if(no.filhoEsquerdo != null && no.filhoEsquerdo.valor == valor) return no;
        if(no.filhoDireito != null && no.filhoDireito.valor == valor) return no;
        if(no.filhoEsquerdo != null && no.valor >= valor) return buscarPai(no.filhoEsquerdo, valor);
        if(no.filhoDireito != null && no.valor <= valor) return buscarPai(no.filhoDireito, valor);

        // Nó raiz
        return null;
    }

    private No inserir(No no, int valor){
        if(no == null) return null;

        if(no.valor > valor && no.filhoEsquerdo == null) {
            no.adicionarFilhoEsquerdo(new No(valor, null, null, 0, 0));
            return no;
        }

        if(no.valor < valor && no.filhoDireito == null) {
            no.adicionarFilhoDireito(new No(valor, null, null, 0, 0));
            return no;
        }

        if(no.valor > valor) {
            No noRetorno = inserir(no.filhoEsquerdo, valor);
            if(no.filhoEsquerdo.valor == noRetorno.valor) no.nivelEsquerdo = noRetorno.maiorNivelSubArvore() + 1;
            balancear(no);
            return no;
        }

        if(no.valor < valor) {
            No noRetorno = inserir(no.filhoDireito, valor);
            if(no.filhoDireito.valor == noRetorno.valor) no.nivelDireito = noRetorno.maiorNivelSubArvore() + 1;
            balancear(no);
            return no;
        }

        return null;
    }

    private No inserir(No no, No noParaInserir, boolean permiteRebalanceamento){
        if(no == null || noParaInserir == null) return null;

        if(no.valor > noParaInserir.valor && no.filhoEsquerdo == null) {
            no.adicionarFilhoEsquerdo(noParaInserir);
            return no;
        }

        if(no.valor < noParaInserir.valor && no.filhoDireito == null) {
            no.adicionarFilhoDireito(noParaInserir);
            return no ;
        }

        if(no.valor > noParaInserir.valor) {
            No noRetorno = inserir(no.filhoEsquerdo, noParaInserir, permiteRebalanceamento);
            if(no.filhoEsquerdo.valor == noRetorno.valor) no.nivelEsquerdo = noRetorno.maiorNivelSubArvore() + 1;
            if(permiteRebalanceamento) balancear(no);
            return no;
        }

        if(no.valor < noParaInserir.valor) {
            No noRetorno = inserir(no.filhoDireito, noParaInserir, permiteRebalanceamento);
            if(no.filhoDireito.valor == noRetorno.valor) no.nivelDireito = noRetorno.maiorNivelSubArvore() + 1;
            if(permiteRebalanceamento) balancear(no);
            return no;
        }

        return null;
    }

    public void inserir(int valor) {
        if(raiz == null){
            raiz = new No(valor, null, null, 0, 0);
            return;
        }

        inserir(raiz, valor);
    }

    public void remover(int valor) {
        remover(valor, true);
    }

    private void remover(int valor, boolean conectarFilhos) {
        No pai = buscarPai(valor);
        No no;

        if(pai == null){
            no = raiz;
        } else if(pai.filhoEsquerdo != null && pai.filhoEsquerdo.valor == valor){
            no = pai.filhoEsquerdo;
        } else {
            no = pai.filhoDireito;
        }

        if(no == null) {
            System.out.println("Nó não encontrado.");
            return;
        }

        if(pai == null){
            No maior = no.getMaiorSubArvore();
            No menor = no.getMenorSubArvore();

            raiz = maior;
            if(conectarFilhos) inserir(raiz, menor, true);

            return;
        }

        pai.removerFilho(no);

        if(conectarFilhos){
            No maior = no.getMaiorSubArvore();
            No menor = no.getMenorSubArvore();
            inserir(raiz, maior, true);
            inserir(raiz, menor, true);
        }

        balancear(pai);
    }

    public void atualizarBalanceamento() {
        atualizarBalanceamento(raiz);
    }

    public int atualizarBalanceamento(No no) {
        if(no == null) return 0;

        int nivelEsquerdo = atualizarBalanceamento(no.filhoEsquerdo);
        int nivelDireito = atualizarBalanceamento(no.filhoDireito);

        no.nivelEsquerdo = nivelEsquerdo + 1;
        no.nivelDireito = nivelDireito + 1;

        return no.maiorNivelSubArvore();
    }

    // nomear melhor
    private void rotacaoSimplesDireita(No no, boolean permiteRebalanceamento) {
        if(no == null)  return;
        No antigoDireito =  no.filhoEsquerdo == null ? null : no.filhoEsquerdo.filhoDireito;
        remover(no.valor, false);
        if(no.filhoEsquerdo != null) no.filhoEsquerdo.adicionarFilhoDireito(null);
        inserir(raiz, no.filhoEsquerdo, permiteRebalanceamento);
        no.adicionarFilhoEsquerdo(null);
        inserir(raiz, no, permiteRebalanceamento);
        inserir(raiz, antigoDireito, permiteRebalanceamento);
    }

    // nomear melhor
    private void rotacaoSimplesEsquerda(No no, boolean permiteRebalanceamento) {
        if(no == null)  return;
        No antigoEsquerdo =  no.filhoDireito == null ? null : no.filhoDireito.filhoEsquerdo;
        remover(no.valor, false);
        if(no.filhoDireito != null) no.filhoDireito.adicionarFilhoEsquerdo(null);
        inserir(raiz, no.filhoDireito, permiteRebalanceamento);
        no.adicionarFilhoDireito(null);
        inserir(raiz, no, permiteRebalanceamento);
        inserir(raiz, antigoEsquerdo, permiteRebalanceamento);
    }

    public void balancear(No no) {
        if(no.fatorBalanceamentoAceitavel()) return;

        int fb = no.fatorBalanceamento();
        int fbe = no.filhoEsquerdo == null ? 0 : no.filhoEsquerdo.fatorBalanceamento();
        int fbd = no.filhoDireito == null ? 0 : no.filhoDireito.fatorBalanceamento();

        if(Utils.ehPositivoOuZero(fb) && Utils.ehPositivoOuZero(fbe) && fb > fbe) {
            rotacaoSimplesDireita(no, true);
        }
        else if (Utils.ehNegativoOuZero(fb) && Utils.ehNegativoOuZero(fbd) && fb < fbd) {
            rotacaoSimplesEsquerda(no, true);
        } else if (Utils.ehPositivoOuZero(fb) && Utils.ehNegativoOuZero(fbe)) {
            rotacaoSimplesEsquerda(no.filhoEsquerdo, false);
            rotacaoSimplesDireita(no, true);
        } else if (Utils.ehNegativoOuZero(fb) && Utils.ehPositivoOuZero(fbd)) {
            rotacaoSimplesDireita(no.filhoDireito, false);
            rotacaoSimplesEsquerda(no, true);
        }
    }

    public String obterPreOrdem(){
        return obterPreOrdem(raiz);
    }

    public String obterPreOrdem(No no){
        if (no == null) return "";

        return (no.valor + " ") + obterPreOrdem(no.filhoEsquerdo) + obterPreOrdem(no.filhoDireito);
    }

    public void listarPreOrdem() {
        listarPreOrdem(raiz);
        System.out.println();
    }

    private void listarPreOrdem(No no) {
        if (no == null) return;

        System.out.print(no.valor + " ");

        listarPreOrdem(no.filhoEsquerdo);
        listarPreOrdem(no.filhoDireito);
    }

    public void listarPosOrdem() {
        listarPosOrdem(raiz);
        System.out.println();
    }

    private void listarPosOrdem(No no) {
        if (no == null) return;

        listarPosOrdem(no.filhoEsquerdo);
        listarPosOrdem(no.filhoDireito);
        System.out.print(no.valor + " ");
    }

    public void listarEmOrdem() {
        listarEmOrdem(raiz);
        System.out.println();
    }

    private void listarEmOrdem(No no) {
        if (no == null) return;

        listarEmOrdem(no.filhoEsquerdo);
        System.out.print(no.valor + " ");
        listarEmOrdem(no.filhoDireito);
    }

    public void printar(){
        printar(raiz, 0);
    }

    private void printar(No no, int nivel) {
        if(no == null) return;

        for(int i = 0; i < nivel; i++) {
            System.out.print("\t");
        }

        System.out.println(no.valor + " (" + no.fatorBalanceamento() + ")");

        printar(no.filhoEsquerdo, nivel + 1);
        printar(no.filhoDireito, nivel + 1);
    }
}
