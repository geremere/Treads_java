package diceGame;

public class Player extends Thread implements Comparable<Player> {
    private int points;
    private String name;
    public static GameOfDice game;
    private int countOfWins;
    private boolean isRolled = false;

    public Player(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void refreshStatusRoll(boolean isrolle) {
        isRolled = isrolle;
    }

    public int getCountOfWins() {
        return countOfWins;
    }

    public void winOnRound() {
        countOfWins++;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void run() {
        while (!game.isEnd()) {
            if (game.isRoundOn() && !isRolled)
                game.playerRoll(this);
        }
    }


    @Override
    public String toString() {
        return name + ".  Points in this round: " + points;
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(player.getCountOfWins(), countOfWins);
    }
}
