package com.osiyotravel.controller;

import com.osiyotravel.service.FilialService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/filial")
@Slf4j
@Api(tags = "Filial Controller 👥")
@RequiredArgsConstructor
public class FilialController {
    private final FilialService filialService;




}
