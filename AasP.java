package whackamol;

public class AasP implements IPowerup {
	
	private Timer timer;
	private int extraTijd;
	
	/**
     * Constructor
     */
	public AasP() {
		extraTijd = 1500;
	}

	@Override
	public void beginPowerupActie() {
		Mol.setVerschijntijd(Mol.getVerschijntijd() + extraTijd);
		timer = new Timer(10000);
	}
	
	@Override
	public void updatePowerupActie() {
		//leeg
	}

	@Override
	public void eindePowerupActie() {
		Mol.setVerschijntijd(Mol.getVerschijntijd() - extraTijd);
	}

	@Override
	public boolean eindvoorwaarde() {
		return timer.isOpNull();
	}
	
}
