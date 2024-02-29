package com.valhalla.input;

import com.valhalla.engine.input.KeyInput;

import java.awt.event.KeyEvent;

public class Player2Input implements PlayerInput {

    @Override
    public boolean holdingUp() {
        return KeyInput.getKeyHeld(KeyEvent.VK_UP);
    }

    @Override
    public boolean holdingDown() {
        return KeyInput.getKeyHeld(KeyEvent.VK_DOWN);
    }
}
