package com.kata.tennis.models;

import com.kata.tennis.enums.StatutJoueurEnum;

import java.util.Objects;

public class Joueur {
  private Personne personne;
  private Set set;
  private Jeu jeu;
  private StatutJoueurEnum statut;

  public Joueur(Personne personne, Set set, Jeu jeu) {
    this.personne = personne;
    this.set = set;
    this.jeu = jeu;
  }

  public Set getSet() {
    return set;
  }

  public Jeu getJeu() {
    return jeu;
  }

  public void setStatut(StatutJoueurEnum statut) {
    this.statut = statut;
  }

  @Override
  public String toString() {
    return "{" +
        "personne=" + personne +
        ", set=" + set +
        ", jeu=" + jeu +
        (Objects.isNull(statut) ? "" : (", statut=" + statut)) +
        '}';
  }
}
