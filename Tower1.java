import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class Tower1 extends Actor {
    
    private int smokeTimer = 0;
    private List<Smoke> smokeList = new ArrayList<>();
    private int Tower1Width;
    private int Tower1Height;
    private boolean markedForRemoval = false;
    
    public Tower1(){
        GreenfootImage image = getImage();
        image.scale(112, 800);
        Tower1Width = image.getWidth();
        Tower1Height = image.getHeight();
    }
    
    public void act() {
        if (!markedForRemoval) {
            setLocation(getX() - FlappyWorld.towerSpeed, getY());
            if (getX() <= 0) {
                markForRemoval();
            }

            smokeTimer++;
            if (smokeTimer % 20 == 0) { 
                createSmoke();
            }
        } else {
            removePipeAndSmoke();
        }
    }

    private void createSmoke() {
        int smokeX = getX();
        int smokeY = getWorld().getHeight() - (Tower1Height / 100);
        Smoke smoke = new Smoke(Tower1Width, Tower1Height);
        getWorld().addObject(smoke, getX(), getY());
        smokeList.add(smoke);
    }

    private void markForRemoval() {
        markedForRemoval = true;
    }

    private void removePipeAndSmoke() {
        for (Smoke smoke : smokeList) {
            if (smoke.getWorld() != null) { 
                getWorld().removeObject(smoke);
            }
        }

        smokeList.clear();

        if (getWorld() != null) {
            getWorld().removeObject(this);
        }
    }
}