package main.model;

import java.util.Map;

public abstract class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public abstract Play getPlay();
    public abstract boolean getGrandCall();
    public abstract Map<Player, Card> getTransfer();
    public abstract Player getTransferDragonTo();

    public abstract void showHand();
    public abstract void showBoard(Board.PlayerView playerView);
    public abstract void showResult(Player[] winningPlayers, int points);
}
