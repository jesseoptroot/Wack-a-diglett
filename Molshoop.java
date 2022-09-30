package whackamol;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Molshoop extends SpriteObject{
	private WhackAMol world;
	private boolean bezet;
	private boolean geraakt;
	
	/**
     * Constructor
     * 
     * @param world		Referentie naar de speelwereld
     * @param sprite	Sprite van de molshoop
     * @param x			x positie van de molshoop
     * @param y			y positie van de molshoop
     * @param b			Breedte van de molshoop
     * @param h 		Hoogte van de molshoop
     */
	public Molshoop(WhackAMol world, Sprite sprite, int x, int y, int b, int h) {
		super(sprite);
		sprite.resize(h, b);
		this.world = world;
		bezet = false;
		geraakt = false;
		world.addGameObject(this, x, y);
		this.setHeight(h);
		this.setWidth(b);
	}
	
	/**
     * Geeft de x positie van de molshoop
     */
	public int getXPos() {
		return (int)getX();
	}
	
	/**
     * Geeft de y positie van de molshoop
     */
	public int getYPos() {
		return (int)getY();
	}
	
	/**
     * Geeft de breedte van de molshoop
     */
	public int getBreedte() {
		return (int) getWidth();
	}
	
	/**
     * Geeft de hoogte van de molshoop
     */
	public int getHoogte() {
		return (int) getHeight();
	}
	
	/**
     * Geeft aan of er een mol in de molshoop zit
     */
	public boolean isBezet() {
		return bezet;
	}
	
	/**
     * Zet een mol in de molshoop
     */
	public void molInMolshoop() {
		bezet = true;
	}
	
	/**
     * Haalt een mol uit de molshoop
     */
	public void molUitMolshoop() {
		bezet = false;
		geraakt = false;
	}

	/**
     *  Als er een mol in de molshoop zit wordt die geraakt
     */
	public void slaanOpMolshoop() {
		if(bezet) {
			geraakt = true;
		}
	}
	
	/**
     * Geeft aan dat de mol in de molshoop geraakt is
     */
	public boolean isGeraakt() {
		return geraakt;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
