package com.kata.tennis.services.impl;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.*;
import com.kata.tennis.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TennisBusinessServiceImpl implements TennisBusinessService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TennisBusinessServiceImpl.class);

  private final PartieService partieService;
  private final SetService setService;
  private final JeuService jeuService;
  private final JoueurService joueurService;
  private final HistoriqueService historiqueService;

  public TennisBusinessServiceImpl(@Autowired @Qualifier("partieService") PartieService partieService,
                                   @Autowired @Qualifier("setService") SetService setService,
                                   @Autowired @Qualifier("jeuService") JeuService jeuService,
                                   @Autowired @Qualifier("joueurService") JoueurService joueurService,
                                   @Autowired @Qualifier("historiqueService") HistoriqueService historiqueService) {
    this.partieService = partieService;
    this.setService = setService;
    this.jeuService = jeuService;
    this.joueurService = joueurService;
    this.historiqueService = historiqueService;
  }

  @Override
  public void process(Partie partie, JoueurMarquePointEnum joueurMarquePointEnum, List<Historique> historiques) {
    final Joueur joueurA = partie.getJoueurA();
    final Joueur joueurB = partie.getJoueurB();
    modifierJeu(joueurMarquePointEnum, joueurA, joueurB);
    modifierSet(joueurMarquePointEnum, joueurA, joueurB);
    modifierPartie(partie);
    modifierHistoriques(historiques, partie);
    LOGGER.info("{}", partie);
  }

  private void modifierPartie(Partie partie) {
    final Joueur joueurA = partie.getJoueurA();
    final Joueur joueurB = partie.getJoueurB();
    if (joueurA.getSet().getNombre() == 2 || joueurB.getSet().getNombre() == 2) {
      joueurService.setStatut(joueurA, setService.isVictoire(joueurA.getSet().getScore(), joueurB.getSet().getScore()));
      joueurService.setStatut(joueurB, setService.isVictoire(joueurB.getSet().getScore(), joueurA.getSet().getScore()));
      partieService.terminer(partie);
    }
  }

  private void modifierSet(JoueurMarquePointEnum joueurMarquePointEnum, Joueur joueurA, Joueur joueurB) {
    if (joueurA.getJeu().getScore() == 0 && joueurB.getJeu().getScore() == 0) {
      setService.incrementerScores(joueurA.getSet(), joueurB.getSet(), joueurMarquePointEnum);
      setService.incrementerNombres(joueurA.getSet(), joueurB.getSet(), joueurMarquePointEnum);
      LOGGER.info("-");
    }
  }

  private void modifierJeu(JoueurMarquePointEnum joueurMarquePointEnum, Joueur joueurA, Joueur joueurB) {
    jeuService.incrementerScores(joueurA.getJeu(), joueurB.getJeu(), joueurMarquePointEnum);
    jeuService.setEgalites(joueurA.getJeu(), joueurB.getJeu());
    jeuService.setAvantages(joueurA.getJeu(), joueurB.getJeu());
    jeuService.terminer(joueurA.getJeu(), joueurB.getJeu());
  }

  private void modifierHistoriques(List<Historique> historiques, Partie partie) {
    historiqueService.creer(historiques, partie);
  }
}
