package com.example.festivalapi.controller;

import com.example.festivalapi.Exception.ResourceNotFoundException;
import com.example.festivalapi.dto.festival.FestivalListDto;
import com.example.festivalapi.dto.festival.FestivalListResDto;
import com.example.festivalapi.dto.festival.FestivalReqDto;
import com.example.festivalapi.service.FestivalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class FestivalController {
    private final FestivalService festivalService;

    @GetMapping("/list")
    public ResponseEntity<FestivalListResDto> getFestivals(@RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "15") int size,
                                                           @RequestParam(required = false) Long lastItemId) {
        Pageable pageable = createPageRequest(page, size);
        try{
            FestivalListResDto content = festivalService.getFestivals(pageable, lastItemId);
            return ResponseEntity.ok(content);
        }catch (ResourceNotFoundException e){
            throw e;
        }
    }

    private Pageable createPageRequest(int page, int size) {
        if (page < 1 || size < 1) {
            throw new IllegalArgumentException("page와 size는 1이상이어야 합니다.");
        }
        return PageRequest.of(page - 1, size);
    }

    @GetMapping( "/{contentId}")
    public ResponseEntity<FestivalListDto> getFestival(@PathVariable Long contentId){
        FestivalListDto festival = festivalService.getFestivalByContentId(contentId);
        return new ResponseEntity<>(festival, HttpStatus.OK);
    }


    @PostMapping()
//    축제를 등록
    public ResponseEntity<FestivalListDto> postFestivalList(@Valid @RequestBody FestivalReqDto reqDto) {
        FestivalListDto response = festivalService.createFestival(reqDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{contentId}")
//    축제 수정
    public FestivalListDto putFestivals(@RequestBody FestivalReqDto reqDto, @PathVariable Long contentId) {
        FestivalListDto response = festivalService.updateFestival(contentId,reqDto);
        return response;
    }

    @DeleteMapping("/{contentId}")
//    축제 삭제
    public FestivalListDto deleteFestivals(@PathVariable Long contentId) {
        return festivalService.delete(contentId);
    }
}
