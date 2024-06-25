package com.example.festivalapi.repository;

import com.example.festivalapi.entity.festival.FestivalList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface FestivalRepository extends JpaRepository<FestivalList, Long>, JpaSpecificationExecutor<FestivalList> {
    Page<FestivalList> findByIdGreaterThanOrderByIdAsc(Long lastItemId, Pageable pageable);

    Optional<FestivalList> findByContentId(Long contentId);

    @Query("SELECT f FROM FestivalList f ORDER BY " +
            "CASE WHEN f.startDate <= :currentDate AND f.endDate >= :currentDate THEN 0 " +
            "WHEN f.startDate > :currentDate THEN 1 " +
            "ELSE 2 END, " +
            "f.startDate ASC")
    Page<FestivalList> findAllOrderByPriority(@Param("currentDate") LocalDate currentDate, Pageable pageable);
}
