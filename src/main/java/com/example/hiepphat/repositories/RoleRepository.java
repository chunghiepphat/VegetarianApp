package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RoleRepo")
public interface RoleRepository extends JpaRepository<Role,Long> {

}
