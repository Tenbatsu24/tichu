package main.model;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Card implements Comparable<Card> {

    int points;

    public Card(int points) {
        this.points = points;
    }

    int getPoints() {
        return 0;
    }

    @Override
    public int compareTo(@NotNull Card o) {
        return ((NormalCard) this).rank.value - ((NormalCard) o).rank.value;
    }

    public static class NormalCard extends Card {

        enum Rank {

            TWO(2, 0), THREE(3, 0), FOUR(4, 0), FIVE(5, 5),
            SIX(6, 0), SEVEN(7, 0), EIGHT(8, 0), NINE(9, 0),
            TEN(10, 10), JACK(11, 0), QUEEN(12, 0), KING(13, 10),
            ACE(14, 0);

            final int value;
            final int points;

            Rank(int value, int points) {
                this.value = value;
                this.points = points;
            }
        }

        enum Suit {
            STAR, JADE, PAGODA, SWORD;
        }

        public static Set<Card> NORMAL_CARDS;

        static {
            NORMAL_CARDS = new HashSet<>();
            Arrays.stream(Rank.values()).forEach((rank) ->
                    Arrays.stream(Suit.values()).forEach(suit -> NORMAL_CARDS.add(new NormalCard(rank, suit))));
        }

        Rank rank;
        Suit suit;

        private NormalCard(Rank rank, Suit suit) {
            super(rank.points);
            this.rank = rank;
            this.suit = suit;
        }



    }

    public static class SpecialCard extends Card {

        enum Special {
            DRAGON(25), PHOENIX(-25), DOG(0), MAH_JONG(0);

            final int value;

            Special(int points) {
                value = points;
            }

        }

        public static final SpecialCard DOG = new SpecialCard(Special.DOG);
        public static final SpecialCard DRAGON = new SpecialCard(Special.DRAGON);
        public static final SpecialCard PHOENIX = new SpecialCard(Special.PHOENIX);
        public static final SpecialCard MAH_JONG = new SpecialCard(Special.MAH_JONG);

        public static Set<Card> SPECIAL_CARDS = Set.of(DOG, DRAGON, PHOENIX, MAH_JONG);

        private SpecialCard(Special type) {
            super(type.value);
        }
    }

}
