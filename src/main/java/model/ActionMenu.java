package model;

import view.Canvas;
import view.FieldView;

import javax.swing.*;
import java.awt.*;

/**
 * A virológus egy körben lehetséges tevékenységeinek megjelenítéséért és a kiválasztott tevékenység végrehajtásáért felelős osztály.
 */
public class ActionMenu extends JPanel{
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

    // TODO ha tényleg a Singleton mintát szeretnénk, akkor kellene egy getInstance() és egyebek is
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
                    JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                //TODO action
            }
        });

        c.insets = new Insets(10,20,0,20);

        c.gridy++;
        smear = new MenuButton("Smear");
        smear.addActionListener(e -> {
            String[] virologists = { "virologist 1", "virologist 2", "virologist 3" };
            JComboBox cbox1 = new JComboBox(virologists);
            String[] agents = { "agent 1", "agent 2" };
            JComboBox cbox2 = new JComboBox(agents);

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.insets = new Insets(00,20,30,20);
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
                    JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                //TODO action
            }
        });
        buttonPanel.add(smear, c);

        c.gridy++;
        interactWithField = new MenuButton("Interact with field");
        buttonPanel.add(interactWithField, c);

        c.gridy++;
        loot = new MenuButton("Loot");
        buttonPanel.add(loot, c);

        c.gridy++;
        craft = new MenuButton("Craft");
        buttonPanel.add(craft, c);

        c.gridy++;
        hit = new MenuButton("Hit");
        buttonPanel.add(hit, c);

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
