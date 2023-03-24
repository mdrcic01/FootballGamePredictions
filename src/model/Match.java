package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Match {
    private Team team1;
    private Team team2;
    private BigInteger score1;
    private BigInteger score2;

    public Match(Team team1, Team team2, BigInteger score1, BigInteger score2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public BigInteger getScore1() {
        return score1;
    }

    public void setScore1(BigInteger score1) {
        this.score1 = score1;
    }

    public BigInteger getScore2() {
        return score2;
    }

    public void setScore2(BigInteger score2) {
        this.score2 = score2;
    }

}
