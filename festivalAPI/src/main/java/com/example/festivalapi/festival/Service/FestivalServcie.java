package com.example.festivalapi.festival.Service;

import com.example.festivalapi.festival.Dto.FestivalRequestDto;
import com.example.festivalapi.festival.Dto.FestivalResponseDto;
import com.example.festivalapi.festival.Entity.Festival;
import com.example.festivalapi.festival.exception.CustomFestivalException;
import com.example.festivalapi.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FestivalServcie {
    private final FestivalRepository festivalRepository;

//    저장
    public FestivalResponseDto save(FestivalRequestDto requestDto){
            // RequestDto -> Entity
            Festival festival = festivalReqToEntity(requestDto);

            // DB 저장
            Festival saveFestival = festivalRepository.save(festival);

            // Entity -> Dto
            FestivalResponseDto response = new FestivalResponseDto(saveFestival);

            return response;
    }
// 수정
    @Transactional
    public FestivalResponseDto update(Long id,FestivalRequestDto requestDto) {
        Festival selectedFestival = festivalRepository.findById(id).orElse(null);

        if(selectedFestival == null){
            throw new CustomFestivalException("존재 하지 않는 데이터입니다.");
        }

        selectedFestival = festivalReqToEntity(requestDto);

        FestivalResponseDto response = new FestivalResponseDto(selectedFestival);

        return response;
    }
// 삭제
    public FestivalResponseDto delete(Long id) {
        Festival selectedFestival = festivalRepository.findById(id).orElse(null);

        if(selectedFestival == null){
            throw new CustomFestivalException("존재 하지 않는 데이터입니다.");
        }
        festivalRepository.delete(selectedFestival);

        FestivalResponseDto response = new FestivalResponseDto(selectedFestival);

        return response;
    }
// 15개씩 조회
    public Page<Festival> getFestivals(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "event_start_date"));
        return festivalRepository.findAll(pageRequest);
    }

//    requestDto를 Entity로 바꾸기.
    private Festival festivalReqToEntity(FestivalRequestDto requestDto){
        String title = requestDto.getTitle();
        String thumbnail_image = requestDto.getThumbnail_image();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate event_start_date = LocalDate.parse(requestDto.getEvent_start_date(),formatter);
        LocalDate event_end_date = LocalDate.parse(requestDto.getEvent_end_date(),formatter);
        String organizer = requestDto.getOrganizer();
        LocalDate tourApi_modified_date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime admin_modified_date = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        // festival 생성
        Festival festival = new Festival().builder()
                .title(title)
                .thumbnail_image(thumbnail_image)
                .organizer(organizer)
                .event_start_date(event_start_date)
                .event_end_date(event_end_date)
                .tourApi_modified_date(tourApi_modified_date)
                .admin_modified_date(admin_modified_date)
                .build();

        return festival;
    }



}
