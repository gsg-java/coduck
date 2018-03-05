package com.coduck.web.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by YG-MAC on 2018. 1. 21..
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGithubId(String id);
}
