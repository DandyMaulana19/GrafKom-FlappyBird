import greenfoot.*;

public class FlappyWorld extends World {
private GreenfootSound backgroundMusic;
private boolean musicStarted;
private boolean gameStarted;
private int gameTime;
public static int towerSpeed;
private int towerSpawnTime;
private boolean isGameOver;
private int level;
private int towerCount; // Counter for pipes in level 1
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
    towerSpeed = 2;
    towerSpawnTime = 100;
    isGameOver = false;
    level = 1;
    towerCount = 0; 
    coinSpawnTime = 150; 
    setBackground("background1.png");
}

public void act() {
    if (!gameStarted) {
        startBackgroundMusic();
        gameStarted = true;
    }
    
    gameTime++;
    
    if (gameTime % towerSpawnTime == 0) {
        if (level == 1) {
            if (towerCount < 10) {
                addObject(new Tower1(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
                towerCount++;
                //System.out.println("Added Pipe at level 1, count: " + pipeCount); // Debug statement
            }
        } else if (level == 2) {
           addObject(new Tower2(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
                towerCount++;
        } else if (level == 3) {
           addObject(new Tower3(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
                towerCount++;
        }
    }
    
    if (level == 2 && gameTime % coinSpawnTime == 0) {
            addObject(new Coin(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
    }


    checkGameOver();
    //finish();
}

private void startBackgroundMusic() {
    backgroundMusic = new GreenfootSound("backsound.mp3");
    backgroundMusic.setVolume(80);
    backgroundMusic.playLoop();
}

private void checkGameOver() {
    //if (Score.score >= 10 && level == 1) {
    if (Score.score >= 0 && level == 1) {
        levelUp();
    //} else if (Score.score >= 30 && level == 2){
    } else if (Score.score >= 0 && level == 2){
        levelUp();
    } else if (Score.score >= 5) {
        finish();
    }
}

private void levelUp() {
    level++;
    towerSpeed += 2;
    if (level == 2){
        setBackground("background2.png");
    }else if(level == 3){
        setBackground("background3.png"); 
        Greenfoot.setSpeed(55); // Increase game speed at level 3
    }
    
    
//    System.out.println("Level up to level " + level); // Debug statement
}

  private void finish() {
        if (!isGameOver) {
            addObject(new Finish(), getWidth() / 2, getHeight() / 2);
            Score.checkHighScore();  // Check and save high score
            backgroundMusic.stop();
            Greenfoot.stop();
            isGameOver = true;
            //System.out.println("Game Finish."); // Debug statement
        }
    }

public void gameOver() {
    if (!isGameOver) {
        addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
        Score.checkHighScore();  // Check and save high score
        backgroundMusic.stop();
        Greenfoot.stop();
        isGameOver = true;
   //     System.out.println("Game Over."); // Debug statement
    }
}
}