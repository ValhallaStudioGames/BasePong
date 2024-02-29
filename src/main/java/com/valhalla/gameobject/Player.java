package com.valhalla.gameobject;

import com.valhalla.Main;
import com.valhalla.engine.BaseClass;
import com.valhalla.engine.render.Draw;
import com.valhalla.input.PlayerInput;

import java.awt.*;

public class Player extends BaseClass {
	
	private final PlayerType playerType;
	private final PlayerInput playerInput;

	private int velocity;

	private final Rectangle playerHitbox = new Rectangle(0,0,25,100);
	
	public Player(PlayerType playerType) {
		this.playerType = playerType;
		this.playerInput = playerType.inputMethod;

		initPlayerPosition();
	}

	@Override
	public void tick() {
		if(playerInput.holdingDown()) {
			playerHitbox.y += 15;
			addVelocity(4);
		}else if(playerInput.holdingUp()) {
			playerHitbox.y -= 15;
			addVelocity(-4);
		}else {
			removeVelocity();
		}

		if(playerHitbox.y > Main.GAME_HEIGHT- playerHitbox.height) {
			playerHitbox.y = Main.GAME_HEIGHT- playerHitbox.height;
		}else if (playerHitbox.y < 0) {
			playerHitbox.y = 0;
		}
	}

	@Override
	public void render() {
		Draw.fillRect(playerHitbox, Color.white);
	}

	public void initPlayerPosition() {
		playerHitbox.x = this.playerType==PlayerType.PLAYER1 ? 150 : Main.GAME_WIDTH-180;
		playerHitbox.y = (Main.GAME_HEIGHT/2)-(playerHitbox.height/2);
	}

	private void addVelocity(int velocity) {
		this.velocity += velocity;
		if (this.velocity > 10) this.velocity = 10;
		if (this.velocity < -10) this.velocity = -10;
	}
	
	private void removeVelocity() {
		if (velocity > 0) velocity--;
		if (velocity < 0) velocity++;
	}

	public Rectangle getHitbox() {
		return playerHitbox;
	}

	public int getVelocity() {
		return velocity;
	}
}
