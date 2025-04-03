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
        if(no == null) return null;

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
            no.filhoEsquerdo = new No(valor, null, null, 0);
            return no;
        }
        if(no.valor < valor && no.filhoDireito == null) {
            no.filhoDireito = new No(valor, null, null, 0);
            return no;
        }

        if(no.valor > valor) return inserir(no.filhoEsquerdo, valor);
        if(no.valor < valor) return inserir(no.filhoDireito, valor);

        // nunca vai chegar aqui
        return null;
    }

    private No inserir(No no, No noParaInserir){
        if(no == null || noParaInserir == null) return null;

        if(no.valor > noParaInserir.valor && no.filhoEsquerdo == null) {
            no.filhoEsquerdo = noParaInserir;
            return no;
        }
        if(no.valor < noParaInserir.valor && no.filhoDireito == null) {
            no.filhoDireito = noParaInserir;
            return no;
        }

        if(no.valor > noParaInserir.valor) return inserir(no.filhoEsquerdo, noParaInserir);
        if(no.valor < noParaInserir.valor) return inserir(no.filhoDireito, noParaInserir);

        // nunca vai chegar aqui
        return null;
    }

    public boolean inserir(int valor) {
        boolean noExistente = buscar(raiz, valor) != null;

        if (noExistente) {
            System.out.println("Nó já existente na árvore.");
            return false;
        }

        inserir(raiz, valor);

        return true;
    }

    public boolean remover(int valor) {
        No pai = buscarPai(valor);
        No no;

        if(pai != null && pai.filhoEsquerdo != null && pai.filhoEsquerdo.valor == valor) {
            no = pai.filhoEsquerdo;
        } else if(pai != null && pai.filhoDireito != null && pai.filhoDireito.valor == valor) {
            no = pai.filhoDireito;
        } else {
            no = raiz;
        }

        if(no == null) {
            System.out.println("Nó não encontrado.");
            return false;
        }

        // Para facilitar o balanceamento, escolher o nó mais "comprido" como raiz
        if(pai == null){
            if(no.filhoEsquerdo != null) {
                raiz = no.filhoEsquerdo;
                inserir(raiz, no.filhoDireito);
            } else if(no.filhoDireito != null) {
                raiz = no.filhoDireito;
                inserir(raiz, no.filhoEsquerdo);
            } else {
                raiz = null;
            }

            return true;
        }

        pai.removerFilho(no);

        if(no.filhoEsquerdo != null) {
            inserir(raiz, no.filhoEsquerdo);
        }

        if(no.filhoDireito != null) {
            inserir(raiz, no.filhoDireito);
        }

        return true;
    }

    public int atualizarBalanceamento(No no) {
        if(no == null) return 0;

        if(no.filhoEsquerdo == null && no.filhoDireito == null) {
            no.fatorBalanceamento = 0;
            return 1;
        }

        int abaixoEsquerda = atualizarBalanceamento(no.filhoEsquerdo);
        int abaixoDireita = atualizarBalanceamento(no.filhoDireito);

        no.fatorBalanceamento = abaixoEsquerda - abaixoDireita;

        return Math.max(abaixoEsquerda, abaixoDireita) + 1;
    }

    public void balancear(int valor) {

    }

    public void listarPreOrdem() {

    }

    public void listarPosOrdem() {

    }

    public void listarEmOrdem() {

    }

    public void printar(){
        printar(raiz, 0);
    }

    private void printar(No no, int nivel) {
        if(no == null) return;

        for(int i = 0; i < nivel; i++) {
            System.out.print("\t");
        }

        System.out.println(no.valor + " (" + no.fatorBalanceamento + ")");

        printar(no.filhoEsquerdo, nivel + 1);
        printar(no.filhoDireito, nivel + 1);
    }
}
