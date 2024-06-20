package com.example.festivalapi.repository;

import com.example.festivalapi.entity.festival.FestivalIntro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IntroRepository extends JpaRepository<FestivalIntro,Long> {
    Optional<FestivalIntro> findByContentId(Long contentId);
}
