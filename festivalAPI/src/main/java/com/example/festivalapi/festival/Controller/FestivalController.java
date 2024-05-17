package com.example.festivalapi.festival.Controller;

import com.example.festivalapi.festival.Dto.FestivalRequestDto;
import com.example.festivalapi.festival.Dto.FestivalResponseDto;
import com.example.festivalapi.festival.Entity.Festival;
import com.example.festivalapi.festival.Service.FestivalServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FestivalController {
    private final FestivalServcie festivalServcie;
//    15개씩 축제 가져오기
    @GetMapping("/festivals")
    public ResponseEntity<Page<Festival>> getFestivalsList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
            ){
        Page<Festival> festivalPage = festivalServcie.getFestivals(page, size);
        return new ResponseEntity<>(festivalPage, HttpStatus.OK);
    }
// 축제 저장
    @PostMapping("festival")
    public ResponseEntity<FestivalResponseDto> saveFestival(@RequestBody FestivalRequestDto requestDto){
        FestivalResponseDto response = festivalServcie.save(requestDto);
        return new ResponseEntity<>(response,null,HttpStatus.CREATED);
    }
//  - 축제를 수정
    @PutMapping("festival/{id}")
    public ResponseEntity<FestivalResponseDto> updateFestival(@RequestBody FestivalRequestDto requestDto, @PathVariable Long id){
        FestivalResponseDto response = festivalServcie.update(id,requestDto);
        return new ResponseEntity<>(response,null,HttpStatus.OK);
    }

    //  - 축제 삭제
    @DeleteMapping("festival/{id}")
    public ResponseEntity<FestivalResponseDto> deleteFestival(@PathVariable Long id){
        FestivalResponseDto response = festivalServcie.delete(id);
        return new ResponseEntity<>(response,null,HttpStatus.NO_CONTENT);
    }
    //  - 축제를 가져오는 API
    //  - 수정된 축제가 있는지 확인하는 API
}
