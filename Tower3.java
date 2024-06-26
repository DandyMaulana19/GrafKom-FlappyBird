import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Tower3 extends Actor {
    
    public Tower3(){
        GreenfootImage image = getImage();
        image.scale(200, 800);
    }
    
    public void act() {
        setLocation(getX() - FlappyWorld.towerSpeed, getY());
        if (getX() == 0) {
            getWorld().removeObject(this);
        }
    }
}