package com.mthree.academy.c458.team3.SeanMeaney.JavaBasics.RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner aScanner = new Scanner(System.in);
        int draws;
        int playerWins;
        int computerWins;
        int roundNums;
        int playerMove;
        boolean playing = true;

        while (playing) {

            //get round count
            System.out.println("How many rounds do you want to play (1-10)?");
            try { //check is int
                roundNums = Integer.parseInt(aScanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Quitting.");
                return;
            }

            //check roundNums is in range
            if (roundNums > 10 || roundNums < 1) {
                System.out.println("Outside of range. Quitting.");
                return;
            }

            draws = 0;
            playerWins = 0;
            computerWins = 0;

            //iterate through rounds
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
                    return;
                }

                //run a round with player move
                var currentRound = new GameRound(playerMove);

                //decare round winner
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

            //decare overall winner
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
}

class GameRound {
    /*
    moves are given by:

    int | string
    ----|-------
    0   | rock
    1   | paper
    2   | scissors

    game results are given by:

    Degenerate Moves | gameState        | result | winner
    (player/computer)| (player-computer)|        |
    -----------------|------------------|--------|---------
    rock/rock,       | 0                | 0      | draw
    paper/paper,     |                  |        |
    scissor/scissor  |                  |        |
    -----------------|------------------|--------|---------
    rock/scissor,    | -2,1             | 1      | player
    paper/rock,      |                  |        |
    scissor/paper    |                  |        |
    -----------------|------------------|--------|---------
    rock/paper,      | -1,2             | 2      | computer
    paper/scissor,   |                  |        |
    scissor/rock     |                  |        |
    -----------------|------------------|--------|---------
     */

    int playerMove;
    int computerMove;
    public int result;
    public String winner;
    private final Random RNG = new Random();

    public GameRound() {}

    //play a round of rock paper scissors given player's move
    public GameRound(int playerMove) {
        this.playerMove = playerMove;
        this.computerMove = generateRandomMove();
        int gameState = playerMove - computerMove;
        this.result = mapGameStateToResult(gameState);
        this.winner = mapResultToWinner();
    }

    //play a round of rock paper scissors given player and computer moves
    public GameRound(int playerMove,int computerMove) {
        this.playerMove = playerMove;
        this.computerMove = computerMove;
        int gameState = playerMove - computerMove;
        this.result = mapGameStateToResult(gameState);
        this.winner = mapResultToWinner();
    }

    //Return a string describing round results
    public String formatResult() {
        return switch (result) {
            case 1 -> //player wins
                    "Player won, " + mapMoveToString(playerMove) +
                    " beats " + mapMoveToString(computerMove);
            case 2 -> //computer wins
                    "Computer won, " + mapMoveToString(computerMove) +
                    " beats " + mapMoveToString(playerMove);
            default -> //draw
                    "Draw, both chose " + mapMoveToString(this.playerMove);
        };
    }

    private int generateRandomMove(){
        return this.RNG.nextInt(0,3);
    }

    //return move name as string
    private String mapMoveToString(int move){
        return switch (move) {
            case 0 -> "rock";
            case 1 -> "paper";
            case 2 -> "scissors";
            default -> null;
        };
    }

    //return result id
    private int mapGameStateToResult(int gameState){
        return switch (gameState) {
            case -2,1 -> 1; //player win
            case -1,2 -> 2; //computer win
            default -> 0; //draw
        };
    }

    //return winner as string
    private String mapResultToWinner(){
        return switch (result) {
            case 0 -> "draw";
            case 1 -> "player";
            case 2 -> "computer";
            default -> null;
        };
    }
}
