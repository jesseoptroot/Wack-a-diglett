package whackamol;

import java.util.Random;

import nl.han.ica.oopg.objects.Sprite;

public class PowerupMol extends Mol {

	private Random random;
	
	/**
     * Constructor
     *
     * @param world      Referentie naar de wereld
     */
	public PowerupMol(WhackAMol world) {
		super(world, new Sprite("src/main/java/whackamol/diglett_sprite_peek_power.png"));
		random = new Random();
	}
	
	@Override
	public void voerActieGeraaktUit() {
		int powerupType = random.nextInt(4);
		IPowerup powerup;
		if(powerupType == 0) {
			powerup = new KlokP(world);
		}else if(powerupType == 1){
			powerup = new AasP();
		}else if(powerupType == 2){
			powerup = new BomP(world);
		}else {
			powerup = new HamerP(world);
		}
		powerup.beginPowerupActie();
		world.addPowerup(powerup);
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
