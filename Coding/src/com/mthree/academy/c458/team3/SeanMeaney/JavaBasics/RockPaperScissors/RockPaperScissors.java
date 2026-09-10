package com.mthree.academy.c458.team3.SeanMeaney.JavaBasics.RockPaperScissors;

import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        boolean playing = true;
        int roundNums = 0;
        Scanner aScanner = new Scanner(System.in);

        while (playing) {

            //get round count
            System.out.println("How many rounds do you want to play (1-10)?");
            try { //check is int
                roundNums = Integer.parseInt(aScanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Quitting.");
                System.exit(0);
            }

            //check roundNums is in range
            if (roundNums > 10 || roundNums < 1) {
                System.out.println("Outside of range. Quitting.");
                System.exit(0);
            }

            //play roundNums rounds
            playGame(roundNums);

            System.out.println(
                    "Play again?\n"+
                    "(Yes/No)"
            );

            if (!aScanner.nextLine().equals("Yes")) {
                playing = false;
                System.out.println("Thanks for playing!");
            }
        }
    }

    //play a game of rock paper scissors with user for roundNums rounds
    private static void playGame(int roundNums){
        Scanner aScanner = new Scanner(System.in);
        int draws = 0;
        int playerWins = 0;
        int computerWins = 0;
        int playerMove = 0;

        //iterate through roundNums rounds
        for (int i = 0; i < roundNums; i++) {
            System.out.println("Round " + (i + 1) + ":");
            System.out.println(
                    "Your move. Enter a number:\n"+
                            "1. Rock\n"+
                            "2. Paper\n"+
                            "3. Scissors"
            );

            //get player move
            try {
                playerMove = Integer.parseInt(aScanner.nextLine()) - 1;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Quitting.");
                System.exit(0);
            }

            //use player's move to generate single round of rock paper scissors
            //generates result of round
            var currentRound = new GameRound(playerMove);

            //declare round winner
            System.out.println("||" + currentRound.formatResult() + "||\n");

            //tally result
            switch (currentRound.result) {
                case 1:
                    playerWins++;
                    break;
                case 2:
                    computerWins++;
                    break;
                default:
                    draws++;
            }
        }

        //tell user who won
        declareWinner(playerWins,computerWins);
    }

    //output winner message to player using number of wins
    private static void declareWinner(int playerWins, int computerWins) {
        if (playerWins > computerWins) { //player wins
            System.out.println(
                    "Player wins overall with " + playerWins + " : " + computerWins
            );
        } else if (playerWins < computerWins) { //computer wins
            System.out.println(
                    "Computer wins with overall " + playerWins + " : " + computerWins
            );
        } else { //draw (default)
            System.out.println(
                    "Ends in a draw with " + playerWins + " : " + computerWins
            );
        }
    }
}

