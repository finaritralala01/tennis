package com.kata.tennis.services;

import com.kata.tennis.models.Jeu;
import com.kata.tennis.models.Joueur;
import com.kata.tennis.models.Personne;
import com.kata.tennis.models.Set;

import java.util.Optional;

public interface JoueurService {
    Optional<Joueur> creer(Personne personne, Set set, Jeu jeu);
    void setStatut(Joueur joueur, boolean isVictoire);
}
