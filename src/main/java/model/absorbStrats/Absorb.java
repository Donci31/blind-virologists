package model.absorbStrats;

import model.agents.Agent;

/**
 * Interfész.
 * Azon függvények gyűjteménye, amelyek a virológusok viselkedését írják le, ha rájuk kennek egy ágenst.
 * Ilyenkor az absorb függvénye kezeli az eseményt.
 * Ezt az interfészt minden absorb stratégiának meg kell valósítania.
 */
public interface Absorb {
    /**
     * Kifejti a paraméterként kapott ágens hatását az ezt meghívó virológusra.
     *
     * @param a - ágens
     */
    void absorb(Agent a);
}
