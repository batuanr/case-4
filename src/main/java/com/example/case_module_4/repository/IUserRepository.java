package com.example.case_module_4.repository;


import com.example.case_module_4.model.Role;
import com.example.case_module_4.model.User;
;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Page<User> findAllByRoles(Pageable pageable,Role role);
}
