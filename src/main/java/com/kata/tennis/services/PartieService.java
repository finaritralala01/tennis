package com.kata.tennis.services;

import com.kata.tennis.models.Partie;
import com.kata.tennis.models.Personne;

import java.util.Optional;

public interface PartieService {
    Optional<Partie> creer(Personne personneA, Personne personneB);
    void lancer(Partie partie);
    void terminer(Partie partie);
}
