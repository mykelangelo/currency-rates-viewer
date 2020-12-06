package com.papenko.currencyratesviewer.repository;

import com.papenko.currencyratesviewer.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByPrivileged(Boolean privileged);
}
