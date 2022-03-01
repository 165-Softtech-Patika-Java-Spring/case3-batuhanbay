package com.softtechbootcamp.case3.app.usr.dao;

import com.softtechbootcamp.case3.app.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrUserRepository extends JpaRepository<UsrUser, Long> {
    Optional<UsrUser> findUsrUserByUsername(String username);
    boolean existsAllByPhoneNumber(String phoneNumber);
    boolean existsAllByMail(String mail);
    boolean existsAllByUsername(String username);
    boolean existsByUsernameAndPhoneNumber(String username, String phoneNumber);
}
