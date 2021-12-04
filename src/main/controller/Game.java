package main.controller;

import main.model.Board;
import main.model.Card;
import main.model.Play;
import main.model.Player;

import java.util.*;

public class Game {

    final Player[] players;
    final Map<Player, List<Play>> winnings;
    final Board board;

    int currPlayerNum;
    int passCount;
    int playersFinished;
    boolean[] calledGrand;

    public static Set<Card> DECK;

    static {
        DECK = new HashSet<>(Card.NormalCard.NORMAL_CARDS);
        DECK.addAll(Card.SpecialCard.SPECIAL_CARDS);
    }

    public Game(Player[] players) {
        this.players = players;
        this.winnings = new HashMap<>();
        Arrays.stream(players).forEach(player -> this.winnings.put(player, new ArrayList<>()));
        this.board = new Board();
        this.currPlayerNum = 0;
        this.calledGrand = new boolean[this.players.length];
        this.passCount = 0;
    }

    void distributePartial() {

    }

    void distributeRemaining() {

    }

    private void sendBoard() {
        Arrays.stream(players).forEach(player -> player.showBoard(board.getPlayerView(player)));
    }
    private void sendResult(Player[] winningPlayers, int points) {
        Arrays.stream(players).forEach(player -> player.showResult(winningPlayers, points));
    }

    void playGame() {
        initialPhase();
        sendBoard();
        beginNextTrick();
        while (!GameLogic.gameOver(playersFinished)) {
            doTurn();
            sendBoard();
        }
        //TODO: sendResult();
    }

    private void initialPhase() {
        distributePartial();
        Arrays.stream(players).forEach(Player::showHand);

        for (int i = 0, playersLength = players.length; i < playersLength; i++) {
            Player player = players[i];
            if (player.getGrandCall()) {
                calledGrand[i] = true;
            }
        }

        distributeRemaining();
        Arrays.stream(players).forEach(Player::showHand);

        for (Player player : players) {
            for (Map.Entry<Player, Card> entry : player.getTransfer().entrySet()) {
                entry.getKey().getHand().addCard(entry.getValue());
                player.getHand().removeCard(entry.getValue());
            }
        }
    }

    void beginNextTrick() {
        board.beginBewTrick();
        passCount = 0;
    }

    void doTurn() {
        Player currPlayer = players[currPlayerNum];
        Play play = Play.NONE;
        if (!currPlayer.getHand().isEmpty()) {
            play = currPlayer.getPlay();
            while (!GameLogic.isValidMove(board.getLastPlay(), play)) {
                play = currPlayer.getPlay();
            }
            if (!(play.equals(Play.PASS) || play.equals(Play.EMPTY))) {
                board.addPlay(play);
                currPlayer.getHand().removePlay(play);
                passCount = 0;
                if (currPlayer.getHand().isEmpty()) {
                    playersFinished++;
                }
            } else {
                passCount++;
            }
        } else {
            passCount++;
        }

        currPlayerNum = GameLogic.nextPlayerNum(currPlayerNum, play);

        if (passCount == 3) {
            Player winningPlayer = players[currPlayerNum];
            GameLogic.completeTrick(board.getTrickPlays(), winningPlayer, winnings);
            beginNextTrick();
        }
    }

}
