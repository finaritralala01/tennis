package com.kata.tennis.services.impl;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.services.PointService;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import static com.kata.tennis.utils.Constantes.POINT_JEU;

@Component("pointService")
public class PointServiceImpl implements PointService {

  @Override
  public JoueurMarquePointEnum random() throws NoSuchAlgorithmException {
    final Random randint;
    randint = SecureRandom.getInstanceStrong();
    final int a = randint.nextInt(2) + 1;
    return POINT_JEU.get(a);
  }
}
