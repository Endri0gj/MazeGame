import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class Lojtar extends Labirint {

    private int pozicioniX;
    private int pozicioniY;
    private int thesarCounter;




    public Lojtar() {
        super();
        this.pozicioniX = super.getNisjeX();
        this.pozicioniY = super.getNisjeY();
        this.thesarCounter = 0;
    }

    public void lundro(char drejtimi) {


        switch (drejtimi) {
            case '1':
                pozicioniX--;
                break;
            case '2':
                pozicioniX++;
                break;
            case '3':
                pozicioniY--;
                break;
            case '4':
                pozicioniY++;
                break;
            default:
                System.out.println("Drejtimi i gabuar!");
                return;
        }



    }
    public void savePlayerPositionToFile(String fileName, int daljeX, int daljeY) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(pozicioniX + " " + pozicioniY);
            writer.println(daljeX + " " + daljeY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void increaseThesarCounter() {
        this.thesarCounter++;
    }
    public int getThesarCounter() {
        return this.thesarCounter;
    }




    public void setPozicioniX(int pozicioniX) {
        this.pozicioniX = pozicioniX;
    }

    public void setPozicioniY(int pozicioniY) {
        this.pozicioniY = pozicioniY;
    }

    public int getPozicioniX() {
        return pozicioniX;
    }

    public int getPozicioniY() {
        return pozicioniY;
    }



}
