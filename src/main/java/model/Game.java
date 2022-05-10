package model;

import view.Canvas;
import view.Drawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A játék elindításáért és megállításáért felelős osztály.
 */
public class Game {
    private static Map map;
    private static Virologist winner;
    private static JFrame frame;
    private static Menu menu;
    private static Canvas canvas;
    private static Virologist activeVirologist;
    private static ActionMenu actionMenu;

    private static ArrayList<Virologist> virologists = new ArrayList<>();

    private static int round = 0;

    /**
     * A Game.virologist objektumok listájához hozzáad egy új model.Virologist objektumot.
     *
     * @param v - hozzáadandó léptethető objektum
     */
    public static void addVirologist(Virologist v) {
        virologists.add(v);
    }

    /**
     * A Game.virologist objektumok listájából kivesz egy korábban hozzáadott model.Virologist objektumot.
     *
     * @param v - hozzáadandó léptethető objektum
     */
    public static void removeVirologist(Virologist v) {
        virologists.remove(v);
    }

    /**
     * Elindítja a játékot.
     *
     * @param vCount - hány virológussal indítsa a játékot
     */
    public static void startGame(int vCount) {
        winner = null;
        map = new Map();
        map.generateMap(vCount);
        activeVirologist = virologists.get(0);
    }

    public static void addDrawable(Drawable d) {
        canvas.addDrawable(d);
    }

    /**
     * Megállítja a játékot
     */
    public static void endGame(Virologist virologist) {
        //TODO controller should be calling this, for now it has a model.Virologist parameter
        winner = virologist;
        //when winner is not null, model.InputWriter knows the game has ended
    }

    /**
     * Lekérdezi a játék nyertesét. Ha még nem nyert senki, null-t ad vissza
     *
     * @return - a nyertes, vagy null
     */
    public Virologist getWinner() {
        return winner;
    }

    public static void endTurn() {
        ++round;
        activeVirologist = virologists.get(round % 2);
        if (round % 2 == 0) {
            SteppableController.step();
        }
    }

    /**
     * Beállítja a frame tulajdonságait.
     */
    public static void init() {
        frame.setTitle("Blind Virologists");
        frame.setJMenuBar(menu);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        BufferedImage gameImage = null;
        try {
            gameImage = ImageIO.read(Game.class.getClassLoader().getResource("bear1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon gameIcon = new ImageIcon(gameImage);
        frame.setIconImage(gameIcon.getImage());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Az activeVirologist gettere.
     *
     * @return az aktív virológus
     */
    public static Virologist getActiveVirologist() {
        return activeVirologist;
    }

    /**
     * A játékot futását magába foglaló main() metódus.
     *
     * @param args main argumentumok
     */
    public static void main(String[] args) {
        frame = new JFrame();
        menu = new Menu();
        canvas = new Canvas(600, 600);
        actionMenu = new ActionMenu();
        //BorderLayout layout = new BorderLayout();
        //frame.setLayout(layout);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(actionMenu, BorderLayout.LINE_END);
        startGame(2);
        init();
    }
}
