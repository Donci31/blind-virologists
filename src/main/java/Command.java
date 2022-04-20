/**
 * A prototípus parancsok Map-be foglalásához használt segédosztály.
 */
public interface Command {
    /**
     * A parancsokat tartalmazó Map-ben tárolt metódusok mintája.
     * @param args - a parancs argumentumainak tömbje
     */
    void execute(String[] args);
}
