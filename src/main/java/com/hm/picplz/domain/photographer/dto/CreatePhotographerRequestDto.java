package com.hm.picplz.domain.photographer.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CreatePhotographerRequestDto {

    private String area;

    private int period;

    private String instagram;

    private List<String> photoMoods;
}
