package com.kata.tennis;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.Historique;
import com.kata.tennis.models.Partie;
import com.kata.tennis.models.Personne;
import com.kata.tennis.services.PartieService;
import com.kata.tennis.services.TennisBusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TennisApplicationTests {

  @Autowired
  private TennisBusinessService tennisBusinessService;
  @Autowired
  private PartieService partieService;

  @Test
  void unPointMarqueParLeJoueurA() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    final JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    partieService.lancer(partie);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    assert partie != null;
    assertEquals(1, partie.getJoueurA().getJeu().getScore());
  }

  @Test
  void unJeuGagneParLeJoueurA() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    partieService.lancer(partie);
    JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    assert partie != null;
    assertEquals(1, partie.getJoueurA().getSet().getScore());
  }

  @Test
  void unePartieGagneeParLeJoueurB() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    partieService.lancer(partie);
    JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    for (int i = 0; i < 6; i++) {
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    }
    assert partie != null;
    assertEquals(2, partie.getJoueurB().getSet().getNombre());
  }

  @Test
  void unJeuEgalite() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    partieService.lancer(partie);
    JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    assert partie != null;
    assertTrue(partie.getJoueurA().getJeu().isEgalite());
  }

  @Test
  void unJeuAvantageJoueurA() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    partieService.lancer(partie);
    JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    assert partie != null;
    assertTrue(partie.getJoueurA().getJeu().isAvantage());
  }

  @Test
  void unAvantageAEgaliteAvantageBJeuGagneParLeJoueurB() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    partieService.lancer(partie);
    JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    assert partie != null;
    assertEquals(1, partie.getJoueurB().getSet().getScore());
  }

  @Test
  void uneTiebreak() {
    final Partie partie = partieService.creer(new Personne("CHARLES"), new Personne("DENIS")).orElse(null);
    List<Historique> historiques = new ArrayList<>();
    partieService.lancer(partie);
    JoueurMarquePointEnum joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    for (int i = 0; i <= 4; i++) {
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    }
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    for (int i = 0; i <= 4; i++) {
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
      tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    }
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_A;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    joueurMarquePointEnum = JoueurMarquePointEnum.JOUEUR_B;
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    tennisBusinessService.process(partie, joueurMarquePointEnum, historiques);
    assert partie != null;
    assertEquals(7, partie.getJoueurB().getSet().getScore());
  }
}
