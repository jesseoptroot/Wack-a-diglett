package whackamol;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Player extends AnimatedSpriteObject{
	
	private WhackAMol world;
	private Sound molMissSound;
	private Timer animatieTimer;
	
	/**
     * Constructor
     *
     * @param world        	Referentie naar de wereld
     * @param molHitSound	Geluid dat moet klinken als er mis geslagen wordt
     */
	public Player(WhackAMol world, Sound molMissSound) {
		super(new Sprite("src/main/java/whackamol/hammer2.png"), 4);
		this.world = world;
		this.molMissSound = molMissSound;
		animatieTimer = new Timer(0);
	}

	@Override
	public void update() {
		if(!animatieTimer.isOpNull()) {
			setCurrentFrameIndex(3);
		}else {
			setCurrentFrameIndex(1);
		}
	}

	@Override
	public void mouseMoved(int x,int y) {
		setX(x);
		setY(y);
	}

	public void mousePressed(int x,int y,int button) {
		metHamerSlaan(x, y);
	}
	
	/**
     * Checkt of de muis boven een molshoop zit en slaat daar op
     *
     * @param x    	x coördinaat van de muis
     * @param y		y coördinaat van de muis
     */
	public void metHamerSlaan(int x, int y) {
		animatieTimer = new Timer(150);
		boolean niksGeraakt = true;
		for(int i = 0; i < world.getMolshopen().length; i++) {
			Molshoop molshoop = world.getMolshopen()[i];
			if(world.isClicked(molshoop, x, y)) {
				molshoop.slaanOpMolshoop();
				niksGeraakt = false;
			}
		}
		if(niksGeraakt) {
			molMissSound.rewind();
        	molMissSound.play();
		}
	}
}
