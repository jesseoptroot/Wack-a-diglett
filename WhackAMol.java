package whackamol;

import java.util.ArrayList;
import java.util.Random;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class WhackAMol extends GameEngine {
	
	private Sound backgroundSound;
	private Sound hitSound;
	private Sound molHitSound;
	private Molshoop[] molshopen;
	private MolSpawner molSpawner;
	private Random random;
	private Player player;
	private Timer timer;
	private int score;
	private TextObject dashboardText;
	private TextObject scoreText;
	private TextObject timerText;
	private TextObject hiscoreText;
	private ArrayList<IPowerup> powerups;

	public static void main(String[] args) {
		String[] processingArgs = {"whackamol.WhackAMol"};
        WhackAMol mySketch = new WhackAMol();

        PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {

		int worldWidth = 600;
        int worldHeight = 720;
        score = 0;
        
        initializeTimer(2*60*1000);
        
        random = new Random();
        powerups = new ArrayList<>();
        initializeSound();
        createDashboard(worldWidth, worldHeight);
        initializeMolshopen(worldWidth);
        
        createMolSpawner();
        
        createViewWithoutViewport(worldWidth, worldHeight);
        
        player = new Player(this, molHitSound);
        addGameObject(player, 100, 100);
	}
	
	/**
     * Creeërt een timer
     *
     * @param speelTijd geeft het aantal milliseconden van de timer aan
     */
	
	private void initializeTimer(int speelTijd) {
		timer = new Timer(speelTijd);
	}
	
	/**
     * Creeërt de view zonder viewport
     *
     * @param screenWidth  Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     */
	private void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("src/main/java/whackamol/background.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }
	
	/**
     * Creeërt een speelveld met molshopen
     *
     * @param worldWidth  Breedte van de speelwereld
     */
	private void initializeMolshopen(int worldWitdh) {
		Sprite molshoopSprite = new Sprite("src/main/java/whackamol/moleIn.png");
		
        int tileSize = worldWitdh / 6;
        int tilesMap[][] = {
        		{-1, -1, -1, -1, -1, -1},
        		{-1, -1, -1, -1, -1, -1},
        		{-1, -1, -1, -1, -1, -1},
                {-1, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, -1}
                
        };
        molshopen = new Molshoop[8];
        int molshoopNr = 0;
        for(int i = 0; i < tilesMap.length; i++) {
        	for(int j = 0; j < tilesMap[0].length; j++) {
        		if(tilesMap[i][j] == 0) {
        			molshopen[molshoopNr] = new Molshoop(this, molshoopSprite, (j * tileSize), (i * tileSize), tileSize, tileSize);
        			molshoopNr++;
        		}
        	}
        }
    }
	
	/**
     * Initialiseert alle geluiden
     */
	private void initializeSound() {
		 backgroundSound = new Sound(this, "src/main/java/whackamol/backgroundmusic.mp3");
	     backgroundSound.loop(-1);
	     hitSound = new Sound(this, "src/main/java/whackamol/splat-gaming-sound-effect-hd.mp3");
	     Mol.setHitSound(hitSound);
	     molHitSound = new Sound(this, "src/main/java/whackamol/misssound.mp3");
    }
	
	/**
     * Creeërt een molspawner
     */
	public void createMolSpawner() {
		Mol.setVerschijntijd(3000);
		molSpawner = new MolSpawner(this, (float)1);
	}
	
	/**
     * Creeërt een het dashbord
     * 
     * @param dashboardWidth	breedte dashbord
     * @param dashboardHeight	hoogte dashbord
     */
	private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboardtext = new Dashboard(100, 0, dashboardWidth, dashboardHeight);
        Dashboard scoretext = new Dashboard(100, 20, dashboardWidth, dashboardHeight);
        Dashboard timertext = new Dashboard(100, 40, dashboardWidth, dashboardHeight);
        Dashboard HiscoreText = new Dashboard(10,110, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("Whack a mol", 30);
        scoreText = new TextObject("",30);
        timerText = new TextObject("",30);
        hiscoreText = new TextObject("" ,80);
        dashboardtext.addGameObject(dashboardText);
        scoretext.addGameObject(scoreText);
        timertext.addGameObject(timerText);
        HiscoreText.addGameObject(hiscoreText);
        addDashboard(dashboardtext);
        addDashboard(scoretext);
        addDashboard(timertext);
        addDashboard(HiscoreText);
        
    }
	
	/**
     * Past de speeltijd aan
     * 
     * @param milliseconden	Aantal milliseconden dat aan de speeltijd wordt toegevoegd
     */
	public void veranderSpeeltijd(int milliseconden) {
		timer.setTijdTotaal(timer.getTijdTotaal() + milliseconden);
	}
	
	/**
     * Past de scores aan
     * 
     * @param punten	Aantal punten dat bij de score moet worden toegevoegd
     */
	public void updateScore(int punten) {
		score += punten;
		refreshDasboardText();
	}
	
	/**
     * Voegt een powerup aan de poweruplijst toe
     * 
     * @param powerup	Toe te voegen powerup
     */
	public void addPowerup(IPowerup powerup) {
		powerups.add(powerup);
	}
	
	/**
     * Geeft een willekeurige molshoop uit de molshooplijst
     */
	public Molshoop getRandomMolshoop() {
		int molshoopIndex = random.nextInt(molshopen.length);
		return molshopen[molshoopIndex];
	}
	
	/**
     * Geeft de molshooplijst
     */
	public Molshoop[] getMolshopen() {
		return molshopen;
	}
	
	/**
     * Geeft of de timer op nul staat
     */
	public boolean isTimerOpNull() {
		return timer.isOpNull();
	}
	
	/**
     * Voert de hamer slag functie van de speler uit
     */
	public void metHamerSlaan(int x, int y) {
		player.metHamerSlaan(x, y);
	}
	
	/**
     * Voert de powerup functies uit en haalt powerups uit de lijst als ze klaar zijn
     */
	private void voerPowerupsUit() {
		int i = 0;
		while(i < powerups.size()) {
			IPowerup currentPowerup = powerups.get(i);
			currentPowerup.updatePowerupActie();
			if(currentPowerup.eindvoorwaarde()) {
				currentPowerup.eindePowerupActie();
				powerups.remove(i);
			}else {
				i++;
			}
		}
	}
	
	/**
     * Geeft aan of de muis boven een object staat
     * 
     * @param object	Het object wat je wil aanklikken
     * @param x			Het x coördinaat van de muis
     * @param y			Het y coördinaat van de muis
     */
	public boolean isClicked(GameObject object, int x, int y) {
		boolean bovenXStrook = x >= object.getX() && x < object.getX() + object.getWidth();
		boolean bovenYStrook = y >= object.getY() && x < object.getY() + object.getHeight();
		return bovenYStrook && bovenXStrook;
	}
	
	/**
     * Update het dashbord
     */
	private void refreshDasboardText() {
		scoreText.setText("Mol Score: " + score);
    }
	
	/**
     * Geeft het eindscherm weer
     */
	private void restart() {
		if (isTimerOpNull()) {
			dashboardText.setText("");
			scoreText.setText("");
			timerText.setText("");
			hiscoreText.setText("je score is:" + score);
		}
	}
	
	@Override
	public void update() {
		deleteGameObject(player);
		addGameObject(player);
		voerPowerupsUit();
		timerText.setText("timer " + timer.toString());
		restart();
	}

}
