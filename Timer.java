package whackamol;

public class Timer {
	private int tijdTotaal;
	private long startTime;
	private int overigeTijdM;
	private int overigeTijdS;
	private boolean opNul;

	/**
     * Constructor
     * 
     * @param tijdTotaal	Hoe lang de timer moet duren
     */
	public Timer(int tijdTotaal) {
		startTime = System.currentTimeMillis();
		this.tijdTotaal = tijdTotaal;
		int overigeTijd = (int)((tijdTotaal + startTime) - System.currentTimeMillis());
		overigeTijdM = (int)((overigeTijd / 1000) / 60);
		overigeTijdS = (int)((overigeTijd / 1000) % 60);
		opNul = false;
	}
	
	/**
     * Past de waarde van de timer aan
     */
	private void updateTimer() {
		int overigeTijd = (int)((tijdTotaal + startTime) - System.currentTimeMillis());
		if(overigeTijd <= 0) {
			opNul = true;
		}
		overigeTijdM = (int)((overigeTijd / 1000) / 60);
		overigeTijdS = (int)((overigeTijd / 1000) % 60);
	}
	
	/**
     * Geeft de totale duur van de timer
     */
	public int getTijdTotaal() {
		return tijdTotaal;
	}
	
	/**
     * Past de totaaltijd van de timer aan
     */
	public void setTijdTotaal(int tijdTotaal) {
		this.tijdTotaal = tijdTotaal;
	}
	
	/**
     * Geeft aan of de timer afgelopen is
     */
	public boolean isOpNull() {
		updateTimer();
		return opNul;
	}
	
	/**
     * Geeft in een m:ss vorm de overige tijd weer
     */
	public String toString() {
		updateTimer();
		String output = overigeTijdM + ":";
		if(overigeTijdS < 10 && overigeTijdS >= 0) {
			output += "0" + overigeTijdS;
		}else if (overigeTijdS >= 10){
			output += overigeTijdS;
		}else {
			output += "00";
		}
		return output;
	}
}
