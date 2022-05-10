package model;

/**
 * A prototípus parancsok model.Map-be foglalásához használt segédosztály.
 */
public interface Command {
    /**
     * A parancsokat tartalmazó model.Map-ben tárolt metódusok mintája.
     *
     * @param args - a parancs argumentumainak tömbje
     */
    void execute(String[] args);
}
