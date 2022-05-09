package model;

import model.absorbStrats.Absorb;
import model.agents.Agent;
import model.agents.AmniVirus;
import model.agents.StunVirus;
import model.fields.Field;
import view.Canvas;
import view.FieldView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * A virológus egy körben lehetséges tevékenységeinek megjelenítéséért és a kiválasztott tevékenység végrehajtásáért felelős osztály.
 */
public class ActionMenu extends JPanel{
    /**
     * Segédosztály, amely saját stílust ad a JButton-nek.
     */
    class MenuButton extends JButton{
        MenuButton(String s){
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
    private static FieldView selectedField;

    /**
     * Konstruktor, amely inicializálja az ActionMenu-ben szereplő gombokat és beállítja az ActionListener-jeiket.
     */
    public ActionMenu(){
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
        c.insets = new Insets(0,20,0,20);
        c.gridx = 0;
        c.gridy = 0;

        // Move gomb inicializálása, ActionListener beállítása
        move = new MenuButton("Move");
        buttonPanel.add(move, c);
        move.addActionListener(e -> {
            String[] fields = { "Field 1", "Field 2", "Field 3", "Field 4", "Field 5", "Field 6" };
            JComboBox cbox1 = new JComboBox(fields);

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(00,20,30,20);
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

            if(result == JOptionPane.OK_OPTION){
                Virologist activeVirologist = Game.getActiveVirologist();
                Field field = activeVirologist.getField();
                int neighborIndex = cbox1.getSelectedIndex();
                activeVirologist.move(field.getNeighbors().get(neighborIndex));
            }
        });

        // Smear gomb inicializálása, ActionListener beállítása
        c.gridy++;
        c.insets = new Insets(10,20,0,20);
        smear = new MenuButton("Smear");
        smear.addActionListener(e -> {
            // TODO az ez alatti két sort kikommentezni
            //Virologist activeVirologist = Game.getActiveVirologist();
            //Field field = activeVirologist.getField();

            Virologist activeVirologist = new Virologist();
            Field field = new Field();
            field.accept(new Virologist());
            field.accept(new Virologist());
            activeVirologist.addCraftedAgent(new AmniVirus());
            activeVirologist.addCraftedAgent(new StunVirus());
            activeVirologist.addCraftedAgent(new StunVirus());

            HashMap<String, Virologist> virologistMap = new HashMap<>();
            for (Virologist v : field.getVirologists()) {
                virologistMap.put(v.getName(), v);
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
            cons.insets = new Insets(0,20,30,20);
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

            if(result == JOptionPane.OK_OPTION){
                Virologist victim = (Virologist)cbox1.getSelectedItem();
                Agent agent = (Agent)cbox2.getSelectedItem();
                activeVirologist.smearAgent(agent, victim);

                //TODO védekezési viselkedés kiválasztása
                JPanel absorbPanel = new JPanel();
                JLabel absorbLabel = new JLabel("Which defense mechanism to use:");
                HashMap<String, Absorb> absorbMap = new HashMap<>();
                for (Absorb a : victim.getAbsorbStrats()) {

                }
                JComboBox absorbComboBox = new JComboBox();
            }
        });
        buttonPanel.add(smear, c);

        // Interact with field gomb inicializálása, ActionListener beállítása
        c.gridy++;
        interactWithField = new MenuButton("Interact with field");
        interactWithField.addActionListener(e -> {
            Virologist activeVirologist = Game.getActiveVirologist();
            activeVirologist.touch();
        });
        buttonPanel.add(interactWithField, c);

        // Loot gomb inicializálása, ActionListener beállítása
        c.gridy++;
        loot = new MenuButton("Loot");
        loot.addActionListener(e -> {
            String[] gears = { "Gear 1", "Gear 2", "Gear 3", "Gear 4" };
            JComboBox cbox = new JComboBox(gears);

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0,20,30,20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which gear to loot:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a gear!",
                    JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                //TODO action
            }
        });
        buttonPanel.add(loot, c);

        // Craft agent gomb inicializálása, ActionListener beállítása
        c.gridy++;
        craft = new MenuButton("Craft agent");
        craft.addActionListener(e -> {
            String[] codes = { "Code 1", "Code 2", "Code 3" };
            JComboBox cbox = new JComboBox(codes);

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0,20,30,20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which code to use:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a code!",
                    JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                //TODO action
            }
        });
        buttonPanel.add(craft, c);

        // Hit gomb inicializálása, ActionListener beállítása
        c.gridy++;
        hit = new MenuButton("Hit");
        hit.addActionListener(e -> {
            String[] virologists = { "Virologist 1", "Virologist 2" };
            JComboBox cbox = new JComboBox(virologists);

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(0,20,30,20);
            cons.gridx = 0;
            cons.gridy = 0;
            selectPanel.add(new JLabel("Which virologist to hit:"), cons);
            cons.gridx = 1;
            cons.gridy = 0;
            selectPanel.add(cbox, cons);

            int result = JOptionPane.showConfirmDialog(this.getParent(),
                    selectPanel,
                    "Select a virologist!",
                    JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                //TODO action
            }
        });
        buttonPanel.add(hit, c);

        // End turn gomb inicializálása, ActionListener beállítása
        c.gridy++;
        endTurn = new MenuButton("End turn");
        buttonPanel.add(endTurn, c);

        buttonPanel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.black));
        this.add(buttonPanel);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 4));
    }


    /**
     * A v virológus hívja meg a saját step() metódusából, ezzel jelezvén, hogy várja a rá vonatkozó akciót, amit az ActionMenu gombjaival tud a felhasználó jelezni neki.
     * Ez a metódus azzal jelzi, hogy a felhasználó az "End Turn" opciót választotta, hogy hamissal tér vissza az igaz helyett, így a virológus nem hívja meg újra, hanem befejezi a körét.
     * @param v az aktív virológus
     */
    public static boolean waitForAction(Virologist v) {
        // TODO
        return false;
    }

    /**
     * A selectedField settere.
     * @param _selectedField az attribútum új értéke
     */
    public static void setSelectedField(FieldView _selectedField) {
        selectedField = _selectedField;
    }
}
