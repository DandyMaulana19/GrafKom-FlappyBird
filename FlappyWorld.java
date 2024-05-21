import greenfoot.*;  

public class FlappyWorld extends World {
    private GreenfootSound backgroundMusic;
    private boolean musicStarted;
    private boolean gameStarted;
    private int timer;
    private int speed = 50;
    private int gameTime;  // Untuk melacak waktu permainan dalam frame
    public static int pipeSpeed;  // Kecepatan pipa, static agar bisa diakses dari kelas Pipe
    private int pipeSpawnTime; // Waktu interval untuk menghasilkan pipa baru

    public FlappyWorld() {    
        super(600, 400, 1);
        addObject(new Player(), 100, 300);
        addObject(new Score(), 300, 100);
        Greenfoot.setSpeed(speed);
        
        musicStarted = false;
        gameStarted = false;
        gameTime = 0;
        pipeSpeed = 2;  // Kecepatan awal pipa
        pipeSpawnTime = 100; // Menghasilkan pipa baru setiap 100 frame

        //spawnInitialPipes(); // Memunculkan pipa awal
    }
    
    private void spawnInitialPipes() {
        addObject(new Pipe(), 600, Greenfoot.getRandomNumber(getHeight()));
        addObject(new Pipe(), 900, Greenfoot.getRandomNumber(getHeight()));
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
        
        if (gameTime == 900) {  // Setelah 15 detik (900 frame dengan kecepatan Greenfoot = 60)
            pipeSpeed += 5;  // Tingkatkan kecepatan pipa
        }
        
        if (timer == 600) {
            timer = 0;
            speed++;
            Greenfoot.setSpeed(speed);
        }
    }

    private void startBackgroundMusic() {
        // Inisialisasi dan mulai memutar musik latar belakang
        backgroundMusic = new GreenfootSound("backsound.mp3");
        backgroundMusic.setVolume(80);  // Atur level volume (opsional)
        backgroundMusic.playLoop();
    }

    public void gameOver() {
        addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
        backgroundMusic.stop();
        Greenfoot.stop();
    }
}