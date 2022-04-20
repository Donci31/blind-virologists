/**
 * A játék elindításáért és megállításáért felelős osztály.
 */
public class Game {
	private Map map;

	/**
	 * Elindítja a játékot.
	 */
	public void startGame() {
		var m = new Map();
		m.generateMap();
	}

	/**
	 * Megállítja a játékot
	 */
	public void endGame() {
		//TODO
	}
}
