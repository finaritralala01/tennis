package com.kata.tennis.models;

public class Set {
  private int nombre;
  private int score;

  public int getNombre() {
    return nombre;
  }

  public void setNombre(int nombre) {
    this.nombre = nombre;
  }

  public Set(int nombre, int score) {
    this.nombre = nombre;
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "" + nombre;
  }
}
