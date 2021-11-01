package com.company;
import java.util.*;
public class Main {

    public static boolean validate(String input, String[] args) {
        if (input != null &&
            Integer.parseInt(input) <= args.length &&
            Integer.parseInt(input) >= 0
            || Objects.equals(input, "?")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Set<String> uniqueArgs = new HashSet<>(Arrays.asList(args));
        if (uniqueArgs.size() != args.length || args.length % 2 == 0 || args.length == 1) {
            System.out.println("Please,enter odd number of moves,don't enter one move, don't enter duplicate!");
            return;
        }
        Key key = new Key(args);
        Table table = new Table(args);
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            key.generateAll();
            key.showHMAC();
            System.out.println("Available moves: ");
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + 1 + " - " + args[i]);
            }
            System.out.print("""
                    0 - exit\s
                    ? - help
                    Enter your move:\s""");
            input = in.next();
            try {
                if (validate(input, args)) {
                    if (Objects.equals(input, "?")) {
                        table.showTable();
                    }
                    if (input.equals("0")) {
                        return;
                    }
                    if (Integer.parseInt(input) <= args.length &&
                        Integer.parseInt(input) >= 0) {
                        System.out.println("Your move: " + args[Integer.parseInt(input) - 1] + "\n" +
                                           "PC move: " + args[key.pcMove] + "\n" +
                                           ChooseWinner.winner(args, key.pcMove, Integer.parseInt(input) - 1));
                        key.showKey();
                        System.out.println("_________________________________________________________________________");
                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                if (Objects.equals(input, "?")) {
                    table.showTable();
                } else {
                    System.out.println("""
                            Please, follow this simple rules:\040
                            1. Select your move by entering a number not a word.
                            2. Enter only existing symbols.\s
                            3. Don't leave empty input.
                            4. Don't enter extra space.
                            Lets try again""");
                }
            }
        }
    }
}
