package com.kata.tennis.services;

import com.kata.tennis.enums.JoueurMarquePointEnum;

import java.security.NoSuchAlgorithmException;

public interface PointService {
    JoueurMarquePointEnum random() throws NoSuchAlgorithmException;
}
