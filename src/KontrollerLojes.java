import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KontrollerLojes {
    private Labirint labirint;
    private Lojtar lojtar;
    private boolean hitWall;


    public KontrollerLojes() {
        this.labirint = new Labirint();
        this.lojtar = new Lojtar();
        this.hitWall=false;
        gjeneroLabirintDheLojtar();
    }
    public boolean hasValidPath() {
        return labirint.LidhjeHyrjeDalje();
    }
    public boolean isHitWall() {
        return hitWall;
    }

    private void gjeneroLabirintDheLojtar() {
        labirint.gjeneroLabirint();
        while (!labirint.LidhjeHyrjeDalje()) {
            labirint.gjeneroLabirint();
        }
        lojtar.setPozicioniX(labirint.getNisjeX());
        lojtar.setPozicioniY(labirint.getNisjeY());
    }
    public boolean kaFituar() {
        return lojtar.getPozicioniX() == labirint.getDaljeX() && lojtar.getPozicioniY() == labirint.getDaljeY();
    }

    public void luajLojen() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            labirint.PrintoLabirint();
            System.out.println("Zgjidh një opsion:");
            System.out.println("1. Shko lart");
            System.out.println("2. Shko poshtë");
            System.out.println("3. Shko majtas");
            System.out.println("4. Shko djathtas");
            System.out.println("5. Ruaj lojën");
            System.out.println("6. Ngarko lojën");
            System.out.println("7. Shfaq pozicionin dhe numrin e thesareve");
            System.out.println("0. Mbylle lojën");

            int opsioni = scanner.nextInt();

            switch (opsioni) {
                case 1:
                    movePlayer('1');
                    break;
                case 2:
                    movePlayer('2');
                    break;
                case 3:
                    movePlayer('3');
                    break;
                case 4:
                    movePlayer('4');
                    break;
                case 5:
                    saveGameState("maze.txt", "player.txt");
                    System.out.println("Loja u ruajt.");
                    break;
                case 6:
                    loadGameState("maze.txt", "player.txt");
                    System.out.println("Loja u ngarkua.");
                    break;
                case 7:
                    displayPlayerPositionAndThesar();
                    break;
                case 0:
                    System.out.println("Loja u mbyll.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsioni i padëshiruar.");
                    break;
            }

            if (kaFituar()) {
                System.out.println("Ju fituat! Urime, keni fituar!");
                break;
            }
        }
        scanner.close();
    }
    public void saveGameState(String mazeFileName, String playerFileName) {
        lojtar.savePlayerPositionToFile(playerFileName, labirint.getDaljeX(), labirint.getDaljeY());
        labirint.saveMazeToFile(mazeFileName);
    }

    public void loadGameState(String mazeFileName, String playerFileName) {
        labirint = new Labirint();
        lojtar = new Lojtar();
        try (Scanner mazeScanner = new Scanner(new File(mazeFileName));
             Scanner playerScanner = new Scanner(new File(playerFileName))) {

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (mazeScanner.hasNextInt()) {
                        labirint.getRrjeti()[i][j] = mazeScanner.nextInt();
                    } else {

                        System.out.println("Invalid maze file format. Please check the file.");
                        return;
                    }
                }
            }


            if (playerScanner.hasNextInt()) {
                lojtar.setPozicioniX(playerScanner.nextInt());
            } else {

                System.out.println("Invalid player file format. X position is missing.");
                return;
            }

            if (playerScanner.hasNextInt()) {
                lojtar.setPozicioniY(playerScanner.nextInt());
            } else {

                System.out.println("Invalid player file format. Y position is missing.");
                return;
            }

        } catch (FileNotFoundException e) {

            System.out.println("File not found. Please provide correct file paths.");
            e.printStackTrace();
        }
    }




    private void displayPlayerPositionAndThesar() {
        System.out.println("Pozicioni i lojtarit: (" + lojtar.getPozicioniX() + ", " + lojtar.getPozicioniY() + ")");
        System.out.println("Numri i thesareve te mbledhura: " + lojtar.getThesarCounter());
    }
    public void movePlayer(char drejtimi) {
        int oldX = lojtar.getPozicioniX();
        int oldY = lojtar.getPozicioniY();

        lojtar.lundro(drejtimi);

        int newX = lojtar.getPozicioniX();
        int newY = lojtar.getPozicioniY();

        if (labirint.eshteMur(newX, newY)) {

            hitWall = true;
        } else {

            if (labirint.eshteDalje(newX, newY)) {
                System.out.println("Ju fituat! Urime, keni fituar!");
                System.out.println("Thesare te mbledhura: " + lojtar.getThesarCounter());
                System.exit(0);
            }
            if (labirint.eshteThesar(newX, newY)) {
                lojtar.increaseThesarCounter();
                System.out.println("Mblodhët një thesar! Totali: " + lojtar.getThesarCounter());
            }
            labirint.updateMaze(oldX, oldY, newX, newY);
        }

        if (hitWall) {
            System.out.println("Ju përplasët në mur. Loja mbyllhet!");
            System.exit(0);
        }

        if (kaFituar()) {
            System.out.println("Ju fituat! Urime, keni fituar!");
            System.out.println("Thesare te mbledhura: " + lojtar.getThesarCounter());
            System.exit(0);
        }
    }
    public Labirint getLabirint() {
        return labirint;
    }


}
