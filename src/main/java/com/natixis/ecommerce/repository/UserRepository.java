package com.natixis.ecommerce.repository;

import com.natixis.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);


    boolean existsByEmail(String email);

}
