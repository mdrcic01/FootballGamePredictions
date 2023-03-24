package comparators;

import model.Player;

public class PlayerComparator {

    public static int compareGoalkeeper(Player p1, Player p2) {
        return (p1.getDefenceContribution() >= p2.getDefenceContribution()) ? 1 : -1;
    }

    public static int compareMidfielders(Player p1, Player p2) {
        Double diff1 = p1.getAttackContribution() - p1.getDefenceContribution();
        Double diff2 = p2.getAttackContribution() - p2.getDefenceContribution();
        return (diff1 >= diff2) ? 1 : -1;
    }

    public static int compareAttackers(Player p1, Player p2) {
        return (p1.getAttackContribution() <= p2.getAttackContribution()) ? 1 : -1;
    }

}
