package com.osiyotravel.repository;

import com.osiyotravel.entity.FilialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FilialRepository extends JpaRepository<FilialEntity, String> {

    Optional<FilialEntity> findByNameAndDeletedDateIsNull(String id);
    Optional<FilialEntity> findByIdAndVisibleTrue(String id);

    @Modifying
    @Transactional
    @Query("update FilialEntity set visible = ?2, deletedDate = ?3 where  id = ?1")
    void updateVisibleAndDeletedDate(String id, boolean b, LocalDateTime time);

    List<FilialEntity> findAllByOwnerIdAndVisibleTrue(String ownerId);

}
