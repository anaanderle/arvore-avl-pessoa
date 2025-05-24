public class Arvore {

    private No raiz;

    public Arvore(No raiz) {
        this.raiz = raiz;
    }

    public No buscar(String index) {
        return buscar(raiz, index);
    }

    private No buscar(No no, String index) {
        if(no == null) return null;

        if(no.index.equals(index)) return no;
        if(no.filhoEsquerdo != null && Utils.maiorOuIgual(no.index, index)) return buscar(no.filhoEsquerdo, index);
        if(no.filhoDireito != null && Utils.menorOuIgual(no.index, index)) return buscar(no.filhoDireito, index);

        return null;
    }

    public No buscarPai(String index) {
        return buscarPai(raiz, index);
    }

    private No buscarPai(No no, String index) {
        // nó não pode ser pai de ninguém
        if(no == null || (no.filhoDireito == null && no.filhoEsquerdo == null)) return null;

        // nó é pai
        if(Utils.maior(no.index, index) && no.filhoEsquerdo != null && no.filhoEsquerdo.index.equals(index)) return no;
        if(Utils.menor(no.index, index) && no.filhoDireito != null && no.filhoDireito.index.equals(index)) return no;

        // nó não é pai mas possui filhos que podem ser
        if(Utils.maior(no.index, index )) return buscarPai(no.filhoEsquerdo, index);
        if(Utils.menor(no.index, index )) return buscarPai(no.filhoDireito, index);

        // Nó raiz
        return null;
    }

    private No inserir(No no, String index){
        if(no == null) return null;

        // encontrado local para inserir
        if(Utils.maior(no.index, index ) && no.filhoEsquerdo == null) {
            no.adicionarFilhoEsquerdo(new No(index, null, null, 0, 0));
            return no;
        }

        // encontrado local para inserir
        if(Utils.menor(no.index, index ) && no.filhoDireito == null) {
            no.adicionarFilhoDireito(new No(index, null, null, 0, 0));
            return no;
        }

        // procurando local para inserir na subárvore
        if(Utils.maior(no.index, index)) {
            No noRetorno = inserir(no.filhoEsquerdo, index);
            // se houver um retorno, então um nó foi inserido e é preciso ajustar o nível
            if(noRetorno != null) no.nivelEsquerdo = buscar(raiz, no.index).filhoEsquerdo.maiorNivelSubArvore() + 1;
            balancear(no);
            return no;
        }

        // procurando local para inserir na subárvore
        if(Utils.menor(no.index, index)) {
            No noRetorno = inserir(no.filhoDireito, index);
            // se houver um retorno, então um nó foi inserido e é preciso ajustar o nível
            if(noRetorno != null) no.nivelDireito = buscar(raiz, no.index).filhoDireito.maiorNivelSubArvore() + 1;
            balancear(no);
            return no;
        }

        return null;
    }

    // funciona igual a inserir(No no, int valor), porém para nós já existentes
    private No inserir(No no, No noParaInserir, boolean permiteRebalanceamento){
        if(no == null || noParaInserir == null) return null;

        if(Utils.maior(no.index, noParaInserir.index) && no.filhoEsquerdo == null) {
            no.adicionarFilhoEsquerdo(noParaInserir);
            return no;
        }

        if(Utils.menor(no.index, noParaInserir.index) && no.filhoDireito == null) {
            no.adicionarFilhoDireito(noParaInserir);
            return no ;
        }

        if(Utils.maior(no.index, noParaInserir.index)) {
            No noRetorno = inserir(no.filhoEsquerdo, noParaInserir, permiteRebalanceamento);
            if(noRetorno != null) no.nivelEsquerdo = buscar(raiz, no.index).filhoEsquerdo.maiorNivelSubArvore() + 1;
            if(permiteRebalanceamento) balancear(no);
            return no;
        }

        if(Utils.menor(no.index, noParaInserir.index)) {
            No noRetorno = inserir(no.filhoDireito, noParaInserir, permiteRebalanceamento);
            if(noRetorno != null) no.nivelDireito = buscar(raiz, no.index).filhoDireito.maiorNivelSubArvore() + 1;
            if(permiteRebalanceamento) balancear(no);
            return no;
        }

        return null;
    }

    public void inserir(String index) {
        if(raiz == null){
            raiz = new No(index, null, null, 0, 0);
            return;
        }

        inserir(raiz, index);
    }

    public void remover(String index) {
        remover(index, true);
    }

    private void remover(String index, boolean conectarFilhos) {
        No pai = buscarPai(index);
        No no;

        if(pai == null){
            no = raiz;
        } else if(pai.filhoEsquerdo != null && pai.filhoEsquerdo.index.equals(index)){
            no = pai.filhoEsquerdo;
        } else {
            no = pai.filhoDireito;
        }

        if(no == null) return;

        // remoção da raiz
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

    private void rotacaoSimplesDireita(No no, boolean permiteRebalanceamento) {
        if(no == null)  return;

        // remove o nó desbalanceado
        remover(no.index, false);

        // extrai o filho direito do nó substituto
        No filhoDireitoSubstituto =  no.filhoEsquerdo == null ? null : no.filhoEsquerdo.filhoDireito;
        if(no.filhoEsquerdo != null) no.filhoEsquerdo.adicionarFilhoDireito(null);

        // insere o nó substituto e atualiza o nó desbalanceado
        inserir(raiz, no.filhoEsquerdo, permiteRebalanceamento);
        no.adicionarFilhoEsquerdo(null);

        // insere o nó desbalanceado e o filho direito do nó substituto
        inserir(raiz, no, permiteRebalanceamento);
        inserir(raiz, filhoDireitoSubstituto, permiteRebalanceamento);
    }

    private void rotacaoSimplesEsquerda(No no, boolean permiteRebalanceamento) {
        if(no == null)  return;

        // remove o nó desbalanceado
        remover(no.index, false);

        // extrai o filho esquerdo do nó substituto
        No filhoEsquerdoSubstituto =  no.filhoDireito == null ? null : no.filhoDireito.filhoEsquerdo;
        if(no.filhoDireito != null) no.filhoDireito.adicionarFilhoEsquerdo(null);

        // insere o nó substituto e atualiza o nó desbalanceado
        inserir(raiz, no.filhoDireito, permiteRebalanceamento);
        no.adicionarFilhoDireito(null);

        // insere o nó desbalanceado e o filho esquerdo do nó substituto
        inserir(raiz, no, permiteRebalanceamento);
        inserir(raiz, filhoEsquerdoSubstituto, permiteRebalanceamento);
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
            // o permite rebalanceamento é falso pois nessa primeira etapa a árvore ainda não fica balanceada
            // então é preciso aguardar a finalização das duas etapas
            rotacaoSimplesEsquerda(no.filhoEsquerdo, false);
            rotacaoSimplesDireita(no, true);
        } else if (Utils.ehNegativoOuZero(fb) && Utils.ehPositivoOuZero(fbd)) {
            // o permite rebalanceamento é falso pois nessa primeira etapa a árvore ainda não fica balanceada
            // então é preciso aguardar a finalização das duas etapas
            rotacaoSimplesDireita(no.filhoDireito, false);
            rotacaoSimplesEsquerda(no, true);
        }
    }

    public String obterPreOrdem(){
        return obterPreOrdem(raiz);
    }

    public String obterPreOrdem(No no){
        if (no == null) return "";

        return (no.index + " ") + obterPreOrdem(no.filhoEsquerdo) + obterPreOrdem(no.filhoDireito);
    }

    public void listarPreOrdem() {
        listarPreOrdem(raiz);
        System.out.println();
    }

    private void listarPreOrdem(No no) {
        if (no == null) return;

        System.out.print(no.index + " ");

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
        System.out.print(no.index + " ");
    }

    public void listarEmOrdem() {
        listarEmOrdem(raiz);
        System.out.println();
    }

    private void listarEmOrdem(No no) {
        if (no == null) return;

        listarEmOrdem(no.filhoEsquerdo);
        System.out.print(no.index + " ");
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

        System.out.println(no.index + " (" + no.fatorBalanceamento() + ") (" + no.nivelEsquerdo + "," + no.nivelDireito + ")");

        printar(no.filhoEsquerdo, nivel + 1);
        printar(no.filhoDireito, nivel + 1);
    }
}
