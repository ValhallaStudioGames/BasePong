package com.valhalla.input;

import com.valhalla.engine.input.KeyInput;

public class Player1Input implements PlayerInput {

    @Override
    public boolean holdingUp() {
        return KeyInput.wHeld();
    }

    @Override
    public boolean holdingDown() {
        return KeyInput.sHeld();
    }
}
