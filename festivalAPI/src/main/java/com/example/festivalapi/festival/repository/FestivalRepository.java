package com.example.festivalapi.festival.repository;

import com.example.festivalapi.festival.Entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival,Long> {
}
