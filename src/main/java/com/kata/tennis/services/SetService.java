package com.kata.tennis.services;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.Set;

public interface SetService {
    Set creer(int nombre, int score);
    void incrementerScores(Set setA, Set setB, JoueurMarquePointEnum joueurMarquePointEnum);
    void incrementerNombres(Set setA, Set setB, JoueurMarquePointEnum joueurMarquePointEnum);
    boolean isVictoire(int score1, int score2);
}
