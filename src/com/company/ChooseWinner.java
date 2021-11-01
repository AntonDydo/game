package com.company;
public class ChooseWinner {
    public static String winner(String[] args, int computerMove, int personMove) {
        String winner;
        if (computerMove == personMove) {
            return "draw";
        }
        if (Math.abs(computerMove - personMove) > (args.length - 1) / 2) {
            winner = args[Math.min(computerMove, personMove)];
        } else winner = args[Math.max(computerMove, personMove)];
        if (winner.equals(args[personMove])) {
            return "win";
        } else return "lose";
    }
}
