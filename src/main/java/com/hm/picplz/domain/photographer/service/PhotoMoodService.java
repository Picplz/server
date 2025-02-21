package com.hm.picplz.domain.photographer.service;

import com.hm.picplz.domain.photographer.domain.PhotoMood;
import com.hm.picplz.domain.photographer.domain.Photographer;
import com.hm.picplz.domain.photographer.repository.PhotoMoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoMoodService {

    private final PhotoMoodRepository photoMoodRepository;

    @Transactional
    public List<PhotoMood> createPhotoMood(List<String> photoMoodContents, Photographer photographer) {
        List<PhotoMood> photoMoods = photoMoodContents.stream()
                .map(photoMood ->
                        PhotoMood.builder()
                                .photographer(photographer)
                                .content(photoMood)
                                .build())
                .collect(Collectors.toList());

        photoMoodRepository.saveAll(photoMoods);

        return photoMoods;
    }
}
