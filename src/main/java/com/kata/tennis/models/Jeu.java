package com.kata.tennis.models;

import static com.kata.tennis.utils.Constantes.SCORE;

public class Jeu {
  private int score;
  private boolean avantage;
  private boolean egalite;

  public Jeu(int score, boolean avantage, boolean egalite) {
    this.score = score;
    this.avantage = avantage;
    this.egalite = egalite;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setAvantage(boolean avantage) {
    this.avantage = avantage;
  }

  public void setEgalite(boolean egalite) {
    this.egalite = egalite;
  }

  public boolean isAvantage() {
    return avantage;
  }

  public boolean isEgalite() {
    return egalite;
  }

  @Override
  public String toString() {
    return "{" +
        toStringScore() +
        toStringAvantage() +
        toStringEgalite() +
        '}';
  }

  private String toStringScore() {
    return score > 3 ? "" : " score = " + SCORE.get(score).getId();
  }

  private String toStringAvantage() {
    return score >= 3 && avantage ? " avantage " : "";
  }

  private String toStringEgalite() {
    return score >= 3 && egalite ? " egalite " : "";
  }
}
