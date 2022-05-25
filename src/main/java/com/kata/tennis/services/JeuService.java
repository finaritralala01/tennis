package com.kata.tennis.services;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.*;

public interface JeuService {
    Jeu creer(int score, boolean avantage, boolean egalite);
    void incrementerScores(Jeu jeuA, Jeu jeuB, JoueurMarquePointEnum joueurMarquePointEnum);
    void setEgalites(Jeu jeuA, Jeu jeuB);
    void setAvantages(Jeu jeuA, Jeu jeuB);
    void terminer(Jeu jeuA, Jeu jeuB);
}
