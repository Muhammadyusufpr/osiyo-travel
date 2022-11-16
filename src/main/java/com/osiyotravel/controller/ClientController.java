package com.osiyotravel.controller;

import com.osiyotravel.service.ClientService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/client")
@Slf4j
@Api(tags = "Client Controller 👥")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


}
