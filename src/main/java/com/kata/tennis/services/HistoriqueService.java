package com.kata.tennis.services;

import com.kata.tennis.models.Historique;
import com.kata.tennis.models.Partie;

import java.util.List;

public interface HistoriqueService {
    List<Historique> creer(List<Historique> historiques, Partie partie);
}
