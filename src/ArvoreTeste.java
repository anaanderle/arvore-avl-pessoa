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

    public static void caso1() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new int[]{3, 2, 1}, new int[]{}, preOrdemEsperada, "caso1");
    }

    public static void caso2() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new int[]{1, 2, 3}, new int[]{}, preOrdemEsperada, "caso2");
    }

    public static void caso3() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new int[]{3, 1, 2}, new int[]{}, preOrdemEsperada, "caso3");
    }

    public static void caso4() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new int[]{1, 3, 2}, new int[]{}, preOrdemEsperada, "caso4");
    }

    public static void caso5() {
        String preOrdemEsperada = "20 10 5 30 25 35";
        baseTeste(new int[]{20, 10, 30, 5, 15, 25, 35}, new int[]{15}, preOrdemEsperada, "caso5");
    }

    public static void caso6() {
        String preOrdemEsperada = "20 5 30 25 35";
        baseTeste(new int[]{20, 10, 30, 5, 25, 35}, new int[]{10}, preOrdemEsperada, "caso6");
    }

    public static void caso7() {
        String preOrdemEsperada = "25 10 5 30 28 35";
        baseTeste(new int[]{20, 10, 30, 5, 25, 35, 28}, new int[]{20}, preOrdemEsperada, "caso7");
    }

    public static void caso8() {
        String preOrdemEsperada = "40 20 10 30 60 50 70";
        baseTeste(new int[]{10, 20, 30, 40, 50, 60, 70}, new int[]{}, preOrdemEsperada, "caso8");
    }

    public static void caso9() {
        String preOrdemEsperada = "40 20 10 30 50 70";
        baseTeste(new int[]{10, 20, 30, 40, 50, 60, 70}, new int[]{60}, preOrdemEsperada, "caso9");
    }

    public static void caso10() {
        String preOrdemEsperada = "40 20 10 30 50";
        baseTeste(new int[]{10, 20, 30, 40, 50, 60, 70}, new int[]{60, 70}, preOrdemEsperada, "caso10");
    }
}