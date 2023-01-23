package com.valhalla;

import com.valhalla.engine.GameLoop;
import com.valhalla.engine.Handler;
import com.valhalla.gamestate.PlayState;

import java.util.Random;

public class Main {

	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 720;

	public static final Random random = new Random();

	public static int HALF_SECOND_TIME = 30;

	public static void main(String[] args) {
		GameLoop gameLoop = new GameLoop("BasePong", GAME_WIDTH, GAME_HEIGHT);
		Handler handler = gameLoop.getHandler();
		
		handler.setGameState(new PlayState(), false);
	}
}
