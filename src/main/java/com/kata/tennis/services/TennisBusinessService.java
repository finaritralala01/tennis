package com.kata.tennis.services;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.Historique;
import com.kata.tennis.models.Partie;

import java.util.List;

public interface TennisBusinessService {
    void process(Partie partie, JoueurMarquePointEnum joueurMarquePointEnum, List<Historique> historiques);
}
