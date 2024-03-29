package com.osiyotravel.repository;

import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);

    Optional<ProfileEntity> findByIdAndVisibleTrue(String id);


    @Modifying
    @Transactional
    @Query("update ProfileEntity set visible = ?2, deletedDate = ?3 where  id = ?1")
    void updateVisibleAndDeletedDate(String id, boolean b, LocalDateTime time);

//    @Query("select count(p.id) from ProfileEntity as p  where p.visible = true and p.deletedDate is null")
    List<ProfileEntity> findAllByVisibleTrue();

    List<ProfileEntity> findByFilialIdAndRoleAndVisibleTrue(String filialId, ProfileRole role);
    List<ProfileEntity> findByRoleAndVisibleTrue(ProfileRole role);


}
