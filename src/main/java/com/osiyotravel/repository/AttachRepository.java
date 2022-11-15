package com.osiyotravel.repository;

import com.osiyotravel.entity.AttachEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface AttachRepository extends JpaRepository<AttachEntity, String> {

    @Modifying
    @Query(" delete  from  AttachEntity  where id = ?1 ")
    void deleteCascade(String id);

    Page<AttachEntity> findAllByDeletedDateIsNull(Pageable pageable);
}