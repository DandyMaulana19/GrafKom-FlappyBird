import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ScoreMessage extends Actor {
    private int lifespan; // Durasi pesan ditampilkan

    public ScoreMessage(String message, int duration) {
        GreenfootImage image = new GreenfootImage(message, 50, Color.YELLOW, new Color(0, 0, 0, 0));
        setImage(image);
        lifespan = duration;
    }

    public void act() {
        if (lifespan > 0) {
            lifespan--;
        } else {
            getWorld().removeObject(this);
        }
    }
}
