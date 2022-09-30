package whackamol;

import nl.han.ica.oopg.objects.Sprite;

public class NormaleMol extends Mol {

	/**
     * Constructor
     * 
     * @param world				Referentie naar de speelwereld
     */
	public NormaleMol(WhackAMol world) {
		super(world, new Sprite("src/main/java/whackamol/diglett_sprite_peek.png"));
	}
	
	@Override
	public void voerActieGeraaktUit() {
		world.updateScore(10);
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
