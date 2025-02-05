package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.SQLite.SQL;

public class Main
{
    public static void main(String[] args)
    {
        //SQL bd= SQL.getInstance();
        Game paooGame = new Game("PaooGame", 800, 600);
        paooGame.StartGame();
    }
}
