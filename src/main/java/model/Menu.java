package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        field = new JMenu("Field");


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
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] possibleVirologistCounts = { 2, 3, 4 };
            Object[] buttonOptions = { "Start!", "Cancel" };

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
                Game.startGame((int)comboBox.getSelectedItem());
            }
        }
    }

    /**
     * A játékból való kilépést megvalósító ActionListener.
     */
    class ExitActionListener implements ActionListener {

        /**
         * Kilép a játékból.
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
