import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor {
    private boolean oldTouchingPipe = false;
    private boolean oldTouchingTower = false;
    private boolean oldTouchingCoin = false; // Tambahkan variabel untuk mendeteksi koin
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
        boolean touchingTower = false;
        boolean touchingCoin = false;

        for (Pipe pipe : getWorld().getObjects(Pipe.class)) {
            if (Math.abs(pipe.getX() - getX()) < 40) {
                if (Math.abs(pipe.getY() + 30 - getY()) > 37) {
                    dead = true;
                }
                touchingPipe = true;
            }
        }

        for (Tower2 tower : getWorld().getObjects(Tower2.class)) {
            if (Math.abs(tower.getX() - getX()) < 50) {  // Menyesuaikan dengan setengah dari lebar Tower2
                if (Math.abs(tower.getY() - getY()) > 70) {  // Menyesuaikan dengan setengah dari tinggi Tower2
                    dead = true;
                }
                touchingTower = true;  
            }
        }

        for (Coin coin : getWorld().getObjects(Coin.class)) {
            if (intersects(coin)) {
                getWorld().removeObject(coin);
                touchingCoin = true;
                 // Tambahkan pesan "+2" ketika menyentuh koin
                getWorld().addObject(new ScoreMessage("+2", 50), getX(), getY());
            }
        }

        if (!oldTouchingPipe && touchingPipe && !dead) {
            Score.add(1);
        }

        if (!oldTouchingTower && touchingTower && !dead) {
            Score.add(1);
        }
        
        if (!oldTouchingCoin && touchingCoin) {
            Score.add(2); // Tambahkan 2 skor ketika menyentuh koin
        }

        oldTouchingPipe = touchingPipe;
        oldTouchingTower = touchingTower;
        oldTouchingCoin = touchingCoin; // Update status menyentuh koin

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
