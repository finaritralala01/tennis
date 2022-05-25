package com.kata.tennis.services.impl;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.models.Set;
import com.kata.tennis.services.SetService;
import org.springframework.stereotype.Component;

import static com.kata.tennis.utils.Utils.scoreDifference;
import static com.kata.tennis.utils.Utils.scoreSuperieurouEgualMax;

@Component("setService")
public class SetServiceImpl implements SetService {
  @Override
  public Set creer(int nombre, int score) {
    return new Set(nombre, score);
  }

  @Override
  public void incrementerScores(Set setA, Set setB, JoueurMarquePointEnum joueurMarquePointEnum) {
    if (JoueurMarquePointEnum.JOUEUR_A.equals(joueurMarquePointEnum)) {
      setA.setScore(setA.getScore() + 1);
    } else {
      setB.setScore(setB.getScore() + 1);
    }
  }

  @Override
  public void incrementerNombres(Set setA, Set setB, JoueurMarquePointEnum joueurMarquePointEnum) {
    if (scoreSuperieurouEgualMax(setA.getScore(), setB.getScore()) &&
        scoreDifference(setA.getScore(), setB.getScore())) {
      if (JoueurMarquePointEnum.JOUEUR_A.equals(joueurMarquePointEnum)) {
        setA.setNombre(setA.getNombre() + 1);
      } else {
        setB.setNombre(setB.getNombre() + 1);
      }
    }
  }

  @Override
  public boolean isVictoire(int score1, int score2) {
    return score1 > score2;
  }
}
