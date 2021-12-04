package main.view;

import main.model.Board;

public interface Output {

    void showSmallTichu();
    void showAll();
    void showBoard(Board.PlayerView playerView);

}
