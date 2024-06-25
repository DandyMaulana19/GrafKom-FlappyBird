import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Tower2 extends Actor
{
    public Tower2(){
        GreenfootImage image = getImage();
        image.scale(290, 950);
    }
    public void act() {
        setLocation(getX() - FlappyWorld.pipeSpeed, getY());
        if (getX() == 0) {
            getWorld().removeObject(this);
        }
    }
}
