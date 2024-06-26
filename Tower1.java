import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class Tower1 extends Actor
{
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

            // Create smoke periodically
            smokeTimer++;
            if (smokeTimer % 20 == 0) { // Adjust the frequency as needed
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
        // Remove all smoke objects associated with this pipe
        for (Smoke smoke : smokeList) {
            if (smoke.getWorld() != null) { // Ensure smoke is still in the world
                getWorld().removeObject(smoke);
            }
        }
        // Clear the smokeList to avoid referencing removed objects
        smokeList.clear();
        // Remove the pipe itself
        if (getWorld() != null) { // Ensure pipe is still in the world
            getWorld().removeObject(this);
        }
    }
}
