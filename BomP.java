package whackamol;

public class BomP implements IPowerup {
	
	private WhackAMol world;
	private Timer timer;
	
	/**
     * Constructor
     *
     * @param world      Referentie naar de wereld
     */
	public BomP(WhackAMol world) {
		this.world = world;
	}

	@Override
	public void beginPowerupActie() {
		timer = new Timer(1000);
		for(int i = 0; i < world.getMolshopen().length; i++) {
			Molshoop molshoop = world.getMolshopen()[i];
			molshoop.slaanOpMolshoop();
		}
	}

	@Override
	public void updatePowerupActie() {
		// TODO Auto-generated method stub

	}

	@Override
	public void eindePowerupActie() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean eindvoorwaarde() {
		return timer.isOpNull();
	}

}
