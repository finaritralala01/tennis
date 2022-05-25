package com.kata.tennis.services.impl;

import com.kata.tennis.enums.StatutPartieEnum;
import com.kata.tennis.models.Joueur;
import com.kata.tennis.models.Partie;
import com.kata.tennis.models.Personne;
import com.kata.tennis.services.JeuService;
import com.kata.tennis.services.JoueurService;
import com.kata.tennis.services.PartieService;
import com.kata.tennis.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component("partieService")
public class PartieServiceImpl implements PartieService {

  private final JoueurService joueurService;
  private final SetService setService;
  private final JeuService jeuService;

  public PartieServiceImpl(@Autowired @Qualifier("joueurService") JoueurService joueurService,
                           @Autowired @Qualifier("setService") SetService setService,
                           @Autowired @Qualifier("jeuService") JeuService jeuService) {
    this.joueurService = joueurService;
    this.setService = setService;
    this.jeuService = jeuService;
  }

  @Override
  public Optional<Partie> creer(Personne personneA, Personne personneB) {
    if (Objects.isNull(personneA) || Objects.isNull(personneB)) {
      return Optional.empty();
    }
    final Joueur joueurA = joueurService.creer(personneA, setService.creer(0, 0), jeuService.creer(0, false, false)).orElse(null);
    final Joueur joueurB = joueurService.creer(personneB, setService.creer(0, 0), jeuService.creer(0, false, false)).orElse(null);
    return Objects.isNull(joueurA) || Objects.isNull(joueurB) ? Optional.empty() : Optional.of(new Partie(joueurA, joueurB, StatutPartieEnum.ARRET));
  }

  @Override
  public void lancer(Partie partie) {
    if (!Objects.isNull(partie)) {
      partie.setStatut(StatutPartieEnum.EN_COURS);
    }
  }

  @Override
  public void terminer(Partie partie) {
    if (!Objects.isNull(partie)) {
      partie.setStatut(StatutPartieEnum.TERMINEE);
    }
  }
}
