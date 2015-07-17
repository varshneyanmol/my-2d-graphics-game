package com.rain;

import javax.swing.JFrame;

public class Main {

	public static JFrame window;
	private static Game game;
	
	public static void main(String[] args) {
		
		game = new Game();
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("learning to make a game");
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		game.start();
	}
}
