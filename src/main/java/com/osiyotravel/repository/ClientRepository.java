package com.osiyotravel.repository;

import com.osiyotravel.entity.ClientEntity;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.ProfileRole;
import com.osiyotravel.mapper.GetClientFullDTO;
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

    List<ClientEntity> findAllByVisibleTrue();

    List<ClientEntity> findAllByFilialIdAndVisibleTrue(String filialId);

    @Query("select  count(p.id)  from ClientEntity  as p where p.visible = true ")
    Optional<Integer> getCount();

    @Query("select client.id as id, client.name as name, client.surname as surname," +
            "       client.phone as phone, client.price as price, client.gender as gender," +
            "       client.attachId as photoId, f.name as filialName" +
            " from ClientEntity as client" +
            " inner join FilialEntity as f on f.id = client.filialId " +
            " where client.id =?1  and client.visible = true ")
    Optional<GetClientFullDTO> getFullClient(String clientId);
}
