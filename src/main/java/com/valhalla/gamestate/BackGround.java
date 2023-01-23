package com.valhalla.gamestate;

import java.awt.Color;

import com.valhalla.Main;
import com.valhalla.engine.BaseClass;
import com.valhalla.engine.render.Draw;


public class BackGround extends BaseClass {

	@Override
	public void tick() { }

	@Override
	public void render() {
		Draw.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT, Color.black);
	}
}
