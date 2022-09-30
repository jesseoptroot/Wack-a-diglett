package whackamol;

public class KlokP implements IPowerup {

	private WhackAMol world;
	private Timer timer;
	
	/**
     * Constructor
     *
     * @param world      Referentie naar de wereld
     */
	public KlokP(WhackAMol world) {
		this.world = world;
	}
	
	@Override
	public void beginPowerupActie() {
		timer = new Timer(1000);
		world.veranderSpeeltijd(15000);
	}

	@Override
	public void updatePowerupActie() {
		//Leeg
	}

	@Override
	public void eindePowerupActie() {
		//Leeg
	}

	@Override
	public boolean eindvoorwaarde() {
		return timer.isOpNull();
	}

}
