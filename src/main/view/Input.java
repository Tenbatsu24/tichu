package main.view;

import main.model.Card;
import main.model.Player;

import java.util.Map;
import java.util.Set;

public interface Input {

    Set<Card> getPlay();
    Map<Player, Card> getCardSwap();

}
