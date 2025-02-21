package com.hm.picplz.domain.photographer.service;

import com.hm.picplz.domain.member.MemberRepository;
import com.hm.picplz.domain.member.domain.Member;
import com.hm.picplz.domain.photographer.domain.Photographer;
import com.hm.picplz.domain.photographer.dto.CreatePhotographerRequestDto;
import com.hm.picplz.domain.photographer.repository.PhotographerRepository;
import com.hm.picplz.global.common.entity.YesNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final MemberRepository memberRepository;
    private final PhotoMoodService photoMoodService;

    @Transactional
    public Long createPhotographer(Long memberId, CreatePhotographerRequestDto createPhotographerRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        Photographer photographer = Photographer.builder()
                .member(member)
                .area(createPhotographerRequestDto.getArea())
                .period(createPhotographerRequestDto.getPeriod())
                .active(YesNo.N)
                .instagram(createPhotographerRequestDto.getInstagram())
                .build();

        photographerRepository.save(photographer);

        photoMoodService.createPhotoMood(createPhotographerRequestDto.getPhotoMoods(), photographer);

        return photographer.getId();
    }
}
