package model;

import enumerations.Morale;
import enumerations.Performance;
import enumerations.Position;
import enumerations.Specialization;

public class Player {

    private String name;
    private Double attack;
    private Double defence;
    private Morale morale;
    private Double trainingAttendance;
    private Performance trainingPerformance;
    private Specialization specialization;
    private Double attackContribution;
    private Double defenceContribution;
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

    public void setName(String name) {
        this.name = name;
    }

    public Double getAttack() {
        return attack;
    }

    public Double getAttackContribution() {
        return attackContribution;
    }

    public void setAttackContribution(Double attackContribution) {
        this.attackContribution = attackContribution;
    }

    public Double getDefenceContribution() {
        return defenceContribution;
    }

    public void setDefenceContribution(Double defenceContribution) {
        this.defenceContribution = defenceContribution;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getDefence() {
        return defence;
    }

    public void setDefence(Double defence) {
        this.defence = defence;
    }

    public Morale getMorale() {
        return morale;
    }

    public void setMorale(Morale morale) {
        this.morale = morale;
    }

    public Double getTrainingAttendance() {
        return trainingAttendance;
    }

    public void setTrainingAttendance(Double trainingAttendance) {
        this.trainingAttendance = trainingAttendance;
    }

    public Performance getTrainingPerformance() {
        return trainingPerformance;
    }

    public void setTrainingPerformance(Performance trainingPerformance) {
        this.trainingPerformance = trainingPerformance;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
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
