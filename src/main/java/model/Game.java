package model;

import view.Canvas;

import javax.swing.*;
import java.awt.*;

/**
 * A játék elindításáért és megállításáért felelős osztály.
 */
public class Game {
	private static Map map;
	private static Virologist winner;
	private static JFrame frame;
	private static Menu menu;
	private static Canvas canvas;
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

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
