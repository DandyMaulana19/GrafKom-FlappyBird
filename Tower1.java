import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Tower1 extends Actor
{
    public Tower1(){
        GreenfootImage image = getImage();
        image.scale(112, 800);
    }
    public void act() {
        setLocation(getX() - FlappyWorld.towerSpeed, getY());
        if (getX() == 0) {
            getWorld().removeObject(this);
        }
    }
}
