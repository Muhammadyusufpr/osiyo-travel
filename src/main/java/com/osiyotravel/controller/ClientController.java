package com.osiyotravel.controller;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ClientFilterDTO;
import com.osiyotravel.dto.request.ClientRequestDTO;
import com.osiyotravel.dto.response.ClientResponseDTO;
import com.osiyotravel.service.ClientService;
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
@RequestMapping("/api/v1/client")
@Slf4j
@Api(tags = "Client Controller 👥")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


    @ApiOperation(value = "Create", notes = "Method used for create client from ROLE_MANAGER")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ClientRequestDTO dto) {
        log.info("Create client:{}", dto);
        return ResponseEntity.ok(clientService.create(dto));
    }

    @ApiOperation(value = "Create", notes = "Method used for get client by id from ROLE_MANAGER")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("Get Client!:{}", id);
        return ResponseEntity.ok(clientService.getById(id));
    }

    @ApiOperation(value = "Create", notes = "Method used for get client info from ROLE_MANAGER")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/getInfo/{id}")
    public ResponseEntity<?> getInfo(@PathVariable String id) {
        log.info("Get Client!:{}", id);
        return ResponseEntity.ok(clientService.getInfo(id));
    }


    @ApiOperation(value = "Create", notes = "Method used for update client from ROLE_MANAGER")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ClientRequestDTO dto) {
        log.info("Update client!:{}{}", id, dto);
        return ResponseEntity.ok(clientService.update(id, dto));
    }

    @ApiOperation(value = "Create", notes = "Method used for delete client from ROLE_MANAGER")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        log.info("Delete client!:{}", id);
        return ResponseEntity.ok(clientService.delete(id));
    }

    @ApiOperation(value = "Create", notes = "Method used for get all clients from ROLE_MANAGER")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ClientResponseDTO>>> getAll() {
        log.info("Get all clients");
        return ResponseEntity.ok(clientService.getAll());
    }

    @ApiOperation(value = "Get", notes = "Method used for filtring client")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ClientFilterDTO dto,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "15") int size) {
        log.info("Client filter:{}{}{}", dto, page, size);
        return ResponseEntity.ok(clientService.filter(dto, page, size));
    }
}
