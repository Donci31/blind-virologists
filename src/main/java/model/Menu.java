package model;

import model.agents.*;
import model.codes.*;
import model.fields.Field;
import model.fields.Laboratory;
import model.fields.Shelter;
import model.fields.Warehouse;
import model.gears.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A menüt megvalósító osztály. Játék állapotáról ad információkat, és irányítani lehet vele a játékot (újraindít, kilép, stb.).
 */
public class Menu extends JMenuBar {
    JMenu game, inventory, field;

    /**
     * Konstruktor, amely felépíti a menusort.
     */
    public Menu() {
        game = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new NewGameActionListener());
        game.add(newGame);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitActionListener());
        game.add(exit);

        inventory = new JMenu("Inventory");
        JMenuItem openInventory = new JMenuItem("Open Inventory...");
        openInventory.addActionListener(new InventoryActionListener());
        inventory.add(openInventory);

        field = new JMenu("Field");
        JMenuItem fieldInfo = new JMenuItem("Field Info...");
        fieldInfo.addActionListener(new FieldInfoActionListener());
        field.add(fieldInfo);

        this.add(game);
        this.add(inventory);
        this.add(field);
    }

    /**
     * Az új játék indítását megvalósító ActionListener.
     */
    class NewGameActionListener implements ActionListener {

        /**
         * Új játék elindítása a kiválasztott számú virológussal.
         *
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] possibleVirologistCounts = {2, 3, 4};
            Object[] buttonOptions = {"Start!", "Cancel"};

            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            JLabel label = new JLabel("How many players:");

            JComboBox comboBox = new JComboBox(possibleVirologistCounts);
            comboBox.setSelectedItem(possibleVirologistCounts[0]);
            panel.add(label);
            panel.add(comboBox);

            int result = JOptionPane.showOptionDialog(
                    frame, panel,
                    "Start New Game",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    buttonOptions,
                    buttonOptions[0]
            );

            if (result == JOptionPane.OK_OPTION) {
                Game.startGame((int) comboBox.getSelectedItem());
            }
        }
    }

    /**
     * A játékból való kilépést megvalósító ActionListener.
     */
    class ExitActionListener implements ActionListener {

        /**
         * Kilép a játékból.
         *
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * A játékos állapotának megjelenítését megvalósító ActionListener.
     */
    class InventoryActionListener implements ActionListener {

        /**
         * Az aktív játékos inventory-jának megnyitása, a virológus állapotának megjelenítése.
         *
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Virologist activeVirologist = Game.getActiveVirologist();

            // Megszerzi az aktív virológus összes adatát
            int aminoAcid = activeVirologist.getAminoAcid();
            int nucleotide = activeVirologist.getNucleotide();
            ArrayList<Code> learntCodes = activeVirologist.getLearntCodes();
            ArrayList<Agent> craftedAgents = activeVirologist.getCraftedAgents();
            ArrayList<Agent> activeAgentsOnVirologist = SteppableController.getAppliedAgents();
            activeAgentsOnVirologist.removeIf(a -> a.getSmearedVirologist() != activeVirologist);
            ArrayList<Gear> gears = activeVirologist.getGears();

            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            frame.add(panel);
            GridBagConstraints con = new GridBagConstraints();

            JLabel aminoLabel = new JLabel("Amino acid:");
            con.gridx = 0;
            con.gridy = 0;
            panel.add(aminoLabel, con);

            JLabel aminoCount = new JLabel(String.valueOf(aminoAcid));
            con.gridx = 1;
            con.gridy = 0;
            panel.add(aminoCount, con);

            JLabel nucleotideLabel = new JLabel("Nucleotide:");
            con.gridx = 0;
            con.gridy = 1;
            panel.add(nucleotideLabel, con);

            JLabel nucleotideCount = new JLabel(String.valueOf(nucleotide));
            con.gridx = 1;
            con.gridy = 1;
            panel.add(nucleotideCount, con);

            JLabel gearsLabel = new JLabel("Gears:");
            con.gridx = 0;
            con.gridy = 2;
            panel.add(gearsLabel, con);

            con.gridx = 1;
            for (Gear gear : gears) {
                JLabel label = new JLabel();
                String name = gear.getClass().getSimpleName();
                if (name.equals("AxeGear")) {
                    name += ((AxeGear) gear).isUsed() ? " (used)" : " (not used)";
                } else if (name.equals("GloveGear")) {
                    name += " (" + ((GloveGear) gear).getTimesUsed() + " times used)";
                }

                label.setText(name);
                con.gridy++;
                panel.add(label, con);
            }

            JLabel learntCodesLabel = new JLabel("Learnt codes:");
            con.gridx = 0;
            con.gridy++;
            panel.add(learntCodesLabel, con);

            con.gridx = 1;
            for (Code code : learntCodes) {
                JLabel label = new JLabel();
                String name = code.getClass().getSimpleName();
                label.setText(name + " (n=" + code.nCost + ", a=" + code.aCost + ")");
                con.gridy++;
                panel.add(label, con);
            }

            JLabel craftedAgentsLabel = new JLabel("Crafted agents:");
            con.gridx = 0;
            con.gridy++;
            panel.add(craftedAgentsLabel, con);

            con.gridx = 1;
            for (Agent agent : craftedAgents) {
                JLabel label = new JLabel(agent.getClass().getSimpleName());
                con.gridy++;
                panel.add(label, con);
            }

            JLabel activeAgentsLabel = new JLabel("Active agents:");
            con.gridx = 0;
            con.gridy++;
            panel.add(activeAgentsLabel, con);

            con.gridx = 1;
            for (Agent agent : activeAgentsOnVirologist) {
                JLabel label = new JLabel(agent.getClass().getSimpleName() + " (" + agent.getVirusTimer() + " turns left)");
                con.gridy++;
                panel.add(label, con);
            }

            JOptionPane.showConfirmDialog(
                    frame, panel,
                    "Inventory",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * A virológus mezője állapotának megjelenítését megvalósító ActionListener.
     */
    class FieldInfoActionListener implements ActionListener {
        /**
         * Arról a mezőről jelenít meg információt, amelyiken a virológus áll.
         *
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            frame.add(panel);
            GridBagConstraints con = new GridBagConstraints();

            Virologist activeVirologist = Game.getActiveVirologist();
            Field field = activeVirologist.getField();
            String fieldName = field.getClass().getSimpleName();

            JLabel fieldLabel = new JLabel("Type of field:");
            con.gridx = 0;
            con.gridy = 0;
            panel.add(fieldLabel, con);

            JLabel fieldNameLabel = new JLabel(fieldName);
            con.gridx = 1;
            con.gridy = 0;
            panel.add(fieldNameLabel, con);

            JLabel virologistsLabel = new JLabel("Other virologists located here:");
            con.gridx = 0;
            con.gridy = 1;
            panel.add(virologistsLabel, con);

            List<Virologist> virologists = field.getVirologists();
            JLabel label = new JLabel(String.valueOf(virologists.size() - 1));
            con.gridx = 1;
            panel.add(label, con);

            switch (fieldName) {
                case "Field":
                    fieldNameLabel.setText("Empty Field");
                    break;
                case "Shelter":
                    JLabel gearOnFieldLabel = new JLabel("Gear in Shelter:");
                    con.gridx = 0;
                    con.gridy++;
                    panel.add(gearOnFieldLabel, con);

                    Gear gear = ((Shelter) field).getGear();
                    JLabel gearNameLabel = new JLabel();
                    if (gear == null) {
                        gearNameLabel.setText("-");
                    } else {
                        gearNameLabel.setText(gear.getClass().getSimpleName());
                    }
                    con.gridx = 1;
                    panel.add(gearNameLabel, con);
                    break;
                case "Laboratory":
                    boolean infected = ((Laboratory) field).isInfected();
                    fieldName += (infected) ? " (infected)" : " (not infected)";
                    fieldNameLabel.setText(fieldName);

                    JLabel codeOnLabLabel = new JLabel("Code in Laboratory:");
                    con.gridx = 0;
                    con.gridy++;
                    panel.add(codeOnLabLabel, con);

                    Code code = ((Laboratory) field).getCode();
                    JLabel codeLabel = new JLabel();
                    if (code == null) {
                        codeLabel.setText("-");
                    } else {
                        codeLabel.setText(code.getClass().getSimpleName());
                    }
                    con.gridx = 1;
                    panel.add(codeLabel, con);
                    break;
                case "Warehouse":
                    int nCount = ((Warehouse) field).getnProduced();
                    JLabel nLabel = new JLabel("Nucleotide in Warehouse:");
                    con.gridx = 0;
                    con.gridy++;
                    panel.add(nLabel, con);

                    JLabel nCountLabel = new JLabel(String.valueOf(nCount));
                    con.gridx = 1;
                    panel.add(nCountLabel, con);

                    int aCount = ((Warehouse) field).getaProduced();
                    JLabel aLabel = new JLabel("Amino acid in Warehouse:");
                    con.gridx = 0;
                    con.gridy++;
                    panel.add(aLabel, con);

                    JLabel aCountLabel = new JLabel(String.valueOf(aCount));
                    con.gridx = 1;
                    panel.add(aCountLabel, con);
                    break;
            }

            JOptionPane.showConfirmDialog(
                    frame, panel,
                    "Field Info",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
