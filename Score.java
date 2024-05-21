import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Score extends Actor
{
    public static int score;
    public Score(){
        score = 0;
    }
    public void act(){
        World myWorld = getWorld();
        myWorld.showText(String.valueOf(score), 300, 100);
    }
    public static void add(int num){
        score += num;
    }
}