import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor {
    private boolean oldTouchingTower = false;
    private boolean oldTouchingCoin = false; 
    private static boolean dead;

    public Player() {
        GreenfootImage image = getImage();
        image.scale(76, 64);
        dead = false; 
    }

    public void act() {
    
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            setLocation(mouseX, mouseY);
            setRotation((int)(mouseY - getY()) * 16);         
        }

        if (isAtEdge()) {
            dead = true;
        }

        boolean touchingTower = false;
        boolean touchingCoin = false;

        for (Tower1 tower1 : getWorld().getObjects(Tower1.class)) {
            if (Math.abs(tower1.getX() - getX()) < 70) {
                if (Math.abs(tower1.getY() - getY()) > 40) {
                    dead = true;
                }
                touchingTower = true;
            }
        }

        for (Tower2 tower2 : getWorld().getObjects(Tower2.class)) {
            if (Math.abs(tower2.getX() - getX()) < 70) {  
                if (Math.abs(tower2.getY() - getY()) > 30) {
                    dead = true;
                }
                touchingTower = true;  
            }
        }
        
        for (Tower3 tower3 : getWorld().getObjects(Tower3.class)) {
            if (Math.abs(tower3.getX() - getX()) < 110) {  
                if (Math.abs(tower3.getY() - getY()) > 30) {  
                    dead = true;
                }
                touchingTower = true;  
            }
        }

        for (Coin coin : getWorld().getObjects(Coin.class)) {
            if (intersects(coin)) {
                getWorld().removeObject(coin);
                touchingCoin = true;
                getWorld().addObject(new ScoreMessage("+2", 50), getX(), getY());
            }
        }

        if (!oldTouchingTower && touchingTower && !dead) {
            Score.add(1);
        }
        
        if (!oldTouchingCoin && touchingCoin) {
            Score.add(2); 
        }

        oldTouchingTower = touchingTower;
        oldTouchingCoin = touchingCoin;

        if (dead) {
            FlappyWorld myWorld = (FlappyWorld) getWorld();
            myWorld.gameOver();
            getWorld().removeObject(this);
        }
        
        //if (finish) {
        //    FlappyWorld myWorld = (FlappyWorld) getWorld();
        //    myWorld.finish();
        //    getWorld().removeObject(this);
        //}
    }


    public static boolean isAlive() {
        return !dead;
    }

    public void setLocation(double x, double y) {
        super.setLocation((int)x, (int)y);
    }
}
