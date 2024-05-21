import greenfoot.*;  

public class FlappyWorld extends World {
    private GreenfootSound backgroundMusic;
    private boolean musicStarted;
    private boolean gameStarted;
    private int timer;
    private int speed = 50;
    private int gameTime;
    public static int pipeSpeed;
    private int pipeSpawnTime;
    private boolean isGameOver;

    public FlappyWorld() {    
        super(600, 400, 1);
        addObject(new Player(), 100, 300);
        addObject(new Score(), 300, 100);
        Greenfoot.setSpeed(speed);
        
        musicStarted = false;
        gameStarted = false;
        gameTime = 0;
        pipeSpeed = 2;
        pipeSpawnTime = 100;
        isGameOver = false;
    }

    public void act() {
        if (!gameStarted) {
            startBackgroundMusic();
            gameStarted = true;
        }
        
        gameTime++;
        
        if (gameTime % pipeSpawnTime == 8) {
            addObject(new Pipe(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
        }
        
        if (gameTime == 900) {
            pipeSpeed += 5;
        }
        
        if (timer == 600) {
            timer = 0;
            speed++;
            Greenfoot.setSpeed(speed);
        }
    }

    private void startBackgroundMusic() {
        backgroundMusic = new GreenfootSound("backsound.mp3");
        backgroundMusic.playLoop();
    }

    public void gameOver() {
        if (!isGameOver) {
            addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
            Score.checkHighScore();
            backgroundMusic.stop();
            Greenfoot.stop();
            isGameOver = true;
        }
    }
}
