package com.example.festivalapi.repository;

import com.example.festivalapi.entity.festival.FestivalList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivalRepository extends JpaRepository<FestivalList, Long> {
    Page<FestivalList> findByIdGreaterThanOrderByIdAsc(Long lastItemId, Pageable pageable);

    Optional<FestivalList> findByContentId(Long contentId);
}
