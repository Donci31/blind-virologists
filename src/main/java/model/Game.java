package model;

/**
 * A játék elindításáért és megállításáért felelős osztály.
 */
public class Game {
	private Map map;
	private static Virologist winner;

	/**
	 * Elindítja a játékot.
	 */
	public static void startGame() {
		winner = null;
		var m = new Map();
		m.generateMap();
		// TODO controller starts handling virologist turns
	}

	/**
	 * Megállítja a játékot
	 */
	public static void endGame(Virologist virologist) {
		//TODO controller should be calling this, for now it has a model.Virologist parameter
		winner = virologist;
		//when winner is not null, model.InputWriter knows the game has ended
	}

	/**
	 * Lekérdezi a játék nyertesét. Ha még nem nyert senki, null-t ad vissza
	 * @return - a nyertes, vagy null
	 */
	public Virologist getWinner(){
		return winner;
	}

}
