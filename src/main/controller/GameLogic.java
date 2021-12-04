package main.controller;

import main.model.Card;
import main.model.Play;
import main.model.Player;

import java.util.List;
import java.util.Map;

public class GameLogic {

    static int nextPlayerNum(int currPlayerNum, Play play) {
        return (play.equals(Play.DOG)?(currPlayerNum+2)%4:(currPlayerNum+1)%4);
    }

    static boolean gameOver(int playersFinished) {
        return playersFinished == 3;
    }

    static boolean isValidMove(Play playToBeat, Play play) {
        int toBeatLength = playToBeat.length();
        int playLength = play.length();

        if (toBeatLength == playLength) {

        } else if (toBeatLength < playLength) {

        } else {

        }
        return false;
    }

    public static void completeTrick(List<Play> trickPlays, Player player, Map<Player, List<Play>> winnings) {
        Play winningHand = trickPlays.get(trickPlays.size()-1);
        Player pointTo;
        if (winningHand.has(Card.SpecialCard.DRAGON)) {
            pointTo = player.getTransferDragonTo();
        } else {
            pointTo = player;
        }
        winnings.get(pointTo).addAll(trickPlays);
    }
}
