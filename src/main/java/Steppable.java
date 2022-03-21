/**
 * Ezt az interfészt valósítják meg azok az osztályok, amiken minden kör végén valamilyen változás megy végbe és ezért egy step() függvénnyel rendelkeznek.
 */
public interface Steppable {
	/**
	 * Minden kör végén meghívódó függvény.
	 */
	void step();
}
