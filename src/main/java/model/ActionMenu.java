package model;

import model.absorbStrats.Absorb;
import model.agents.Agent;
import model.codes.Code;
import model.fields.Field;
import model.gears.Gear;
import view.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A virológus egy körben lehetséges tevékenységeinek megjelenítéséért és a kiválasztott tevékenység végrehajtásáért felelős osztály.
 */
public class ActionMenu extends JPanel {
    /**
     * Segédosztály, amely saját stílust ad a JButton-nek.
     */
    class MenuButton extends JButton {
        MenuButton(String s) {
            super(s);
            setFocusPainted(false);
            Dimension menuButtonSize = new Dimension(200, 50);
            setPreferredSize(menuButtonSize);
            Font bigFont = super.getFont().deriveFont(Font.PLAIN, 20f);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            setFont(bigFont);
            setBackground(Color.LIGHT_GRAY);
        }
    }

    private MenuButton move, smear, interactWithField, loot, craft, hit, endTurn;
    private Canvas canvas;
    private boolean hasMoved = false;
    private boolean hasInteractedWithField = false;

    /**
     * Konstruktor, amely inicializálja az ActionMenu-ben szereplő gombokat és beállítja az ActionListener-jeiket.
     */
    public ActionMenu() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));
        var label = new JLabel("Actions");
        label.setFont(super.getFont().deriveFont(Font.PLAIN, 40f));
        labelPanel.add(label);
        this.add(labelPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 0;

        // Move gomb inicializálása, ActionListener beállítása
        move = new MenuButton("Move");
        buttonPanel.add(move, c);
        move.addActionListener(e -> {
            Virologist activeVirologist = Game.getActiveVirologist();
            if (activeVirologist == null || activeVirologist.isStunned() || hasMoved) {
                return;
            }
            Field field = activeVirologist.getField();

            LinkedHashMap<String, Field> fieldMap = new LinkedHashMap<>();
            List<Field> fieldList = field.getNeighbors();
            for (int i = 0; i < fieldList.size(); i++) {
                if (fieldList.get((i + 3) % fieldList.size()) != null) {
                    String fieldString = String.valueOf(i + 1);
                    if ((i + 3) % fieldList.size() == 3) {
                        fieldString += " (North)";
                    } else if ((i + 3) % fieldList.size() == 0) {
                        fieldString += " (South)";
                    }
                    fieldMap.put(fieldString, fieldList.get((i + 3) % fieldList.size()));
                }
            }

            JComboBox cbox1 = new JComboBox(fieldMap.keySet().toArray(new String[0]));

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(00, 20, 30, 20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which field to move to:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox1, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a field!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                hasMoved = true;
                hasInteractedWithField = false;
                String neighborString = (String)cbox1.getSelectedItem();
                activeVirologist.move(fieldMap.get(neighborString));
                Game.getCanvas().repaint();
            }
        });

        // Smear gomb inicializálása, ActionListener beállítása
        c.gridy++;
        c.insets = new Insets(10, 20, 0, 20);
        smear = new MenuButton("Smear");
        smear.addActionListener(e -> {

            Virologist activeVirologist = Game.getActiveVirologist();
            if (activeVirologist == null || activeVirologist.isStunned()) {
                return;
            }
            Field field = activeVirologist.getField();

            HashMap<String, Virologist> virologistMap = new HashMap<>();
            for (Virologist v : field.getVirologists()) {
                if (activeVirologist.getName().equals(v.getName())) {
                    virologistMap.put(v.getName() + " (yourself)", v);
                } else {
                    virologistMap.put(v.getName(), v);
                }
            }
            JComboBox cbox1 = new JComboBox(virologistMap.keySet().toArray(new String[0]));

            HashMap<String, Agent> agentMap = new HashMap<>();
            for (Agent a : activeVirologist.getCraftedAgents()) {
                agentMap.put(a.getClass().getSimpleName(), a);
            }
            JComboBox cbox2 = new JComboBox(agentMap.keySet().toArray(new String[0]));

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0, 20, 30, 20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which virologist to target:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox1, cons);
            cons.gridx = 0;
            cons.gridy = 1;
            selectPanel.add(new JLabel("Which agent to use:"), cons);
            cons.gridx = 1;
            cons.gridy = 1;
            selectPanel.add(cbox2, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a target and an agent!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION && cbox1.getSelectedItem() != null && cbox2.getSelectedItem() != null) {
                String victimString = (String) cbox1.getSelectedItem();
                String agentString = (String) cbox2.getSelectedItem();
                Virologist victim = virologistMap.get(victimString);
                Agent agent = agentMap.get(agentString);

                // Victim védekezési viselkedésének kiválasztása
                JPanel absorbPanel = new JPanel();
                JLabel absorbLabel = new JLabel("Which defense mechanism to use:");
                HashMap<String, Absorb> absorbMap = new HashMap<>();
                for (Absorb a : victim.getAbsorbStrats()) {
                    String absorbName = a.getClass().getSimpleName().replace("Absorb", "");
                    absorbMap.put(absorbName, a);
                }

                String[] stringAbsorbs = absorbMap.keySet().toArray(new String[0]);
                JComboBox absorbComboBox = new JComboBox(stringAbsorbs);
                absorbComboBox.setSelectedItem(stringAbsorbs[0]);
                absorbPanel.add(absorbLabel);
                absorbPanel.add(absorbComboBox);

                JOptionPane.showConfirmDialog(this.getParent(),
                        absorbPanel,
                        "Select a defensive mechanism!",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                String absorbString = (String) absorbComboBox.getSelectedItem();
                Absorb selectedAbsorb = absorbMap.get(absorbString);

                victim.choosePrimaryAbsorbStrat(selectedAbsorb);
                activeVirologist.smearAgent(agent, victim);
            }
        });
        buttonPanel.add(smear, c);

        // Interact with field gomb inicializálása, ActionListener beállítása
        c.gridy++;
        interactWithField = new MenuButton("Interact with field");
        interactWithField.addActionListener(e -> {
            Virologist activeVirologist = Game.getActiveVirologist();
            if (activeVirologist == null || activeVirologist.isStunned() || hasInteractedWithField) {
                return;
            }
            activeVirologist.touch();
            Game.getCanvas().repaint();
            hasInteractedWithField = true;
        });
        buttonPanel.add(interactWithField, c);

        // Loot gomb inicializálása, ActionListener beállítása
        c.gridy++;
        loot = new MenuButton("Loot");
        loot.addActionListener(e -> {
            Virologist activeVirologist = Game.getActiveVirologist();
            if (activeVirologist == null || activeVirologist.isStunned()) {
                return;
            }
            Field field = activeVirologist.getField();

            HashMap<String, Virologist> virologistMap = new HashMap<>();
            for (Virologist v : field.getVirologists()) {
                if (!activeVirologist.getName().equals(v.getName()) && v.isStunned()) {
                    virologistMap.put(v.getName(), v);
                }
            }

            JComboBox cbox = new JComboBox(virologistMap.keySet().toArray(new String[0]));

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0, 20, 30, 20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which virologist to loot:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox, cons);

            // Virológus kiválasztása kifosztásra
            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a virologist to loot!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // A kiválasztott virológus egy felszerelésének kiválasztása elcsenésre
            if (result == JOptionPane.OK_OPTION && cbox.getSelectedItem() != null) {
                JPanel selectGearPanel = new JPanel();
                String victimString = (String) cbox.getSelectedItem();
                Virologist victim = virologistMap.get(victimString);

                HashMap<String, Gear> gearsMap = new HashMap<>();
                for (Gear g : victim.getGears()) {
                    gearsMap.put(g.getClass().getSimpleName(), g);
                }
                JComboBox cbox2 = new JComboBox(gearsMap.keySet().toArray(new String[0]));
                selectGearPanel.add(new JLabel("Which gear to loot:"));
                selectGearPanel.add(cbox2);

                int selectGearResult = JOptionPane.showConfirmDialog(this.getParent(),
                        selectGearPanel,
                        "Select a gear!",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (selectGearResult == JOptionPane.OK_OPTION && cbox2.getSelectedItem() != null) {
                    String gearString = (String) cbox2.getSelectedItem();
                    Gear gear = gearsMap.get(gearString);
                    activeVirologist.loot(victim, gear);
                }
            }
        });
        buttonPanel.add(loot, c);

        // Craft agent gomb inicializálása, ActionListener beállítása
        c.gridy++;
        craft = new MenuButton("Craft agent");
        craft.addActionListener(e -> {
            Virologist activeVirologist = Game.getActiveVirologist();
            if (activeVirologist == null || activeVirologist.isStunned()) {
                return;
            }

            HashMap<String, Code> codeMap = new HashMap<>();
            for (Code code : activeVirologist.getLearntCodes()) {
                codeMap.put(code.getClass().getSimpleName(), code);
            }
            JComboBox cbox = new JComboBox(codeMap.keySet().toArray(new String[0]));

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0, 20, 30, 20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which code to use:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a code!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION && cbox.getSelectedItem() != null) {
                String codeString = (String) cbox.getSelectedItem();
                Code selectedCode = codeMap.get(codeString);
                activeVirologist.craftAgent(selectedCode);
            }
        });
        buttonPanel.add(craft, c);

        // Hit gomb inicializálása, ActionListener beállítása
        c.gridy++;
        hit = new MenuButton("Hit");
        hit.addActionListener(e -> {
            Virologist activeVirologist = Game.getActiveVirologist();
            if (activeVirologist == null || activeVirologist.isStunned()) {
                return;
            }
            Field field = activeVirologist.getField();

            HashMap<String, Virologist> virologistMap = new HashMap<>();
            for (Virologist v : field.getVirologists()) {
                if (!activeVirologist.getName().equals(v.getName())) {
                    virologistMap.put(v.getName(), v);
                }
            }
            JComboBox cbox = new JComboBox(virologistMap.keySet().toArray(new String[0]));

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0, 20, 30, 20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which virologist to hit:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a virologist!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION && cbox.getSelectedItem() != null) {
                String victimString = (String) cbox.getSelectedItem();
                Virologist victim = virologistMap.get(victimString);
                activeVirologist.hit(victim);
            }
        });
        buttonPanel.add(hit, c);

        // End turn gomb inicializálása, ActionListener beállítása
        c.gridy++;
        endTurn = new MenuButton("End turn");
        endTurn.addActionListener(e -> {
            hasMoved = false;
            hasInteractedWithField = false;
            Game.endTurn();
            Game.getCanvas().repaint();
        });
        buttonPanel.add(endTurn, c);

        buttonPanel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.black));
        this.add(buttonPanel);
        this.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.black));
    }
}
