package com.osiyotravel.controller;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.FilialRequestDTO;
import com.osiyotravel.dto.response.FilialResponseDTO;
import com.osiyotravel.service.FilialService;
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
@RequestMapping("/api/v1/filial")
@Slf4j
@Api(tags = "Filial Controller 👥")
@RequiredArgsConstructor
public class FilialController {
    private final FilialService filialService;


    @ApiOperation(value = "Create", notes = "Method used for create filial only can ROLE_SUPER_ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody FilialRequestDTO dto) {
        log.info("Create filial:{}", dto);
        return ResponseEntity.ok(filialService.create(dto));
    }


    @ApiOperation(value = "Get", notes = "Method used for get filial by id only can ROLE_SUPER_ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("Get filial by id:{}", id);
        return ResponseEntity.ok(filialService.getById(id));
    }

    @ApiOperation(value = "Update", notes = "Method used for update filial only can ROLE_SUPER_ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody FilialRequestDTO dto) {
        log.info("Update filial:{}{}", id, dto);
        return ResponseEntity.ok(filialService.update(id, dto));
    }

    @ApiOperation(value = "Delete", notes = "Method used for delete filial by id only can ROLE_SUPER_ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        log.info("Delete filial:{}", id);
        return ResponseEntity.ok(filialService.delete(id));
    }

    @ApiOperation(value = "GetAll", notes = "Method used for get all filials only can ROLE_SUPER_ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<FilialResponseDTO>>> getAll() {
        log.info("Get all filial:");
        return ResponseEntity.ok(filialService.getAll());
    }


}
