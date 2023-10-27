package com.ryconnect.sprinboot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ryconnect.sprinboot.model.UserT;

public interface UserRepository extends JpaRepository<UserT, Integer> {

  Optional<UserT> findById(Integer userId);

}
