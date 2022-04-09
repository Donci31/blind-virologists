/**
 * Interfész. Azon függvények gyűjteménye, amelyek a virológusok viselkedését írják le, támadni akarnak.
 * Ilyenkor a hit függvénye kezeli az eseményt. Ezt az interfészt minden hit stratégiának meg kell valósítania.
 */
public interface Hit {
    /**
     * A virológus támadásakor ez a függvény hívódik meg, ez valósítja meg a támadási viselkedését.
     * @param v - a támadás célpontja, virológus
     */
    void hit(Virologist v);
}
