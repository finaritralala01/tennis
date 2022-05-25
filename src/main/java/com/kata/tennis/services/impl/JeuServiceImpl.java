package com.kata.tennis.services.impl;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.Jeu;
import com.kata.tennis.services.JeuService;
import org.springframework.stereotype.Component;

import static com.kata.tennis.utils.Utils.scoreDifference;


@Component("jeuService")
public class JeuServiceImpl implements JeuService {

  @Override
  public Jeu creer(int score, boolean avantage, boolean egalite) {
    return new Jeu(score, avantage, egalite);
  }

  @Override
  public void incrementerScores(Jeu jeuA, Jeu jeuB, JoueurMarquePointEnum joueurMarquePointEnum) {
    if (JoueurMarquePointEnum.JOUEUR_A.equals(joueurMarquePointEnum)) {
      jeuA.setScore(jeuA.getScore() + 1);
    } else {
      jeuB.setScore(jeuB.getScore() + 1);
    }
  }

  @Override
  public void setEgalites(Jeu jeuA, Jeu jeuB) {
    final boolean isEgual = jeuA.getScore() == jeuB.getScore();
    jeuA.setEgalite(isEgual);
    jeuB.setEgalite(isEgual);
    if (isEgual) {
      jeuA.setAvantage(Boolean.FALSE);
      jeuB.setAvantage(Boolean.FALSE);
    }
  }

  @Override
  public void setAvantages(Jeu jeuA, Jeu jeuB) {
    if (jeuA.getScore() != jeuB.getScore()) {
      if (jeuA.getScore() > jeuB.getScore()) {
        setAvantage(jeuA, jeuB);
      } else {
        setAvantage(jeuB, jeuA);
      }
    }
  }

  @Override
  public void terminer(Jeu jeuA, Jeu jeuB) {
    if ((jeuA.getScore() >= 4 || jeuB.getScore() >= 4) && scoreDifference(jeuA.getScore(), jeuB.getScore())) {
      terminer(jeuA);
      terminer(jeuB);
    }
  }

  private void setAvantage(Jeu jeu1, Jeu jeu2) {
    jeu1.setAvantage(Boolean.TRUE);
    jeu1.setEgalite(Boolean.FALSE);
    jeu2.setAvantage(Boolean.FALSE);
    jeu2.setEgalite(Boolean.FALSE);
  }

  private void terminer(Jeu jeu) {
    jeu.setScore(0);
    jeu.setAvantage(Boolean.FALSE);
    jeu.setEgalite(Boolean.FALSE);
  }
}
