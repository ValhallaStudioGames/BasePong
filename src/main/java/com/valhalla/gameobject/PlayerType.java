package com.valhalla.gameobject;

import com.valhalla.input.Player1Input;
import com.valhalla.input.Player2Input;
import com.valhalla.input.PlayerInput;

public enum PlayerType {

    PLAYER1(new Player1Input()),
    PLAYER2(new Player2Input());

    public final PlayerInput inputMethod;

    PlayerType(PlayerInput inputMethod) {
        this.inputMethod = inputMethod;
    }
}
