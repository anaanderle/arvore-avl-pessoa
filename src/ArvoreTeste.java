class ArvoreTeste {

    private static void baseTeste(int[] inserirValores, int[] removerValores, String preOrdemEsperada, String nomeTest){
        Arvore arvore = new Arvore(null);

        for(int valor : inserirValores){
            arvore.inserir(valor);
        }

        for(int valor : removerValores){
            arvore.remover(valor);
        }

        String preOrdemRecebida = arvore.obterPreOrdem().trim();

        if(!preOrdemEsperada.equals(preOrdemRecebida)){
            System.out.println("Erro em " + nomeTest + ". Esperado: (" + preOrdemEsperada + "). Recebido: (" + preOrdemRecebida + ").");
            return;
        }
        System.out.println("Teste " + nomeTest + " passou.");
    }

    public static void rotacaoSimplesDireitaSlide13() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new int[]{120, 110, 150, 100, 130, 200, 80}, new int[]{}, preOrdemEsperada, "rotacaoSimplesDireitaSlide13");
    }

    public static void rotacaoSimplesDireitaSlide18() {
        String preOrdemEsperada = "15 6 4 42 27 88";
        baseTeste(new int[]{42, 15, 88, 6, 27, 4}, new int[]{}, preOrdemEsperada, "rotacaoSimplesDireitaSlide18");
    }

    public static void rotacaoSimplesEsquerdaSlide20() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new int[]{120, 100, 130, 80, 110, 150, 200}, new int[]{}, preOrdemEsperada, "rotacaoSimplesEsquerdaSlide20");
    }

    public static void rotacaoSimplesEsquerdaSlide23() {
        String preOrdemEsperada = "88 42 15 67 94 90";
        baseTeste(new int[]{42, 15, 88, 67, 94, 90}, new int[]{}, preOrdemEsperada, "rotacaoSimplesEsquerdaSlide23");
    }

    public static void rotacaoDuplaDireitaSlide25() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new int[]{120, 110, 150, 80, 130, 200, 100}, new int[]{}, preOrdemEsperada, "rotacaoDuplaDireitaSlide25");
    }

    public static void rotacaoDuplaDireitaSlide29() {
        String preOrdemEsperada = "27 15 6 42 34 88";
        baseTeste(new int[]{42, 15, 88, 6, 27, 34}, new int[]{}, preOrdemEsperada, "rotacaoDuplaDireitaSlide29");
    }

    public static void rotacaoDuplaEsquerdaSlide32() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new int[]{120, 100, 130, 80, 110, 200, 150}, new int[]{}, preOrdemEsperada, "rotacaoDuplaEsquerdaSlide32");
    }

    public static void exclusaoSlide38SemExcluir() {
        String preOrdemEsperada = "32 16 8 24 28 48 40 36 44 56 52 60 58 62";
        baseTeste(new int[]{32, 16, 48, 8, 24, 40, 56, 28, 36, 44, 52, 60, 58, 62}, new int[]{}, preOrdemEsperada, "exclusaoSlide38SemExcluir");
    }

    public static void exclusaoSlide38() {
        String preOrdemEsperada = "48 32 24 16 28 40 36 44 56 52 60 58 62";
        baseTeste(new int[]{32, 16, 48, 8, 24, 40, 56, 28, 36, 44, 52, 60, 58, 62}, new int[]{8}, preOrdemEsperada, "exclusaoSlide38");
    }

}