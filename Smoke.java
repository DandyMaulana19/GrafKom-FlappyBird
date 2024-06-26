import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Smoke extends Actor {
    private int lifeSpan;
    
    public Smoke(int Tower1Width, int Tower1Height) {
        GreenfootImage image = new GreenfootImage("Smoke.png");
        image.scale(300, 300);
        setImage(image);
        lifeSpan = 100;
    }

    public void act() {
        lifeSpan--;
        if (lifeSpan <= 0) {
            getWorld().removeObject(this);
        } else {
            setLocation(getX() - 1, getY());
            getImage().setTransparency(lifeSpan * 2);
        }
    }
}