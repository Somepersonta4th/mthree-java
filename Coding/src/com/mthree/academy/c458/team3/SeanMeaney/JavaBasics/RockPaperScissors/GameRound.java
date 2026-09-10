package com.mthree.academy.c458.team3.SeanMeaney.JavaBasics.RockPaperScissors;

import java.util.Random;

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
