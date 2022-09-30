package whackamol;

import nl.han.ica.oopg.objects.Sprite;

public class GoudenMol extends Mol {

	/**
     * Constructor
     *
     * @param world      Referentie naar de wereld
     */
	public GoudenMol(WhackAMol world) {
		super(world, new Sprite("src/main/java/whackamol/diglett_sprite_peek_gold.png"));
	}
	
	@Override
	public void voerActieGeraaktUit() {
		world.updateScore(50);
	}

	@Override
	public void update() {
		if(System.currentTimeMillis() >= startTime + verschijnTijd) {
			molUitMolshoop();
			world.deleteGameObject(this);
		}else if(isGeraakt()) {
			hitSound.rewind();
            hitSound.play();
			voerActieGeraaktUit();
			molUitMolshoop();
			world.deleteGameObject(this);
		}else if(world.isTimerOpNull()) {
			molUitMolshoop();
			world.deleteGameObject(this);
		}
	}
}
