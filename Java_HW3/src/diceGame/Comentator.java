package diceGame;

public class Comentator extends Thread {

    public static GameOfDice game;

    @Override
    public void run() {
        while (!game.isEnd()) {
            if (game.isRoundOn())
                game.commentatorSay();
            else
                game.resultOfRound();
        }
    }
}
