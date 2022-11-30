package com.osiyotravel.controller;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.TicketRequestDTO;
import com.osiyotravel.dto.response.TicketResponseDTO;
import com.osiyotravel.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ticket")
@Slf4j
@Api(tags = "Ticket Controller 🎫")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;


    @ApiOperation(value = "Get", notes = "Method used for get ticket by id")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("Get ticket by id:{}", id);
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @ApiOperation(value = "Get", notes = "Method used for get ticket by id")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getByClientId(@PathVariable String id) {
        log.info("Get ticket by id:{}", id);
        return ResponseEntity.ok(ticketService.getByClientId(id));
    }

    @ApiOperation(value = "GetAll", notes = "Method used for get all tickets")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<TicketResponseDTO>>> getAll() {
        log.info("Get all tickets");
        return ResponseEntity.ok(ticketService.getAll());
    }

}
