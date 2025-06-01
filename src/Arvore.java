import java.util.Arrays;

public class Arvore {

    private No raiz;

    public Arvore(No raiz) {
        this.raiz = raiz;
    }

    public No[] buscarInicial(String valor) {
        return buscarInicial(raiz, valor);
    }

    private No[] buscarInicial(No no, String valor) {
        if(no == null) return new No[]{};

        if(no.valor.equals(valor)) {
            return Utils.concatenar(no, buscarInicial(no.filhoDireito, valor));
        }

        if(no.valor.startsWith(valor)) {
            No[] nosEsquerdos = buscarInicial(no.filhoEsquerdo, valor);
            No[] nosDiretos = buscarInicial(no.filhoDireito, valor);
            return Utils.concatenar(no, nosEsquerdos, nosDiretos);
        }

        if(Utils.menor(no.valor, valor)) {
            return buscarInicial(no.filhoDireito, valor);
        }

        if(Utils.maior(no.valor, valor)) {
            return buscarInicial(no.filhoEsquerdo, valor);
        }

        return new No[]{};
    }

    public No[] buscarIntervalo(String valorInicial, String valorFinal) {
        return buscarIntervalo(raiz, valorInicial, valorFinal);
    }

    private No[] buscarIntervalo(No no, String valorInicial, String valorFinal) {
        if(no == null) return new No[]{};

        if(no.valor.equals(valorInicial)) {
            return Utils.concatenar(no, buscarIntervalo(no.filhoDireito, valorInicial, valorFinal));
        }

        if(Utils.menor(no.valor, valorInicial)) {
            return buscarIntervalo(no.filhoDireito, valorInicial, valorFinal);
        }

        if(Utils.maior(no.valor, valorFinal)) {
            return  buscarIntervalo(no.filhoEsquerdo, valorInicial, valorFinal);
        }

        No[] nosEsquerdos = buscarIntervalo(no.filhoEsquerdo, valorInicial, valorFinal);
        No[] nosDiretos = buscarIntervalo(no.filhoDireito, valorInicial, valorFinal);
        return Utils.concatenar(no, nosEsquerdos, nosDiretos);
    }

    public No buscar(String valor) {
        return buscar(raiz, valor);
    }

    private No buscar(No no, String valor) {
        if(no == null) return null;

        if(no.valor.equals(valor)) return no;
        if(no.filhoEsquerdo != null && Utils.maiorOuIgual(no.valor, valor)) return buscar(no.filhoEsquerdo, valor);
        if(no.filhoDireito != null && Utils.menorOuIgual(no.valor, valor)) return buscar(no.filhoDireito, valor);

        return null;
    }

    public No buscarPai(String valor) {
        return buscarPai(raiz, valor);
    }

    private No buscarPai(No no, String valor) {
        // nó não pode ser pai de ninguém
        if(no == null || (no.filhoDireito == null && no.filhoEsquerdo == null)) return null;

        // nó é pai
        if(Utils.maior(no.valor, valor) && no.filhoEsquerdo != null && no.filhoEsquerdo.valor.equals(valor)) return no;
        if(Utils.menor(no.valor, valor) && no.filhoDireito != null && no.filhoDireito.valor.equals(valor)) return no;

        // nó não é pai mas possui filhos que podem ser
        if(Utils.maior(no.valor, valor )) return buscarPai(no.filhoEsquerdo, valor);
        if(Utils.menor(no.valor, valor )) return buscarPai(no.filhoDireito, valor);

        // Nó raiz
        return null;
    }

    private No inserir(No no, String valor, int[] indexes){
        if(no == null) return null;

        if(no.valor.equals(valor)) {
            int[] resultado = Arrays.copyOf(indexes, indexes.length + no.indexes.length);
            System.arraycopy(no.indexes, 0, resultado, indexes.length, no.indexes.length);
            no.indexes = resultado;
            return no;
        }

        // encontrado local para inserir
        if(Utils.maior(no.valor, valor) && no.filhoEsquerdo == null) {
            no.adicionarFilhoEsquerdo(new No(valor, null, null, 0, 0, indexes));
            return no;
        }

        // encontrado local para inserir
        if(Utils.menor(no.valor, valor) && no.filhoDireito == null) {
            no.adicionarFilhoDireito(new No(valor, null, null, 0, 0, indexes));
            return no;
        }

        // procurando local para inserir na subárvore
        if(Utils.maior(no.valor, valor)) {
            No noRetorno = inserir(no.filhoEsquerdo, valor, indexes);
            // se houver um retorno, então um nó foi inserido e é preciso ajustar o nível
            if(noRetorno != null) no.nivelEsquerdo = buscar(raiz, no.valor).filhoEsquerdo.maiorNivelSubArvore() + 1;
            balancear(no);
            return no;
        }

        // procurando local para inserir na subárvore
        if(Utils.menor(no.valor, valor)) {
            No noRetorno = inserir(no.filhoDireito, valor, indexes);
            // se houver um retorno, então um nó foi inserido e é preciso ajustar o nível
            if(noRetorno != null) no.nivelDireito = buscar(raiz, no.valor).filhoDireito.maiorNivelSubArvore() + 1;
            balancear(no);
            return no;
        }

        return null;
    }

    // funciona igual a inserir(No no, int valor), porém para nós já existentes
    private No inserir(No no, No noParaInserir, boolean permiteRebalanceamento){
        if(no == null || noParaInserir == null) return null;

        if(Utils.maior(no.valor, noParaInserir.valor) && no.filhoEsquerdo == null) {
            no.adicionarFilhoEsquerdo(noParaInserir);
            return no;
        }

        if(Utils.menor(no.valor, noParaInserir.valor) && no.filhoDireito == null) {
            no.adicionarFilhoDireito(noParaInserir);
            return no ;
        }

        if(Utils.maior(no.valor, noParaInserir.valor)) {
            No noRetorno = inserir(no.filhoEsquerdo, noParaInserir, permiteRebalanceamento);
            if(noRetorno != null) no.nivelEsquerdo = buscar(raiz, no.valor).filhoEsquerdo.maiorNivelSubArvore() + 1;
            if(permiteRebalanceamento) balancear(no);
            return no;
        }

        if(Utils.menor(no.valor, noParaInserir.valor)) {
            No noRetorno = inserir(no.filhoDireito, noParaInserir, permiteRebalanceamento);
            if(noRetorno != null) no.nivelDireito = buscar(raiz, no.valor).filhoDireito.maiorNivelSubArvore() + 1;
            if(permiteRebalanceamento) balancear(no);
            return no;
        }

        return null;
    }

    public void inserir(String valor, int[] indexes) {
        if(raiz == null){
            raiz = new No(valor, null, null, 0, 0, indexes);
            return;
        }

        inserir(raiz, valor, indexes);
    }

    public void remover(String valor) {
        remover(valor, true);
    }

    private void remover(String valor, boolean conectarFilhos) {
        No pai = buscarPai(valor);
        No no;

        if(pai == null){
            no = raiz;
        } else if(pai.filhoEsquerdo != null && pai.filhoEsquerdo.valor.equals(valor)){
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
        remover(no.valor, false);

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
        remover(no.valor, false);

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

        System.out.println(no.valor + " (" + no.fatorBalanceamento() + ")" + Arrays.toString(no.indexes));

        printar(no.filhoEsquerdo, nivel + 1);
        printar(no.filhoDireito, nivel + 1);
    }
}
