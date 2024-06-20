package com.example.festivalapi.service;

import com.example.festivalapi.Exception.ResourceNotFoundException;
import com.example.festivalapi.dto.festival.FestivalListResDto;
import com.example.festivalapi.dto.festival.FestivalReqDto;
import com.example.festivalapi.dto.festival.FestivalListDto;
import com.example.festivalapi.entity.festival.FestivalDetail;
import com.example.festivalapi.entity.festival.FestivalIntro;
import com.example.festivalapi.entity.festival.FestivalList;
import com.example.festivalapi.repository.DetailRepository;
import com.example.festivalapi.repository.FestivalRepository;
import com.example.festivalapi.repository.IntroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;
    private final DetailRepository detailRepository;
    private final IntroRepository introRepository;

    public FestivalListDto createFestival(FestivalReqDto reqDto) {
        FestivalList savedFestivalList = festivalRepository.save(new FestivalList(reqDto));
        FestivalDetail festivalDetail = detailRepository.save(new FestivalDetail(reqDto));
        FestivalIntro festivalIntro = introRepository.save(new FestivalIntro(reqDto));
        FestivalListDto resDto = new FestivalListDto(savedFestivalList);

        return resDto;
    }

    public FestivalListDto getFestivalByContentId(Long contentId) {

        return festivalRepository.findByContentId(contentId).stream().map(FestivalListDto::new).findAny().orElseThrow();
    }

    public FestivalListResDto  getFestivals(Pageable pageable, Long lastItemId){
        Page<FestivalList> festivals;
        if (lastItemId != null) {
            festivalRepository.findById(lastItemId).orElseThrow(
                    ()-> new ResourceNotFoundException("Id에 해당하는 정보가 없습니다.")
            );
            festivals = festivalRepository.findByIdGreaterThanOrderByIdAsc(lastItemId, pageable);
        } else {
            festivals = festivalRepository.findAll(pageable);
        }
        Page<FestivalListDto> festivalListDtoPage = festivals.map(FestivalListDto::new);
        return new FestivalListResDto(festivalListDtoPage);
    }

    @Transactional
    public FestivalListDto updateFestival(Long contentId, FestivalReqDto reqDto) {
        FestivalList festivalList = findFestival(contentId);
        festivalList.update(reqDto);
        FestivalIntro festivalIntro = findFestivalIntro(contentId);
        festivalIntro.update(reqDto);
        FestivalDetail festivalDetail = findFestivalDetail(contentId);
        festivalDetail.update(reqDto);
//        festival.update(reqDto);

        return new FestivalListDto(festivalList);
    }

    public FestivalListDto delete(Long contentId) {
        FestivalList festivalList = findFestival(contentId);
        festivalRepository.delete(festivalList);
        return new FestivalListDto(festivalList);
    }

    private FestivalList findFestival(Long contentId){
        return festivalRepository.findByContentId(contentId).orElseThrow(()->
            new IllegalArgumentException("Festival not found")
        );
    }

    private FestivalIntro findFestivalIntro(Long contentId){
        return introRepository.findByContentId(contentId).orElseThrow(()->
                new IllegalArgumentException("Festival not found")
        );
    }
    private FestivalDetail findFestivalDetail(Long contentId){
        return detailRepository.findByContentId(contentId).orElseThrow(()->
                new IllegalArgumentException("Festival not found")
        );
    }

}
