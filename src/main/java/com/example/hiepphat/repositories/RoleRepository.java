package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("RoleRepo")
public interface RoleRepository extends JpaRepository<Role,Long> {

}
