package com.osiyotravel.repository;

import com.osiyotravel.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);

}
