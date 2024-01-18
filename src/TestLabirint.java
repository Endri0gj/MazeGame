public class TestLabirint {
    public static void main(String[] args) {
        Labirint labirint=new Labirint();
        labirint.gjeneroLabirint();
        while (!labirint.LidhjeHyrjeDalje()){
            labirint.gjeneroLabirint();
        }
        labirint.PrintoLabirint();
    }
}
