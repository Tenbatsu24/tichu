package main.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Play> trickPlays;
    private Play lastPlay;

    public Board() {
        this.trickPlays = new ArrayList<>(List.of(Play.NONE));
        this.lastPlay = Play.NONE;
    }

    public List<Play> getTrickPlays() {
        return trickPlays;
    }

    public Play getLastPlay() {
        return lastPlay;
    }

    public static class PlayerView {

        private final List<Play> plays;
        private final Player player;

        public PlayerView(List<Play> plays, Player player) {
            this.plays = plays;
            this.player = player;
        }

        public List<Play> getPlays() {
            return plays;
        }

        public Player getPlayer() {
            return player;
        }
    }

    public void beginBewTrick() {
        this.trickPlays.clear();
        this.trickPlays.add(Play.NONE);
        this.lastPlay = Play.NONE;
    }

    public void addPlay(Play play) {
        this.trickPlays.add(play);
    }

    public PlayerView getPlayerView(Player player) {
        return new PlayerView(this.trickPlays, player);
    }

}
