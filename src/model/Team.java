package model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Team {
    private String name;
    private String formation;
    private List<Player> players;
    private Integer defenderNumber;
    private Integer midfielderNumber;
    private Integer attackerNumber;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getDefenderNumber() {
        return defenderNumber;
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

    public void setAttackScore(Double attackScore) {
        this.attackScore = attackScore;
    }

    public Double getDefenceScore() {
        return defenceScore;
    }

    public void setDefenceScore(Double defenceScore) {
        this.defenceScore = defenceScore;
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
