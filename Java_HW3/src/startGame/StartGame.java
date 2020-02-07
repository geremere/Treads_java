package startGame;

import diceGame.Comentator;
import diceGame.GameOfDice;
import diceGame.Player;

import java.util.Scanner;

public final class StartGame {

    static void Start(int countOfPlayers, int countOfDice, int countOfWins) {
        Comentator comentator = new Comentator();
        Player[] players = new Player[countOfPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player" + (i));
        }
        GameOfDice dice = new GameOfDice(countOfDice, countOfWins, players);
        Comentator.game = dice;
        Player.game = dice;
        System.out.println("welcome to the club body");
        comentator.start();
        for (int i = 0; i < players.length; i++) {
            players[i].start();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if(Integer.parseInt(args[0])<2 ||Integer.parseInt(args[0])>6)
        {
            System.out.println("Input count of players in the border from 2 to 6");
            return;
        }
        if(Integer.parseInt(args[1])<2 ||Integer.parseInt(args[1])>5)
        {
            System.out.println("Input count of dice in the border from 2 to 5");
            return;
        }
        if(Integer.parseInt(args[2])<1 ||Integer.parseInt(args[2])>100)
        {
            System.out.println("Input count of winning rounds to win the game in the border from 1 to 100");
            return;
        }
        Start(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
    }

}
