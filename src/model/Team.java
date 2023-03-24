package model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Team {
    private final String name;
    private final String formation;
    private List<Player> players;
    private final Integer defenderNumber;
    private final Integer midfielderNumber;
    private final Integer attackerNumber;
    private Double attackScore;
    private Double defenceScore;

    public Team(String name, String formation) {
        this.name = name;
        this.formation = formation;

        List<Integer> formationDelimited = Arrays.stream(formation.split("-")).map(Integer::valueOf).toList();

        this.defenderNumber = formationDelimited.get(0);
        this.midfielderNumber = formationDelimited.get(1);
        this.attackerNumber = formationDelimited.get(2);

        this.attackScore = 0D;
        this.defenceScore = 0D;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getMidfielderNumber() {
        return midfielderNumber;
    }

    public Integer getAttackerNumber() {
        return attackerNumber;
    }

    public Double getAttackScore() {
        return attackScore;
    }

    public Double getDefenceScore() {
        return defenceScore;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", formation='" + formation + '\'' +
                ", players=" + players +
                '}';
    }
    
    public void calculateScores() {
        for (Player player : this.players) {

            switch(player.getPosition()) {
                case GOALKEEPER -> this.defenceScore += 1.2*player.getDefenceContribution();
                case DEFENDER -> {
                    this.defenceScore += 0.9*player.getDefenceContribution();
                    this.attackScore += 0.2* player.getAttackContribution();
                }
                case MIDFIELDER -> {
                    this.defenceScore += 0.6*player.getDefenceContribution();
                    this.attackScore += 0.6* player.getAttackContribution();
                }
                case ATTACKER -> {
                    this.defenceScore += 0.2*player.getDefenceContribution();
                    this.attackScore += 0.9* player.getAttackContribution();
                }
            }
        }

        for (Player player : this.players) {

            switch (player.getSpecialization()) {
                case NONE -> {}
                case TRUE_LEADER -> {
                    this.defenceScore *= 1.10;
                    this.attackScore *= 1.10;
                }
                case PLAYMAKER -> this.attackScore *= 1.20;
            }
        }

        for (Player player : this.players) {

            switch (player.getSpecialization()) {
                case NONE -> {}
                case FK_SPECIALIST -> this.attackScore += 1;
                case GK_SPECIALIST -> this.defenceScore += 1;
                case COACHES_SON -> {
                    this.defenceScore -= 0.5;
                    this.attackScore -= 0.5;
                }
            }
        }
    }

}
