package com.hm.picplz.domain.photographer.controller;

import com.hm.picplz.domain.photographer.dto.CreatePhotographerRequestDto;
import com.hm.picplz.domain.photographer.service.PhotographerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/photographers")
@Tag(name = "Photographer")
public class PhotographerController {

    private final PhotographerService photographerService;

    @Operation(summary = "회원가입 시 작가 생성 api")
    @PostMapping
    public ResponseEntity createMemberTest(@AuthenticationPrincipal Long memberId, @RequestBody CreatePhotographerRequestDto createPhotographerRequestDto) {
        return new ResponseEntity(photographerService.createPhotographer(memberId, createPhotographerRequestDto), HttpStatus.OK);
    }
}
