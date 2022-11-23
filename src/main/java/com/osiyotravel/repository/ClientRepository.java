package com.osiyotravel.repository;

import com.osiyotravel.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByIdAndVisibleTrue(String id);

    @Modifying
    @Transactional
    @Query("update ClientEntity set visible = ?2, deletedDate = ?3 where  id = ?1")
    void updateVisibleAndDeletedDate(String id, boolean b, LocalDateTime time);

    List<ClientEntity> findAllByFilialIdAndVisibleTrue(String filialId);

    @Query("select  count(p.id)  from ClientEntity  as p where p.visible = true ")
    Optional<Integer> getCount();

    Page<ClientEntity> findAllByVisibleTrue(PageRequest pageable);
}
