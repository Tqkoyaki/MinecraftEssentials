package com.takoyaki.essentials.utils;

public enum CommandPermission {
    // The permissions levels for commands
    ALL(0),
    MODERATOR(1),
    GAMEMASTER(2),
    ADMIN(3),
    OWNER(4);

    private int value;

    private CommandPermission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
