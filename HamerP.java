package whackamol;

public class HamerP implements IPowerup {

	private WhackAMol world;
	private Timer timerTotaal;
	private Timer hamerInterval;
	
	/**
     * Constructor
     *
     * @param world      Referentie naar de wereld
     */
	public HamerP (WhackAMol world) {
		this.world = world;
	}
	
	@Override
	public void beginPowerupActie() {
		timerTotaal = new Timer(15000);
		hamerInterval = new Timer(250);
	}

	@Override
	public void updatePowerupActie() {
		if(hamerInterval.isOpNull()) {
			world.metHamerSlaan(world.mouseX, world.mouseY);
			hamerInterval = new Timer(250);
		}
	}

	@Override
	public void eindePowerupActie() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean eindvoorwaarde() {
		return timerTotaal.isOpNull();
	}

}
