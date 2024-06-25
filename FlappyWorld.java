import greenfoot.*;

public class FlappyWorld extends World {
private GreenfootSound backgroundMusic;
private boolean musicStarted;
private boolean gameStarted;
private int gameTime;
public static int pipeSpeed;
private int pipeSpawnTime;
private boolean isGameOver;
private int level;
private int pipeCount; // Counter for pipes in level 1
private int coinSpawnTime;


public FlappyWorld() {    
    super(600, 400, 1);
    addObject(new Player(), 100, 300);
    Score score = new Score();
    addObject(score, 300, 100);
    Greenfoot.setSpeed(50);
    
    musicStarted = false;
    gameStarted = false;
    gameTime = 0;
    pipeSpeed = 2;
    pipeSpawnTime = 100;
    isGameOver = false;
    level = 1;
    pipeCount = 0; // Initialize pipe counter
    coinSpawnTime = 150; // Set coin spawn interval
    setBackground("background1.png"); // Set initial background
}

public void act() {
    if (!gameStarted) {
        startBackgroundMusic();
        gameStarted = true;
    }
    
    gameTime++;
    
    if (gameTime % pipeSpawnTime == 0) {
        if (level == 1) {
            if (pipeCount < 10) {
                addObject(new Pipe(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
                pipeCount++;
                System.out.println("Added Pipe at level 1, count: " + pipeCount); // Debug statement
            }
        } else if (level == 2) {
           addObject(new Tower2(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
                pipeCount++;
        }
    }
    
     if (level == 2 && gameTime % coinSpawnTime == 0) {
            addObject(new Coin(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
    }


    checkGameOver();
    checkLevelUp();
}

private void startBackgroundMusic() {
    backgroundMusic = new GreenfootSound("backsound.mp3");
    backgroundMusic.setVolume(80);
    backgroundMusic.playLoop();
}

private void checkGameOver() {
    if (Score.score >= 10 && level == 1) {
        levelUp();
    } else if (Score.score >= 30) {
        gameOver();
    }
}

private void levelUp() {
    level++;
    //Score.score = 0; // Reset score for the new level
    setBackground("background2.png"); // Change background for new level
    pipeSpeed += 2; // Increase pipe speed or any other game difficulty parameter
    System.out.println("Level up to level " + level); // Debug statement
}

private void checkLevelUp() {
    // Additional logic for more levels can be added here if needed
}

public void gameOver() {
    if (!isGameOver) {
        addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
        Score.checkHighScore();  // Check and save high score
        backgroundMusic.stop();
        Greenfoot.stop();
        isGameOver = true;
        System.out.println("Game Over."); // Debug statement
    }
}
}