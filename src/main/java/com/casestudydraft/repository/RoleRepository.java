package com.casestudydraft.repository;

import com.casestudydraft.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);

    //CONSTANTS are right here
     String createAdmin =  "insert into role(id, name) VALUES (1, 'admin')";
     String setAdmin = "insert into user_roles(users_id, roles_id) VALUES (1, 1)";
    @Modifying
    @Query(value =createAdmin, nativeQuery = true)
    @Transactional
    void createAdmin();

    @Modifying
    @Query(value = setAdmin, nativeQuery = true)
    @Transactional
    void setAdmin();
}
