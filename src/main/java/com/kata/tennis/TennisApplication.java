package com.kata.tennis;

import com.kata.tennis.enums.StatutPartieEnum;
import com.kata.tennis.models.Historique;
import com.kata.tennis.models.Partie;
import com.kata.tennis.models.Personne;
import com.kata.tennis.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TennisApplication {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(TennisApplication.class, args);
    Run service = applicationContext.getBean(Run.class);
    service.run();
  }

}

@Service
class Run {

  private static final Logger LOGGER = LoggerFactory.getLogger(Run.class);

  private final PartieService partieService;
  private final PointService pointService;
  private final TennisBusinessService tennisBusinessService;

  public Run(@Autowired @Qualifier("partieService") PartieService partieService,
             @Autowired @Qualifier("pointService") PointService pointService,
             @Autowired TennisBusinessService tennisBusinessService) {
    this.partieService = partieService;
    this.pointService = pointService;
    this.tennisBusinessService = tennisBusinessService;
  }

  public void run() {
    try {
      final Optional<Partie> partieOptional = partieService.creer(new Personne("RASOLO"), new Personne("DENIS"));
      final List<Historique> historiques = new ArrayList<>();
      if (partieOptional.isPresent()) {
        final Partie partie = partieOptional.get();
        partieService.lancer(partie);
        while (StatutPartieEnum.EN_COURS.equals(partie.getStatut())) {
          tennisBusinessService.process(partieOptional.get(), pointService.random(), historiques);
        }
      }
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("{}", e.getMessage(), e);
    }
  }

}
