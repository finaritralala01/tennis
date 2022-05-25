package com.kata.tennis.utils;

import com.kata.tennis.enums.JoueurMarquePointEnum;
import com.kata.tennis.enums.PointJeuEnum;

import java.util.Map;

public class Constantes {
    public static final Integer SCORE_MAX_SET = 6;
    public static final Integer SCORE_DIFFRENCE = 2;
    public static final Map<Integer, PointJeuEnum> SCORE = Map.of(
        0, PointJeuEnum.ZERO,
        1, PointJeuEnum.UN,
        2, PointJeuEnum.DEUX,
        3, PointJeuEnum.TROIS,
        4, PointJeuEnum.QUATRE
    );
    public static final Map<Integer, JoueurMarquePointEnum> POINT_JEU = Map.of(
        1, JoueurMarquePointEnum.JOUEUR_A,
        2, JoueurMarquePointEnum.JOUEUR_B
    );
    private Constantes() {
    }
}
