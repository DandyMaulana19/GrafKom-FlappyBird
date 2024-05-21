import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pipe extends Actor
{
    public Pipe(){
        GreenfootImage image = getImage();
        image.scale(112, 800);
    }
    public void act() {
        setLocation(getX() - FlappyWorld.pipeSpeed, getY());
        if (getX() == 0) {
            getWorld().removeObject(this);
        }
    }
}
