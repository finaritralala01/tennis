package com.kata.tennis.services.impl;

import com.kata.tennis.enums.StatutJoueurEnum;
import com.kata.tennis.models.Jeu;
import com.kata.tennis.models.Joueur;
import com.kata.tennis.models.Personne;
import com.kata.tennis.models.Set;
import com.kata.tennis.services.JoueurService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component("joueurService")
public class JoueurServiceImpl implements JoueurService {

  @Override
  public Optional<Joueur> creer(Personne personne, Set set, Jeu jeu) {
    if (Objects.isNull(personne) || Objects.isNull(set) || Objects.isNull(jeu)) {
      return Optional.empty();
    }
    return Optional.of(new Joueur(personne, set, jeu));
  }

  @Override
  public void setStatut(Joueur joueur, boolean isVictoire) {
    if (!Objects.isNull(joueur)) {
      joueur.setStatut(isVictoire ? StatutJoueurEnum.VICTOIRE : StatutJoueurEnum.DEFAITE);
    }
  }
}
