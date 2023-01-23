package com.valhalla.gameobject;

import com.valhalla.Main;
import com.valhalla.engine.BaseClass;
import com.valhalla.engine.render.Draw;

import java.awt.*;

public class Ball extends BaseClass {

	private static final Color BROWN_COLOUR = new Color(90, 55, 30);

	private int horizontalVelocity;
	private int verticalVelocity; //vertical velocity positive = down, negative = up (because of how screen coordinates work)

	private final Rectangle ballRect;

	public Ball() {
		stopMoving();
		ballRect = new Rectangle(0, 0, 12, 12);
	}

	@Override
	public void tick() {
		ballRect.x += horizontalVelocity;
		ballRect.y += verticalVelocity;
	}

	@Override
	public void render() {
		Draw.fillRect(ballRect, BROWN_COLOUR);
	}

	public void setCentered() {
		ballRect.x = (Main.GAME_WIDTH/2)-(ballRect.width/2);
		ballRect.y = (Main.GAME_HEIGHT/2)-(ballRect.height/2);
	}

	public void startMoving() {
		horizontalVelocity = (Main.random.nextInt()%2==0) ? 5 : -5;
		verticalVelocity = (Main.random.nextInt()%2==0) ? 3 : -3;
	}

	public void stopMoving() {
		horizontalVelocity = 0;
		verticalVelocity = 0;
	}

	public void flipVerticalVelocity() {
		verticalVelocity = -verticalVelocity;
	}
	
	public void setPositiveHorizontalVelocity() {
		horizontalVelocity = (horizontalVelocity < 0)  ? -horizontalVelocity : horizontalVelocity;
 	}
	
	public void setNegativeHorizontalVelocity() {
		horizontalVelocity = (horizontalVelocity > 0)  ? -horizontalVelocity : horizontalVelocity;
	}
	
	public void addHorizontalVelocity(int velocity) {
		verticalVelocity += velocity / 2;
	}

	public Rectangle getHitbox() {
		return ballRect;
	}
}
