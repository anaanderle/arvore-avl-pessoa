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
        No no = buscar(valor);

        if(no == null) {
            System.out.println("Nó não encontrado.");
            return false;
        }

        // logica de remocao

        return true;
    }

    public void balancear(int valor) {

    }

    public void listarPreOrdem() {

    }

    public void listarPosOrdem() {

    }

    public void listarEmOrdem() {

    }

    public void printar() {

    }
}
