package com.kata.tennis.services.impl;

import com.kata.tennis.models.Historique;
import com.kata.tennis.models.Partie;
import com.kata.tennis.services.HistoriqueService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("historiqueService")
public class HistoriqueServiceImpl implements HistoriqueService {

    @Override
    public List<Historique> creer(List<Historique> historiques, Partie partie) {
        if (Objects.isNull(partie)) {
           return historiques;
        }
        historiques.add(new Historique(partie));
        return historiques;
    }
}
