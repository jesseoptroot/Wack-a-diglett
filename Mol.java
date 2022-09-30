package whackamol;

import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Mol extends SpriteObject{
	protected WhackAMol world;
	protected Molshoop molshoop;
	protected long startTime;
	protected static int verschijnTijd;
	protected static Sound hitSound;
	
	/**
     * Constructor
     *
     * @param world     Referentie naar de wereld
     * @param sprite	Sprite voor de mol
     */
	public Mol(WhackAMol world, Sprite sprite){
		super(sprite);
		
		this.world = world;
		boolean molshoopGevonden = false;
		while(!molshoopGevonden) {
			Molshoop willekeurigeMolshoop;
			do {
				willekeurigeMolshoop = world.getRandomMolshoop();
			}while(willekeurigeMolshoop == null);
			if(!willekeurigeMolshoop.isBezet()) {
				molshoop = willekeurigeMolshoop;
				molshoop.molInMolshoop();
				molshoopGevonden = true;
			}
		}
		sprite.resize(getHoogte(), getBreedte());
		startTime = System.currentTimeMillis();
	}
	
	/**
     * Geeft de tijd dat de mol te zien is
     */
	public static int getVerschijntijd() {
		return verschijnTijd;
	}
	
	/**
     * Zet de tijd dat alle mollen te zien zijn
     *
     * @param tijd      De nieuwe verschijntijd
     */
	public static void setVerschijntijd(int tijd) {
		verschijnTijd = tijd;
	}
	
	/**
     * Zet het raak geluid voor alle mollen
     *
     * @param hit      Het raak geluid
     */
	public static void setHitSound(Sound hit) {
		hitSound = hit;
	}
	
	/**
     * Geeft de x positie van de molshoop van de mol
     */
	protected int getXPos() {
		return molshoop.getXPos();
	}
	
	/**
     * Geeft de y positie van de molshoop van de mol
     */
	protected int getYPos() {
		return molshoop.getYPos();
	}
	
	/**
     * Geeft de breedte van de molshoop van de mol
     */
	protected int getBreedte() {
		return molshoop.getBreedte();
	}
	
	/**
     * Geeft de hoogte van de molshoop van de mol
     */
	protected int getHoogte() {
		return molshoop.getHoogte();
	}
	
	/**
     * Laat de molshoop de mol uit de molshoop halen
     */
	protected void molUitMolshoop() {
		molshoop.molUitMolshoop();
	}
	
	/**
     * Geeft aan of de molshoop geraakt is met de hamer
     */
	protected boolean isGeraakt() {
		return molshoop.isGeraakt();
	}
	
	/**
     * Voert de actie uit van de mol als deze geraakt is
     */
	public abstract void voerActieGeraaktUit();
}
