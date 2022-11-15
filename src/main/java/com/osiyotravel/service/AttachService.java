package com.osiyotravel.service;

import com.osiyotravel.dto.AttachDTO;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.response.AttachForResponseDTO;
import com.osiyotravel.entity.AttachEntity;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttachService {

    private final AttachRepository attachRepository;


    @Value("${attach.upload.folder}")
    private String attachFolder;

    @Value("${server.domain.name}")
    private String domainName;


    public ApiResponse<List<AttachDTO>> upload(Map<String, MultipartFile> file) {

        String pathFolder = getYmDString();

        File folder = new File(attachFolder + pathFolder);

        if (!folder.exists()) {
            boolean create = folder.mkdirs();
        }
        List<AttachDTO> list = new LinkedList<>();
        file.forEach((s, multipartFile) -> {
            String extension = getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            AttachEntity entity = saveAttach(pathFolder, extension, multipartFile);
            AttachDTO dto = toDTO(entity);

            try {
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(attachFolder + pathFolder + "/" + entity.getId() + "." + extension);
                Files.write(path, bytes);

                list.add(dto);

            } catch (IOException e) {
                log.warn("Upload Attach Exception = {}", e.getMessage());
            }
        });


        return new ApiResponse<>("Succes!", 200, false, list);
    }

    public ApiResponse<AttachDTO> upload(MultipartFile file) {

        String pathFolder = getYmDString();

        File folder = new File(attachFolder + pathFolder);

        if (!folder.exists()) {
            boolean create = folder.mkdirs();
        }

        String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

        AttachEntity entity = saveAttach(pathFolder, extension, file);
        AttachDTO dto = toDTO(entity);

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachFolder + pathFolder + "/" + entity.getId() + "." + extension);
            Files.write(path, bytes);
        } catch (IOException e) {
            log.warn("Upload Attach Exception = {}", e.getMessage());
            return new ApiResponse<>("", 400, true);
        }
        return new ApiResponse<>("Succes!", 200, false, dto);
    }

    public ApiResponse<ResponseEntity<Resource>> download(String key) { // images.png
        try {
            AttachEntity entity = get(key);

            String path = entity.getPath() + "/" + key + "." + entity.getExtension();

            Path file = Paths.get(attachFolder + path);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {

                return new ApiResponse<>("Success!", 400, false, ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + entity.getOrigenName() + "\"")
                        .body(resource));
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public byte[] open_general(String key) {
        byte[] data;

        try {
            AttachEntity entity = get(key);

            if (entity == null) {
                return new byte[0];
            }

            String path = entity.getPath() + "/" + key + "." + entity.getExtension();
            Path file = Paths.get(attachFolder + path);

            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            System.out.println("Error");
            return new byte[0];
        }
    }

    public String toOpenURL(String id) {
        return domainName + "api/v1/attach/open/" + id;
    }

    public AttachEntity saveAttach(String pathFolder, String extension, MultipartFile file) {
        AttachEntity entity = new AttachEntity();
        entity.setPath(pathFolder);
        entity.setOrigenName(file.getOriginalFilename());
        entity.setExtension(extension);
        entity.setSize(file.getSize());
        attachRepository.save(entity);
        return entity;
    }

    public AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("Attach not found!");
        });
    }


    public AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setOrigenName(entity.getOrigenName());
        dto.setPath(entity.getPath());
        dto.setUrl(domainName + "attach/download/" + entity.getId());
        return dto;
    }

    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day;
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public ApiResponse<?> delete(String fileName) {
        AttachEntity entity = get(fileName);

        if (entity == null) {
            return new ApiResponse<>(true);
        }

        File file = new File(attachFolder + entity.getPath() +
                "/" + entity.getId() + "." + entity.getExtension());

        if (file.delete()) {
            attachRepository.deleteCascade(fileName);
            return new ApiResponse<>("Success!", 200, false);
        } else {
            return new ApiResponse<>("Attach delete failed", 400, true);
        }
    }

    public ApiResponse<PageImpl<AttachDTO>> getPaginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<AttachEntity> pagination = attachRepository.findAllByDeletedDateIsNull(pageable);
        List<AttachDTO> dtoList = new ArrayList<>();
        pagination.stream().forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new ApiResponse<>("Success!", 200, false, new PageImpl<>(dtoList, pageable, pagination.getTotalElements()));
    }

    public AttachForResponseDTO getForResponse(String logoId) {
        AttachEntity entity = get(logoId);
        AttachForResponseDTO dto = new AttachForResponseDTO();
        dto.setId(entity.getId());
        dto.setUrl(toOpenURL(entity.getId()));
        return dto;
    }
}
