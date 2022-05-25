package com.kata.tennis.models;


public class Historique {
    private Partie partie;
    public Historique(Partie partie) {
        this.partie = partie;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
