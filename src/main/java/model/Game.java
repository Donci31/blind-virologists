package model;

import view.Canvas;
import view.Drawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

	/**
	 * Elindítja a játékot.
	 * @param vCount - hány virológussal indítsa a játékot
	 */
	public static void startGame(int vCount) {
		winner = null;
		var map = new Map();
		map.generateMap();
		// TODO controller starts handling virologist turns
	}

	public static void addDrawable(Drawable d){canvas.addDrawable(d);}

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
	 * @return - a nyertes, vagy null
	 */
	public Virologist getWinner(){
		return winner;
	}

	/**
	 * Beállítja a frame tulajdonságait.
	 */
	public static void init() {
		frame.setTitle("Szofti");
		frame.setJMenuBar(menu);
		frame.setPreferredSize(new Dimension(1000,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		BufferedImage gameImage = null;
		try {
			gameImage = ImageIO.read(Game.class.getClassLoader().getResource("brown_bear.png"));
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
	 * @return az aktív virológus
	 */
	public static Virologist getActiveVirologist() {
		return activeVirologist;
	}

	/**
	 * A játékot futását magába foglaló main() metódus.
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

		while (true) {
			SteppableController.step();
		}
	}
}
