package com.hm.picplz.domain.customer.controller;

import com.hm.picplz.domain.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
@Tag(name = "Customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "회원가입 시 고객 생성 api")
    @PostMapping
    public ResponseEntity createCustomer(@AuthenticationPrincipal Long memberId) {
        return new ResponseEntity(customerService.createPhotographer(memberId), HttpStatus.OK);
    }
}
