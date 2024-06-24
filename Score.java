import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class Score extends Actor
{
    public static int score;
    public static int highScore;  // Variabel untuk menyimpan skor tertinggi

    public Score(){
        score = 0;
        highScore = readHighScore();  // Membaca skor tertinggi dari file saat objek Score dibuat
    }

    public void act(){
        World myWorld = getWorld();
        myWorld.showText("Score: " + score, 300, 100);
        myWorld.showText("High Score: " + highScore, 300, 120);  // Menampilkan skor tertinggi
    }

    public static void add(int num){
        score += num;
    }

    public static void checkHighScore() {
        if (score > highScore) {
            highScore = score;
            writeHighScore(highScore);  // Menyimpan skor tertinggi ke file
        }
    }

    private static int readHighScore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"));
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;  // Jika file tidak ditemukan atau ada kesalahan, mulai dari 0
        }
    }

    private static void writeHighScore(int highScore) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"));
            writer.write(Integer.toString(highScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
