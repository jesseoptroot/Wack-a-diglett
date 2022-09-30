package whackamol;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class MolSpawner implements IAlarmListener {

	private float MollenPerSeconde;
	private Random random;
	private WhackAMol world;

	/**
     * Constructor
     * 
     * @param world				Referentie naar de speelwereld
     * @param MollenPerSeconde	Het aantal mollen dat per seconde verschijnt
     */
	public MolSpawner(WhackAMol world, float MollenPerSeconde) {
		this.MollenPerSeconde = MollenPerSeconde;
		this.world = world;
		random = new Random();
		startAlarm();
	}

	private void startAlarm() {
		Alarm alarm = new Alarm("New mol", 1 / MollenPerSeconde);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (!world.isTimerOpNull()) {
			//System.out.println("Alarm triggered");
			int molType = random.nextInt(10);
			Mol m;
			if (molType < 6) {
				m = new NormaleMol(world);
			} else if (molType < 8) {
				m = new BomMol(world);
			} else if (molType < 9) {
				m = new GoudenMol(world);
			} else {
				m = new PowerupMol(world);
			}
			world.addGameObject(m, m.getXPos(), m.getYPos());
			m.setHeight(m.getBreedte());
			m.setWidth(m.getHoogte());
			startAlarm();
		}
	}
}
