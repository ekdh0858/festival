package com.example.festivalapi.repository;

import com.example.festivalapi.entity.festival.FestivalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailRepository extends JpaRepository<FestivalDetail,Long> {
    Optional<FestivalDetail> findByContentId(Long contentId);
}
