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
        if(no == null || (no.filhoDireito == null && no.filhoDireito == null)) return null;

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
            no.filhoEsquerdo = new No(valor, null, null, 0, 0);
            no.nivelEsquerdo = 1;
            return no;
        }

        if(no.valor < valor && no.filhoDireito == null) {
            no.filhoDireito = new No(valor, null, null, 0, 0);
            no.nivelDireito = 1;
            return no;
        }

        if(no.valor > valor) {
            No noRetorno = inserir(no.filhoEsquerdo, valor);
            no.nivelEsquerdo = noRetorno.maiorNivelSubArvore() + 1;
            return no;
        }

        if(no.valor < valor) {
            No noRetorno = inserir(no.filhoDireito, valor);
            no.nivelDireito = noRetorno.maiorNivelSubArvore() + 1;
            return no;
        }

        return null;
    }

    private void inserir(No no, No noParaInserir){
        if(no == null || noParaInserir == null) return;

        if(no.valor > noParaInserir.valor && no.filhoEsquerdo == null) {
            no.filhoEsquerdo = noParaInserir;
            no.nivelEsquerdo = noParaInserir.maiorNivelSubArvore() + 1;
            return;
        }

        if(no.valor < noParaInserir.valor && no.filhoDireito == null) {
            no.filhoDireito = noParaInserir;
            no.nivelDireito = noParaInserir.maiorNivelSubArvore() + 1;
            return;
        }

        if(no.valor > noParaInserir.valor) {
            inserir(no.filhoEsquerdo, noParaInserir);
            no.nivelEsquerdo = no.filhoEsquerdo.maiorNivelSubArvore() + 1;
            return;
        }

        if(no.valor < noParaInserir.valor) {
            inserir(no.filhoDireito, noParaInserir);
            no.nivelDireito = no.filhoDireito.maiorNivelSubArvore() + 1;
        }
    }

    public No inserir(int valor) {
        return inserir(raiz, valor);
    }

    public void remover(int valor) {
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
            inserir(raiz, menor);

            return;
        }

        pai.removerFilho(no);

        No maior = no.getMaiorSubArvore();
        No menor = no.getMenorSubArvore();
        inserir(raiz, maior);
        inserir(raiz, menor);
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

        return Math.max(no.nivelEsquerdo, no.nivelDireito);

    }

    public void balancear(int valor) {

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
