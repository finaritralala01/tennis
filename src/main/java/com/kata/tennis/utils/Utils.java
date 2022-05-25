package com.kata.tennis.utils;

import static com.kata.tennis.utils.Constantes.SCORE_DIFFRENCE;
import static com.kata.tennis.utils.Constantes.SCORE_MAX_SET;

public class Utils {

  public static boolean scoreDifference(int scoreA, int scoreB) {
    int difference = scoreA - scoreB;
    return difference < 0 ? (difference * (-1)) >= SCORE_DIFFRENCE : difference >= SCORE_DIFFRENCE;
  }

  public static boolean scoreSuperieurouEgualMax(int scoreA, int scoreB) {
    return scoreA >= SCORE_MAX_SET || scoreB >= SCORE_MAX_SET;
  }

  private Utils() {
  }
}
