package diceGame;

import java.util.Arrays;
import java.util.Random;

public class GameOfDice {
    private int mCountOfWins;
    private static int countOfDice;
    private boolean isSay = false;
    private boolean roundOn = true;
    private boolean isEnded = false;
    private Player[] players;
    private Player lastPlayedRoll = null;
    private Player leader = null;
    private int countOfRolls = 0;

    public GameOfDice(int countOfDice, int countOfWins, Player[] players) {
        this.countOfDice = countOfDice;
        this.mCountOfWins = countOfWins;
        this.players = players;
    }

    public synchronized void commentatorSay() {
        while (!isSay) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if (isRoundOn()) {
            System.out.println(lastPlayedRoll.toString() + "  Current leader: " + leader.toString());
            isSay = false;
            notifyAll();
        }
    }

    public synchronized void resultOfRound() {
        countOfRolls = 0;
        System.out.println(lastPlayedRoll.toString());
        for (Player p : players)
            p.refreshStatusRoll(false);
        leader.winOnRound();
        System.out.println();

        if (leader.getCountOfWins() == mCountOfWins) {
            isEnded = true;
            System.out.println("Congratulations to the winner " + leader.getPlayerName());
            Arrays.sort(players);
            System.out.println();
            System.out.println("Rating of players: ");
            for (Player p : players) {
                System.out.println(p.toString() + " Count of wins: " + p.getCountOfWins());
            }
        } else
            System.out.println("The winner of the round: " + leader.toString() + ". Count of wins: " + leader.getCountOfWins());

        System.out.println();
        leader = null;
        if (!isEnd()) {
            isSay = false;
            roundOn = true;
            notifyAll();
        }
    }

    public synchronized void playerRoll(Player player) {
        while (isSay) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        player.setPoints(Randomize.RandomRoll());
        lastPlayedRoll = player;
        player.refreshStatusRoll(true);
        countOfRolls++;
        if (player.getPoints() == countOfDice * 6 || countOfRolls == players.length) {
            roundOn = false;
        }
        if (leader == null || player.getPoints() > leader.getPoints()) {
            leader = player;
        }
        isSay = true;
        notify();
    }

    public synchronized boolean isEnd() {
        return isEnded;
    }

    public synchronized boolean isRoundOn() {
        return roundOn;
    }


    static class Randomize {

        static Random rnd = new Random();

        static int RandomRoll() {
            return rnd.nextInt(6 * countOfDice - countOfDice + 1) + countOfDice;
        }
    }
}
