import comparators.PlayerComparator;
import enumerations.Morale;
import enumerations.Performance;
import enumerations.Position;
import enumerations.Specialization;
import model.Match;
import model.Player;
import model.Team;

import java.io.FilterOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        Team team1 = inputTeam(userInput);
        team1.setPlayers(insertPlayers(userInput));

        Team team2 = inputTeam(userInput);
        team2.setPlayers(insertPlayers(userInput));

        team1.setPlayers(assignPositions(team1));
        team2.setPlayers(assignPositions(team2));

        Match match = simulateMatch(team1, team2);

        System.out.println("REZULTATI UTAKMICE");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%s %d:%d %s%n", match.getTeam1().getName(), match.getScore1(), match.getScore2(), match.getTeam2().getName());
    }

    private static Match simulateMatch(Team team1, Team team2) {
        team1.calculateScores();
        team2.calculateScores();

        BigDecimal scoreUnparsedTeam1 = BigDecimal.valueOf(team1.getAttackScore() - team2.getDefenceScore());
        BigDecimal scoreUnparsedTeam2 = BigDecimal.valueOf(team2.getAttackScore() - team1.getDefenceScore());

        BigInteger score1 = (scoreUnparsedTeam1.setScale(0, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO) >= 0)
                ? scoreUnparsedTeam1.setScale(1, RoundingMode.HALF_DOWN).toBigInteger()
                : BigInteger.ZERO;
        BigInteger score2 = (scoreUnparsedTeam2.setScale(0, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO) >= 0)
                ? scoreUnparsedTeam2.setScale(1, RoundingMode.HALF_DOWN).toBigInteger()
                : BigInteger.ZERO;

        return new Match(team1,
                         team2,
                         score1,
                         score2);
    }

    private static List<Player> assignPositions(Team team) {
        List<Player> workingList = team.getPlayers();
        List<Player> positionedPlayers = new ArrayList<>();

        Player goalkeeper = workingList.stream().max(PlayerComparator::compareGoalkeeper).get();
        workingList.remove(goalkeeper);
        goalkeeper.setPosition(Position.GOALKEEPER);

        List<Player> midfielders = workingList.stream()
                                              .sorted(PlayerComparator::compareMidfielders)
                                              .limit(team.getMidfielderNumber()).toList();
        midfielders.forEach(workingList::remove);
        midfielders.forEach(player -> player.setPosition(Position.MIDFIELDER));

        List<Player> attackers = workingList.stream().sorted(PlayerComparator::compareAttackers).limit(team.getAttackerNumber()).toList();
        attackers.forEach(workingList::remove);
        attackers.forEach(player -> player.setPosition(Position.ATTACKER));

        List<Player> defenders = workingList;
        defenders.forEach(player -> player.setPosition(Position.DEFENDER));

        positionedPlayers.add(goalkeeper);
        positionedPlayers.addAll(defenders);
        positionedPlayers.addAll(midfielders);
        positionedPlayers.addAll(attackers);

        return positionedPlayers;
    }

    private static List<Player> insertPlayers(Scanner userInput) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            players.add(inputPlayer(userInput, i));
        }

        return players;
    }

    private static Player inputPlayer(Scanner userInput, int i) {
        //System.out.printf("\nInsert player %d\n", i);
        //System.out.println("Insert name: ");
        String tmpName = userInput.nextLine();
        //System.out.println("Insert attack (1-10): ");
        Double tmpAttack = userInput.nextDouble();
        //System.out.println("Insert defence (1-10): ");
        Double tmpDefence = userInput.nextDouble();
        userInput.nextLine();
        //System.out.println("Insert morale (HIGH, MID, LOW): ");
        Morale tmpMorale = Morale.valueOf(userInput.nextLine().toUpperCase());
        //System.out.println("Insert training attendance: ");
        //System.out.println("Attended: ");
        long tmpTrainingAttendance = userInput.nextLong();
        //System.out.println("Total trainings: ");
        long tmpTrainingsTotal = userInput.nextLong();
        userInput.nextLine();
        //System.out.println("Training performance (HIGH, MID, LOW)");
        Performance tmpPerformance = Performance.valueOf(userInput.nextLine().toUpperCase());
//        System.out.print(
//                "\nSpecial trait: \n [1] FK specialist\n [2] True leader\n [3] GK specialist\n [4] Playmaker\n [5] Coaches son\n [6] None");
//        System.out.println("Choose trait: ");
        Specialization tmpSpecialization;
        switch (userInput.nextInt()) {
            case 1 -> tmpSpecialization = Specialization.FK_SPECIALIST;
            case 2 -> tmpSpecialization = Specialization.TRUE_LEADER;
            case 3 -> tmpSpecialization = Specialization.GK_SPECIALIST;
            case 4 -> tmpSpecialization = Specialization.PLAYMAKER;
            case 5 -> tmpSpecialization = Specialization.COACHES_SON;
            default -> tmpSpecialization = Specialization.NONE;
        }
        userInput.nextLine();

        return new Player(tmpName,
                          tmpAttack,
                          tmpDefence,
                          tmpMorale,
                          (double) tmpTrainingAttendance / tmpTrainingsTotal,
                          tmpPerformance,
                          tmpSpecialization);
    }

    private static Team inputTeam(Scanner userInput) {
        //System.out.println("Input team name: ");
        String tmpName = userInput.nextLine();
        //System.out.println("Input formation: ");
        String tmpFormation = userInput.nextLine();

        return new Team(tmpName, tmpFormation);
    }

}