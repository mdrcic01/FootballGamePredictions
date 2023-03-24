package model;

import java.math.BigInteger;

public class Match {
    private final Team team1;
    private final Team team2;
    private final BigInteger score1;
    private final BigInteger score2;

    public Match(Team team1, Team team2, BigInteger score1, BigInteger score2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public BigInteger getScore1() {return score1;}

    public BigInteger getScore2() {
        return score2;
    }

}
