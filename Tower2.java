import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Tower2 extends Actor {
    
    public Tower2(){
        GreenfootImage image = getImage();
        image.scale(300, 950);
    }
    
    public void act() {
        setLocation(getX() - FlappyWorld.towerSpeed, getY());
        if (getX() == 0) {
            getWorld().removeObject(this);
        }
    }
}