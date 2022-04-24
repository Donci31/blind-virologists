/**
 * A játék elindításáért és megállításáért felelős osztály.
 */
public class Game {
	private Map map;

	/**
	 * Elindítja a játékot.
	 */
	public static void startGame() {
		var m = new Map();
		m.generateMap();
		// TODO controller starts handling virologist turns
	}

	/**
	 * Megállítja a játékot
	 */
	public static void endGame(Virologist virologist) {
		//TODO controller should be calling this, for now it has a Virologist parameter
		System.out.println("The game has ended, " + virologist.getName() + " won!");
	}
}
