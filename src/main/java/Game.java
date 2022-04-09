/**
 * A játék elindításáért és megállításáért felelős osztály.
 */
public class Game {
	private Map map;

	/**
	 * Elindítja a játékot.
	 */
	public void startGame() {
		Skeleton.log("->startGame()");
		var m=new Map();
		m.generateMap();
		Skeleton.log("<-startGame()");
	}

	/**
	 * Megállítja a játékot
	 */
	public void endGame() {
		Skeleton.log("->endGame()");
		//TODO
		Skeleton.log("<-endGame()");
	}
}
