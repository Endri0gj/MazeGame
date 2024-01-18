import edu.princeton.cs.algs4.UF;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Labirint {

    private int[][] rrjeti = new int[10][10];
    private int thesar = 8;
    private int daljeX;
    private int daljeY;
    private int nisjeX;
    private int nisjeY;
    Random rand = new Random();
    public Labirint(){

    }
    public void gjeneroLabirint() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    rrjeti[i][j] = 1;
                } else {
                    rrjeti[i][j] = rand.nextInt(2);
                }
            }
        }


        for (int i = 0; i < thesar; i++) {
            int x = rand.nextInt(8) + 1;
            int y = rand.nextInt(8) + 1;
            rrjeti[x][y] = 9;
        }


        daljeX = rand.nextInt(8) + 1;
        daljeY = rand.nextInt(8) + 1;
        rrjeti[daljeX][daljeY] = 8;


        nisjeX = rand.nextInt(8) + 1;
        nisjeY = 1;
        rrjeti[nisjeX][nisjeY] = 7;
    }
    public boolean LidhjeHyrjeDalje() {
        int nisjePosition = nisjeX * 10 + nisjeY;
        int daljePosition = daljeX * 10 + daljeY;

        UF uf = new UF(100);


        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (!eshteMur(i, j)) {
                    int currPosition = i * 10 + j;
                    if (i > 1 && !eshteMur(i - 1, j)) uf.union(currPosition, (i - 1) * 10 + j);
                    if (i < 8 && !eshteMur(i + 1, j)) uf.union(currPosition, (i + 1) * 10 + j);
                    if (j > 1 && !eshteMur(i, j - 1)) uf.union(currPosition, i * 10 + (j - 1));
                    if (j < 8 && !eshteMur(i, j + 1)) uf.union(currPosition, i * 10 + (j + 1));
                }
            }
        }


        return uf.connected(nisjePosition, daljePosition);
    }

    public boolean eshteMur(int i, int j) {
        return rrjeti[i][j] == 1;
    }
    public boolean eshteThesar(int i,int j){
        return rrjeti[i][j] == 9;
    }
    public boolean eshteDalje(int i,int j){
        return rrjeti[i][j] == 8;
    }

    public void PrintoLabirint() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(rrjeti[i][j] + "  ");
            }
            System.out.println("");
        }
    }
    public void saveMazeToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    writer.print(rrjeti[i][j] + "  ");
                }
                writer.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNisjeX() {
        return nisjeX;
    }

    public int getDaljeX() {
        return daljeX;
    }

    public int getDaljeY() {
        return daljeY;
    }

    public int getNisjeY() {
        return nisjeY;
    }

    public int[][] getRrjeti() {
        return rrjeti;
    }




    public void updateMaze(int oldX, int oldY, int newX, int newY) {
        rrjeti[oldX][oldY] = 0;
        rrjeti[newX][newY] = 7;
    }



}






