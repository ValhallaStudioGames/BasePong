package com.valhalla.gamestate;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.valhalla.Main;
import com.valhalla.engine.GameLoop;
import com.valhalla.engine.GameState;
import com.valhalla.engine.input.KeyInput;
import com.valhalla.engine.render.Draw;
import com.valhalla.gameobject.Ball;
import com.valhalla.gameobject.Player;
import com.valhalla.gameobject.PlayerType;

public class PlayState extends GameState {

	//bounding boxes for centered strings
	private static final Rectangle SCOREBOARD_BOX = new Rectangle(Main.GAME_WIDTH/4, -25, (Main.GAME_WIDTH/4)*2, 300);
	private static final Rectangle MESSAGE_BOX = new Rectangle(Main.GAME_WIDTH/4, Main.GAME_HEIGHT/2-50, (Main.GAME_WIDTH/4)*2, 100);
	private static final Rectangle MESSAGE_SUBTITLE_BOX = new Rectangle(Main.GAME_WIDTH/4, Main.GAME_HEIGHT/2+25, (Main.GAME_WIDTH/4)*2, 100);

	private static final int MAX_SCORE = 15;

	private GameStatus gameStatus;

	private Player player1;
	private Player player2;
	private Ball ball;

	private int player1Score;
	private int player2Score;

	private String displayMessage;
	private String displayMessageSubtitle;

	private final int roundStartDelay = Main.HALF_SECOND_TIME;
	private long startDelayTick;

	@Override
	protected void initialise() {
		player1 = new Player(PlayerType.PLAYER1);
		player2 = new Player(PlayerType.PLAYER2);
		ball = new Ball();

		handler.addClass(player1, 2);
		handler.addClass(player2, 2);
		handler.addClass(ball, 1);
		
		handler.addClass(new BackGround(), 0);
		
		resetGame();
	}

	@Override
	public void tick() {
		if(gameStatus == GameStatus.PLAYING) {

			// check for collision with left player
			if(ball.getHitbox().intersects(player1.getHitbox())) {
				ball.addHorizontalVelocity(player1.getVelocity());
				ball.setPositiveHorizontalVelocity();
				ball.flipVerticalVelocity();
			}

			// check for collision with right player
			if (ball.getHitbox().intersects(player2.getHitbox())) {
				ball.addHorizontalVelocity(player2.getVelocity());
				ball.setNegativeHorizontalVelocity();
				ball.flipVerticalVelocity();
			}

			// check for collision with wall
			if(ball.getHitbox().y < 0 || ball.getHitbox().y+ball.getHitbox().height > Main.GAME_HEIGHT)  {
				ball.flipVerticalVelocity();
			}

			// check if player has scored
			if(ball.getHitbox().x > Main.GAME_WIDTH) {
				player1Score++;
				displayMessage = "Player 1 scored";
				displayMessageSubtitle = "Press SPACE to pass ball";
				gameStatus = GameStatus.SCORE;
			}
		
			if(ball.getHitbox().x < 0) {
				player2Score++;
				displayMessage = "Player 2 scored";
				displayMessageSubtitle = "Press SPACE to pass ball";
				gameStatus = GameStatus.SCORE;
			}

			if(player1Score == MAX_SCORE || player2Score == MAX_SCORE) {
				displayMessage = (player1Score > player2Score) ? "Player 1 won the game" : "Player 2 won the game";
				displayMessageSubtitle = "press ENTER to start a new game";
				gameStatus = GameStatus.WIN;
			}
		}

		if(gameStatus == GameStatus.SCORE && KeyInput.getKeyPressed(KeyEvent.VK_SPACE)) {
			resetGame();
		}

		if(gameStatus == GameStatus.WIN && KeyInput.getKeyPressed(KeyEvent.VK_ENTER)) {
			initGame();
		}

		if(gameStatus == GameStatus.DELAY) {
			if(startDelayTick + roundStartDelay < GameLoop.getTicksPassed()) {
				gameStatus = GameStatus.PLAYING;
				ball.startMoving();
			}
		}
	}

	@Override
	public void render() {
		if(gameStatus == GameStatus.SCORE || gameStatus == GameStatus.WIN) {
			Draw.drawCenteredString(displayMessage, MESSAGE_BOX, "ARIAL", 50, Color.red);
			Draw.drawCenteredString(displayMessageSubtitle, MESSAGE_SUBTITLE_BOX, "ARIAL", 40, Color.red);
		}

		Draw.drawCenteredString(player1Score + "          " + player2Score, SCOREBOARD_BOX, "ARIAL", 150, Color.white);
	}

	private void initGame() {
		player1Score = 0;
		player2Score = 0;
		resetGame();
	}

	private void resetGame() {
		player1.initPlayerPosition();
		player2.initPlayerPosition();
		ball.setCentered();
		ball.stopMoving();
		gameStatus = GameStatus.DELAY;
		startDelayTick = GameLoop.getTicksPassed();
	}
}
