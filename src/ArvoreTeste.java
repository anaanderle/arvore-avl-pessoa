class ArvoreTeste {

    public static void testar(){
        rotacaoSimplesDireitaSlide13();
        rotacaoSimplesDireitaSlide18();
        rotacaoSimplesEsquerdaSlide20();
        rotacaoSimplesEsquerdaSlide23();
        rotacaoDuplaDireitaSlide25();
        rotacaoDuplaDireitaSlide29();
        rotacaoDuplaEsquerdaSlide32();
        exclusaoSlide38SemExcluir();
        exclusaoSlide38();
        caso1();
        caso2();
        caso3();
        caso4();
        caso5();
        caso6();
        caso7();
        caso8();
        caso9();
        caso10();
        caso11();
    }

    private static void baseTeste(String[] inserirValores, String[] removerValores, String preOrdemEsperada, String nomeTest){
        Arvore arvore = new Arvore(null);

        for(String valor : inserirValores){
            arvore.inserir(valor, new Pessoa());
        }

        for(String valor : removerValores){
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
        baseTeste(new String[]{"120", "110", "150", "100", "130", "200", "80"}, new String[]{}, preOrdemEsperada, "rotacaoSimplesDireitaSlide13");
    }

    public static void rotacaoSimplesDireitaSlide18() {
        String preOrdemEsperada = "15 6 4 42 27 88";
        baseTeste(new String[]{"42", "15", "88", "6", "27", "4"}, new String[]{}, preOrdemEsperada, "rotacaoSimplesDireitaSlide18");
    }

    public static void rotacaoSimplesEsquerdaSlide20() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new String[]{"120", "100", "130", "80", "110", "150", "200"}, new String[]{}, preOrdemEsperada, "rotacaoSimplesEsquerdaSlide20");
    }

    public static void rotacaoSimplesEsquerdaSlide23() {
        String preOrdemEsperada = "88 42 15 67 94 90";
        baseTeste(new String[]{"42", "15", "88", "67", "94", "90"}, new String[]{}, preOrdemEsperada, "rotacaoSimplesEsquerdaSlide23");
    }

    public static void rotacaoDuplaDireitaSlide25() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new String[]{"120", "110", "150", "80", "130", "200", "100"}, new String[]{}, preOrdemEsperada, "rotacaoDuplaDireitaSlide25");
    }

    public static void rotacaoDuplaDireitaSlide29() {
        String preOrdemEsperada = "27 15 6 42 34 88";
        baseTeste(new String[]{"42", "15", "88", "6", "27", "34"}, new String[]{}, preOrdemEsperada, "rotacaoDuplaDireitaSlide29");
    }

    public static void rotacaoDuplaEsquerdaSlide32() {
        String preOrdemEsperada = "120 100 80 110 150 130 200";
        baseTeste(new String[]{"120", "100", "130", "80", "110", "200", "150"}, new String[]{}, preOrdemEsperada, "rotacaoDuplaEsquerdaSlide32");
    }

    public static void exclusaoSlide38SemExcluir() {
        String preOrdemEsperada = "32 16 8 24 28 48 40 36 44 56 52 60 58 62";
        baseTeste(new String[]{"32", "16", "48", "8", "24", "40", "56", "28", "36", "44", "52", "60", "58", "62"}, new String[]{}, preOrdemEsperada, "exclusaoSlide38SemExcluir");
    }

    public static void exclusaoSlide38() {
        String preOrdemEsperada = "48 32 24 16 28 40 36 44 56 52 60 58 62";
        baseTeste(new String[]{"32", "16", "48", "8", "24", "40", "56", "28", "36", "44", "52", "60", "58", "62"}, new String[]{"8"}, preOrdemEsperada, "exclusaoSlide38");
    }

    public static void caso1() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new String[]{"3", "2", "1"}, new String[]{}, preOrdemEsperada, "caso1");
    }

    public static void caso2() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new String[]{"1", "2", "3"}, new String[]{}, preOrdemEsperada, "caso2");
    }

    public static void caso3() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new String[]{"3", "1", "2"}, new String[]{}, preOrdemEsperada, "caso3");
    }

    public static void caso4() {
        String preOrdemEsperada = "2 1 3";
        baseTeste(new String[]{"1", "3", "2"}, new String[]{}, preOrdemEsperada, "caso4");
    }

    public static void caso5() {
        String preOrdemEsperada = "20 10 5 30 25 35";
        baseTeste(new String[]{"20", "10", "30", "5", "15", "25", "35"}, new String[]{"15"}, preOrdemEsperada, "caso5");
    }

    public static void caso6() {
        String preOrdemEsperada = "20 5 30 25 35";
        baseTeste(new String[]{"20", "10", "30", "5", "25", "35"}, new String[]{"10"}, preOrdemEsperada, "caso6");
    }

    public static void caso7() {
        String preOrdemEsperada = "25 10 5 30 28 35";
        baseTeste(new String[]{"20", "10", "30", "5", "25", "35", "28"}, new String[]{"20"}, preOrdemEsperada, "caso7");
    }

    public static void caso8() {
        String preOrdemEsperada = "40 20 10 30 60 50 70";
        baseTeste(new String[]{"10", "20", "30", "40", "50", "60", "70"}, new String[]{}, preOrdemEsperada, "caso8");
    }

    public static void caso9() {
        String preOrdemEsperada = "40 20 10 30 50 70";
        baseTeste(new String[]{"10", "20", "30", "40", "50", "60", "70"}, new String[]{"60"}, preOrdemEsperada, "caso9");
    }

    public static void caso10() {
        String preOrdemEsperada = "40 20 10 30 50";
        baseTeste(new String[]{"10", "20", "30", "40", "50", "60", "70"}, new String[]{"60", "70"}, preOrdemEsperada, "caso10");
    }

    public static void caso11() {
        String preOrdemEsperada = "20 10 30";
        baseTeste(new String[]{"10", "20", "30", "10"}, new String[]{}, preOrdemEsperada, "caso11");
    }
}