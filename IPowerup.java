package whackamol;

public interface IPowerup {
	/**
     * Voert de beginactie van de powerup uit die maar 1 keer uitgevoerd moet worden
     */
	public void beginPowerupActie();
	/**
     * Voert de powerup actie uit die tot het einde uitgevoerd moet blijven worden
     */
	public void updatePowerupActie();
	/**
     * Voert de allerlaatste powerup actie uit en zet alles weer terug als nodig
     */
	public void eindePowerupActie();
	/**
     * Geeft aan of de powerup gestopt moet worden
     */
	public boolean eindvoorwaarde();
}
