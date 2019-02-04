package edu.cnm.deepdive.craps.controller;

import edu.cnm.deepdive.craps.model.Game;
import edu.cnm.deepdive.craps.model.State;
import java.util.Random;
import java.util.Scanner;

public class Craps {

  private Random rng;
  private Game game;// looking into our jar file


  public static void main(String[] args) {
    Craps craps = new Craps();
    if (args.length > 0) {

      int numTrials = Integer.parseInt(args[0]);
      for (int i = 0; i < numTrials; i++) {
        craps.play(false);
      }
 craps.displayTally();
    } else {

//    Craps craps = new Craps();
//    craps.play(true);
      try (Scanner scanner = new Scanner(System.in)) {
        do {
          craps.play(true);
        } while (craps.playAgain(scanner));
      }
    }
  }
  public Craps() {
      rng = new Random();
      game = new Game(rng);

    }

    public void play ( boolean verbose){

      game.reset();
      State state = game.play();
      if (verbose) {

        for (int[] diceRoll : game.getRolls()) {
          System.out.printf("%d:%d%n", diceRoll[0], diceRoll[1]);
        }
        System.out.printf("Outcome: %s%n", state);
        displayTally();
      }

    }

  private void displayTally() {
    long wins = game.getWins();
    long total = wins + game.getLosses();
    double percentage = 100.0 * wins / total;
    System.out.printf("Winning percentage = (%d/%d) = %.2f%n", wins, total, percentage);
  }

  private boolean playAgain (Scanner scanner){
      System.out.print("Play again (y/n)?");
      String input;

      do {
        input = scanner.nextLine().trim().toLowerCase();
      }
      while (input.isEmpty());

      return input.charAt(0) == 'y';
    }
  }

