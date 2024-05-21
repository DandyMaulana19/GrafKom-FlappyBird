import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor {
    private boolean oldTouchingPipe = false;
    private static boolean dead;

    public Player() {
        GreenfootImage image = getImage();
        image.scale(76, 64);
        dead = false;
    }

    public void act() {
        // Move the player according to the mouse position
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            setLocation(mouseX, mouseY);
            setRotation((int)(mouseY - getY()) * 16);  // Optional: Rotate player based on mouse movement
        }

        // Check for collisions and update game state
        if (isAtEdge()) {
            dead = true;
        }
        
        boolean touchingPipe = false;
        for (Pipe pipe : getWorld().getObjects(Pipe.class)) {
            if (Math.abs(pipe.getX() - getX()) < 40) {
                if (Math.abs(pipe.getY() + 30 - getY()) > 37) {
                    dead = true;
                }
                touchingPipe = true;
            }
        }
        
        if (!oldTouchingPipe && touchingPipe && !dead) {
            Score.add(1);
        }
        oldTouchingPipe = touchingPipe;
        
        if (dead) {
            FlappyWorld myWorld = (FlappyWorld) getWorld();
            myWorld.gameOver();
            getWorld().removeObject(this);
        }
    }

    public static boolean isAlive() {
        return !dead;
    }

    public void setLocation(double x, double y) {
        super.setLocation((int)x, (int)y);
    }
}
