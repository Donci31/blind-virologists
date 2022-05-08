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

        move = new MenuButton("Move");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(move, c);

        c.insets = new Insets(10,20,0,20);

        smear = new MenuButton("Smear");
        c.gridy++;
        buttonPanel.add(smear, c);

        interactWithField = new MenuButton("Interact with field");
        c.gridy++;
        buttonPanel.add(interactWithField, c);

        loot = new MenuButton("Loot");
        c.gridy++;
        buttonPanel.add(loot, c);

        craft = new MenuButton("Craft");
        c.gridy++;
        buttonPanel.add(craft, c);

        hit = new MenuButton("Hit");
        c.gridy++;
        buttonPanel.add(hit, c);

        endTurn = new MenuButton("End turn");
        c.gridy++;
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
