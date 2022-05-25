package com.kata.tennis.models;

import com.kata.tennis.enums.StatutPartieEnum;

public class Partie {
    private Joueur joueurA;
    private  Joueur joueurB;
    private StatutPartieEnum statut;

    public Partie(Joueur joueurA, Joueur joueurB, StatutPartieEnum statut) {
        this.joueurA = joueurA;
        this.joueurB = joueurB;
        this.statut = statut;
    }

    public Joueur getJoueurA() {
        return joueurA;
    }

    public Joueur getJoueurB() {
        return joueurB;
    }

    public StatutPartieEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutPartieEnum statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Partie{" +
            "statut=" + statut +
            ", joueurA=" + joueurA +
            ", joueurB=" + joueurB +
            '}';
    }
}
