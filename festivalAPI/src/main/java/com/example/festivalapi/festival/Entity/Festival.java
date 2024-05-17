package com.example.festivalapi.festival.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name ="festival")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contentId;

    @Column(nullable = false)
    @NonNull
    private String title;
    @Column(nullable = false)
    @NonNull
    private LocalDate event_start_date;
    @Column(nullable = false)
    @NonNull
    private LocalDate event_end_date;
    private LocalDate tourApi_modified_date;
    private String thumbnail_image;
    private String organizer;
    private LocalDateTime admin_modified_date;

}
