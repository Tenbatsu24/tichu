package main.model;

import java.util.HashSet;
import java.util.Set;

public class Hand {

    Set<Card> hand;

    public Hand() {
        this(new HashSet<>());
    }

    public Hand(Set<Card> cards) {
        this.hand = cards;
    }

    public Set<Card> getHand() {
        return this.hand;
    }

    public void removePlay(Play play) {
        this.hand.removeAll(play.cards());
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void removeCard(Card card) {
        this.hand.remove(card);
    }

    public void resetHand() {
        this.hand.clear();
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }
}
