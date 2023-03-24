package model;

import enumerations.Morale;
import enumerations.Performance;
import enumerations.Position;
import enumerations.Specialization;

public class Player {

    private final String name;
    private final Double attack;
    private final Double defence;
    private final Morale morale;
    private final Double trainingAttendance;
    private final Performance trainingPerformance;
    private final Specialization specialization;
    private final Double attackContribution;
    private final Double defenceContribution;
    private Position position;

    public Player(String name,
                  Double attack,
                  Double defence,
                  Morale morale,
                  Double trainingAttendance,
                  Performance trainingPerformance,
                  Specialization specialization) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.morale = morale;
        this.trainingAttendance = trainingAttendance;
        this.trainingPerformance = trainingPerformance;
        this.specialization = specialization;

        this.attackContribution = (attack * morale.getValue() * trainingAttendance * trainingPerformance.getValue()) / 10;
        this.defenceContribution = (defence * morale.getValue() * trainingAttendance * trainingPerformance.getValue()) / 10;
    }

    public String getName() {
        return name;
    }

    public Double getAttackContribution() {
        return attackContribution;
    }

    public Double getDefenceContribution() {
        return defenceContribution;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defence=" + defence +
                ", morale=" + morale +
                ", trainingAttendance=" + trainingAttendance +
                ", trainingPerformance=" + trainingPerformance +
                ", specialization=" + specialization +
                ", attackContribution=" + attackContribution +
                ", defenceContribution=" + defenceContribution +
                ", position=" + position +
                '}';
    }

}
