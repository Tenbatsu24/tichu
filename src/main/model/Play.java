package main.model;

import java.util.*;
import java.util.stream.Collectors;

public class Play {

    public static Play PASS;
    public static Play EMPTY;
    public static Play NONE;
    public static Play DOG;

    static {
        PASS = new Play(Collections.emptySet());
        EMPTY = new Play(Collections.emptySet());
        NONE = new Play(Collections.emptySet());
        DOG = new Play(Set.of(Card.SpecialCard.DOG));
    }

    enum PlayType {
        SINGLE, PAIR, CONSECUTIVE_PAIR, TRIPLE, FULL_HOUSE, STRAIGHT, BOMB_SUIT, BOMB_STRAIGHT, INVALID;
    }

    Set<Card> cardSet;
    PlayType type;

    public Play(Set<Card> cardSet) {
        this.cardSet = cardSet;

        Map<Card.NormalCard.Rank, Long> countOfRanks = cardSet.stream()
                .filter(card -> card instanceof Card.NormalCard)
                .collect(Collectors.groupingBy(card -> ((Card.NormalCard) card).rank, Collectors.counting()));



    }

    public boolean has(Card card) {
        return cardSet.contains(card);
    }

    public Set<Card> cards() {
        return cardSet;
    }

    public int length() {
        return cardSet.size();
    }

}
